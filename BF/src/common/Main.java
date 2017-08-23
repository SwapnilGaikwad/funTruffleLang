package common;

import simple.SimpleBF;
import simple.SimpleBFParser;
import simple.SimpleBFParser.Operation;
import truffle.simple.SimpleTruffleBF;

public class Main {

	private Operation[] parseResult;
	public void parse(String fileName) {
		SimpleBFParser parser = new SimpleBFParser();
		parseResult = parser.parse(fileName);
	}

	public void execute(SimpleBFImpl bfImpl) {
		bfImpl.prepareAST(parseResult);
		bfImpl.runAST();
	}
	public static void main(String[] args) {

		Main executor = new Main();

		if(args.length < 1 ){
			System.out.println("Please pass the input file");
			return;
		}
	
		//Parse the input
		executor.parse(args[0]);

		//SimpleBFImpl bf = new SimpleBF();
		SimpleBFImpl bf = new SimpleTruffleBF();
		executor.execute(bf);
	}

}
