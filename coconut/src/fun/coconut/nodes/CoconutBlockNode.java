package fun.coconut.nodes;

import java.util.List;

import com.oracle.truffle.api.frame.VirtualFrame;

public class CoconutBlockNode extends CoconutExpressionNode{

	List<CoconutExpressionNode> nodeList;

	public CoconutBlockNode(List<CoconutExpressionNode> nodeList) {
		this.nodeList = nodeList;
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