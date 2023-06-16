import java.util.LinkedList;

public class Boss extends Enemy implements  HeroicUnit {
    protected int abilityFreq;
    protected int combatTicks;


    public Boss(int X, int Y, char Sign, int EXPGain, int HealthPool, int AttackPoints, int DefencePoints, int Vision, String Name, int AbilityFreq) {
        super(X, Y, HealthPool, AttackPoints, DefencePoints, Name);
        sign = Sign;
        EXPgain = EXPGain;
        vision = Vision;
        abilityFreq = AbilityFreq;
        combatTicks = 0;
    }

    public Pair<LinkedList<Unit>, String> castAbility(Player p) {
        LinkedList<Unit> player = new LinkedList<>();
        player.addFirst(p);
    return new Pair<LinkedList<Unit>, String>(player,"");
    }

    //note from developers: this "cast ability" thing is stupid and unesesery  but you want it so here you got it...

    public Pair<Integer,Integer> move(Player p) {
        if (this.isInRange(p,this.vision)) {
            if (combatTicks == abilityFreq) {//shooting him
                combatTicks = 0;
                shooting=true;
                LinkedList<Unit> player=castAbility(p).first();
                return new Pair<Integer,Integer>(player.getFirst().x,player.getFirst().y);//trying to go to his place(aka attacking him)(aka shooting him)
            } else {
                int DX = this.GetX() - p.GetX();
                int DY = this.GetY() - p.GetY();
                if (Math.abs(DX) > Math.abs(DY)) {
                    if (DX > 0)
                        return new Pair<Integer, Integer>(this.GetX() - 1, this.GetY());//going left
                    else
                        return new Pair<Integer, Integer>(this.GetX() + 1, this.GetY());//going right
                } else {
                    if (DY > 0)
                        return new Pair<Integer, Integer>(this.GetX(), this.GetY() - 1);//going up
                    else
                        return new Pair<Integer, Integer>(this.GetX(), this.GetY() + 1);//going down
                }
            }
        }
        else {

            combatTicks=0;
            return randomMove();
        }
    }


    private Pair<Integer,Integer> randomMove(){
        double move = Math.random();
        if (move < 0.2) {
            return new Pair<>(this.GetX() - 1, this.GetY());//going left
        }
        if (move < 0.4) {
            return new Pair<>(this.GetX(), this.GetY() + 1);//going down
        }
        if (move < 0.6) {
            return new Pair<>(this.GetX(), this.GetY() - 1);//going up
        }
        if (move < 0.8) {
            return new Pair<>(this.GetX() + 1, this.GetY());//going right
        }
        return new Pair<>(this.GetX(), this.GetY());//staying in place
    }
}
