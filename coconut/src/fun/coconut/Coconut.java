package fun.coconut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.source.MissingMIMETypeException;
import com.oracle.truffle.api.source.MissingNameException;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.source.Source.Builder;
import com.oracle.truffle.api.vm.PolyglotEngine;

@TruffleLanguage.Registration(name = "Coconut", version = "0.1", mimeType = "application/coconut")
public class Coconut extends TruffleLanguage<Coconut>{

	@Override
	protected Coconut createContext(Env env) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object findExportedSymbol(Coconut context, String globalName,
			boolean onlyExplicit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getLanguageGlobal(Coconut context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isObjectOfLanguage(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Object evalInContext(Source source, Node node,
			MaterializedFrame mFrame) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CallTarget parse(Source code, Node context,
			String... argumentNames) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		PolyglotEngine pe = PolyglotEngine.newBuilder().build();
		assert pe.getLanguages().containsKey("application/coconut");
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
			source = Source.newBuilder(isr).name("coconutBuilder").mimeType("application/coconut").build();
		} catch (IOException | RuntimeException e) {
			System.err.println("Error while converting to source object");
			System.err.println(e);
			return;
		}
		pe.eval(source);
	}
}
