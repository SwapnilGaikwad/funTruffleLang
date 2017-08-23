package common;

import common.SimpleBFParser.Operation;

public interface SimpleBFImpl {

	public void prepareAST(Operation[] parseResult);
	public void runAST();
}
