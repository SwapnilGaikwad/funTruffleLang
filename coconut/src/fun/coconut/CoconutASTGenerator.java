package fun.coconut;

import java.util.ArrayList;
import java.util.List;

import com.oracle.truffle.api.frame.FrameDescriptor;

import fun.coconut.nodes.CoconutBlockNode;
import fun.coconut.nodes.CoconutExpressionNode;
import fun.coconut.nodes.CoconutRootNode;
import fun.coconut.nodes.CoconutUnimplementedOperationNode;
import fun.coconut.nodes.arithmetic.CoconutAddNodeGen;
import fun.coconut.nodes.arithmetic.CoconutDivNodeGen;
import fun.coconut.nodes.arithmetic.CoconutLShiftNodeGen;
import fun.coconut.nodes.arithmetic.CoconutModNodeGen;
import fun.coconut.nodes.arithmetic.CoconutMulNodeGen;
import fun.coconut.nodes.arithmetic.CoconutRShiftNodeGen;
import fun.coconut.nodes.arithmetic.CoconutSubNodeGen;
import fun.coconut.nodes.literals.CoconutFloat32NodeGen;
import fun.coconut.nodes.literals.CoconutInt32NodeGen;

public class CoconutASTGenerator {

	FrameDescriptor frameDescriptor;
	List<CoconutExpressionNode> instructionList;

	public CoconutASTGenerator(FrameDescriptor frameDescriptor){
		frameDescriptor = this.frameDescriptor;
		instructionList = new ArrayList<>();
	}

	public void createBinaryInstruction(String operator, CoconutExpressionNode lhsNode, CoconutExpressionNode rhsNode){
		CoconutExpressionNode result = null;
		switch (operator) {
		case "+" :
			result = CoconutAddNodeGen.create(lhsNode, rhsNode);
			break;
		case "-" :
			result = CoconutSubNodeGen.create(lhsNode, rhsNode);
			break;
		case "/" :
			result = CoconutDivNodeGen.create(lhsNode, rhsNode);
			break;
		case "*" :
			result = CoconutMulNodeGen.create(lhsNode, rhsNode);
			break;
		case "%" :
			result = CoconutModNodeGen.create(lhsNode, rhsNode);
			break;
		case "<<":
			result = CoconutLShiftNodeGen.create(lhsNode, rhsNode);
			break;
		case ">>":
			result = CoconutRShiftNodeGen.create(lhsNode, rhsNode);
			break;
		default :
			result = new CoconutUnimplementedOperationNode();
			break;
		}
		instructionList.add(result);
	}

	public CoconutRootNode generateAST(){
		CoconutExpressionNode node = new CoconutBlockNode(instructionList);
		CoconutRootNode rootNode = new CoconutRootNode(Coconut.class, null, frameDescriptor, node);
		return rootNode;
	}

	public CoconutExpressionNode createNode(float value) {
		return CoconutFloat32NodeGen.create(value);
	}

	public CoconutExpressionNode createNode(int value) {
		return CoconutInt32NodeGen.create(value);
	}
}
