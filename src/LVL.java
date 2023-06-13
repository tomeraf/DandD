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
    public String Display() {
        String messege;
        messege=MapDisplay();
        messege+=PlayerDisplay();
        return messege;
    }
    private String MapDisplay(){
        return board.MapDisplay();
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
    public String Tick() {
        String messege="";
        messege+=p.tick(e);
        for(Enemy enemy:e) {
            Pair<Integer,Integer> whereToMove =  enemy.move(p);
            Tiles tile = board.getTile(whereToMove.first(),whereToMove.second());
            Pair<Unit,String> attackResult = enemy.attack(tile);
            messege+=attackResult.second();
            if (attackResult.first()!=null && attackResult.first().isDead()){
                return messege;
            }
        }
        PowerListRefresh();
        messege+=Display();
        return messege;
    }
    public String Act(char input){
        String messege="";

        if(input=='w' || input=='s' ||input=='a' ||input=='d') {
            Pair<Integer,Integer> whereToMove = p.move(input);
            Tiles tile = board.getTile(whereToMove.first(),whereToMove.second());
            Pair<Unit,String> attackResult = p.attack(tile);
            if (attackResult.first()!=null && attackResult.first().isDead()){
                Pair<Integer,Integer> newEmpty = new Pair<>(p.GetX(),p.GetY());
                p.movement(attackResult.first().GetX(),attackResult.first().GetY());
                board.replaceAfterEnemyKilled(newEmpty.first(), newEmpty.second());
                e.remove(attackResult.first());
            } else if (attackResult.first()==null) {
                board.swap(p,whereToMove);
            }
        }
        else if(input=='e'){
            LinkedList<Enemy> killed=null;
            Pair<LinkedList<Enemy>,String> castResult=p.cast();
            killed=castResult.first();
            messege=castResult.second();

            if(!killed.isEmpty()) {
                board.delete(killed);
                for(Enemy enemy:killed)
                    e.remove(enemy);
            }
        } else if (input=='q') {
            // to implement 'Resting'.
        }
        return messege;
    }

}
