import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class LVL {

    public LVL(String name)  {
        FileReader FR=null;
        try {
            FR = new FileReader(name);
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Error 404: file not found");
        }
        try {
            while (FR.read()!= -1){
                char sign = (char)FR.read();
                //the init start here











            }
            FR.close();
        }
        catch(IOException ex){
            System.out.println("Error 666: file was corrupted");
        }
    }
}
