
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args){
		ArrayList<File> corpus = new ArrayList<File>();
		for(int i =1; i<=20; i++){
			String in = new String("corpus/corpus"+i+".txt");
			corpus.add(new File(in));
		}
		Controlador controlador = new Controlador();
		controlador.analizarDesdeCorpus(new File("corpus/corpustodo.txt"), corpus, new File("stackoverflowtestnoclass.txt"));
	}

}
