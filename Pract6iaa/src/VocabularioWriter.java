import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;

public class VocabularioWriter {
  
  public static void fileWriter(Vocabulario toWrite, String outputFile){
    FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(outputFile);
            pw = new PrintWriter(fichero);
            SortedMap map = new TreeMap(toWrite.getPalabras());
          java.util.Iterator iterator = map.keySet().iterator();

          while (iterator.hasNext()) {
            Object key = iterator.next();
            pw.write("Palabra : " + key + " Nº Apariciones :" + map.get(key) + "\n");
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
