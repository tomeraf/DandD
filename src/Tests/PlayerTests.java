package Tests;
import DaD.*;
import org.junit.*;
import java.util.LinkedList;

public class PlayerTests {
    public Warrior player;
    private Monster monster1;
    private Monster monster2;
    private Monster monster3;
    private Monster monster4;
    private Wall wall;
    private Empty empty;
    private LinkedList<Enemy> powerlist;
    private LinkedList<Enemy> powerlistRefreshed;
    Pair<Integer,Integer> playerLocation;
    Pair<Integer,Integer> emptyLocation;
    private PrintInStyle PIM;
    @Before
    public void initTest(){
        PIM = new PrintInStyle(true);
        monster1 = new Monster(1, 1, 'm',0, 1,1,1,1,"monster1",PIM);
        monster2 = new Monster(10, 10, 'm',0, 1,1,1,1,"monster2",PIM);
        monster3 = new Monster(1, 2, 'm',0, 1,1000000,1,1,"monster3",PIM);
        monster4 = new Monster(2, 1, 'm',0, 1,1,1,1,"monster4",PIM);
        player = new Warrior(2, 2,10, 1,100, 100,"warrior",PIM);

        wall = new Wall(50,50);
        playerLocation =player.GetLocation();
        empty=new Empty(100,100);
        emptyLocation = empty.GetLocation();
        powerlist = new LinkedList<>();
        powerlist.add(monster1);
        powerlist.add(monster2);
        powerlistRefreshed = new LinkedList<>();
        powerlistRefreshed.add(monster1);
    }
    @Test
    public void powerRefreshTest(){player.powerRefresh(powerlist);Assert.assertEquals("expected only monster1",player.GetPower(), powerlistRefreshed);}
    @Test
    public void attackWallTest(){player.attack(wall);Assert.assertTrue("expected player to stay in place",player.GetLocation().equals(playerLocation));}
    @Test
    public void attackEmptyTest(){player.attack(empty);Assert.assertTrue("expected player in 100,100",player.GetLocation().equals(emptyLocation));}
    @Test
    public void acceptEnemyDieTest(){player.accept(monster3);Assert.assertTrue("expected player to die",player.isDead());}
    @Test
    public void acceptEnemyNotDieTest(){
    player.SetHealthPool(10000000);
    player.increaseHealth(10000000);
    player.accept(monster3);
    Assert.assertTrue("expected player to be damaged",player.GetHealthAmount()<10000000);
    }
}
