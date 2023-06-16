public class Monster extends  Enemy{


    public Monster(int X,int Y,char Sign,int EXPGain,int HealthPool,int AttackPoints,int DefencePoints,int Vision,String Name){
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name);
        sign=Sign;
        EXPgain=EXPGain;
        vision=Vision;
    }
    @Override
    public Pair<Integer,Integer> move(Player p) {
        if (this.isInRange(p,this.vision)) {
            int DX = this.GetX()-p.GetX();
            int DY = this.GetY()-p.GetY();
            if (Math.abs(DX)>Math.abs(DY)){
                if (DX>0){
                    return new Pair<>(this.GetX() - 1, this.GetY());
                } else {
                    return new Pair<>(this.GetX() + 1, this.GetY());
                }
            } else {
                if (DY>0){
                    return new Pair<>(this.GetX(), this.GetY() - 1);
                } else {
                    return new Pair<>(this.GetX(), this.GetY() + 1);
                }
            }
        } else {
            return randomMove();
        }
    }

    private Pair<Integer,Integer> randomMove(){
        double move = Math.random();
        if (move < 0.2) {
            return new Pair<>(this.GetX() - 1, this.GetY());
        }
        if (move < 0.4) {
            return new Pair<>(this.GetX(), this.GetY() + 1);
        }
        if (move < 0.6) {
            return new Pair<>(this.GetX(), this.GetY() - 1);
        }
        if (move < 0.8) {
            return new Pair<>(this.GetX() + 1, this.GetY());
        }
        return new Pair<>(this.GetX(), this.GetY());
    }
}
