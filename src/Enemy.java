abstract public class Enemy extends  Unit {
    protected int EXPgain;
    public Enemy(int X,int Y,int HealthPool,int AttackPoints,int DefencePoints,String Name){
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        EXPgain=0;
    }

    public int GetEXPgain(){return EXPgain;}
    public void SetEXPgain(int value){EXPgain=value;}


}
