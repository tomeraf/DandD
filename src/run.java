public class run {
    public static void main(String[] args) throws InterruptedException{
        args = new String[1];
        args[0] = "C:\\Users\\tomer\\Desktop\\levels_dir";
        if (args.length == 0) {
            System.out.println("this program need the lvls directory as an argument");
        } else {
            String path = args[0];
            boolean on = true;
            while (on)
                on = Client.start(path);
        }

       /*
        if (args!=null || args.length == 0) {
            System.out.println("this program need the lvls directory as an argument");
        } else {
            String path = args[0];
            boolean on = true;
            while (on)
                on = Client.start(path);
        }
       */

    }
}
