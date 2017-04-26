import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;

public class VocabularioWriter {
	
	public static void fileWriter(Vocabulario toWrite, String outputFile){
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(outputFile);
            pw = new PrintWriter(fichero);
            
            Set<String> keys = toWrite.getPalabras().keySet();
            for(String key: keys){
                System.out.println(key + ", " + toWrite.getPalabras().get(key));
                pw.println(key + ", " + toWrite.getPalabras().get(key));
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
