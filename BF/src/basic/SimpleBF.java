package basic;


public class SimpleBF {

	private char[] program;

	public char[] getProgram() {
		return program;
	}

	public void setProgram(char[] program) {
		this.program = program;
	}

	public void execute() {
		for(char c : program) {
			System.out.println(c);
		}
	}
}
