package truffle.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import basic.SimpleBFParser;
import basic.SimpleBFParser.Loop;
import basic.SimpleBFParser.OpCode;
import basic.SimpleBFParser.Operation;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RootNode;

public class SimpleTruffle {

	private CallTarget callTarget;

	public void prepareAST(Operation[] parseResult) {
		BFRootNode bfRootNode = new BFRootNode(prepareNodes(parseResult));
		callTarget = Truffle.getRuntime().createCallTarget(bfRootNode);
	}

	private BFNode[] prepareNodes(Operation[] operations) {
		List<BFNode> nodeList = new ArrayList<>();
		for(Operation op : operations) {
			if (op instanceof Loop) {
				nodeList.add(new BFNode(OpCode.LOOP_START, prepareNodes(((Loop) op).getOperations())));
			}
			nodeList.add(new BFNode(op.getCode(), null));
		}
		return nodeList.toArray(new BFNode[0]);
	}
	public void runAST() {
		callTarget.call();
	}

	public static void main(String[] args) {
		if(args.length < 1 ){
			System.out.println("Please pass the input file");
			return;
		}
	
		SimpleBFParser parser = new SimpleBFParser();
		Operation[] parseResult = parser.parse(args[0]);

		SimpleTruffle simpleTruffle = new SimpleTruffle();
		simpleTruffle.prepareAST(parseResult);
		simpleTruffle.runAST();
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

		case PRINT_MEM: System.out.print((char)memory.cells[memory.position]);
			break;

		case READ_MEM:	Scanner scanner = new Scanner(System.in);
						memory.cells[memory.position] = scanner.next().charAt(0);
			break;

		case LOOP_START: while(memory.cells[memory.position] > 0){
				executeChildren(memory);
			}
			break;

		case LOOP_END:
			break;
		}
	}

	private void executeChildren(Memory memory) {
		for(BFNode child : children) {
			child.execute(memory);
		}
	}
}