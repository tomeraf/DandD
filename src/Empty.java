public class Empty extends Tiles {
    public Empty(int X,int Y){
        super(X,Y);
        sign='.';
    }
    public Pair<Unit,String> accept(Enemy e){
        int temp = e.GetX();
        e.SetX(this.GetX());
        this.SetX(temp);
        temp = e.GetY();
        e.SetY(this.GetY());
        this.SetY(temp);
        return new Pair<Unit,String>(null,"");
    }
    public Pair<Unit,String> accept(Player p){
        int temp = p.GetX();
        p.SetX(this.GetX());
        this.SetX(temp);
        temp = p.GetY();
        p.SetY(this.GetY());
        this.SetY(temp);
        return new Pair<Unit,String>(null,"");
    }
    public Pair<Unit,String> accept(Unit u){
        return new Pair<Unit,String>(null,"");
    }

}
