package fun.coconut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.vm.PolyglotEngine;

import fun.coconut.parser.Parser;
import fun.coconut.parser.Scanner;
import fun.coconut.runtime.CoconutContext;

@TruffleLanguage.Registration(name = "coconut", version = "0.1", mimeType = Coconut.MIME_TYPE)
public class Coconut extends TruffleLanguage<CoconutContext>{

	public static final String MIME_TYPE = "application/x-coconut";

	public static final Coconut INSTANCE = new Coconut();

	private Coconut() {
		//No instance apart from singleton INSTANCE
	}

	@Override
	protected CoconutContext createContext(Env env) {
		System.out.println("Create context...");
		return null;
	}

	@Override
	protected Object findExportedSymbol(CoconutContext context,
			String globalName, boolean onlyExplicit) {
		System.out.println("Find exported symbols");
		return null;
	}

	@Override
	protected Object getLanguageGlobal(CoconutContext context) {
		System.out.println("Get language global");
		return null;
	}

	@Override
	protected boolean isObjectOfLanguage(Object object) {
		System.out.println("In isObjectOfLanguage");
		return false;
	}

	@Override
	protected Object evalInContext(Source source, Node node,
			MaterializedFrame mFrame) throws Exception {
		System.out.println("In evaluInContext");
		return null;
	}

	@Override
	protected CallTarget parse(Source code, Node context,
			String... argumentNames) throws Exception {
		System.out.println("In parse method...");
		Scanner scanner = new Scanner("/home/sgaikwad/sandbox/compiler/funTruffleLang/coconut/input.cnut");
		Parser parser = new Parser(scanner);
		parser.Parse();
		
		RootNode rootNode = new RootNode(Coconut.class, null, null) {
			
			@Override
			public Object execute(VirtualFrame frame) {
				System.out.println("In execute method of RootNode in Coconut class...");
				return null;
			}
		};
		return Truffle.getRuntime().createCallTarget(rootNode);
	}

	public static void main(String[] args) {
		PolyglotEngine engine = PolyglotEngine.newBuilder().setIn(System.in).setOut(System.out).build();
		System.out.println("Size: " + engine.getLanguages().size());
		assert engine.getLanguages().containsKey(Coconut.MIME_TYPE);

		System.out.println("Hello from coconut!");
		if(args.length < 1){
			System.err.println("No input file is provied!");
			return;
		}

		String inputFileName = args[0];
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new FileInputStream(inputFileName));
		} catch (FileNotFoundException e) {
			System.err.println("Error while reading an input file - " + inputFileName);
			System.err.println(e);
			return;
		}

		Source source = null;
		try {
			source = Source.newBuilder(isr).name("coconutBuilder").mimeType(Coconut.MIME_TYPE).build();
		} catch (IOException | RuntimeException e) {
			System.err.println("Error while converting to source object");
			System.err.println(e);
			return;
		}

		engine.eval(source);
	}
}