public class Wall extends Tiles {

    public Wall(int X,int Y){
        super(X,Y);
        sign='#';
    }
    public Pair<Unit,String> accept(Enemy e){
        return new Pair<Unit,String>(e,"");
    }
    public Pair<Unit,String> accept(Player p){
        return new Pair<Unit,String>(p,"");
    }
    public Pair<Unit,String> accept(Unit u){
        return new Pair<Unit,String>(u,"");
    }


}
