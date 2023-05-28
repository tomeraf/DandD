public class Mage extends Player{
    protected int manaPool;
    protected int manaRemaining;
    protected  int manaCost;
    protected int spellPower;
    protected int hitsCount;
    public Mage(int X,int Y,int ManaPool,int ManaCost,int SpellPower,int HitsCount) {
        super(X, Y);
        manaPool=ManaPool;
        manaRemaining=manaPool/4;
        manaCost=ManaCost;
        spellPower=SpellPower;
        hitsCount=HitsCount;
    }
    public int GetResourcePool(){return manaPool;}
    public void SetResourcePool(int value){manaPool=value;}

    public int GetResourceRemaining(){return manaRemaining;}
    public void SetResourceRemaining(int value){manaRemaining=value;}

    public String cast(){
        if(manaRemaining>=manaCost){
            manaRemaining-=Math.max(0,manaRemaining-manaCost);

            //need to implemet the hit enemy, maybe in the UI



            return "hocus pocus,Blizzard apearus";
        }
        return "fail,not enough mana";
    }

    public boolean LVLUP(){
        if(this.LVLUP()) {
            manaPool+=25*LVL;
            manaRemaining = Math.min(manaRemaining+manaPool/4,manaPool);
            spellPower+=10*LVL;
            return true;
        }
        return false;
    }
    public void tick(){
        manaRemaining=Math.min(manaPool,manaRemaining+LVL);
    }
}
