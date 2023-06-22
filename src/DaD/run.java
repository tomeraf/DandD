package DaD;

public class run {
       public static void main(String[] args) throws InterruptedException {
           if (args == null || args.length == 0) {
               System.out.println("this program need the lvls directory as an argument");
           } else {
               String path = args[0];
               Client c = new Client();
               while (c.start(path)) ;
           }
       }
 }

