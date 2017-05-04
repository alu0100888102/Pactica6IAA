
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import java.io.*;

public class Vocabulario {
	
	Hashtable<String, Integer> palabras;
	long npalabras;
	int nLineas = 0;
	
	public Vocabulario(){
		palabras = new Hashtable<String, Integer>();
		npalabras =0;
	}
	
	/**
	 * Metodo que utiliza un vocabulario objeto proveniente de un corpus y que compara las palabras del vocabularioTodo de entrada con las del objeto
	 * para obtener el logaritmo del suavizado laplaciano en el fichero de salida.
	 * @param vocabularioTodo Fichero con todo el vocabulario del corpus
	 * @param outputFile Fichero de salida del aprendizaje
	 */
	public void suavizadoLaplaciano(File vocabularioTodo, File outputFile){
		
		try{
			//Limpiamos el fichero de salida
			PrintWriter writer = new PrintWriter(outputFile);
			writer.print("");
			writer.close();
			//Cabecera del fichero
			this.writeLineToFile(outputFile, "Numero de documentos (preguntas) del corpus : " + this.nLineas);
			this.writeLineToFile(outputFile, "Numero de palabras del corpus: " + this.npalabras);
			
			//Declaraciones de variables
			FileInputStream istreamVocabulario = new FileInputStream(vocabularioTodo);
			BufferedReader bufferedReaderVocabulario = new BufferedReader(new InputStreamReader(istreamVocabulario));
			String lineVocabulario = null;
			lineVocabulario =bufferedReaderVocabulario.readLine();
			int nPalabrasVocabulario = Integer.parseInt(lineVocabulario.split("\\s+")[3]);
			
			System.out.println(nPalabrasVocabulario);
			
			//Iteración entre las palabras del vocabulario y las del corpus
			while((lineVocabulario = bufferedReaderVocabulario.readLine()) != null){
				if(lineVocabulario.split("\\s+").length == 3){
				
					String palabraActual = lineVocabulario.split("\\s+")[2]; //Capturar palabra de la linea
					int nApariciones = 0;
					
					if(this.getPalabras().containsKey(palabraActual)){ //Si está en el corpus, obtener las apariciones
						nApariciones = this.getPalabras().get(palabraActual);
					}
					
					//Suavizado laplaciano
					double logaritmoSuavizadoLaplaciano = Math.log((double)(nApariciones + 1) / (double)(this.npalabras + nPalabrasVocabulario + 1));

					
					//Escritura de la linea
					this.writeLineToFile(outputFile, "Palabra: " + palabraActual + " Frec: " + nApariciones  + " LogProb: " + logaritmoSuavizadoLaplaciano);
					
					//Mensaje para el debuggin
					System.out.println(palabraActual);
					
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public Vocabulario(File input){
		palabras = new Hashtable<String, Integer>();
		npalabras =0;
		try{
			FileInputStream istream = new FileInputStream(input);
			BufferedReader bufferreader = new BufferedReader(new InputStreamReader(istream));
			String line = null;
			while ((line = bufferreader.readLine()) != null) {
				if(line.isEmpty())
					continue;
				
				this.nLineas++;
				String[] division = line.split("\\W+");
				for(String text : division){
					if(palabras.containsKey(text)){
						palabras.put(text, palabras.get(text)+1);
					}
					else{
						npalabras++;
						palabras.put(text, 1);
					}
				}
			}
			bufferreader.close();
		}
		catch(FileNotFoundException e){
			System.err.println("Error en el fichero: no se encuentra " + e);
			System.exit(1);
		}
		catch(IOException e){
			System.err.println("Error en el fichero: error de entrada/salida " + e);
			System.exit(1);
		}
		catch(IllegalArgumentException e){
			System.err.println("Linea  Error en el fichero: error de entrada/salida " + e);
			System.exit(1);
		}
	}
	public Hashtable<String, Integer> getPalabras() {
		return palabras;
	}
	public void setPalabras(Hashtable<String, Integer> palabras) {
		this.palabras = palabras;
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
	
	public void writeToFile(File out){
		 FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter(out);
	            pw = new PrintWriter(fichero);
	            SortedMap map = new TreeMap(getPalabras());
	          java.util.Iterator iterator = map.keySet().iterator();
	          pw.write("Numero de palabras: "+npalabras+"\n");
	          while (iterator.hasNext()) {
	            Object key = iterator.next();
	            pw.write("Palabra : " + key + "\n");
	          }
	            

	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        } 
	        finally {
	           try {
	             if (null != fichero)
	                fichero.close();
	           } 
	           catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
	}
	
}
