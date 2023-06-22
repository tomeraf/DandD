package DaD;

public class PrintInStyle {
    public boolean speedrun;
    public PrintInStyle(boolean SpeedRun) {speedrun=SpeedRun;}

    public void printClient(String message, int speed) {//print at a reduced speed
        Printer massage = new Printer();
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            massage.print(ch);
            try {
                Thread.sleep(speed); // Sleep 100= 100ms (0.1 seconds)
            }
            catch(InterruptedException ex){}
        }
        massage.print("\n");
    }

    public void printClient(String message) //default print
    {
        this.printClient(message,50);
    }

    public  void print(String message){//special printer for the LVLs
        Printer massage = new Printer();
        char ch;
        int i=0;
        if(speedrun) {
            for (; i < message.length(); i++) {
                ch = message.charAt(i);
            if(ch!='$' && ch !='%')
                massage.print(ch);
            }
            massage.print("\n");
            return;
        }

        int speed;
        while(i < message.length()) {
            speed=40;
            for (; i < message.length() && message.charAt(i) != '$'; i++) {
                ch = message.charAt(i);
                massage.print(ch);
            }
            if (i < message.length() && message.charAt(i) == '$')//combat! slower speed
                i++;
            if(i < message.length() && message.charAt(i)=='$') {//lvl up/rest more speed
                speed = 10;
                i++;
                }
                for (; i < message.length() && message.charAt(i) != '$'; i++) {
                    ch = message.charAt(i);
                    massage.print(ch);
                    try {
                        Thread.sleep(speed); // Sleep 100= 100ms (0.1 seconds)
                    }
                    catch(InterruptedException ex){}
                }
            if(i < message.length() && message.charAt(i)=='$')//back to normal speed
                i++;
        }
        massage.print("\n");
    }

    public  void victory(){
        Printer massage = new Printer();
        String s="V    V  IIIII  CCCCC  TTTTT  OOOOO  RRRRR  Y   Y   !\n";
        massage.print(s);
        s="V    V    I   C         T   O     O R   R   Y Y    !\n";
        massage.print(s);
        s="V    V    I   C         T   O     O RRRRR     Y    !\n";
        massage.print(s);
        s=" V  V     I   C         T   O     O R R        Y   !\n";
        massage.print(s);
        s="  VV    IIIII  CCCCC    T    OOOOO  R  RR       Y  !\n";
        massage.print(s);
    }
}
