package fun.coconut.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;

@NodeChildren({@NodeChild("left"), @NodeChild("right")})
public abstract class CoconutAddNode extends CoconutExpressionNode {

	@Specialization
	public int addInt(int left, int right){
		System.out.println("Adding " + left + " + " + right);
		return left + right;
	}
}
