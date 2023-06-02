import java.util.Scanner;

public class Client {
    public static boolean start() {
        System.out.println("CHOOSE YOUR CHAMPION:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Rogue");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();;
        while(input<1 || input>3)
            System.out.println("CHOOSE 1, 2, OR 3:");
        Player p=null;

        if(input==1){
            System.out.println("CHOOSE YOUR Warrior:");
            System.out.println("1. Jon Snow");
            System.out.println("2. The Hound");
            System.out.println("OR PRESS 0 TO GO BACK");
            input = scanner.nextInt();
            while(input<0 || input>2)
                System.out.println("CHOOSE 0, 1, OR 2:");
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
            while(input<0 || input>2)
                System.out.println("CHOOSE 0, 1, OR 2:");
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
        if(input==3){
            System.out.println("CHOOSE YOUR Rogue:");
            System.out.println("1. Arya Stark");
            System.out.println("2. Bronn");
            System.out.println("OR PRESS 0 TO GO BACK");
            input = scanner.nextInt();
            while(input<0 || input>2)
                System.out.println("CHOOSE 0, 1, OR 2:");
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
        System.out.println("2. Elder Dragon");
        System.out.println("3. Kindred The Eternal Hunters");
        System.out.println("OR PRESS 0 TO RESTART CHOICES");
        input = scanner.nextInt();
        while(input<0 || input>3)
            System.out.println("CHOOSE 0, 1, OR 2:");
        String CName=null;
        if(input==0) return true;
        if(input==1) CName="Night's King";
        if(input==2) CName="Elder Mor";
        if(input==3) CName="Tomer The Eternal";

        for(int i=1;i<=4 && !p.isDead();i++) {
            LevelManager(""+i, p,);
        }
        if(!p.isDead())
            LevelManager("",p,CName);
        return false;
    }

    private static void LevelManager(String number,Player p,String CName){
        if(CName=="Night's King") {
            LVL l = new LVL(number, p);
            l.start();
            while (!l.isEnd()) {
                l.act(userinput);
                l.tick();
            }
            EndLVLDisplay(p, number);
        }
        else {
            //rest of the campaigns tbd

        }
    }


    private static void EndLVLDisplay(Player p,String name){
        if(p.isDead())
            System.out.println("You died in combat, you'll be remembered");
        else if(name=="4")
            System.out.println("Victory!");
        else {
            System.out.println("you won "+ name +", proceed to the next Level by inputting any key");

        }



    }

}
