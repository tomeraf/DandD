public class Rogue extends Player {
    protected int energyPool;
    protected int energyRemaining;
    protected int energyCost;


    public Rogue(int X, int Y, int ManaPool, int EnergyCost) {
        super(X, Y);
        energyPool = 100;
        energyRemaining =100;
        energyCost = EnergyCost;
    }

    public int GetResourcePool() {
        return energyPool;
    }

    public void SetResourcePool(int value) {
        energyPool = value;
    }

    public int GetResourceRemaining() {
        return energyRemaining;
    }

    public void SetResourceRemaining(int value) {
        energyRemaining = value;
    }

    public String cast() {
        if (energyRemaining >= energyCost) {
            energyRemaining -= Math.max(0, energyRemaining - energyCost);
            //need to implemet the hit enemy, maybe in the UI

            return "i have only 1 fan of my Knives,he is OnlyFan of Knives";
        }
        return "fail,not enough mana";
    }
    public boolean LVLUP(){
        if(this.LVLUP()) {
            energyRemaining = 100;
            attackPoints+=3*LVL;
            return true;
        }
        return false;
    }
    public void tick(){
        energyRemaining=Math.min(energyRemaining+10,100);
    }

}

