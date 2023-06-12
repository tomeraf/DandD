
public class run {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("this program need the lvls directory as an argument");
        } else {
            String path = args.toString();
            boolean on = true;
            while (on)
                on = Client.start(path);
        }


    }
}
