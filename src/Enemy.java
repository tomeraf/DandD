abstract public class Enemy extends  Unit {
    protected int EXPgain;
    public Enemy(int X,int Y){
        super(X,Y);
        EXPgain=0;
    }

    public int GetEXPgain(){return EXPgain;}
    public void SetEXPgain(int value){EXPgain=value;}
}
