package fun.coconut.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class CoconutDummyExpressionNode extends CoconutExpressionNode {


	@Override
	public Object executeGeneric(VirtualFrame frame) {
		System.out.println("Executing Dummy expression node");
		return null;
	}
}
