package fun.coconut.nodes.arithmetic;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;

import fun.coconut.nodes.CoconutExpressionNode;

@NodeChildren({@NodeChild("left"), @NodeChild("right")})
public abstract class CoconutSubNode extends CoconutExpressionNode {

	@Specialization
	public int subInt(int left, int right){
		System.out.println("Subtracting " + left + " - " + right);
		return left - right;
	}

	@Specialization
	public float subFloat(float left, float right){
		System.out.println("Subtracting " + left + " - " + right);
		return left - right;
	}

	@Specialization
	public float subFloat(float left, int right){
		System.out.println("Subtracting " + left + " - " + right);
		return left - (float)right;
	}

	@Specialization
	public float subFloat(int left, float right){
		System.out.println("Subtracting " + left + " - " + right);
		return (float)left - right;
	}
}
