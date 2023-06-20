package DaD;

import java.util.Scanner;

public class Client {


    public static boolean start(String path) throws InterruptedException {
        Printer.print("CHOOSE YOUR CHAMPION:");
        Thread.sleep(300);
        System.out.println("1. DaD.Warrior\n");
        System.out.println("2. DaD.Mage\n");
        System.out.println("3. DaD.Rogue\n");
        System.out.println("4. DaD.Hunter\n");
        int input = Inputer.numberInput('4');
        Player p = null;

        if (input == 0)
            return true;

        if (input == 1) {
            Printer.print("CHOOSE YOUR DaD.Warrior:");
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
        else if (input == 2) {
            Printer.print("CHOOSE YOUR DaD.Mage:");
            Thread.sleep(300);
            System.out.println("1. Melisandre\n");
            System.out.println("2. Thoros of Myr\n");
            System.out.println("3. Lihi\n");
            System.out.println("OR PRESS 0 TO GO BACK\n");
            input = Inputer.numberInput('3');

            if (input == 0)
                return true;
            if (input == 1)
                p = new Mage(0, 0, 300, 30, 15, 5, 100, 5, 1, 6, "Melisandre");
            if (input == 2)
                p = new Mage(0, 0, 150, 20, 20, 3, 250, 25, 4, 4, "Thoros of Myr");
            if (input == 3)
                p = new Mage(0, 0, 5, 1, 10000, 100, 10000, 10000, 4000, 10, "Lihi");

        }//end of input mage
        else if (input == 3) {
            Printer.print("CHOOSE YOUR DaD.Rogue:");
            Thread.sleep(300);
            System.out.println("1. Arya Stark\n");
            System.out.println("2. Bronn\n");
            System.out.println("OR PRESS 0 TO GO BACK\n");
            input = Inputer.numberInput('2');

            if (input == 0)
                return true;
            if (input == 1)
                p = new Rogue(0, 0, 20, 150, 40, 2, "Arya Stark");
            if (input == 2)
                p = new Rogue(0, 0, 50, 250, 35, 3, "Bronn");

        }//end if input rogue
        else if (input == 4) {
            Printer.print("CHOOSE YOUR DaD.Hunter:");
            Thread.sleep(300);
            System.out.println("1. Ygritte\n");
            System.out.println("2. Tal Brami\n");
            System.out.println("OR PRESS 0 TO GO BACK\n");
            input = Inputer.numberInput('2');

            if (input == 0)
                return true;
            if (input == 1)
                p = new Hunter(0, 0, 220, 30, 2, 6, "Ygritte");
            if (input == 2)
                p = new Hunter(0, 0, 100, 1000, 1, 50, "Tal Brami");

        }//end of input DaD.Hunter

        Printer.print("\n\nYou Choose: " + p + "\n");

        Printer.print("CHOOSE CAMPAIGN:");
        Thread.sleep(300);
        System.out.println("1. Night's King\n");
        System.out.println("2. Mor the wise (tutorial)\n");
        System.out.println("3. Tomer The Eternal (Endless)\n");
        System.out.println("OR PRESS 0 TO RESTART CHOICES\n");
        input = Inputer.numberInput('3');

        String CName = null;
        int numberOFLVLs = 0;
        if (input == 0) return true;
        if (input == 1) {
            CName = "Night's King";
            numberOFLVLs = 4;
        }
        if (input == 2) {
            CName = "Mor the wise";
            numberOFLVLs = 1;
        }
        if (input == 3) {
            CName = "Tomer The Eternal";
            numberOFLVLs = 1;
        }

        Printer.print("SPEEDRUN?");
        Thread.sleep(300);
        System.out.println("1. yes\n");
        System.out.println("2. no\n");
        input = Inputer.numberInput('2');
        boolean speedrun = false;
        if (input == 1)
            speedrun = true;

        for (int i = 1; i <= numberOFLVLs && !p.isDead(); i++) {
            Printer.print("Loading DaD.LVL");
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
            LevelManager("" + i, p, CName, path, speedrun);
        }
        if(!p.isDead())
            Printer.victory();
        return false;
    }

    private static void LevelManager(String number, Player p, String CName, String path, boolean speedrun) throws InterruptedException {
        if (CName.equals("Night's King")) {
            LVL l = new LVL(number, p, path);
            System.out.println(l.Start());
            while (!l.IsEnd()) {
                String act = l.Act(Inputer.actInput());
                while (act.contains("fail")) {
                    Printer.printLVL(act, speedrun);
                    act = l.Act(Inputer.actInput());
                }
                Printer.printLVL(act, speedrun);
                Printer.printLVL(l.Tick(), speedrun);

                System.out.println(l.Display());
            }
            EndLVLDisplay(p, number);
        }
        else if (CName.equals("Mor the wise")) {
            speedrun=false;
            MTWStart();
            LVL l = new LVL("MTW", p, path);
            System.out.println(l.Start());
            MTW2();
            for(int i=0;i<5;i++)
            {
                String act = l.Act(Inputer.actInput());
                while (act.contains("fail")) {
                    Printer.printLVL(act, speedrun);
                    act = l.Act(Inputer.actInput());
                }
                Printer.printLVL(act, speedrun);
                Printer.printLVL(l.Tick(), speedrun);
            }
            MTW3();
            l = new LVL("MTW2", p, path);
            System.out.println(l.Start());
            while (!l.IsEnd()) {
                String act = l.Act(Inputer.actInput());
                while (act.contains("fail")) {
                    Printer.printLVL(act, speedrun);
                    act = l.Act(Inputer.actInput());
                }
                Printer.printLVL(act, speedrun);
                Printer.printLVL(l.Tick(), speedrun);

                System.out.println(l.Display());
            }
            EndLVLDisplay(p, "T");
        }//mor the wise end

     else if (CName.equals("Tomer The Eternal")) {
            //tbd
        }
    }

    private static void EndLVLDisplay(Player p, String name) throws InterruptedException {
        if (p.isDead()) {
            Printer.print("You died in combat", 200);
            Thread.sleep(500);
            Printer.print(",you'll be remembered", 200);
        } else if (name.equals("T")){
            String s="Mor: You finished the tutorial, now go and play the game!\n\n\n";
            Printer.print(s);
        }

        else {
            Printer.print("you finished level " + name + ", input any key to continue.\n\n");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            System.out.println("\n\n");
        }
    }

    private static void MTWStart() throws InterruptedException {
        String s = "?: Hello and welcome to dungeon and dragons!\nMor: My name is Mor and ill be helping you learn the basic of the game\nMor: in this game you play as a brave champion trying to defeat enemies\nMor: gain EX\nMor: and cast powers\n";
        Printer.print("s");
        Thread.sleep(100);
        s = "Mor: first, lets learn the basics\n";
        Printer.print("s");
    }

    private static void MTW2() throws InterruptedException {
        String s = "Mor: this is the game board\nMor: the @ is you, the champion\nMor: the dots are DaD.Empty tiles you can move to freely\nMor: the # are walls you can't move into\n";
        s += "Mor: everything else represent an enemy of some kind, some can move some not, some have special ability and some not.\n";
        s += "Mor: below the board are represented your current stats\nMor: every act you do influence your stats\nMor: you gain resources from moving or attacking enemies\n";
        s += "Mor: you can rest and gain some extra hp and resources too.\n\n";
        Printer.print("s");
        Thread.sleep(100);
        s = "Mor: Lets learn how to move!\nMor: use the WASD keys to move around,you can move only 1 space away each time\nMor: press enter after you pressed one of the WASD keys\n";
        s += "Mor: here, try it now!";
        Printer.print("s");
    }
    private static void MTW3() throws InterruptedException {
        String s ="Mor: good job!\nMor: next, lets understand how battle works!\n";
        s+="Mor: every time a battle happen, we roll 2 dices, first for the attacker, second for the defender.\n";
        s+="Mor: the dices can go from 1 to the max value of the DaD.Unit's attack/defence.\n";
        s+="Mor: But!\nMor: you are not the only one who can attack,after you end your turn,all the enemies may move/attack you too if they are near you.\n";
        Printer.print("s");
        Thread.sleep(100);
        s+="Mor: another thing that each hero have is the ability to cast powers!\n";
        s+="Mor: press e to cast powers, each hero has a different power:\n";
        s+="Mor: DaD.Warrior heal 10% of his max HP and damage a near by enemy\nMor: DaD.Mage cast a powerfull Blizard who damages enemies near him by random\n";
        s+="Mor: DaD.Rogue attack all nearby enemies once\nMor: DaD.Hunter shoot and arrow at a nearby enemy\n";
        s+="Mor: inorder to complete a lvl you must kill all the enemies in the board\n";
        s+="Mor: try to defeate all the enemies here:\n";
        Printer.print("s");
    }

    }