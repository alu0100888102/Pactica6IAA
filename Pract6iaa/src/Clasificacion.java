
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Clasificacion {
	ArrayList<Hashtable<String,Double>> vocabularios;

	public Clasificacion(){
		vocabularios = new ArrayList<Hashtable<String,Double>>();
	}
	
	/** Cojemos cada uno de los ficheros de aprendizaje y guardamos en una tabla hash
	 * cada palabra y su LogProb.
	 */
	public void aprender(){
		try{
			for(int i =1; i<=20; i++){
				//Declaraciones de variables
				FileInputStream istreamVocabulario = new FileInputStream("aprendizaje/aprendizaje"+i+".txt");
				BufferedReader bufferedReaderVocabulario = new BufferedReader(new InputStreamReader(istreamVocabulario));
				String lineVocabulario = null;
				lineVocabulario =bufferedReaderVocabulario.readLine();
				
				//Coge cada palabra del fichero de aprendizaje
				Hashtable<String, Double> palabras = new Hashtable<String, Double>();
				while((lineVocabulario = bufferedReaderVocabulario.readLine()) != null){
					String[] data = lineVocabulario.split("\\s+");
					palabras.put(data[1], Double.parseDouble(data[5])); //Cogemos el String de la palabra y su LogProb
				}
				vocabularios.add(palabras);
				System.out.println("Aprendizaje "+i+" compleatdo.");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
