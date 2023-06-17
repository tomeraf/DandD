public class Printer {
    public static void print(String message,int speed)throws InterruptedException{//print at a reduced speed
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            System.out.print(ch);
            Thread.sleep(speed); // Sleep 100= 100ms (0.1 seconds)
        }
        System.out.println();
    }

    public static void print(String message)throws InterruptedException//default print
    {print(message,50);}

    public static void printLVL(String message,boolean speedrun)throws InterruptedException{//special printer for the LVLs

        char ch;
        int i=0;
        if(speedrun) {
            for (; i < message.length(); i++) {
                ch = message.charAt(i);
            if(ch!='$' && ch !='%')
                System.out.print(ch);
            }
            System.out.println();
            return;
        }

        int speed;
        while(i < message.length()) {
            speed=40;
            for (; i < message.length() && message.charAt(i) != '$'; i++) {
                ch = message.charAt(i);
                System.out.print(ch);
            }
            if (i < message.length() && message.charAt(i) == '$')//combat! slower speed
                i++;
            if(i < message.length() && message.charAt(i)=='$') {//lvl up/rest more speed
                speed = 10;
                i++;
                }
                for (; i < message.length() && message.charAt(i) != '$'; i++) {
                    ch = message.charAt(i);
                    System.out.print(ch);
                    Thread.sleep(speed);
                }
            if(i < message.length() && message.charAt(i)=='$')//back to normal speed
                i++;
            if(i == message.length()-1 && message.charAt(i)=='%') {//sleep time after something happen
                Thread.sleep(500);
                i++;
            }
        }
        System.out.println();
    }
}
