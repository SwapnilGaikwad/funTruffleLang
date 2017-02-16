package fun.coconut;

import java.util.ArrayList;
import java.util.List;

import com.oracle.truffle.api.frame.FrameDescriptor;

import fun.coconut.nodes.CoconutDummyExpressionNode;
import fun.coconut.nodes.CoconutExpressionNode;
import fun.coconut.nodes.CoconutRootNode;
import fun.coconut.nodes.CoconutUnimplementedOperationNode;

public class CoconutASTGenerator {

	FrameDescriptor frameDescriptor;
	List<CoconutExpressionNode> instructionList;

	public CoconutASTGenerator(){
		frameDescriptor = new FrameDescriptor();
		instructionList = new ArrayList<>();
	}

	public void createBinaryInstruction(String operator, int lhs, int rhs){
		System.out.println("Create instruction: " + lhs + " '" + operator + "' " + rhs);
		CoconutExpressionNode result = null;
		switch (operator) {
		case "+" :
			result = new CoconutUnimplementedOperationNode();
			break;
		case "-" :
			result = new CoconutUnimplementedOperationNode();
			break;
		case "/" :
			result = new CoconutUnimplementedOperationNode();
			break;
		case "*" :
			result = new CoconutUnimplementedOperationNode();
			break;
		case "%" :
			result = new CoconutUnimplementedOperationNode();
			break;
		default :
			result = new CoconutUnimplementedOperationNode();
			break;
		}
		instructionList.add(result);
	}

	public CoconutRootNode generateAST(){
		//TODO: Create block of instructions and pass to root node to execute
		CoconutExpressionNode node = new CoconutDummyExpressionNode();
		if( instructionList.size() > 0){
			//Temporary node for testing
			node = instructionList.get(0);
		}
		CoconutRootNode rootNode = new CoconutRootNode(Coconut.class, null, frameDescriptor, node);
		return rootNode;
	}
}
