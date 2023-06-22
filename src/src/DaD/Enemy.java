package DaD;

import java.util.Random;

abstract public class Enemy extends Unit {
    protected int EXPgain;
    protected int vision;
    protected boolean shooting;

    public Enemy(int X, int Y, int HealthPool, int AttackPoints, int DefencePoints, String Name,PrintInStyle PrintInStyle) {
        super(X, Y, HealthPool, AttackPoints, DefencePoints, Name,PrintInStyle);
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
    public Unit attack(Unit u) {
        return u.accept(this);
    }
    @Override
    public Unit attack(Enemy e) {
        return e.accept(this);
    }
    @Override
    public Unit attack(Player p) {
        return p.accept(this);
    }
    @Override
    public Unit attack(Wall w) {
        return w.accept(this);
    }
    @Override
    public Unit attack(Empty empty) {
        return empty.accept(this);
    }
    @Override
    public Unit attack(Tiles t) {
        return t.accept(this);
    }
    @Override
    public Unit accept(Enemy e) {
        return this;
    }
    @Override
    public Unit accept(Unit u) {
        return null;
    }
    @Override
    public Unit accept(Player p) {
        String messege = "$you are attacking the enemy "+this.name+ "\n$"+ p.combatString() + this.name+ " stats:\n "+this;
        Random random = new Random();
        int playerAttackPower = random.nextInt(p.attackPoints);
        printInStyle.print(messege);
        printInStyle.print(this.attacked(playerAttackPower));
        if (this.isDead()){
            messege = this.name + " killed and you gained " + this.EXPgain + " EXP\n";
            p.EXPGain(this.EXPgain);
            printInStyle.print(messege);
        }
        return this;
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

    abstract public Pair<Integer, Integer> move(Player p);

    protected  Pair<Integer,Integer> randomMove(){
        double move = Math.random();
        if (move < 0.2) {
            return new Pair<Integer,Integer>(this.GetX() - 1, this.GetY());//going left
        }
        if (move < 0.4) {
            return new Pair<Integer,Integer>(this.GetX(), this.GetY() + 1);//going down
        }
        if (move < 0.6) {
            return new Pair<Integer,Integer>(this.GetX(), this.GetY() - 1);//going up
        }
        if (move < 0.8) {
            return new Pair<Integer,Integer>(this.GetX() + 1, this.GetY());//going right
        }
        return new Pair<Integer,Integer>(this.GetX(), this.GetY());//staying in place
    }
}