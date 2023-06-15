import java.util.Scanner;

public  class Inputer {
    public static char actInput()throws InterruptedException{
        Scanner scanner = new Scanner(System.in);
        String s=scanner.next();
        while(s.length()!=1 ||! (s.equals("w") || s.equals("s") ||s.equals("a") ||s.equals("d") || s.equals("q")||s.equals("e"))) {
            if(s.equals("h")){
                Printer.print("moving is done with ASWD keys.\npress the Q key to Rest.\nfor casting press the E key.");
            } else System.out.println("Illegal input, for information about legal inputs, send h");
            s=scanner.next();
        }
        return s.charAt(0);
    }

    public static int numberInput(char chooseFrom)throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String s = scanner.next();
            if(s.length() != 1)
                Printer.print("Illegal input,press a single number between the choices");
            else if(s.charAt(0)>chooseFrom || s.charAt(0)<'0')
                Printer.print("Illegal input,press a single number between the choices");
            else
                return s.charAt(0)-'0';
        }
    }
}
