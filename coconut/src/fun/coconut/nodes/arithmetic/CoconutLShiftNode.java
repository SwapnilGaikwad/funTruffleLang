package fun.coconut.nodes.arithmetic;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;

import fun.coconut.nodes.CoconutExpressionNode;
import fun.coconut.runtime.CoconutUnsupportedOperationException;

@NodeChildren({@NodeChild("left"), @NodeChild("right")})
public abstract class CoconutLShiftNode extends CoconutExpressionNode {

	@Specialization
	public int addInt(int left, int right){
		System.out.println("LShifting " + left + " << " + right);
		return left << right;
	}

	@Specialization
	public int addInt(float left, int right){
		throw new CoconutUnsupportedOperationException("Unsupported Operation for floats");
	}

	@Specialization
	public int addInt(int left, float right){
		throw new CoconutUnsupportedOperationException("Unsupported Operation for floats");
	}

	@Specialization
	public int addInt(float left, float right){
		throw new CoconutUnsupportedOperationException("Unsupported Operation for floats");
	}
}
