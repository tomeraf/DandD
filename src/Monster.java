public class Monster extends  Enemy{

    public Monster(int X,int Y,char Sign,int EXPGain,int HealthPool,int AttackPoints,int DefencePoints){
        super(X,Y,HealthPool,AttackPoints,DefencePoints);
        sign=Sign;
        EXPgain=EXPGain;
    }
    public void tick(){
        move();

    }

    public void move(){}
}
