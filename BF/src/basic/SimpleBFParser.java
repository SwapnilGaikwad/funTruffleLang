package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleBFParser {

	public char[] parse(String inputFile) {

		File input = new File(inputFile);
		if(!input.isFile()){
			System.out.println("Given file '" + inputFile + "' not a valid file");
			throw new IllegalStateException();
		}

		StringBuilder program = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input)))){
			String line;
			while((line = br.readLine())!=null){
				program.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}

		return program.toString().toCharArray();
	}

}
