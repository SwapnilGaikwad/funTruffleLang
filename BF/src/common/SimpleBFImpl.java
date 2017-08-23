package common;

import simple.SimpleBFParser.Operation;

public interface SimpleBFImpl {

	public void prepareAST(Operation[] parseResult);
	public void runAST();
}
