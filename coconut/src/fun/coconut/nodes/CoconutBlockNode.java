package fun.coconut.nodes;

import java.util.List;

import com.oracle.truffle.api.frame.VirtualFrame;

public class CoconutBlockNode extends CoconutExpressionNode {

	@Children private final CoconutExpressionNode[] nodeList;

	public CoconutBlockNode(List<CoconutExpressionNode> nodeList) {
		this.nodeList = nodeList.toArray(new CoconutExpressionNode[0]);
	}

	@Override
	public Object executeGeneric(VirtualFrame frame) {
		Object retValue = null;
		for(CoconutExpressionNode node : nodeList){
			retValue = node.executeGeneric(frame);
		}
		return retValue;
	}
}