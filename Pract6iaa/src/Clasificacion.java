
import java.io.*;
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
				FileInputStream istreamVocabulario = new FileInputStream(new File("aprendizaje/aprendizaje"+i+".txt"));
				BufferedReader bufferedReaderVocabulario = new BufferedReader(new InputStreamReader(istreamVocabulario));
				String line = null;
				line =bufferedReaderVocabulario.readLine();
				line =bufferedReaderVocabulario.readLine();
				
				//Coge cada palabra del fichero de aprendizaje
				Hashtable<String, Double> palabras = new Hashtable<String, Double>();
				while((line = bufferedReaderVocabulario.readLine()) != null){
					String[] data = line.split("\\s+");
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
	
	public void analizar(File toAnalize, File outputFile){
		try{
			//Limpiamos el fichero de salida
			PrintWriter writer = new PrintWriter(outputFile);
			writer.print("");
			writer.close();
			//Declaraciones de variables
			FileInputStream istreamVocabulario = new FileInputStream(toAnalize);
			BufferedReader bufferedReaderVocabulario = new BufferedReader(new InputStreamReader(istreamVocabulario));
			String line = null;
			
			//Iteración entre las palabras del vocabulario y las del corpus
			while((line = bufferedReaderVocabulario.readLine()) != null){
				int perteneceA = 0;
				double greater = Double.MIN_VALUE;
				String[] data = line.split("\\s+");
				
				for(int i = 0; i< vocabularios.size(); i++){
					double value = 0;
					for(String word : data){
						value += vocabularios.get(i).get(word);
					}
					if(value > greater){
						greater = value;
						perteneceA = i+1;
					}
				}
				
				//Escritura de la linea
				this.writeLineToFile(outputFile, "Frase: " + line + "Pertenece a: "+ perteneceA);
					
				//Mensaje para el debuggin
				System.out.println(line);
					
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void writeLineToFile(File out, String line){
		FileWriter fichero = null;
		PrintWriter pw = null;
		try{
			fichero = new FileWriter(out, true);
			pw = new PrintWriter(fichero);
			pw.println(line);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				if(null != fichero)
					fichero.close();
			}
			catch (Exception e2){
				e2.printStackTrace();
			}
		}
	}
}

