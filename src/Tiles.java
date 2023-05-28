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


}
