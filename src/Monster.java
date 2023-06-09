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

    public String move(){return "";}
}
