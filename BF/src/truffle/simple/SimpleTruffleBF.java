package truffle.simple;

import java.util.Scanner;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.LoopNode;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RepeatingNode;
import com.oracle.truffle.api.nodes.RootNode;

import common.SimpleBFImpl;
import common.SimpleBFParser.Loop;
import common.SimpleBFParser.OpCode;
import common.SimpleBFParser.Operation;

public class SimpleTruffleBF implements SimpleBFImpl {

	private CallTarget callTarget;
	private FrameSlot cellSlot;
	private FrameSlot cellPositionSlot;

	public void prepareAST(Operation[] parseResult) {
		BFRootNode bfRootNode = new BFRootNode(prepareNodes(parseResult));
		callTarget = Truffle.getRuntime().createCallTarget(bfRootNode);
		cellSlot = bfRootNode.getFrameDescriptor().addFrameSlot(0, FrameSlotKind.Object);
		cellPositionSlot = bfRootNode.getFrameDescriptor().addFrameSlot(1, FrameSlotKind.Int);
	}

	private BFNode[] prepareNodes(Operation[] operations) {
		BFNode[] opNodes = new BFNode[operations.length];
		for(int i = 0; i < operations.length; i++) {
			if (operations[i] instanceof Loop) {
				opNodes[i] = new BFNode(OpCode.LOOP_START, prepareNodes(((Loop) operations[i]).getOperations()));
				continue;
			}
			opNodes[i] = new BFNode(operations[i].getCode(), null);
		}
		return opNodes;
	}

	public void runAST() {
		callTarget.call();
	}

	class Memory {
		public int[] cells= new int[1024];
		public int position = 0;
	}

	class BFRootNode extends RootNode {

		@Children private final BFNode[] bfNodes;

		@SuppressWarnings("deprecation")
		public BFRootNode(BFNode[] bfNodes) {
			super(TruffleLanguage.class, null, null);
			this.bfNodes = bfNodes;
		}

		@Override
		public Object execute(VirtualFrame frame) {
			Memory memory = new Memory();
			frame.setObject(cellSlot, memory.cells);
			frame.setInt(cellPositionSlot, memory.position);

			for(BFNode bfNode : bfNodes) {
				bfNode.execute(frame);
			}
			return null;
		}
	}

	private class BFNode extends Node {

		private OpCode opCode;
		@Children private final BFNode[] children;

		@Child private LoopNode loopNode;

		public BFNode(OpCode opCode, BFNode[] children){
			this.opCode = opCode;
			this.children = children;
			if(opCode == OpCode.LOOP_START) {
				loopNode = Truffle.getRuntime().createLoopNode(new BFLoopNode());
			}
		}

		private class BFLoopNode extends Node implements RepeatingNode {

			@Override
			@ExplodeLoop
			public boolean executeRepeating(VirtualFrame frame) {
				try {
					int[] cells = getCells(frame);
					int position = getPosition(frame);

					if(cells[position] > 0 ){
						for(BFNode child : children){
							child.execute(frame);
						}
						return true;
					}
				} catch (FrameSlotTypeException e) {
					CompilerDirectives.transferToInterpreter();
					e.printStackTrace();
				}
				return false;
			}

		}

		public void execute(VirtualFrame frame) {
			int position;
			try {
				position = getPosition(frame);

				int[] cells = getCells(frame);

				switch(opCode) {
				case MOVE_RIGHT: position++;
				setPosition(frame, position);
				break;

				case MOVE_LEFT: position--;
				setPosition(frame, position);
				break;

				case INC_MEM: cells[position]++;
				break;

				case DEC_MEM: cells[position]--;
				break;

				case PRINT_MEM: printValue(cells[position]);
				break;

				case READ_MEM:	cells[position] = readValue();
				break;

				case LOOP_START: loopNode.executeLoop(frame);
				break;

				case LOOP_END:
					break;
				}
			} catch (FrameSlotTypeException e) {
				CompilerDirectives.transferToInterpreter();
				e.printStackTrace();
			}
		}

		private int getPosition(VirtualFrame frame)
				throws FrameSlotTypeException {
			return frame.getInt(cellPositionSlot);
		}

		private int[] getCells(VirtualFrame frame)
				throws FrameSlotTypeException {
			return (int[])frame.getObject(cellSlot);
		}

		private void setPosition(VirtualFrame frame, int position) {
			frame.setInt(cellPositionSlot, position);
		}

		@CompilerDirectives.TruffleBoundary
		private int readValue() {
			try(Scanner scanner = new Scanner(System.in)){
				return scanner.next().charAt(0);
			} catch (Exception e) {
				CompilerDirectives.transferToInterpreter();
				throw new RuntimeException();
			}
		}

		@CompilerDirectives.TruffleBoundary
		private void printValue(int value) {
			System.out.print((char) value);
		}
	}
}
