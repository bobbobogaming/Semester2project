package Test.Database;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Database.Adapters.CharacterDeleteFromDatabase;
import Database.Adapters.CharacterInsertIntoDatabase;
import Database.Adapters.SelectAllCharacterFromTableDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CharacterDeleteFromDatabaseTest {
    private String name;
    private UserID userID;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        name = "something";
        userID = new UserID("Morten");
    }

    @org.junit.jupiter.api.Test
    void CharacterDelete() {
       //you need to run CharacterInsertIntoDatabaseTest before this
        CharacterDeleteFromDatabase characterDeleteFromDatabase = new CharacterDeleteFromDatabase();
        characterDeleteFromDatabase.deleteCharacterFromDatabase(userID, name);

        ArrayList<Character> characterArrayList;
        SelectAllCharacterFromTableDatabase select = new SelectAllCharacterFromTableDatabase();
        characterArrayList = select.getAllCharacterFromTableDatabase(userID);

        boolean test_name = false;

        for (Character character : characterArrayList)
        {
            if (character.getName().equals(name))
            {
                test_name = true;
            }
        }
        assertTrue(test_name);
    }



}