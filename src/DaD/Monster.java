package DaD;

public class Monster extends  Enemy{


    public Monster(int X,int Y,char Sign,int EXPGain,int HealthPool,int AttackPoints,int DefencePoints,int Vision,String Name,PrintInStyle PrintInStyle){
        super(X,Y,HealthPool,AttackPoints,DefencePoints,Name,PrintInStyle);
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

    @Override
    public boolean equals(Object other)
    {
        if(other instanceof  Monster)
        {
            Monster o= (Monster) other;
            return x==o.x && y==o.y && sign==o.sign && EXPgain==o.EXPgain && healthPool== o.healthPool && attackPoints==o.attackPoints && defencePoints==o.defencePoints && vision==o.vision;
        }
        return false;
    }

}
