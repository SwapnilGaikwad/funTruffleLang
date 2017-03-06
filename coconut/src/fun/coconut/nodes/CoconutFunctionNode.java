package fun.coconut.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class CoconutFunctionNode extends CoconutExpressionNode {

	private CoconutExpressionNode functionBlock;
	private String name;

	public void coconutfunctionnode(String name, CoconutBlockNode functionBlock) {
		this.functionBlock = functionBlock;
		this.name = name;
	}

	@Override
	public Object executeVoid(VirtualFrame frame){
		System.out.println("Executing function '" + name + "'");
		return functionBlock.executeVoid(frame);
	}
}
