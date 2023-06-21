package Tests;
import DaD.*;
import org.junit.*;
import java.util.LinkedList;

public class MageTests {
    private Mage mage;
    private Mage mage2;
    private Mage mage3;
    private Monster monster1;
    private Monster monster2;
    private Monster monster3;

    private Pair<LinkedList<Enemy>,String> CastPair;
    private LinkedList<Enemy> powerlist;

    @Before
    public void initTest(){
        monster1 = new Monster(1, 1, 'm',0, 1,1,1,1,"monster1");
        monster2 = new Monster(1, 1, 'm',0, 1,1,1,1,"monster2");
        monster3 = new Monster(10, 10, 'm',0, 1,1,1,1,"monster3");
        mage = new Mage(2, 2,10, 1,100, 100,10, 100,100, 4,"mage");
        mage.SetEXP(50);
        mage2 = new Mage(2, 2,10, 1,100, 100,10, 100,100, 4,"mage2");
        mage2.SetEXP(50);
        mage3 = new Mage(2, 2,50, 1,120, 100,30, 108,102, 4,"mage3");
        mage3.SetLVL(2);
        mage3.SetResourceRemaining(17);
        mage3.SetResourcePool(60);
        powerlist = new LinkedList<>();
        powerlist.add(monster1);
        mage.powerRefresh(powerlist);
        CastPair = new Pair<>(powerlist,"hocus pocus,Blizzard apearus");
    }
    @Test
    public void castAbilityTest(){
        Pair<LinkedList<Enemy>,String> WCast = mage.castAbility();
        Assert.assertTrue("power list should have monster1 and the power active string",CastPair.equals(WCast));
    }
    @Test
    public void equalsTest(){Assert.assertTrue("expected true",mage2.equals(mage));}
    @Test
    public void lvlupTest(){mage.LVLUP();Assert.assertTrue("expected true",mage3.equals(mage));}
    @Test
    public void tickTest(){
        powerlist = new LinkedList<>();
        powerlist.addFirst(monster2);
        powerlist.addFirst(monster3);
        mage3.SetResourceRemaining(3);
        mage3.tick(powerlist);
        powerlist.removeFirst();
        Assert.assertEquals("expected 5 mana",5,mage3.GetResourceRemaining());
        Assert.assertEquals("expected true",powerlist,mage3.GetPower());
    }
}
