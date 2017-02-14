package fun.coconut;

import java.util.List;

import com.oracle.truffle.api.frame.FrameDescriptor;

import fun.coconut.nodes.CoconutDummyExpressionNode;
import fun.coconut.nodes.CoconutExpressionNode;
import fun.coconut.nodes.CoconutRootNode;

public class CoconutASTGenerator {

	FrameDescriptor frameDescriptor;
	List<CoconutExpressionNode> instructionList;

	public CoconutASTGenerator(){
		frameDescriptor = new FrameDescriptor();
	}

	public void createBinaryInstruction(String operator, int lhs, int rhs){
		System.out.println("Create instruction: " + lhs + " '" + operator + "' " + rhs);
	}

	public CoconutRootNode generateAST(){
		CoconutRootNode rootNode = new CoconutRootNode(Coconut.class, null, frameDescriptor, new CoconutDummyExpressionNode());
		return rootNode;
	}
}
