package fun.coconut.nodes;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;

@NodeChild(value = "sourceValue")
@NodeField(name = "frameSlot", type = FrameSlot.class)
public abstract class CoconutDummyOperationNode extends CoconutExpressionNode {

	public abstract FrameSlot getFrameSlot();

	@Specialization
	public int performDummyOperation(VirtualFrame frame, int val){
		frame.setInt(getFrameSlot(), val);
		return val;
	}
}
