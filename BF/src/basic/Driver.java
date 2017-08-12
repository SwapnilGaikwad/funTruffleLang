package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {

	public static void main(String[] args) {

		if(args.length < 1 ){
			System.out.println("Please pass the input file");
			return;
		}
	
		File input = new File(args[0]);
		if(!input.isFile()){
			System.out.println("Given file '" + args[0] + "' not a valid file");
			return;
		}

		StringBuilder program = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input)))){
			String line;
			while((line = br.readLine())!=null){
				program.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		SimpleBF bf = new SimpleBF();
		bf.setProgram(program.toString().toCharArray());
		bf.execute();
	}

}
