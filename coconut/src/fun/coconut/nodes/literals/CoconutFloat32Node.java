package fun.coconut.nodes.literals;

import com.oracle.truffle.api.dsl.Specialization;

import fun.coconut.nodes.CoconutExpressionNode;

public abstract class CoconutFloat32Node extends CoconutExpressionNode{

	private float value;

	public CoconutFloat32Node(float value){
		this.value = value;
	}

	@Specialization
	public float execute(){
		return value;
	}
}
