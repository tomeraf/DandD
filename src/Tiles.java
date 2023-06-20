abstract public class Tiles {
    protected char sign;
    protected int x;
    protected int y;

    public Tiles(int X,int Y){
        sign='?';
        x=X;
        y=Y;
    }
    public char GetSign(){return sign;}
    public void SetSign(char Sign){sign=Sign;}

    public int GetX(){return x;}
    public void SetX(int X){x=X;}

    public int GetY(){return y;}
    public void SetY(int Y){y=Y;}
    public Pair<Integer,Integer> getLocation(){
        return new Pair<>(GetX(),GetY());
    }
    abstract public Pair<Unit,String> accept(Enemy e);
    abstract public Pair<Unit,String> accept(Player p);
    abstract public Pair<Unit,String> accept(Unit u);

    @Override
    public boolean equals(Object other) {
        if (other instanceof Tiles) {
            Tiles o = (Tiles) other;
            return x == o.x && y == o.y && sign == o.sign;
        }
        return false;

    }

}
