
import java.io.*;

public class Main {
	
	public static void main(String[] args){
		/*for(int i =1; i<=20; i++){
			String in = new String("corpus/corpus"+i+".txt");
			String out= new String("aprendizaje/aprendizaje"+i+".txt");
			File input = new File(in);
			File output = new File(out);
			File todo = new File("vocabulario/vocabulariotodo.txt");
			Vocabulario vocabulario = new Vocabulario(input);
			vocabulario.suavizadoLaplaciano(todo, output);
			System.out.print("====================================================\nCorpus "+i+" Completo\n====================================================\n");
		}*/
		
		Clasificacion clasificador = new Clasificacion();
		clasificador.aprender();
		clasificador.analizar(new File("corpus/corpustodo.txt"), new File("outputs/output.txt"));
		
	}

}
