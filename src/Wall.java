public class Wall extends Tiles {

    public Wall(int X,int Y){
        super(X,Y);
        sign='#';
    }
    @Override
    public Pair<Unit,String> accept(Enemy e){
        return new Pair<>(e, "");
    }
    @Override
    public Pair<Unit,String> accept(Player p){
        return new Pair<>(p, "");
    }
    @Override
    public Pair<Unit,String> accept(Unit u){
        return new Pair<>(u, "");
    }


}
