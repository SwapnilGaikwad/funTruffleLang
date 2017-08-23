package simple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SimpleBFParser {

	public Operation[] parse(String inputFile) {

		File input = new File(inputFile);
		if(!input.isFile()){
			System.out.println("Given file '" + inputFile + "' not a valid file");
			throw new IllegalStateException();
		}

		Operation[] ops;
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input)))){
			ops = parseStream(br);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}

		return ops;
	}

	private Operation[] parseStream(BufferedReader br) {

		List<Operation> operations = new ArrayList<>();

		int ch;
		try {
			Operation operation = null;
			outer:
				while((ch = br.read()) != -1){
					switch(ch) {
					case '+':	operation = new Operation();
					operation.setCode(OpCode.INC_MEM);
					break;

					case '-':	operation = new Operation();
					operation.setCode(OpCode.DEC_MEM);
					break;

					case '<':	operation = new Operation();
					operation.setCode(OpCode.MOVE_LEFT);
					break;

					case '>':	operation = new Operation();
					operation.setCode(OpCode.MOVE_RIGHT);
					break;

					case '.':	operation = new Operation();
					operation.setCode(OpCode.PRINT_MEM);
					break;

					case ',':	operation = new Operation();
					operation.setCode(OpCode.READ_MEM);
					break;

					case '[':	Loop loop= new Loop();
					loop.setOperations(parseStream((br)));
					operation = loop;
					operation.setCode(OpCode.LOOP_START);
					break;

					case ']':	break outer;

					default:
						System.out.println("Unknown operator : '" + ch + "'");
						break;
					}
					operations.add(operation);
				}
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}

		Operation[] ops = new Operation[operations.size()];
		return operations.toArray(ops);
	}

	public enum OpCode{
		MOVE_LEFT,
		MOVE_RIGHT,
		INC_MEM,
		DEC_MEM,
		LOOP_START,
		LOOP_END,
		PRINT_MEM,
		READ_MEM
	}

	public class Operation {

		private OpCode code;

		public OpCode getCode() {
			return code;
		}
		public void setCode(OpCode code) {
			this.code = code;
		}

		public String toString(){
			return code.name();
		}
	}

	public class Loop extends Operation {
		private Operation[] operations;

		public Operation[] getOperations() {
			return operations;
		}

		public void setOperations(Operation[] operations) {
			this.operations = operations;
		}
	}
}