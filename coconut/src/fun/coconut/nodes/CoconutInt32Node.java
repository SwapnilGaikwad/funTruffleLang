package fun.coconut.nodes;

import com.oracle.truffle.api.dsl.Specialization;

public abstract class CoconutInt32Node extends CoconutExpressionNode{

	private int value;

	public CoconutInt32Node(int value){
		this.value = value;
	}

	@Specialization
	public int execute(){
		return value;
	}
}
