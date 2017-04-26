
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import java.io.*;

public class Vocabulario {
	
	Hashtable<String, Integer> palabras;
	
	public Vocabulario(){
		palabras = new Hashtable<String, Integer>();
	}
	public Vocabulario(File input){
		palabras = new Hashtable<String, Integer>();
		try{
			FileInputStream istream = new FileInputStream(input);
			BufferedReader bufferreader = new BufferedReader(new InputStreamReader(istream));
			String line = null;
			AtomicBoolean comment = new AtomicBoolean(false);
			while ((line = bufferreader.readLine()) != null) {
				if(line.isEmpty())
					continue;
				String[] division = line.split("\\s+");
				for(String text : division){
					if(palabras.contains(text)){
						palabras.put(text, palabras.get(text)+1);
					}
					else
						palabras.put(text, 1);
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
	
}
