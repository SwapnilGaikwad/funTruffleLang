package fun.coconut.nodes.arithmetic;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;

import fun.coconut.nodes.CoconutExpressionNode;

@NodeChildren({@NodeChild("left"), @NodeChild("right")})
public abstract class CoconutModNode extends CoconutExpressionNode {

	@Specialization
	public int modInt(int left, int right){
		System.out.println("Modulus " + left + " % " + right);
		return left % right;
	}

	@Specialization
	public float modInt(float left, int right){
		System.out.println("Modulus " + left + " % " + right);
		return left % right;
	}

	@Specialization
	public float modInt(int left, float right){
		System.out.println("Modulus " + left + " % " + right);
		return left % right;
	}

	@Specialization
	public float modInt(float left, float right){
		System.out.println("Modulus " + left + " % " + right);
		return left % right;
	}
}
