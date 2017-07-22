package fun.coconut.nodes;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

import fun.coconut.Coconut;

public class CoconutRootNode extends RootNode {

	@Child CoconutExpressionNode expressionNode;

	public CoconutRootNode(Coconut language, 
			FrameDescriptor frameDescriptor, CoconutExpressionNode expressionNode) {
		super(language, frameDescriptor);
		this.expressionNode = expressionNode;
	}

	@Override
	public Object execute(VirtualFrame frame) {
		System.out.println("In execute method of CoconutRootNode...");
		return expressionNode.executeGeneric(frame);
	}

}
