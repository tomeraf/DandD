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
    protected int wallTime;
    protected LinkedList<Pair<Integer,Integer>> WallsUp;
    protected int shootingFreq;//shooting ability
    protected int shootingTicks;
    protected int dialog;
    protected PrintInStyle PIS;


    public FinaleBossNK(int X, int Y, char Sign, int EXPGain, int HealthPool, int AttackPoints, int DefencePoints, int Vision, String Name,
                        int ShootingFreq, PrintInStyle printInStyle) {
        super(X, Y, HealthPool, AttackPoints, DefencePoints, Name,printInStyle);
        sign = Sign;
        EXPgain = EXPGain;
        vision = Vision;
        spawnFreq=15;
        spawnTicks=14;
        tpFreq=75;
        tpTicks=tpFreq;
        dodgeCounter=0;
        dodgeHappen=5;
        wallFreq=20;
        wallTicks=13;
        shootingFreq=ShootingFreq;
        shootingTicks=0;
        dialog=0;
        PIS= printInStyle;
        locationsToTP=new LinkedList<>();
        WhereTPIter=locationsToTP.iterator();
        hpRegenCounter=11;
        shooting=true;
        WallsUp=new LinkedList<>();
        wallTime=0;
    }
    private void IncreaseTicks(){
        spawnTicks=Math.min(spawnTicks+1,spawnFreq);
        tpTicks=Math.min(tpTicks+1,tpFreq);
        wallTicks=Math.min(wallTicks+1,wallFreq);
        shootingTicks=Math.min(shootingTicks+1,shootingFreq);
    }
    @Override
    public Pair<Integer,Integer> move(Player p) {return new Pair<>(-100,-100);}//indicate it's the boss
    public String turn(Player p) {
        String Do="";
        if (hpRegenCounter < 11 && hpRegenCounter > 0){
            Do="regen";
            hpRegenCounter--;
            PIS.print(Dialog.talk("NKregenState"));
        }
        else if(hpRegenCounter==0){
            Do="SR";
            PIS.print(Dialog.talk("NKsuperRegenState"));
            if(healthAmount==healthPool) {
                hpRegenCounter = 11;
                PIS.print(Dialog.talk("NKsuperRegen"));
            }
        }
        else if (healthAmount<=healthPool*0.3 && tpTicks == tpFreq) {
            Do="TP";
            PIS.print(Dialog.talk("NKregen"));
            hpRegenCounter=10;
            tpTicks=-1;
        } else if (wallTicks == wallFreq)
        {
            int DX = this.GetX()-p.GetX();
            int DY = this.GetY()-p.GetY();
            if (Math.abs(DX)>Math.abs(DY)){
                if (DX>0){
                    Do="wallsLeft";
                } else {
                    Do="wallsRight";
                }
            } else {
                if (DY>0){
                    Do="wallsUp";
                } else {
                    Do="wallsDown";
                }
            }
            PIS.print(Dialog.talk("NKwalls"));
            wallTicks=-1;;
        }
        else if(spawnTicks==spawnFreq)
        {
            Do="spawn";
            PIS.print(Dialog.talk("NKspawn"));
            spawnTicks=-1;;
        }
        else if(shootingTicks==shootingFreq)
        {
            Do="shoot";
            shootingTicks=-1;;
        }
        else{
            PIS.print(Dialog.talk("NK"+dialog));
            dialog=(dialog+1)%5;
        }
        IncreaseTicks();
        return Do;
    }
    @Override
    public String attacked(int playerAttackPower) {
        if(dodgeCounter==dodgeHappen) {
            dodgeCounter=0;
            return Dialog.talk("NKdodge");
        }
        dodgeCounter++;
        hpRegenCounter=11;
        return super.attacked(playerAttackPower);
    }
}
