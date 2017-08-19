package basic;

import java.util.Scanner;

import basic.SimpleBFParser.Loop;
import basic.SimpleBFParser.OpCode;
import basic.SimpleBFParser.Operation;

public class SimpleBF {

	private int memoryCounter;
	private char[] memory;

	public SimpleBF() {
		memoryCounter = 0;
		memory= new char[1024];	//1k memory
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

	public static void main(String[] args) {

		if(args.length < 1 ){
			System.out.println("Please pass the input file");
			return;
		}
	
		SimpleBFParser parser = new SimpleBFParser();
		Operation[] parseResult = parser.parse(args[0]);
		SimpleBF bf = new SimpleBF();
		bf.execute(parseResult);
	}

}
