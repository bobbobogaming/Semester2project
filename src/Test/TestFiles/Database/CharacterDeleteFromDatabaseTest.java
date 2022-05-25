package Test.TestFiles.Database;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Database.Adapters.CharacterDeleteFromDatabase;
import Database.Adapters.CharacterInsertIntoDatabase;
import Database.Adapters.SelectAllCharacterFromTableDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

class CharacterDeleteFromDatabaseTest {
    private UserID userID;
    private Stats stats;

    @BeforeEach
    void setUp() {
        userID = new UserID("Morten");
        stats = new Stats(1,2,4,5,6,7,8);
    }

    @Test
    void CharacterDelete() {
       //you need to run CharacterInsertIntoDatabaseTest before this
        String charName = "deletChartest";
        Character character = new Character(stats,charName,1,"Monk");
        CharacterInsertIntoDatabase characterInsertIntoDatabase = new CharacterInsertIntoDatabase();
        try {
            characterInsertIntoDatabase.InsertCharacterIntoDatabase(character,userID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        CharacterDeleteFromDatabase characterDeleteFromDatabase = new CharacterDeleteFromDatabase();
        characterDeleteFromDatabase.deleteCharacterFromDatabase(userID, charName);

        ArrayList<Character> characterArrayList;
        SelectAllCharacterFromTableDatabase select = new SelectAllCharacterFromTableDatabase();
        characterArrayList = select.getAllCharacterFromTableDatabase(userID);

        boolean test_name = false;

        for (Character charactertjek : characterArrayList)
        {
            if (charactertjek.getName().equals(charName))
            {
                test_name = true;
            }
        }
        assertFalse(test_name);
    }

    @Test
    void removeACharacterThatDontISInDatabase(){
        String charName = "deletChar3test";
        CharacterDeleteFromDatabase characterDeleteFromDatabase = new CharacterDeleteFromDatabase();
        characterDeleteFromDatabase.deleteCharacterFromDatabase(userID, charName);

        ArrayList<Character> characterArrayList;
        SelectAllCharacterFromTableDatabase select = new SelectAllCharacterFromTableDatabase();
        characterArrayList = select.getAllCharacterFromTableDatabase(userID);

        boolean test_name = false;

        for (Character charactertjek : characterArrayList)
        {
            if (charactertjek.getName().equals(charName))
            {
                test_name = true;
            }
        }
        assertFalse(test_name);
    }




}