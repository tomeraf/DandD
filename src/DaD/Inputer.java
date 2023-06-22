package DaD;

import java.util.Scanner;

public  class Inputer {
    public static char actInput(Player p)throws InterruptedException{
        PrintInStyle PIS = new PrintInStyle(true);
        Scanner scanner = new Scanner(System.in);
        String s=scanner.next();
        while(s.length()!=1 ||! (s.equals("w") || s.equals("s") ||s.equals("a") ||s.equals("d") || s.equals("q"))) {
            if(s.equals("h")){
                PIS.printClient("moving is done with ASWD keys.\npress the Q key to Rest.\nfor casting press the E key.");
            }
            else if (s.equals("e") && p.canCast())
                    return 'e';
            else PIS.printClient("Illegal input, for information about legal inputs, send h",0);
            s=scanner.next();
        }
        return s.charAt(0);
    }

    public static int numberInput(char chooseFrom)throws InterruptedException {
        PrintInStyle PIS = new PrintInStyle(true);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String s = scanner.next();
            if(s.length() != 1)
                PIS.printClient("Illegal input,press a single number between the choices");
            else if(s.charAt(0)>chooseFrom || s.charAt(0)<'0')
                PIS.printClient("Illegal input,press a single number between the choices");
            else
                return s.charAt(0)-'0';
        }
    }
}
