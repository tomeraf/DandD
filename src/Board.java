import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Board {
    private Tiles[][] map;

    public LinkedList<Enemy>  CreateBoard(String number, Player p, String path) {
        LinkedList<Enemy> e =new LinkedList<Enemy>();
        int height=0;
        try
        {
            File file = new File(path+"\\level"+number+".txt");
            Scanner scanner = new Scanner(file);
            String Line=null;
            while (scanner.hasNextLine()) {//check the height of the map
                height++;
                scanner.nextLine();
            }
            map=new Tiles[height][];
            height=0;
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Line = scanner.nextLine();
                map[height] = new Tiles[Line.length()];
                for(int i=0;i<Line.length();i++) {

                    if (Line.charAt(i) == '.') {
                        map[height][i] = new Empty(i, height);
                    } else if (Line.charAt(i) == '#') {
                        map[height][i] = new Wall(i, height);
                    } else if (Line.charAt(i) == '@'){
                        map[height][i] = p;
                    p.SetY(height);
                    p.SetX(i);
                    } else//enemy
                    {
                        Enemy en =EnemyCreator(Line.charAt(i),i,height);
                        map[height][i]= en;
                        e.add(en);
                    }
                }//end line
                height++;
            }//end txt file
        }//end try
        catch (FileNotFoundException ex)
        {
            System.out.println("Error 404: file not found");
        }


        return e;
    }
    public String MapDisplay(){
        String messege="";
        for(int i=0;i<map.length;i++,messege+="\n")
            for(int j=0;j<map[i].length;j++)
                messege+=map[i][j].GetSign();
        return messege+"\n";
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

    public void delete(LinkedList<Enemy> killed){
        for(Enemy e:killed)
            map[e.GetY()][e.GetX()] = new Empty(e.GetX(),e.GetY());
    }
    public void replaceAfterEnemyKilled(int x, int y){
        map[y][x] = new Empty(x,y);
    }


    public Tiles getTile(Integer first, Integer second) {
        return map[second][first];
    }

    public void swap(Unit u, Pair<Integer, Integer> whereHeWas) {
        map[u.GetY()][u.GetX()] = u;
        map[whereHeWas.second()][whereHeWas.first()] = new Empty(whereHeWas.first(), whereHeWas.second());
    }
}
