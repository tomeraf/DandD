import java.util.LinkedList;

public class Boss extends Enemy implements  HeroicUnit {
    protected int abilityFreq;
    protected int combatTicks;

    protected boolean dialog;

    public Boss(int X, int Y, char Sign, int EXPGain, int HealthPool, int AttackPoints, int DefencePoints, int Vision, String Name, int AbilityFreq) {
        super(X, Y, HealthPool, AttackPoints, DefencePoints, Name);
        sign = Sign;
        EXPgain = EXPGain;
        vision = Vision;
        abilityFreq = AbilityFreq;
        combatTicks = 0;
        dialog=true;
    }

    @Override
    public Pair<LinkedList<Unit>, String> castAbility(Player p) {
        LinkedList<Unit> player = new LinkedList<>();
        player.addFirst(p);
    return new Pair<>(player, "");
    }

    //note from developers: this "cast ability" thing is stupid and unesesery  but you want it so here you got it...

    @Override
    public Pair<Pair<Integer,Integer>,String> move(Player p) {
        String messege="";
        if (this.isInRange(p,this.vision)) {
            if(dialog) {
                dialog = false;
                messege=Dialog.talk(sign);
            }
            if (combatTicks == abilityFreq) {//shooting him
                combatTicks = 0;
                shooting=true;
                LinkedList<Unit> player=castAbility(p).first();

                return new Pair<>(new Pair<>(player.getFirst().x, player.getFirst().y),messege);//trying to go to his place(aka attacking him)(aka shooting him)
            } else {
                combatTicks++;
                int DX = this.GetX() - p.GetX();
                int DY = this.GetY() - p.GetY();
                if (Math.abs(DX) > Math.abs(DY)) {
                    if (DX > 0)
                        return new Pair<>(new Pair<>(this.GetX() - 1, this.GetY()),messege);//going left
                    else
                        return new Pair<>(new Pair<>(this.GetX() + 1, this.GetY()),messege);//going right
                } else {
                    if (DY > 0)
                        return new Pair<>(new Pair<>(this.GetX(), this.GetY() - 1),messege);//going up
                    else
                        return new Pair<>(new Pair<>(this.GetX(), this.GetY() + 1),messege);//going down
                }
            }
        }
        else {

            combatTicks=0;
            return randomMove();
        }
    }



}