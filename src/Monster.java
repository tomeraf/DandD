public class Monster extends  Enemy{


    public Monster(int X,int Y,char Sign,int EXPGain,int HealthPool,int AttackPoints,int DefencePoints,int Vision,String Name){
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        sign=Sign;
        EXPgain=EXPGain;
        vision=Vision;
    }
    @Override
    public Pair<Pair<Integer,Integer>,String> move(Player p) {
        if (this.isInRange(p,this.vision)) {
            int DX = this.GetX()-p.GetX();
            int DY = this.GetY()-p.GetY();
            if (Math.abs(DX)>Math.abs(DY)){
                if (DX>0){
                    return new Pair<>(new Pair<>(this.GetX() - 1, this.GetY()),"");//going left
                } else {
                    return new Pair<>(new Pair<>(this.GetX() + 1, this.GetY()),"");//going right
                }
            } else {
                if (DY>0){
                    return new Pair<>(new Pair<>(this.GetX(), this.GetY() - 1),"");//going up
                } else {
                    return new Pair<>(new Pair<>(this.GetX(), this.GetY() + 1),"");//going down
                }
            }
        } else {
            return randomMove();
        }
    }

}
