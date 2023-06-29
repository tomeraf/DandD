package Backend;

import utilites.Pair;

import java.util.LinkedList;

public class LVL {
    private LinkedList<Enemy> e;
    private Board board;
    private Player p;
    private PrintInStyle PIS;
    private FinaleBossNK boss;
    public LVL(String number, Player player, String path,PrintInStyle PIS)  {
        p =player;
        board=new Board();
        e=board.CreateBoard(number,p,path,PIS);
        this.PIS=PIS;
        boss=null;
    }
    public boolean IsEnd(){
        if(p.isDead())
            return true;
        if(boss==null)
            return e.isEmpty();
        return boss.isDead();
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
        p.SetHPotions(2);
        p.powerRefresh(e);
        Display();
    }
    public void StartEternal(int power){
        p.SetHPotions(3);
        e=board.EternalBoard(power,PIS);
        p.powerRefresh(e);
        Display();
    }
    public void InitFinaleBossNK(){
        LinkedList<Pair<Integer,Integer>> LocationsToTP= new LinkedList<>();
        LocationsToTP.addLast(new Pair<>(12,3));
        LocationsToTP.addLast(new Pair<>(12,15));
        boss = (FinaleBossNK)e.getFirst();
        boss.locationsToTP=LocationsToTP;
    }
    public void Tick() {
        p.tick(e);
        for(Enemy enemy:e) {
            Pair<Integer, Integer> whereHeWas = enemy.GetLocation();
            Pair<Integer, Integer> whereToMove = enemy.move(p);
            if (whereToMove.equals(new Pair<>(-100, -100))) {
                bossAction(boss.turn(p));
                return;
            }
            else {
                Tiles tile = board.getTile(whereToMove.first(), whereToMove.second());
                Unit attackResult = enemy.attack(tile);
                if (attackResult == null) //go to empty
                    board.moved(enemy, whereHeWas);
                }
                if (p.isDead()) {//player died
                    return;
            }
        }
        PowerListRefresh();
    }
    public void bossAction(String Do) {
        LinkedList<Pair<Integer, Integer>> walls;
        if(!boss.WallsUp.isEmpty())//encase there were walls last turn, we need to turn them down now
        {
            walls=boss.WallsUp;
            if(boss.wallTime!=2)
                boss.wallTime++;
            else {
                boss.wallTime=0;
                for (Pair<Integer, Integer> wallLocation : boss.WallsUp)
                    board.deleteInPlace(wallLocation);
                walls=new LinkedList<>();//reset the walls in boss
            }
        }
        else
            walls = new LinkedList<>();//reset the walls in boss
        Pair<Integer, Integer> whereHeWas = p.GetLocation();//encase we make walls and player was there

        switch (Do) {
            case "regen"://regen
            {
                boss.increaseHealth(boss.healthPool * 0.01);
                break;
            }
            case "SR"://super regen
            {
                boss.increaseHealth(boss.healthPool * 0.1);
                break;
            }
            case "TP"://teleport
            {
                Pair<Integer, Integer> whereBossWas = boss.GetLocation();

                if (!boss.WhereTPIter.hasNext())
                    boss.WhereTPIter = boss.locationsToTP.iterator();
                Pair<Integer, Integer> whereToGo = boss.WhereTPIter.next();
                boss.movement(whereToGo.first(), whereToGo.second());
                board.TPBoss(boss, whereBossWas);
                break;
            }
            case "wallsLeft": {
                Pair<Integer, Integer> BLMid = new Pair<>(boss.GetLocation().first() - 1, boss.GetLocation().second());
                Pair<Integer, Integer> BLUp = new Pair<>(boss.GetLocation().first() - 1, boss.GetLocation().second() - 1);
                Pair<Integer, Integer> BLDown = new Pair<>(boss.GetLocation().first() - 1, boss.GetLocation().second() + 1);
                walls.addFirst(BLDown);
                walls.addFirst(BLUp);
                walls.addFirst(BLMid);

                if (p.GetLocation().equals(BLMid)) {
                    e.remove(board.getTile(BLMid.first() - 1, BLMid.second()));
                    board.deleteInPlace(BLMid);
                    p.x--;
                    board.moved(p,whereHeWas);
                }
                else if (p.GetLocation().equals(BLUp)) {
                    e.remove(board.getTile(BLUp.first() - 1, BLUp.second()));
                    board.deleteInPlace(BLUp);
                    p.x--;
                    board.moved(p,whereHeWas);
                }
                else if(p.GetLocation().equals(BLDown)) {
                    e.remove(board.getTile(BLDown.first() - 1, BLDown.second()));
                    board.deleteInPlace(BLDown);
                    p.x--;
                    board.moved(p,whereHeWas);
                }
                board.BossWalls(BLMid, BLUp, BLDown);
                break;
            }
            case "wallsRight": {
                Pair<Integer, Integer> BLMid = new Pair<>(boss.GetLocation().first() + 1, boss.GetLocation().second());
                Pair<Integer, Integer> BLUp = new Pair<>(boss.GetLocation().first() + 1, boss.GetLocation().second() - 1);
                Pair<Integer, Integer> BLDown = new Pair<>(boss.GetLocation().first() + 1, boss.GetLocation().second() + 1);
                walls.addFirst(BLDown);
                walls.addFirst(BLUp);
                walls.addFirst(BLMid);
                if (p.GetLocation().equals(BLMid)) {
                    e.remove(board.getTile(BLMid.first() - 1, BLMid.second()));
                    board.deleteInPlace(BLMid);
                    p.x++;
                    board.moved(p,whereHeWas);

                }
                else if (p.GetLocation().equals(BLUp)) {
                    e.remove(board.getTile(BLUp.first() - 1, BLUp.second()));
                    board.deleteInPlace(BLUp);
                    p.x++;
                    board.moved(p,whereHeWas);
                }
                else if (p.GetLocation().equals(BLDown)) {
                    e.remove(board.getTile(BLDown.first() - 1, BLDown.second()));
                    board.deleteInPlace(BLDown);
                    p.x++;
                    board.moved(p,whereHeWas);
                }
                board.BossWalls(BLMid, BLUp, BLDown);
                break;
            }
            case "wallsUp": {
                Pair<Integer, Integer> BLMid = new Pair<>(boss.GetLocation().first(), boss.GetLocation().second() - 1);
                Pair<Integer, Integer> BLLeft = new Pair<>(boss.GetLocation().first() - 1, boss.GetLocation().second() - 1);
                Pair<Integer, Integer> BLRight = new Pair<>(boss.GetLocation().first() + 1, boss.GetLocation().second() - 1);
                walls.addFirst(BLLeft);
                walls.addFirst(BLRight);
                walls.addFirst(BLMid);
                if (p.GetLocation().equals(BLMid)) {
                    e.remove(board.getTile(BLMid.first() - 1, BLMid.second()));
                    board.deleteInPlace(BLMid);
                    p.y--;
                    board.moved(p,whereHeWas);
                }
                else if (p.GetLocation().equals(BLLeft)) {
                    e.remove(board.getTile(BLLeft.first() - 1, BLLeft.second()));
                    board.deleteInPlace(BLLeft);
                    p.y--;
                    board.moved(p,whereHeWas);
                }
                else if (p.GetLocation().equals(BLRight)) {
                    e.remove(board.getTile(BLRight.first() - 1, BLRight.second()));
                    board.deleteInPlace(BLRight);
                    p.y--;
                    board.moved(p,whereHeWas);
                }
                board.BossWalls(BLMid, BLLeft, BLRight);
                break;
            }
            case "wallsDown": {
                Pair<Integer, Integer> BLMid = new Pair<>(boss.GetLocation().first(), boss.GetLocation().second() + 1);
                Pair<Integer, Integer> BLLeft = new Pair<>(boss.GetLocation().first() - 1, boss.GetLocation().second() + 1);
                Pair<Integer, Integer> BLRight = new Pair<>(boss.GetLocation().first() + 1, boss.GetLocation().second() + 1);
                walls.addFirst(BLLeft);
                walls.addFirst(BLRight);
                walls.addFirst(BLMid);
                if (p.GetLocation().equals(BLMid)) {
                    e.remove(board.getTile(BLMid.first() - 1, BLMid.second()));
                    board.deleteInPlace(BLMid);
                    p.y++;
                    board.moved(p,whereHeWas);
                }
                else if (p.GetLocation().equals(BLLeft)) {
                    e.remove(board.getTile(BLLeft.first() - 1, BLLeft.second()));
                    board.deleteInPlace(BLLeft);
                    p.y++;
                    board.moved(p,whereHeWas);
                }
                else if (p.GetLocation().equals(BLRight)) {
                    e.remove(board.getTile(BLRight.first() - 1, BLRight.second()));
                    board.deleteInPlace(BLRight);
                    p.y++;
                    board.moved(p,whereHeWas);
                }
                board.BossWalls(BLMid, BLLeft, BLRight);
                break;
            }
            case "spawn": {
                board.BossSpawner(p, PIS,e);
                break;
            }
            case "shoot": {
                boss.attack(p);
                break;
            }
            }//switch
        boss.WallsUp=walls;//encase walls went up
        }
    public void Act(char input){
        if(input=='w' || input=='s' ||input=='a' ||input=='d') {
            Pair<Integer,Integer> whereHeWas = p.GetLocation();
            Pair<Integer,Integer> whereToMove = p.move(input);
            Tiles tile = board.getTile(whereToMove.first(),whereToMove.second());
            Unit attackResult = p.attack(tile);
            if (attackResult!=null && attackResult.isDead()){
                p.movement(attackResult.GetX(),attackResult.GetY());
                board.moved(p,whereHeWas);
                e.remove(attackResult);
            } else if (attackResult==null) {
                board.moved(p,whereHeWas);
            }
        }
        else if(input=='e'){
            LinkedList<Enemy> killed;
            LinkedList<Enemy> castResult=p.castAbility();
            killed=castResult;
            if(!killed.isEmpty()) {
                board.deleteEnemies(killed);
                for(Tiles t:killed)
                    e.remove(t);
            }
        } else if (input=='q')
            p.rest();
        else if (input=='r')
            p.drinkHPPotion();
        PowerListRefresh();
    }
}
