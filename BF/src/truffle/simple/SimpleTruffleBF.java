package truffle.simple;

import java.util.Scanner;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RootNode;
import common.SimpleBFImpl;
import common.SimpleBFParser.Loop;
import common.SimpleBFParser.OpCode;
import common.SimpleBFParser.Operation;

public class SimpleTruffleBF implements SimpleBFImpl {

	private CallTarget callTarget;

	public void prepareAST(Operation[] parseResult) {
		BFRootNode bfRootNode = new BFRootNode(prepareNodes(parseResult));
		callTarget = Truffle.getRuntime().createCallTarget(bfRootNode);
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
		for(BFNode bfNode : bfNodes) {
			bfNode.execute(memory);
		}
		return null;
	}
}

class BFNode extends Node {

	private OpCode opCode;
	@Children private final BFNode[] children;

	public BFNode(OpCode opCode, BFNode[] children){
		this.opCode = opCode;
		this.children = children;
	}

	public void execute(Memory memory) {
		switch(opCode) {
		case MOVE_RIGHT: memory.position++;
			break;

		case MOVE_LEFT: memory.position--;
			break;

		case INC_MEM: memory.cells[memory.position]++;
			break;

		case DEC_MEM: memory.cells[memory.position]--;
			break;

		case PRINT_MEM: printValue(memory.cells[memory.position]);
			break;

		case READ_MEM:	memory.cells[memory.position] = readValue();
			break;

		case LOOP_START: while(memory.cells[memory.position] > 0){
				executeChildren(memory);
				}
			break;

		case LOOP_END:
			break;
		}
	}

	@CompilerDirectives.TruffleBoundary
	private int readValue() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		return scanner.next().charAt(0);
	}
	@CompilerDirectives.TruffleBoundary
	private void printValue(int value) {
		System.out.print((char) value);
	}

	@ExplodeLoop
	private void executeChildren(Memory memory) {
		for(BFNode child : children) {
			child.execute(memory);
		}
	}
}