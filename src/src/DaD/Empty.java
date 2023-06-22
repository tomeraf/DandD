package DaD;

public class Empty extends Tiles {
    public Empty(int X,int Y){
        super(X,Y);
        sign='.';
    }
    @Override
    public Unit accept(Enemy e){
        int temp = e.GetX();
        e.SetX(this.GetX());
        this.SetX(temp);
        temp = e.GetY();
        e.SetY(this.GetY());
        this.SetY(temp);
        return null;
    }
    @Override
    public Unit accept(Player p){
        int temp = p.GetX();
        p.SetX(this.GetX());
        this.SetX(temp);
        temp = p.GetY();
        p.SetY(this.GetY());
        this.SetY(temp);
        return null;
    }
    @Override
    public Unit accept(Unit u){
        return null;
    }

}
