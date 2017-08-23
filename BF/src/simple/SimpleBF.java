package simple;

import java.util.Scanner;

import common.SimpleBFImpl;

import simple.SimpleBFParser.Loop;
import simple.SimpleBFParser.OpCode;
import simple.SimpleBFParser.Operation;

public class SimpleBF implements SimpleBFImpl {

	private int memoryCounter;
	private char[] memory;
	private Operation[] operations;

	public SimpleBF() {
		memoryCounter = 0;
		memory= new char[1024];	//1k memory
	}

	@Override
	public void prepareAST(Operation[] parseResult) {
		this.operations = parseResult;
	}

	@Override
	public void runAST() {
		execute(operations);
		
	}

	public void execute(Operation[] operations) {
		for(Operation operation : operations){
			OpCode op = operation.getCode();
			switch(op) {
				case MOVE_LEFT:	memoryCounter--;
							break;

				case MOVE_RIGHT:	memoryCounter++;
							break;

				case INC_MEM:	memory[memoryCounter]++;
							break;

				case DEC_MEM:	memory[memoryCounter]--;
							break;

				case LOOP_START:	while(memory[memoryCounter] != 0) {
										execute(((Loop)operation).getOperations());
									}
							break;

				case LOOP_END:	return;

				case PRINT_MEM :	System.out.print(memory[memoryCounter]);
							break;

				case READ_MEM :	try(Scanner reader = new Scanner(System.in)){
								memory[memoryCounter] = reader.next().charAt(0);
							} catch(Exception e) {
								System.out.println("Error while reading input from STDIN. PTR=" + memoryCounter);
							}
				default	:
							System.err.println("Unsupported operation: '" + op +"'");
			}
		}
	}
}
