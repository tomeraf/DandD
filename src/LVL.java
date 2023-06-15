import java.util.LinkedList;

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
            Pair<Integer,Integer> whereHeWas = enemy.getLocation();
            Pair<Integer,Integer> whereToMove =  enemy.move(p);
            Tiles tile = board.getTile(whereToMove.first(),whereToMove.second());
            Pair<Unit,String> attackResult = enemy.attack(tile);
            messege+=attackResult.second();
            if (attackResult.first()!=null && attackResult.first().isDead()){//player died
                return messege;
            } else if (attackResult.first()==null) {//go to empty
                board.swap(enemy,whereHeWas);
            }
        }
        if(!messege.isEmpty())
            messege+="%";
        PowerListRefresh();
        return messege;
    }
    public String Act(char input){
        String messege="";

        if(input=='w' || input=='s' ||input=='a' ||input=='d') {
            Pair<Integer,Integer> whereHeWas = p.getLocation();
            Pair<Integer,Integer> whereToMove = p.move(input);
            Tiles tile = board.getTile(whereToMove.first(),whereToMove.second());
            Pair<Unit,String> attackResult = p.attack(tile);
            messege+=attackResult.second();
            if (attackResult.first()!=null && attackResult.first().isDead()){
                Pair<Integer,Integer> newEmpty = new Pair<>(p.GetX(),p.GetY());
                p.movement(attackResult.first().GetX(),attackResult.first().GetY());
                board.swap(p,whereHeWas);
                e.remove(attackResult.first());
            } else if (attackResult.first()==null) {
                board.swap(p,whereHeWas);
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

        PowerListRefresh();
        return messege;
    }

}
