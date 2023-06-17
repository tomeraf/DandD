import java.util.Scanner;

public class Client {


    public static boolean start(String path) throws InterruptedException {
        Printer.print("CHOOSE YOUR CHAMPION:");
        Thread.sleep(300);
        System.out.println("1. Warrior\n");
        System.out.println("2. Mage\n");
        System.out.println("3. Rogue\n");
        System.out.println("4. Hunter\n");
        int input= Inputer.numberInput('4');
        Player p=null;

        if(input==0)
            return true;

        if(input==1) {
            Printer.print("CHOOSE YOUR Warrior:");
            Thread.sleep(300);
            System.out.println("1. Jon Snow\n");
            System.out.println("2. The Hound\n");
            System.out.println("OR PRESS 0 TO GO BACK\n");
            input = Inputer.numberInput('2');

            if (input == 1)
                p = new Warrior(0, 0, 3, 300, 30, 4, "Jon Snow");
            if (input == 2)
                p = new Warrior(0, 0, 5, 400, 20, 6, "The Hound");

        }//end of input warrior
        else if(input==2){
            Printer.print("CHOOSE YOUR Mage:");
            Thread.sleep(300);
            System.out.println("1. Melisandre\n");
            System.out.println("2. Thoros of Myr\n");
            System.out.println("3. Lihi\n");
            System.out.println("OR PRESS 0 TO GO BACK\n");
            input = Inputer.numberInput('3');

            if(input==0)
                return true;
            if(input==1)
                p=new Mage(0,0,300,30,15,5,100,5,1,6,"Melisandre");
            if(input==2)
                p=new Mage(0,0,150,20,20,3,250,25,4,4,"Thoros of Myr");
            if(input==3)
                p=new Mage(0,0,5,1,10000,100,10000,10000,4000,10,"Lihi");

        }//end of input mage
        else if(input==3) {
            Printer.print("CHOOSE YOUR Rogue:");
            Thread.sleep(300);
            System.out.println("1. Arya Stark\n");
            System.out.println("2. Bronn\n");
            System.out.println("OR PRESS 0 TO GO BACK\n");
            input = Inputer.numberInput('2');

            if(input==0)
                return true;
            if(input==1)
                p=new Rogue(0,0,20,150,40,2,"Arya Stark");
            if(input==2)
                p=new Rogue(0,0,50,250,35,3,"Bronn");

        }//end if input rogue
        else if(input==4) {
            Printer.print("CHOOSE YOUR Hunter:");
            Thread.sleep(300);
            System.out.println("1. Ygritte\n");
            System.out.println("2. Tal Brami\n");
            System.out.println("OR PRESS 0 TO GO BACK\n");
            input = Inputer.numberInput('2');

            if(input==0)
                return true;
            if (input == 1)
                p = new Hunter(0, 0, 220, 30, 2, 6, "Ygritte");
            if (input == 2)
                p = new Hunter(0, 0, 100, 1000, 1, 50, "Tal Brami");

        }//end of input Hunter

        Printer.print("\n\nYou Choose: "+p+"\n");

        Printer.print("CHOOSE CAMPAIGN:");
        Thread.sleep(300);
        System.out.println("1. Night's King\n");
        System.out.println("2. Mor the wise (tutorial)\n");
        System.out.println("3. Tomer The Eternal (Endless)\n");
        System.out.println("OR PRESS 0 TO RESTART CHOICES\n");
        input = Inputer.numberInput('3');

        String CName=null;
        int numberOFLVLs=0;
        if(input==0) return true;
        if(input==1) {CName="Night's King";numberOFLVLs=4;}
        if(input==2) {CName="Elder Mor";numberOFLVLs=1;}
        if(input==3) {CName="Tomer The Eternal";numberOFLVLs=1;}

        Printer.print("SPEEDRUN?");
        Thread.sleep(300);
        System.out.println("1. yes\n");
        System.out.println("2. no\n");
        input = Inputer.numberInput('2');
        boolean speedrun=false;
        if(input==1)
            speedrun=true;

        for(int i=1;i<=numberOFLVLs && !p.isDead();i++) {
            Printer.print("Loading LVL");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            System.out.println(".");
            Thread.sleep(500);
            LevelManager("" + i, p,CName,path,speedrun);
        }
        return false;
    }

    private static void LevelManager(String number, Player p, String CName, String path,boolean speedrun)throws InterruptedException{
        if(CName.equals("Night's King")) {
            LVL l = new LVL(number, p,path);
            System.out.println(l.Start());
            while (!l.IsEnd()) {
                String act=l.Act(Inputer.actInput());
                while(act.contains("fail")) {
                    Printer.printLVL(act,speedrun);
                    act=l.Act(Inputer.actInput());
                }
                Printer.printLVL(act,speedrun);
                Printer.printLVL(l.Tick(),speedrun);

                System.out.println(l.Display());
            }
            EndLVLDisplay(p, number);
        }
        else {
            //rest of the campaigns tbd
        }
    }

    private static void EndLVLDisplay(Player p, String name)throws InterruptedException {
        if (p.isDead()){
            Printer.print("You died in combat",200);
            Thread.sleep(500);
            Printer.print(",you'll be remembered",200);
        }
        else if(name.equals("4"))
            Printer.print("Victory!");
        else {
            Printer.print("you finished level " + name + ", proceed to the next Level by inputting any key\n\n");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            System.out.println("\n\n");
        }
    }
}
