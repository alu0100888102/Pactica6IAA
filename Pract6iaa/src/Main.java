
import java.io.*;

public class Main {
	
	public static void main(String[] args){
		File input = new File(args[0]);
		File output = new File(args[1]);
		Vocabulario vocabulario = new Vocabulario(input);
		vocabulario.writeToFile(output);
		
	}

}
