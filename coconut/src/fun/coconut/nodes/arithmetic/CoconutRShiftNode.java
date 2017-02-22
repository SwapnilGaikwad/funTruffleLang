package fun.coconut.nodes.arithmetic;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;

import fun.coconut.nodes.CoconutExpressionNode;
import fun.coconut.runtime.CoconutUnsupportedOperationException;

@NodeChildren({@NodeChild("left"), @NodeChild("right")})
public abstract class CoconutRShiftNode extends CoconutExpressionNode {

	@Specialization
	public int addInt(int left, int right){
		System.out.println("LShifting " + left + " >> " + right);
		return left >> right;
	}

	@Specialization
	public int addFloat(float left, float right){
		throw new CoconutUnsupportedOperationException("Unsupported Operation for floats");
	}

	@Specialization
	public int addFloat(float left, int right){
		throw new CoconutUnsupportedOperationException("Unsupported Operation for floats");
	}

	@Specialization
	public int addFloat(int left, float right){
		throw new CoconutUnsupportedOperationException("Unsupported Operation for floats");
	}
}
