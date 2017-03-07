package fun.coconut.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;

public class CoconutUnimplementedOperationNode extends CoconutExpressionNode {

	private String message;
	
	public CoconutUnimplementedOperationNode(){
		message = "Operation is not supported!";
	}

	public CoconutUnimplementedOperationNode(String message){
		this.message = message;
	}

	@Override
	public Object executeGeneric(VirtualFrame frame) {
		return executeUnimplemented(frame, message);
	}
}
