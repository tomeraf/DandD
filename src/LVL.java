import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Scanner;

public class LVL {
    LinkedList<Enemy> e;
    Tiles[][] map;
    Player p;

    public LVL(String number, Player player, String path)  {
        p =player;
        e =new LinkedList<Enemy>();
        int height=0;
        try
        {
            File file = new File(path+"\\Level"+number);
            Scanner scanner = new Scanner(file);
            String Line=null;
            while (scanner.hasNextLine()) {//check the height of the map
                height++;
                scanner.nextLine();
            }
            map=new Unit[height][];
            height=-1;
            while (scanner.hasNextLine()) {
                height++;
                Line = scanner.nextLine();
                for(int i=0;i<Line.length();i++)
                {

                    if(Line.charAt(i)=='.')
                    {
                        map[height][i]=new Empty(i,height);
                    }
                    else if(Line.charAt(i)=='#')
                    {
                        map[height][i]=new Wall(i,height);
                    }
                   else if(Line.charAt(i)=='@')
                        map[height][i]=p;
                    else//enemy
                    {
                        Enemy en =EnemyCreator(Line.charAt(i),i,height);
                        map[height][i]= en;
                        e.add(en);
                    }
                }//end line
            }//end txt file
        }//end try
        catch (FileNotFoundException ex)
        {
        System.out.println("Error 404: file not found");
        }
    }

    private Enemy EnemyCreator(char sign,int x,int y){
        if(sign=='s') return new Monster(x,y,'s',25,80,8,3,3,"Lannister Solider");
        if(sign=='k') return new Monster(x,y,'k',50,200,14,8,4,"Lannister Knight");
        if(sign=='q') return new Monster(x,y,'q',100,400,20,15,5,"Queen's Guard");
        if(sign=='z') return new Monster(x,y,'z',100,600,30,15,3,"Wright");
        if(sign=='b') return new Monster(x,y,'b',250,1000,75,30,4,"Bear-Wright");
        if(sign=='g') return new Monster(x,y,'g',500,1500,100,40,5,"Giant-Wright");
        if(sign=='w') return new Monster(x,y,'w',1000,2000,150,50,6,"White Walker");
        if(sign=='M') return new Monster(x,y,'M',500,1000,60,25,6,"The Mountain");
        if(sign=='C') return new Monster(x,y,'C',1000,100,10,10,1,"Queen Cersei");
        if(sign=='K') return new Monster(x,y,'K',5000,5000,300,150,8,"Night's King");
        if(sign=='B') return new Trap(x,y,'B',250,1,1,1,"Bonus Trap",3,5);
        if(sign=='Q') return new Trap(x,y,'Q',100,250,50,10,"Queen's Trap",3,7);
        if(sign=='D') return new Trap(x,y,'D',250,500,100,20,"Death Trap",1,10);
        else return null;
    }

    public boolean IsEnd(){
        if(p.isDead())
            return true;
        return e.isEmpty();
    }

    public String Display()
    {
        String messege;
        messege=MapDisplay();
        messege+=PlayerDisplay();
        return messege;
    }

    private String MapDisplay(){
        String messege="";
        for(int i=0;i<map.length;i++,messege+="\n")
            for(int j=0;j<map[i].length;j++)
                messege+=map[i][j].GetSign();
        return messege+"\n";
    }
    private  String PlayerDisplay(){
        return p.toString();
    }
    private void PowerListRefresh(){
        p.powerRefresh(e);


    }
    public String Start(){
        p.powerRefresh(e);
        return Display();
    }

    public String Tick()
    {
        String messege="";
        messege+=p.tick(e);
        for(Enemy enemy:e)
            messege+=enemy.move();
        PowerListRefresh();
        messege+=Display();
        return messege;
    }

    public String Act(char input){
        String messege="";

        if(input=='w' || input=='s' ||input=='a' ||input=='d' || input=='q') {
            messege = p.move();
        }
        else if(input=='e')
            messege=p.cast();

        //need to change the e according to what happen in the act




        return messege;
    }

}
