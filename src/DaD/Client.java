package DaD;

import java.util.Scanner;

public class Client {
    private PrintInStyle PIS = new PrintInStyle(false);
    public Client() {}

    public boolean start(String path) throws InterruptedException {
        PIS.printClient("CHOOSE YOUR CHAMPION:\n");
        Thread.sleep(300);
        PIS.printClient("0. exit the game\n",0);
        PIS.printClient("1. Warrior\n",0);
        PIS.printClient("2. Mage\n",0);
        PIS.printClient("3. Rogue\n",0);
        PIS.printClient("4. Hunter\n",0);
        int input = Inputer.numberInput('4');
        Player p = null;

        if (input == 0)
            return false;

        if (input == 1) {
            PIS.printClient("CHOOSE YOUR Warrior:\n");
            Thread.sleep(300);
            PIS.printClient("1. Jon Snow\n",0);
            PIS.printClient("2. The Hound\n",0);
            PIS.printClient("OR PRESS 0 TO GO BACK\n",0);
            input = Inputer.numberInput('2');

            if (input == 1)
                p = new Warrior(0, 0, 3, 300, 30, 4, "Jon Snow",PIS);
            if (input == 2)
                p = new Warrior(0, 0, 5, 400, 20, 6, "The Hound",PIS);

        }//end of input warrior
        else if (input == 2) {
            PIS.printClient("CHOOSE YOUR Mage:\n");
            Thread.sleep(300);
            PIS.printClient("1. Melisandre\n",0);
            PIS.printClient("2. Thoros of Myr\n",0);
            PIS.printClient("3. Lihi\n",0);
            PIS.printClient("OR PRESS 0 TO GO BACK\n",0);
            input = Inputer.numberInput('3');

            if (input == 0)
                return true;
            if (input == 1)
                p = new Mage(0, 0, 300, 30, 15, 5, 100, 5, 1, 6, "Melisandre",PIS);
            if (input == 2)
                p = new Mage(0, 0, 150, 20, 20, 3, 250, 25, 4, 4, "Thoros of Myr",PIS);
            if (input == 3)
                p = new Mage(0, 0, 5, 1, 10000, 100, 10000, 10000, 4000, 10, "Lihi",PIS);

        }//end of input mage
        else if (input == 3) {
            PIS.printClient("CHOOSE YOUR Rogue:\n");
            Thread.sleep(300);
            PIS.printClient("1. Arya Stark\n",0);
            PIS.printClient("2. Bronn\n",0);
            PIS.printClient("OR PRESS 0 TO GO BACK\n",0);
            input = Inputer.numberInput('2');

            if (input == 0)
                return true;
            if (input == 1)
                p = new Rogue(0, 0, 20, 150, 40, 2, "Arya Stark",PIS);
            if (input == 2)
                p = new Rogue(0, 0, 50, 250, 35, 3, "Bronn",PIS);

        }//end if input rogue
        else if (input == 4) {
            PIS.printClient("CHOOSE YOUR Hunter:\n");
            Thread.sleep(300);
            PIS.printClient("1. Ygritte\n",0);
            PIS.printClient("2. Tal Brami\n",0);
            PIS.printClient("OR PRESS 0 TO GO BACK\n",0);
            input = Inputer.numberInput('2');

            if (input == 0)
                return true;
            if (input == 1)
                p = new Hunter(0, 0, 220, 30, 2, 6, "Ygritte",PIS);
            if (input == 2)
                p = new Hunter(0, 0, 100, 1000, 1, 50, "Tal Brami",PIS);

        }//end of input DaD.Hunter

        PIS.printClient("\n\nYou Choose: " + p + "\n");
        PIS.printClient("CHOOSE CAMPAIGN:");
        Thread.sleep(300);
        PIS.printClient("1. Night's King\n",0);
        PIS.printClient("2. Mor the wise (tutorial)\n",0);
        PIS.printClient("3. Tomer The Eternal (Endless)\n",0);
        PIS.printClient("OR PRESS 0 TO RESTART CHOICES\n",0);
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

        PIS.printClient("SPEEDRUN?");
        Thread.sleep(300);
        PIS.printClient("1. yes\n",0);
        PIS.printClient("2. no\n",0);
        input = Inputer.numberInput('2');
        boolean speedrun = (input == 1);
        PIS.speedrun=speedrun;

        for (int i = 1; i <= numberOFLVLs && !p.isDead(); i++) {
            PIS.printClient("Loading LVL");
            for(int j=0;j<5;j++) {
                Thread.sleep(500);
                PIS.printClient(".", 0);
            }
            LevelManager("" + i, p, CName, path);
        }
        if(!p.isDead())
            PIS.victory();
        return true;
    }

