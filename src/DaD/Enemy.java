package DaD;

import java.util.Random;

abstract public class Enemy extends Unit {
    protected int EXPgain;
    protected int vision;
    protected boolean shooting;

    public Enemy(int X, int Y, int HealthPool, int AttackPoints, int DefencePoints, String Name) {
        super(X, Y, HealthPool, AttackPoints, DefencePoints, Name);
        EXPgain = 0;
        vision=0;
        shooting=false;
    }

    public boolean isShooting(){return shooting;}

    public int GetEXPgain() {
        return EXPgain;
    }

    public void SetEXPgain(int value) {
        EXPgain = value;
    }
    @Override
    public Pair<Unit, String> attack(Unit u) {
        return u.accept(this);
    }
    @Override
    public Pair<Unit, String> attack(Enemy e) {
        return e.accept(this);
    }
    @Override
    public Pair<Unit, String> attack(Player p) {
        return p.accept(this);
    }
    @Override
    public Pair<Unit, String> attack(Wall w) {
        return w.accept(this);
    }
    @Override
    public Pair<Unit, String> attack(Empty empty) {
        return empty.accept(this);
    }
    @Override
    public Pair<Unit, String> attack(Tiles t) {
        return t.accept(this);
    }
    @Override
    public Pair<Unit, String> accept(Enemy e) {
        return new Pair<>(this, "");
    }
    @Override
    public Pair<Unit, String> accept(Unit u) {
        return new Pair<>(null, "");
    }
    @Override
    public Pair<Unit, String> accept(Player p) {
        String messege = "$you are attacking the enemy "+this.name+ "\n$"+ p.combatString() + this.name+ "stats:\n "+this;
        Random random = new Random();
        int playerAttackPower = random.nextInt(p.attackPoints);
        messege += this.attacked(playerAttackPower);
        if (this.isDead()){
            messege += this.name + " killed and you gained " + this.EXPgain + " EXP\n";
            p.EXPGain(this.EXPgain);
        }
        return new Pair<>(this, messege);

    }

    public String attacked(int playerAttackPower) {
        String messege = "";
        Random random = new Random();
        int monsterDefense = random.nextInt(this.defencePoints);
        double damage= Math.max(0,playerAttackPower - monsterDefense);
        this.reduceHealth(damage);
        messege += "$combat info:\nattack roll: " + playerAttackPower + "\ndefense roll: " + monsterDefense +
                "\ndamage: " + damage + "\n$";
        return messege;
    }

    abstract public Pair<Pair<Integer, Integer>,String> move(Player p);

    protected  Pair<Pair<Integer,Integer>,String> randomMove(){
        double move = Math.random();
        if (move < 0.2) {
            return new Pair<>(new Pair<Integer,Integer>(this.GetX() - 1, this.GetY()),"");//going left
        }
        if (move < 0.4) {
            return new Pair<>(new Pair<Integer,Integer>(this.GetX(), this.GetY() + 1),"");//going down
        }
        if (move < 0.6) {
            return new Pair<>(new Pair<Integer,Integer>(this.GetX(), this.GetY() - 1),"");//going up
        }
        if (move < 0.8) {
            return new Pair<>(new Pair<Integer,Integer>(this.GetX() + 1, this.GetY()),"");//going right
        }
        return new Pair<>(new Pair<Integer,Integer>(this.GetX(), this.GetY()),"");//staying in place
    }
}