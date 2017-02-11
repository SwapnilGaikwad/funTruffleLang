package fun.coconut;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

public class CoconutASTGenerator {

	public RootNode generateAST(){
		RootNode rootNode = new RootNode(Coconut.class, null, null) {

			@Override
			public Object execute(VirtualFrame frame) {
				System.out.println("In execute method of RootNode in CoconutASTGenerator class...");
				return null;
			}
		};
		return rootNode;
	}
}
