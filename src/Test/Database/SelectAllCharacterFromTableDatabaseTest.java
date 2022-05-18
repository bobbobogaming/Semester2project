package Test.Database;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Database.Adapters.CharacterDeleteFromDatabase;
import Database.Adapters.CharacterInsertIntoDatabase;
import Database.Adapters.SelectAllCharacterFromTableDatabase;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SelectAllCharacterFromTableDatabaseTest {
    @org.junit.jupiter.api.Test
    void characters() {
        CharacterInsertIntoDatabase characterInsertIntoDatabase = new CharacterInsertIntoDatabase();




        //you need to run CharacterInsertIntoDatabaseTest before this
        SelectAllCharacterFromTableDatabase selectAllCharacterFromTableDatabase = new SelectAllCharacterFromTableDatabase();


        String charname = "selecttest";

        Stats stats =new Stats(10,11,12,13,14,15,14);
        Character character = new Character(stats,charname);
        UserID userID = new UserID("Morten");

        try {
            characterInsertIntoDatabase.InsertCharacterIntoDatabase(character,userID);
        } catch (SQLException e) {
            CharacterDeleteFromDatabase deleteFromDatabase = new CharacterDeleteFromDatabase();
            deleteFromDatabase.deleteCharacterFromDatabase(userID, charname);
            throw new RuntimeException(e);
        }

        boolean test = false;
        for (Character character1:selectAllCharacterFromTableDatabase.getAllCharacterFromTableDatabase(new UserID("Morten"))
             ) {
            if(character1.equals(character)){
                test = true;
            }
        }


        assertTrue(test);

        CharacterDeleteFromDatabase deleteFromDatabase = new CharacterDeleteFromDatabase();
        deleteFromDatabase.deleteCharacterFromDatabase(userID, charname);

    }
}