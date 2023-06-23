package Backend;

import utilites.Pair;

import java.util.LinkedList;

public class LVL {
    private LinkedList<Enemy> e;
    private Board board;
    private Player p;
    private PrintInStyle PIS;

    public LVL(String number, Player player, String path,PrintInStyle PIS)  {
        p =player;
        board=new Board();
        e=board.CreateBoard(number,p,path,PIS);
        this.PIS=PIS;
    }
    public boolean IsEnd(){
        if(p.isDead())
            return true;
        return e.isEmpty();
    }
    public void Display() {
        MapDisplay();
        PlayerDisplay();
    }
    private void MapDisplay(){
        PIS.print(board.MapDisplay());
    }
    private  void PlayerDisplay(){
        PIS.print(p.toString());
    }
    private void PowerListRefresh(){
        p.powerRefresh(e);
    }
    public void Start(){
        p.powerRefresh(e);
        Display();
    }
    public void StartEternal(int power){
        e=board.EternalBoard(power,PIS);
        p.powerRefresh(e);
        Display();
    }
    public void Tick() {
        p.tick(e);
        int test=0;
        for(Enemy enemy:e) {
            Pair<Integer,Integer> whereHeWas = enemy.GetLocation();
            Pair<Integer, Integer> whereToMove =  enemy.move(p);
            Tiles tile = board.getTile(whereToMove.first(),whereToMove.second());
            Unit attackResult = enemy.attack(tile);
            if (attackResult!=null && attackResult.isDead()){//player died
                return ;
            } else if (attackResult==null) {//go to empty
                board.swap(enemy,whereHeWas);
            }
        }
        PowerListRefresh();
    }
    public void Act(char input){
        if(input=='w' || input=='s' ||input=='a' ||input=='d') {
            Pair<Integer,Integer> whereHeWas = p.GetLocation();
            Pair<Integer,Integer> whereToMove = p.move(input);
            Tiles tile = board.getTile(whereToMove.first(),whereToMove.second());
            Unit attackResult = p.attack(tile);
            if (attackResult!=null && attackResult.isDead()){
                p.movement(attackResult.GetX(),attackResult.GetY());
                board.swap(p,whereHeWas);
                e.remove(attackResult);
            } else if (attackResult==null) {
                board.swap(p,whereHeWas);
            }
        }
        else if(input=='e'){
            LinkedList<Enemy> killed;
            LinkedList<Enemy> castResult=p.castAbility();
            killed=castResult;
            if(!killed.isEmpty()) {
                board.delete(killed);
                for(Unit u:killed)
                    e.remove(u);
            }
        } else if (input=='q') {
            p.rest();
        }
        PowerListRefresh();
    }
}
