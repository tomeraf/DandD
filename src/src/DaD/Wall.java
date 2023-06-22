package DaD;

public class Wall extends Tiles {

    public Wall(int X,int Y){
        super(X,Y);
        sign='#';
    }
    @Override
    public Unit accept(Enemy e){
        return e;
    }
    @Override
    public Unit accept(Player p){
        return p;
    }
    @Override
    public Unit accept(Unit u){
        return u;
    }


}
