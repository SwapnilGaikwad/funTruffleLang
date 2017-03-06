package fun.coconut;

import java.util.ArrayList;
import java.util.List;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;

import fun.coconut.nodes.CoconutBlockNode;
import fun.coconut.nodes.CoconutExpressionNode;
import fun.coconut.nodes.CoconutFunctionNode;
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
	List<CoconutExpressionNode> functions;

	public CoconutASTGenerator(FrameDescriptor frameDescriptor){
		frameDescriptor = this.frameDescriptor;
		instructionList = new ArrayList<>();
		functions = new ArrayList<CoconutExpressionNode>();
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

	public void createFunctionNode(String functionName){
		CoconutExpressionNode node = new CoconutBlockNode(instructionList);
		CoconutFunctionNode functionNode = new CoconutFunctionNode(functionName, node);
		functions.add(functionNode);
	}

	public CallTarget generateAST(){
		CoconutExpressionNode node = functions.get(0);
		CoconutRootNode rootNode = new CoconutRootNode(Coconut.class, null, frameDescriptor, node);
		return Truffle.getRuntime().createCallTarget(rootNode);
	}

	public CoconutExpressionNode createNode(float value) {
		return CoconutFloat32NodeGen.create(value);
	}

	public CoconutExpressionNode createNode(int value) {
		return CoconutInt32NodeGen.create(value);
	}
}
