package Backend;

import utilites.Pair;

import java.util.Iterator;
import java.util.LinkedList;

public class FinaleBossNK extends Enemy {
    protected int spawnFreq;//spawn enemies ability
    protected int spawnTicks;
    protected int tpFreq;//tp ability
    protected int tpTicks;
    protected LinkedList<Pair<Integer,Integer>> locationsToTP;//locations to tp into
    protected Iterator<Pair<Integer,Integer>> WhereTPIter;//indicate which location to tp too
    protected int hpRegenCounter;//time before start healing until damaged
    protected int dodgeCounter;
    protected int dodgeHappen;//when the dodge counter==dodge happen he dodge
    protected int wallFreq;//wall ability
    protected int wallTicks;
    protected int shootingFreq;//shooting ability
    protected int shootingTicks;
    protected int dialog;
    protected PrintInStyle PIS;
    protected Tiles[] map;

    public FinaleBossNK(int X, int Y, char Sign, int EXPGain, int HealthPool, int AttackPoints, int DefencePoints, int Vision, String Name,
                        int ShootingFreq, PrintInStyle printInStyle, LinkedList<Pair<Integer,Integer>> LocationsToTP) {
        super(X, Y, HealthPool, AttackPoints, DefencePoints, Name,printInStyle);
        sign = Sign;
        EXPgain = EXPGain;
        vision = Vision;
        spawnFreq=15;
        spawnTicks=10;
        tpFreq=100;
        tpTicks=tpFreq;
        dodgeCounter=0;
        dodgeHappen=5;
        wallFreq=70;
        wallTicks=wallFreq;
        shootingFreq=ShootingFreq;
        shootingTicks=0;
        dialog=0;
        PIS= printInStyle;
        locationsToTP=LocationsToTP;
        WhereTPIter=locationsToTP.iterator();
        hpRegenCounter=11;
        map=null;
    }
    public void initBoss(Tiles[] Map){
        map=Map;
    }

    private void decreaseTicks(){
        spawnTicks=Math.min(spawnTicks+1,spawnFreq);
        tpTicks=Math.min(tpTicks+1,tpFreq);
        wallTicks=Math.min(wallTicks+1,wallTicks);
        shootingTicks=Math.min(shootingTicks+1,shootingFreq);
    }
    public Pair<Integer,Integer> move(Player p) {
        Pair<Integer,Integer> location=GetLocation();
        if (hpRegenCounter < 11 && hpRegenCounter > 0){
            increaseHealth(healthAmount * 0.01);
        }
        else if(hpRegenCounter==0){
            increaseHealth(healthAmount * 0.1);
            //dialog super health regen
        }
        else if (healthAmount<=healthPool*0.3 && tpTicks == tpFreq) {
            //in lvl- replace boss with #
            //dialog health regen
            if(!WhereTPIter.hasNext())
                WhereTPIter=locationsToTP.iterator();
            location= WhereTPIter.next();
            tpTicks=tpFreq+1;
        } else if (wallTicks == wallFreq)
        {
            //locate where player is and put walls infront of the boss and if the a unit is there push it

        }
        else if(spawnTicks==spawnFreq)
        {
            //spawn enemies if 4 corners, if player is there ,spawn near boss, spawn eternal monsters
            spawnTicks=spawnFreq+1;
        }
        else if(shootingTicks==shootingFreq)
        {
            location=p.GetLocation();
            shootingTicks=shootingFreq+1;
        }
        else{
            PIS.print(Dialog.talk("NK"+dialog));
            dialog=(dialog+1)%5;
        }
        decreaseTicks();
        return location;
    }





}
