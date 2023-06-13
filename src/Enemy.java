import java.util.Random;

abstract public class Enemy extends  Unit {
    protected int EXPgain;

    public Enemy(int X, int Y, int HealthPool, int AttackPoints, int DefencePoints, String Name) {
        super(X, Y, HealthPool, AttackPoints, DefencePoints, Name);
        EXPgain = 0;
    }

    public int GetEXPgain() {
        return EXPgain;
    }

    public void SetEXPgain(int value) {
        EXPgain = value;
    }

    public Pair<Unit, String> attack(Unit u) {
        return u.accept(this);
    }

    public Pair<Unit, String> attack(Enemy e) {
        return e.accept(this);
    }

    public Pair<Unit, String> attack(Player p) {
        return p.accept(this);
    }

    public Pair<Unit, String> attack(Wall w) {
        return w.accept(this);
    }

    public Pair<Unit, String> attack(Empty empty) {
        return empty.accept(this);
    }

    public Pair<Unit, String> attack(Tiles t) {
        return t.accept(this);
    }

    public Pair<Unit, String> accept(Enemy e) {
        return new Pair<Unit, String>(this, "");
    }

    public Pair<Unit, String> accept(Unit u) {
        return new Pair<Unit, String>(null, "");
    }

    public Pair<Unit, String> accept(Player p) {
        String messege = p.toString() + "you are attacking the enemy "+this.name+ " stats:\n "+this.toString();
        Random random = new Random();
        int playerAttackPower = random.nextInt(p.attackPoints);
        messege += this.attacked(playerAttackPower);
        if (this.isDead()){
            messege += this.name + " killed and you gained " + this.EXPgain + " EXP\n";
            p.EXPGain(this.EXPgain);
        }
        return new Pair<Unit, String>(this, messege);

    }

    public String attacked(int playerAttackPower) {
        String messege = "";
        Random random = new Random();
        int monsterDefense = random.nextInt(this.defencePoints);
        double damage= Math.max(0,playerAttackPower - monsterDefense);
        this.reduceHealth(damage);
        messege += "combat info:\nattack roll: " + playerAttackPower + "\ndefense roll: " + monsterDefense +
                "\ndamage: " + damage + "\n";
        return messege;
    }

    abstract public Pair<Integer, Integer> move(Player p);
}