package fun.coconut.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class CoconutFunctionNode extends CoconutExpressionNode {

	@Child private CoconutExpressionNode functionBlock;
	private String name;

	public CoconutFunctionNode(String name, CoconutExpressionNode functionBlock) {
		this.functionBlock = functionBlock;
		this.name = name;
	}

	@Override
	public Object executeGeneric(VirtualFrame frame) {
		System.out.println("Executing function '" + name + "'");
		return functionBlock.executeVoid(frame);
	}
}
