import java.util.Scanner;

public class Client {
    public static boolean start(String path) {
        System.out.println("CHOOSE YOUR CHAMPION:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Rogue");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        while(input<1 || input>3) {
            System.out.println("CHOOSE 1, 2, OR 3:");
            input = scanner.nextInt();
        }
        Player p=null;

        if(input==1){
            System.out.println("CHOOSE YOUR Warrior:");
            System.out.println("1. Jon Snow");
            System.out.println("2. The Hound");
            System.out.println("OR PRESS 0 TO GO BACK");
            input = scanner.nextInt();
            while(input<0 || input>2) {
                System.out.println("CHOOSE 0, 1, OR 2:");
                input = scanner.nextInt();
            }
            if(input==0)
                return true;
            if(input==1)
            {
                p=new Warrior(0,0,3,300,30,4,"Jon Snow");
            }
            if(input==2)
            {
                p=new Warrior(0,0,5,400,20,6,"The Hound");
            }

        }
        if(input==2){
            System.out.println("CHOOSE YOUR Mage:");
            System.out.println("1. Melisandre");
            System.out.println("2. Thoros of Myr");
            System.out.println("OR PRESS 0 TO GO BACK");
            input = scanner.nextInt();
            while(input<0 || input>2) {
                System.out.println("CHOOSE 0, 1, OR 2:");
                input = scanner.nextInt();
            }
            if(input==0)
                return true;
            if(input==1)
            {
                p=new Mage(0,0,300,30,15,5,100,5,1,6,"Melisandre");
            }
            if(input==2)
            {
                p=new Mage(0,0,150,20,20,3,250,25,4,4,"Thoros of Myr");
            }

        }
        if(input==3) {
            System.out.println("CHOOSE YOUR Rogue:");
            System.out.println("1. Arya Stark");
            System.out.println("2. Bronn");
            System.out.println("OR PRESS 0 TO GO BACK");
            input = scanner.nextInt();
            while (input < 0 || input > 2){
                System.out.println("CHOOSE 0, 1, OR 2:");
                input = scanner.nextInt();
            }
            if(input==0)
                return true;
            if(input==1)
            {
                p=new Rogue(0,0,20,150,40,2,"Arya Stark");
            }
            if(input==2)
            {
                p=new Rogue(0,0,50,250,35,3,"Bronn");
            }
        }

        System.out.println("CHOOSE CAMPAIGN:");
        System.out.println("1. Night's King");
        System.out.println("2. Elder Mor");
        System.out.println("3. Tomer The Eternal");
        System.out.println("OR PRESS 0 TO RESTART CHOICES");
        input = scanner.nextInt();
        while(input<0 || input>3) {
            System.out.println("CHOOSE 0, 1, 2 OR 3:");
            input = scanner.nextInt();
        }
        String CName=null;
        if(input==0) return true;
        if(input==1) CName="Night's King";
        if(input==2) CName="Elder Mor";
        if(input==3) CName="Tomer The Eternal";

        for(int i=1;i<=4 && !p.isDead();i++) {
            LevelManager("" + i, p,CName,path);
        }
        return false;
    }


    private static void LevelManager(String number,Player p,String CName,String path){
        if(CName.equals("Night's King")) {
            LVL l = new LVL(number, p,path);
            System.out.println(l.Start());
            while (!l.IsEnd()) {
                System.out.println(l.Act(userInput()));
                System.out.println(l.Tick());
            }
            EndLVLDisplay(p, number);
        }
        else {
            //rest of the campaigns tbd
        }
    }

    private static char userInput(){
        Scanner scanner = new Scanner(System.in);
        String s=scanner.next();
        while(s.length()>1 ||! (s.equals("w") || s.equals("s") ||s.equals("a") ||s.equals("d") || s.equals("q")||s.equals("e"))) {
            if(s.equals("h")){
                System.out.println("moving is done with ASWD keys.\npress the Q key to stay in your place.\nfor casting press the E key.");
            } else System.out.println("Illegal input, for information about legal inputs, send h");
            s=scanner.next();
        }
        return s.charAt(0);
    }

    private static void EndLVLDisplay(Player p,String name){
        if(p.isDead())
            System.out.println("You died in combat, you'll be remembered");
        else if(name.equals("4"))
            System.out.println("Victory!");
        else
            System.out.println("you finished level "+ name +", proceed to the next Level by inputting any key\n\n\n\n");
    }



}
