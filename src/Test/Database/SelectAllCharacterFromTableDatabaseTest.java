package Test.Database;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Database.Adapters.CharacterDeleteFromDatabase;
import Database.Adapters.SelectAllCharacterFromTableDatabase;

import static org.junit.jupiter.api.Assertions.*;

class SelectAllCharacterFromTableDatabaseTest {
    @org.junit.jupiter.api.Test
    void characters() {
        //you need to run CharacterInsertIntoDatabaseTest before this
        SelectAllCharacterFromTableDatabase selectAllCharacterFromTableDatabase = new SelectAllCharacterFromTableDatabase();


        Stats stats =new Stats(10,11,12,13,14,15,10);
        Character character = new Character(stats,"something");
        UserID userID = new UserID("Morten");




        assertEquals( selectAllCharacterFromTableDatabase.getAllCharacterFromTableDatabase(new UserID("Morten")).get(0),character);

    }
}