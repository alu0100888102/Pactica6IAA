import java.io.*;
import java.util.*;

public class Controlador {
	private Vocabulario vocabulario;
	private Clasificacion clasificador;
	
	public Controlador(){
		vocabulario = new Vocabulario();
		clasificador = new Clasificacion();
	}
	
	public void generaVocabulario(ArrayList<File> corpus, ArrayList<File> vocabulario){
		for(int i =0; i< corpus.size(); i++){
			this.vocabulario.geneaVocabulario(corpus.get(i));
			this.vocabulario.writeToFile(vocabulario.get(i));
		}
	}
	
	public void generaAprendizaje(File vocabularioTodo, File aprendizaje){
		this.vocabulario.suavizadoLaplaciano(vocabularioTodo, aprendizaje);
	}
	
	public void analiza(File corpusToAnalize, ArrayList<File> aprendizaje, File output){
		for(File f : aprendizaje)
			this.clasificador.aprender(f);
		this.clasificador.analizar(corpusToAnalize, output);
	}
	
	public void analizarDesdeCorpus(File corpusTodo, ArrayList<File> corpus, File corpusToAnalize){
		File vocabularioTodo = new File("vocabulario/vocabularioTodo.txt");
		this.vocabulario.geneaVocabulario(corpusTodo);
		this.vocabulario.writeToFile(vocabularioTodo);
		for(int i =0; i< corpus.size(); i++){
			File aprendizaje = new File("aprendizaje/aprendizaje"+(i+1)+".txt");
			/*File vocabulario = new File("vocabulario/vocabulario"+(i+1)+".txt");
			this.vocabulario.geneaVocabulario(corpus.get(i));
			this.vocabulario.writeToFile(vocabulario);
			this.vocabulario.suavizadoLaplaciano(vocabularioTodo, aprendizaje);
			
			Descomente esto para que haga todo corpus->vocabulario->aprendizaje->analisis.*/
			this.clasificador.aprender(aprendizaje);
		}
		File outputFile = new File("output.txt");
		this.clasificador.analizar(corpusToAnalize, outputFile);
		this.clasificador.calcularAciertos(corpus);
	}
}
