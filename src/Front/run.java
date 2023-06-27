package Front;

import Front.Client;

public class run {
       public static void main(String[] args) throws InterruptedException {
           String path;
           if (args == null || args.length == 0) {
               //System.out.println("this program need the lvls directory as an argument");
               path=System.getProperty("user.dir")+"\\levels_dir";
           } else {
               path = args[0];
           }
           Client c = new Client();
           boolean on=c.start(path,true);
           while (on)
               on=c.start(path,false);
       }
 }

