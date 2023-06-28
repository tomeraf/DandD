package Backend;

public class Dialog {
    public static String talk(String s){
        if(s.contains("NK"))
            return NKTalk(Integer.parseInt(s.substring(2)));
       else if(s.equals("K"))
           return KingTalk();
       else if(s.equals("C"))
           return QueenTalk();
       else if(s.equals("M"))
           return MountainTalk();
       else
           return "";

    }
    private static String KingTalk(){
        return "$Night's King: The day will never prevail!!!$\n\n";
    }
    private static String QueenTalk(){
        return "$Queen Cersei: you will never see the day light again!$\n\n";
    }
    private static String MountainTalk(){
        return "$The Mountain: I will end you!$\n\n";
    }

    private static String NKTalk(int number){
        if(number==0)
            return "$Night's King: Its time for the night the rule!!!$";
        else if(number==1)
            return "$Night's King: I shall destroy every single sunlight left in this world!!!$";
        else if(number==2)
            return "$Night's King: Its time for the endless night time!!!$";
        else if(number==3)
            return "$Night's King: You cant defeat the night itself!!!$";
        else if(number==4)
            return "$Night's King: The night shall conquer all!!!$";
        return "";
    }



    public static void MTWStart() throws InterruptedException {
        PrintInStyle PIS = new PrintInStyle(false);
        String s = "?: Hello and welcome to dungeon and dragons!\nMor: My name is Mor and ill be helping you learn the basic of the game\nMor: in this game you play as a brave champion trying to defeat enemies\nMor: gain EXP\nMor: and cast powers\n";
        PIS.printClient(s);
        Thread.sleep(100);
        s = "Mor: first, lets learn the basics\n";
        PIS.printClient(s);
    }
    public static void MTW2() throws InterruptedException {
        PrintInStyle PIS = new PrintInStyle(false);
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
    public static void MTW3() throws InterruptedException {
        PrintInStyle PIS = new PrintInStyle(false);
        String s = "Mor: good job!\nMor: next, lets understand how battle works!\n";
        s += "Mor: every time a battle happen, we roll 2 dices, first for the attacker, second for the defender.\n";
        s += "Mor: the dices can go from 1 to the max value of the Unit's attack/defence.\n";
        s += "Mor: But!\nMor: you are not the only one who can attack,after you end your turn,all the enemies may move/attack you too if they are near you.\n";
        PIS.printClient(s);
        Thread.sleep(100);
        s = "Mor: another thing that each hero have is the ability to cast powers!\n";
        s += "Mor: press e to cast powers, each hero has a different power:\n";
        s += "Mor: Warrior heal 10% of his max HP and damage a near by enemy\nMor: Mage cast a powerful Blizzard who damages enemies near him by random\n";
        s += "Mor: Rogue attack all nearby enemies once\nMor: Hunter shoot and arrow at a nearby enemy\n";
        s += "Mor: another thing you can do is rest/drink HP potion if you have, rest give you some hp back while potions give you much more\n";
        s+= "Mor: but you only have 2 in each lvl. for rest press q, for HP potions press r\n";
        s += "Mor: inorder to complete a lvl you must kill all the enemies in the board\n";
        s += "Mor: try to defeat all the enemies here:\n";
        PIS.printClient(s);
    }
    public static void TTE() throws InterruptedException {
        PrintInStyle PIS = new PrintInStyle(false);
        String s = "\nTomer: welcome champion to the last fight you will ever face:";
        PIS.printClient(s);
        Thread.sleep(100);
        s = "Tomer: THE ARMY OF THE ETERNAL!\n";
        PIS.printClient(s);
        s = "HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHA\n\n";
        PIS.printClient(s,10);
    }
}
