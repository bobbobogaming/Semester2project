package Test.Database;

import Application.MVVM.Model.monster.Monster;
import Database.Adapters.GetMonsterFromDataBaseView;
import Database.DataBaseConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GetMonsterFromDataBaseViewTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getData() {
        
        GetMonsterFromDataBaseView data = new GetMonsterFromDataBaseView();
        ArrayList<Monster> monsterArrayList =  data.getData();

        for (Monster :
             ) {
            
        }
        
    }
}