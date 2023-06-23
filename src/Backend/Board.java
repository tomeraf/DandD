package Backend;

import Backend.*;
import utilites.*;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class Board {
    private Tiles[][] map;

    public void SetMap(Tiles[][] Map){
        map=Map;
    }

    public LinkedList<Enemy>  CreateBoard(String number, Player p, String path, PrintInStyle PIS) {
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
                        Enemy en =EnemyCreator(Line.charAt(i),i,height,PIS);
                        map[height][i]= en;
                        e.add(en);
                    }
                }//end line
                height++;
            }//end txt file
        }//end try
        catch (Exception ex)
        {
            PIS.printClient("Error: file not found or not completed");
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
    private Enemy EnemyCreator(char sign, int x, int y,PrintInStyle PIS){
        if(sign=='s') return new Monster(x,y,'s',25,80,8,3,3,"Lannister Solider",PIS);
        if(sign=='k') return new Monster(x,y,'k',50,200,14,8,4,"Lannister Knight",PIS);
        if(sign=='q') return new Monster(x,y,'q',100,400,20,15,5,"Queen's Guard",PIS);
        if(sign=='z') return new Monster(x,y,'z',100,600,30,15,3,"Wright",PIS);
        if(sign=='b') return new Monster(x,y,'b',250,1000,75,30,4,"Bear-Wright",PIS);
        if(sign=='g') return new Monster(x,y,'g',500,1500,100,40,5,"Giant-Wright",PIS);
        if(sign=='w') return new Monster(x,y,'w',1000,2000,150,50,6,"White Walker",PIS);
        if(sign=='M') return new Boss(x,y,'M',500,1000,60,25,6,"The Mountain",5,PIS);
        if(sign=='C') return new Boss(x,y,'C',1000,100,10,10,1,"Queen Cersei",8,PIS);
        if(sign=='K') return new Boss(x,y,'K',5000,5000,300,150,8,"Night's King",3,PIS);
        if(sign=='B') return new Trap(x,y,'B',250,1,1,1,"Bonus Trap",3,5,PIS);
        if(sign=='Q') return new Trap(x,y,'Q',100,250,50,10,"Queen's Trap",3,7,PIS);
        if(sign=='D') return new Trap(x,y,'D',250,500,100,20,"Death Trap",1,10,PIS);
        else return new Monster(0,0,'?',0,0,0,0,0,"null",PIS);
    }

    public void delete(LinkedList<Enemy> killed){
        for(Unit u:killed)
            map[u.GetY()][u.GetX()] = new Empty(u.GetX(),u.GetY());
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
    private Enemy EternalCreator(int x,int y,int power,PrintInStyle PIS){
        return new Monster(x,y,'e',10*power,40*power,30*power,3*power,10,"Eternal Monster "+power,PIS);
    }

    public LinkedList<Enemy> EternalBoard(int power,PrintInStyle PIS){
        LinkedList<Enemy> e =new LinkedList<Enemy>();
        for(int j=1;j< map.length;j+=map.length-3)
            for(int i=1;i<map[j].length-1;i++) {
                e.addLast(EternalCreator(i, j, power, PIS));
                map[j][i] = e.getLast();
            }

        for(int i=2;i< map.length-1;i++) {
            e.addLast(EternalCreator(1, i, power, PIS));
            map[i][1] =e.getLast();
            e.addLast(EternalCreator(map[i].length-2,i, power, PIS));
            map[i][map[i].length-2] = e.getLast();
        }
            return e;
    }

}
