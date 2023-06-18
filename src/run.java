public class run {


    public static void main(String[] args) throws InterruptedException{
            String path = "C:\\Users\\tomer\\Desktop\\levels_dir";
            boolean on = true;
            while (on)
                on = Client.start(path);
        }

       /*
       public static void main(String[] args) throws InterruptedException{
        if (args==null || args.length == 0) {
            System.out.println("this program need the lvls directory as an argument");
        } else {
            String path = args[0];
            boolean on = true;
            while (on)
                on = Client.start(path);
        }
*/
 }

