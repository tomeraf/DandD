abstract public class Enemy extends  Unit {
    protected int EXPgain;
    public Enemy(int X,int Y,int HealthPool,int AttackPoints,int DefencePoints){
        super(X,Y,HealthPool,AttackPoints,DefencePoints);
        EXPgain=0;
    }

    public int GetEXPgain(){return EXPgain;}
    public void SetEXPgain(int value){EXPgain=value;}


}
