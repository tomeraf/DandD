import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Scanner;

public class LVL {
    private LinkedList<Enemy> e;
    private Board board;
    private Player p;

    public LVL(String number, Player player, String path)  {
        p =player;
        board=new Board();
        e=board.CreateBoard(number,p,path);
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
        for(int i=0;i<board.getMap().length;i++,messege+="\n")
            for(int j=0;j<board.getMap()[i].length;j++)
                messege+=board.getMap()[i][j].GetSign();
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
        for(Enemy enemy:e) {
            messege += enemy.move();
            //board change
        }

        PowerListRefresh();
        messege+=Display();
        return messege;
    }

    public String Act(char input){
        String messege="";
        LinkedList<Enemy> killed=null;
        if(input=='w' || input=='s' ||input=='a' ||input=='d' || input=='q') {
            messege = p.move();
            //need to change the map according to what happen in the act
        }
        else if(input=='e'){
            Pair<LinkedList<Enemy>,String> result=p.cast();
            killed=result.first();
            messege=result.second();
        }
        if(!killed.isEmpty())
            board.delete(killed);






        return messege;
    }

}
