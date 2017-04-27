
import java.io.*;

public class Main {
	
	public static void main(String[] args){
		for(int i =1; i<=20; i++){
			String in = new String("corpus/corpus"+i+".txt");
			String out= new String("vocabulario/vocabulario"+i+".txt");
			File input = new File(in);
			File output = new File(out);
			Vocabulario vocabulario = new Vocabulario(input);
			vocabulario.writeToFile(output);
			System.out.print("Corpus "+i+" Completo\n");
		}
		String in = new String("corpus/corpustodo.txt");
		String out= new String("vocabulario/vocabulariotodo.txt");
		File input = new File(in);
		File output = new File(out);
		Vocabulario vocabulario = new Vocabulario(input);
		vocabulario.writeToFile(output);
		System.out.print("Corpus todo Completo\n");
		
	}

}
