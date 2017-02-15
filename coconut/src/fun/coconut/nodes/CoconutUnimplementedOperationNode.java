package fun.coconut.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class CoconutUnimplementedOperationNode extends CoconutExpressionNode {

	@Override
	public Object executeGeneric(VirtualFrame frame) {
		return executeUnimplemented(frame);
	}
}
