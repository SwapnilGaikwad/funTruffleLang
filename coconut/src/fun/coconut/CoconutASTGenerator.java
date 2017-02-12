package fun.coconut;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.nodes.RootNode;

import fun.coconut.nodes.CoconutDummyExpressionNode;
import fun.coconut.nodes.CoconutRootNode;

public class CoconutASTGenerator {

	FrameDescriptor frameDescriptor;

	public CoconutASTGenerator(){
		frameDescriptor = new FrameDescriptor();
	}

	public RootNode generateAST(){
		RootNode rootNode = new CoconutRootNode(Coconut.class, null, frameDescriptor, new CoconutDummyExpressionNode());
		return rootNode;
	}
}
