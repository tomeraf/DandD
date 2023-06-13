import java.util.Random;

public class Monster extends  Enemy{
    int vision;

    public Monster(int X,int Y,char Sign,int EXPGain,int HealthPool,int AttackPoints,int DefencePoints,int Vision,String Name){
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        sign=Sign;
        EXPgain=EXPGain;
        vision=Vision;
    }
    public void tick(){
        move();

    }
    public Pair<Integer,Integer> move(){
        double move = Math.random();
        if (move<0.2){
            return new Pair<>(this.GetX()-1,this.GetY());
        }
        if (move<0.4){
            return new Pair<>(this.GetX(),this.GetY()+1);
        }
        if (move<0.6){
            return new Pair<>(this.GetX(),this.GetY()-1);
        }
        if (move<0.8){
            return new Pair<>(this.GetX()+1,this.GetY());
        }
        return new Pair<>(this.GetX(),this.GetY());
    }




}