    private  void LevelManager(String number, Player p, String CName, String path) throws InterruptedException {
        if (CName.equals("Night's King")) {
            LVL l = new LVL(number, p, path,PIS);
            l.Start();
            while (!l.IsEnd()) {
                l.Act(Inputer.actInput(p));
                l.Tick();
                l.Display();
            }
            EndLVLDisplay(p, number);
        }
        else if (CName.equals("Mor the wise")) {
            PIS.speedrun=false;
            MTWStart();
            LVL l = new LVL("MTW", p, path,PIS);
            l.Start();
            MTW2();
            for(int i=0;i<5;i++)
            {
                l.Act(Inputer.actInput(p));
                l.Tick();
                l.Display();
            }
            MTW3();
            l = new LVL("MTW2", p, path,PIS);
            l.Start();
            while (!l.IsEnd()) {
                l.Act(Inputer.actInput(p));
                l.Tick();
                l.Display();
            }
            EndLVLDisplay(p, "T");
        }//mor the wise end

     else if (CName.equals("Tomer The Eternal")) {
            //tbd
        }
    }

    private void EndLVLDisplay(Player p, String name) throws InterruptedException {
        if (p.isDead()) {
            PIS.printClient("You died in combat", 200);
            Thread.sleep(500);
            PIS.printClient(",you'll be remembered", 200);
        } else if (name.equals("T")){
            String s="Mor: You finished the tutorial, now go and play the game!\n\n\n";
            PIS.printClient(s);
        }

        else {
            PIS.printClient("you finished level " + name + ", input any key to continue.\n\n");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            PIS.printClient("\n\n",0);
        }
    }

    private void MTWStart() throws InterruptedException {
        String s = "?: Hello and welcome to dungeon and dragons!\nMor: My name is Mor and ill be helping you learn the basic of the game\nMor: in this game you play as a brave champion trying to defeat enemies\nMor: gain EXP\nMor: and cast powers\n";
        PIS.printClient(s);
        Thread.sleep(100);
        s = "Mor: first, lets learn the basics\n";
        PIS.printClient(s);
    }

    private void MTW2() throws InterruptedException {
        String s = "Mor: this is the game board\nMor: the @ is you, the champion\nMor: the dots are Empty tiles you can move to freely\nMor: the # are walls you can't move into\n";
        s += "Mor: everything else represent an enemy of some kind, some can move some not, some have special ability and some not.\n";
        s += "Mor: below the board are represented your current stats\nMor: every act you do influence your stats\nMor: you gain resources from moving or attacking enemies\n";
        s += "Mor: you can rest and gain some extra hp and resources too.\n\n";
        PIS.printClient(s);
        Thread.sleep(100);
        s = "Mor: Lets learn how to move!\nMor: use the WASD keys to move around,you can move only 1 space away each time\nMor: press enter after you pressed one of the WASD keys\n";
        s += "Mor: here, try it now!";
        PIS.printClient(s);
    }
    private void MTW3() throws InterruptedException {
        String s ="Mor: good job!\nMor: next, lets understand how battle works!\n";
        s+="Mor: every time a battle happen, we roll 2 dices, first for the attacker, second for the defender.\n";
        s+="Mor: the dices can go from 1 to the max value of the Unit's attack/defence.\n";
        s+="Mor: But!\nMor: you are not the only one who can attack,after you end your turn,all the enemies may move/attack you too if they are near you.\n";
        PIS.printClient(s);
        Thread.sleep(100);
        s="Mor: another thing that each hero have is the ability to cast powers!\n";
        s+="Mor: press e to cast powers, each hero has a different power:\n";
        s+="Mor: Warrior heal 10% of his max HP and damage a near by enemy\nMor: Mage cast a powerfull Blizard who damages enemies near him by random\n";
        s+="Mor: Rogue attack all nearby enemies once\nMor: Hunter shoot and arrow at a nearby enemy\n";
        s+="Mor: inorder to complete a lvl you must kill all the enemies in the board\n";
        s+="Mor: try to defeate all the enemies here:\n";
        PIS.printClient(s);
    }
}