package fun.coconut.nodes.arithmetic;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;

import fun.coconut.nodes.CoconutExpressionNode;

@NodeChildren({@NodeChild("left"), @NodeChild("right")})
public abstract class CoconutMulNode extends CoconutExpressionNode {

	@Specialization
	public int mulInt(int left, int right){
		System.out.println("Multiplying " + left + " * " + right);
		return left * right;
	}

	@Specialization
	public float mulInt(float left, int right){
		System.out.println("Multiplying " + left + " * " + right);
		return left * right;
	}

	@Specialization
	public float mulInt(int left, float right){
		System.out.println("Multiplying " + left + " * " + right);
		return (float)left * right;
	}

	@Specialization
	public float mulInt(float left, float right){
		System.out.println("Multiplying" + left + " * " + right);
		return left * (float)right;
	}
}
