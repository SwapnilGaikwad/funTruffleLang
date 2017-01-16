package fun.coconut.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public abstract class CoconutExpressionNode extends Node {

	public abstract Object executeGeneric(VirtualFrame frame);

	public Object executeVoid(VirtualFrame frame) {
		System.out.println("In expression node");
		return executeGeneric(frame);
	}
}
