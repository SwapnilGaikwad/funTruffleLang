package fun.coconut.nodes.arithmetic;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;

import fun.coconut.nodes.CoconutExpressionNode;

@NodeChildren({@NodeChild("left"), @NodeChild("right")})
public abstract class CoconutMulNode extends CoconutExpressionNode {

	@Specialization
	public int mulInt(int left, int right){
		System.out.println("Adding " + left + " * " + right);
		return left * right;
	}
}
