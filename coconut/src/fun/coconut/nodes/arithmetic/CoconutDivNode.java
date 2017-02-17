package fun.coconut.nodes.arithmetic;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;

import fun.coconut.nodes.CoconutExpressionNode;
import fun.coconut.runtime.CoconutUnsupportedOperationException;

@NodeChildren({@NodeChild("left"), @NodeChild("right")})
public abstract class CoconutDivNode extends CoconutExpressionNode {

	@Specialization
	public int divInt(int left, int right){
		System.out.println("Adding " + left + " / " + right);
		if (right == 0)
			throw new CoconutUnsupportedOperationException("Divide by zero error");
		return left / right;
	}
}
