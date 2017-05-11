
import java.io.*;
import java.util.*;

public class Clasificacion {
	ArrayList<HashMap<String,Double>> vocabularios;
	HashMap<String, Integer> salida;

	public Clasificacion(){
		vocabularios = new ArrayList<HashMap<String,Double>>();
		salida = new HashMap<String, Integer>();
	}
	
	/** Cojemos cada uno de los ficheros de aprendizaje y guardamos en una tabla hash
	 * cada palabra y su LogProb.
	 */
	public void aprender(File input){
		try{
			//Declaraciones de variables
			FileInputStream istreamVocabulario = new FileInputStream(input);
			BufferedReader bufferedReaderVocabulario = new BufferedReader(new InputStreamReader(istreamVocabulario));
			String line = null;
			line =bufferedReaderVocabulario.readLine();
			line =bufferedReaderVocabulario.readLine();
				
			//Coge cada palabra del fichero de aprendizaje
			HashMap<String, Double> palabras = new HashMap<String, Double>();
			while((line = bufferedReaderVocabulario.readLine()) != null){
				String[] data = line.split("\\s+");
				palabras.put(data[1], Double.parseDouble(data[5])); //Cogemos el String de la palabra y su LogProb
				System.out.println(line);
			}
					
			vocabularios.add(palabras);
			System.out.println("Aprendizaje compleatdo.");
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
			
			//Iteraci√≥n entre las palabras del vocabulario y las del corpus
			while((line = bufferedReaderVocabulario.readLine()) != null){
				int perteneceA = 0;
				double greater = -50000;
				String[] data = line.split("\\s+");
				
				for(int i = 0; i< vocabularios.size(); i++){
					double value = 0;
					for(String word : data){
						if(vocabularios.get(i).containsKey(word))
							value += vocabularios.get(i).get(word);
					}
					if(value > greater){
						greater = value;
						perteneceA = i+1;
					}
				}
				
				//Escritura de la linea
				this.writeLineToFile(outputFile, "Frase: " + line + " Pertenece a: "+ perteneceA);
				salida.put(line, perteneceA);
					
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
	
	public void calcularAciertos(ArrayList<File> corpus){
		try{
			int palabrasTotales =0;
			int aciertos =0;
			int fallos =0;
			for(int i =1; i<=corpus.size(); i++){
				//Declaraciones de variables
				FileInputStream istreamVocabulario = new FileInputStream(corpus.get(i-1));
				BufferedReader bufferedReaderVocabulario = new BufferedReader(new InputStreamReader(istreamVocabulario));
				String line = null;
				
				while((line = bufferedReaderVocabulario.readLine()) != null){
					palabrasTotales++;
					if(salida.containsKey(line))
						if(salida.get(line) == i)
							aciertos++;
						else
							fallos++;
					else
						fallos++;
				}
				
			}
			System.out.println("Total: " + palabrasTotales +"\nAciertos: " + aciertos + "\nFallos: " + fallos + "\nPorcentaje de Èxito: " + (((double)aciertos/(double)palabrasTotales)*100));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

