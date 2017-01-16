package fun.coconut.runtime;

import com.oracle.truffle.api.ExecutionContext;

public class CoconutContext extends ExecutionContext {

	public CoconutContext() {
		super();
		System.out.println("Initiating coconut execution context");
	}
}
