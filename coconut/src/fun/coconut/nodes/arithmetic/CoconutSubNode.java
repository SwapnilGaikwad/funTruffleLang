package fun.coconut.nodes.arithmetic;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;

import fun.coconut.nodes.CoconutExpressionNode;
import fun.coconut.runtime.CoconutUnsupportedOperationException;

@NodeChildren({@NodeChild("left"), @NodeChild("right")})
public abstract class CoconutSubNode extends CoconutExpressionNode {

	@Specialization
	public int subInt(int left, int right){
		System.out.println("Adding " + left + " - " + right);
		return left - right;
	}

	@Specialization
	public int subFloat(float left, float right){
		throw new CoconutUnsupportedOperationException("Unsupported Operation for floats");
	}

	@Specialization
	public int subFloat(float left, int right){
		throw new CoconutUnsupportedOperationException("Unsupported Operation for floats");
	}

	@Specialization
	public int subFloat(int left, float right){
		throw new CoconutUnsupportedOperationException("Unsupported Operation for floats");
	}
}
