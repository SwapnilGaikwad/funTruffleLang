package fun.coconut;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.source.Source;

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

	public static void main(String[] args) {
		System.out.println("Hello from coconut!");
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
}
