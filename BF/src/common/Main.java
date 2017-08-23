package common;

import common.SimpleBFParser.Operation;

import simple.SimpleBF;
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

		SimpleBFImpl bf = null;
		String BF = "" + System.getProperty("BF");
		switch(BF) {
		case "1" :
			bf = new SimpleBF();
			break;

		default:
			bf = new SimpleTruffleBF();
		}
		executor.execute(bf);
	}

}
