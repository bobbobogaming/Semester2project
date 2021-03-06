package Test.TestFiles.Database;

import Application.MVVM.Model.monster.Monster;
import Database.Adapters.GetMonsterFromDataBaseView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GetMonsterFromDataBaseViewTest {

    ArrayList<Monster> monsterArrayList;

    @BeforeEach
    void setUp() {

        GetMonsterFromDataBaseView data = new GetMonsterFromDataBaseView();
        monsterArrayList =  data.getData();
    }

    @Test
    void zeroNameInMonster() {



        int count = 0;

        for (Monster monster:monsterArrayList
             ) {
            if (monster.getMonsterName().equals("nothing at all")){
                count++;
            }
        }

        assertEquals(0,count);
    }
    @Test
    void oneNameInList(){
        boolean test = false;

        for (Monster monster: monsterArrayList
             ) {

            if (monster.getMonsterName().equals("Bulette")){
                test = true;

            }
        }
        assertTrue(test);

    }

    @Test
    void testForBehir(){
        boolean test = false;

        for (Monster monster: monsterArrayList
        ) {
            if (monster.getMonsterName().equals("Behir")){
                test = true;

            }
        }
        assertTrue(test);

    }

    @Test
    void sizeOfMonster(){
        assertEquals(796,monsterArrayList.size());
    }

    @Test
    void testForZuggtmoyChamberlain(){
        boolean test = false;

        for (Monster monster: monsterArrayList
        ) {
            if (monster.getMonsterName().equals("Zuggtmoy, Chamberlain")){
                test = true;

            }
        }
        assertTrue(test);
    }

    @Test
    void testForOneAbility(){
        boolean test = false;


        for (Monster monster: monsterArrayList
        ) {
            if (monster.getMonsterName().equals("Zuggtmoy, Bridesmaid") && monster.getMonsterAction().size()==1){
                test = true;
            }
        }
        assertTrue(test);
    }

    @Test
    void testForUnicornHas6Ability(){
        boolean test = false;


        for (Monster monster: monsterArrayList
        ) {
            if (monster.getMonsterName().equals("Unicorn") && monster.getMonsterAction().size()==6){
                test = true;
            }
        }
        assertTrue(test);

    }
}