package basic;

import java.util.Scanner;
import java.util.Stack;

public class SimpleBF {

	private char[] program;
	private int programCounter;
	private int memoryCounter;
	private char[] memory;
	private Stack<Integer> loopNest;

	public SimpleBF() {
		programCounter = 0;
		memoryCounter = 0;
		loopNest = new Stack<>();
		memory= new char[1024];	//1k memory
	}

	public void setProgram(char[] program) {
		this.program = program;
	}

	private void moveToLoopEnd() {
		while(program[programCounter++]!= ']' && programCounter < program.length);
	}

	private void moveToLoopBegin() {
		programCounter = loopNest.lastElement();
	}

	public void execute() {
		while(programCounter < program.length){
			char op = program[programCounter];
			switch(op) {
				case '<':	memoryCounter--;
							break;

				case '>':	memoryCounter++;
							break;

				case '+':	memory[memoryCounter]++;
							break;

				case '-':	memory[memoryCounter]--;
							break;

				case '[':	if(memory[memoryCounter] == 0) {
								moveToLoopEnd();
							} else {
								loopNest.add(programCounter);
							}
							break;

				case ']':	if(memory[memoryCounter] != 0) {
								moveToLoopBegin();
							} else {
								loopNest.pop();
							}
							break;

				case '.' :	System.out.print(memory[memoryCounter]);
							break;

				case ',' :	try(Scanner reader = new Scanner(System.in)){
								memory[memoryCounter] = reader.next().charAt(0);
							} catch(Exception e) {
								System.out.println("Error while reading input from STDIN. PC=" + programCounter + ", PTR=" + memoryCounter);
							}
				default	:
							System.err.println("Unsupported operation: '" + op +"'");
			}
			programCounter++;
		}
	}
}
