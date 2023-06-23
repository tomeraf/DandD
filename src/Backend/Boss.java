package Backend;

import utilites.Pair;

import java.util.LinkedList;

public class Boss extends Enemy implements  HeroicUnit {
    protected int abilityFreq;
    protected int combatTicks;

    protected boolean dialog;

    public void SetAbilityFreq(int value){abilityFreq=value;}

    public Boss(int X, int Y, char Sign, int EXPGain, int HealthPool, int AttackPoints, int DefencePoints, int Vision, String Name, int AbilityFreq,PrintInStyle PrintInStyle) {
        super(X, Y, HealthPool, AttackPoints, DefencePoints, Name,PrintInStyle);
        sign = Sign;
        EXPgain = EXPGain;
        vision = Vision;
        abilityFreq = AbilityFreq;
        combatTicks = 0;
        dialog=true;
    }

    @Override
    public LinkedList<Enemy> castAbility() {
        return null;
    }

    //note from developers: we didn't need the cast ability for the boss, we do the attack in the move.

    @Override
    public Pair<Integer,Integer> move(Player p) {
        String messege="";
        if (this.isInRange(p,this.vision)) {
            if(dialog) {
                dialog = false;
                messege=Dialog.talk(sign);
            }
            if (combatTicks == abilityFreq) {//shooting him
                combatTicks = 0;
                shooting=true;
                printInStyle.print(messege);
                return new Pair<>(p.x, p.y);//trying to go to his place(aka attacking him)(aka shooting him)
            } else {
                combatTicks++;
                int DX = this.GetX() - p.GetX();
                int DY = this.GetY() - p.GetY();
                if (Math.abs(DX) > Math.abs(DY)) {
                    if (DX > 0) {
                        printInStyle.print(messege);
                        return new Pair<>(this.GetX() - 1, this.GetY());//going left
                    } else {
                        printInStyle.print(messege);
                        return new Pair<>(this.GetX() + 1, this.GetY());//going right
                    }
                } else {
                    if (DY > 0) {
                        printInStyle.print(messege);
                        return new Pair<>(this.GetX(), this.GetY() - 1);//going up
                    } else {
                        printInStyle.print(messege);
                        return new Pair<>(this.GetX(), this.GetY() + 1);//going down
                    }
                }
            }
        }
        else {

            combatTicks=0;
            return randomMove();
        }
    }
    @Override
    public boolean equals(Object other)
    {
        if(other instanceof  Boss)
        {
            Boss o= (Boss)other;
            return x==o.x && y==o.y && sign==o.sign && EXPgain==o.EXPgain && healthPool== o.healthPool && attackPoints==o.attackPoints && defencePoints==o.defencePoints && vision==o.vision && abilityFreq==o.abilityFreq;
        }
        return false;

    }
}
