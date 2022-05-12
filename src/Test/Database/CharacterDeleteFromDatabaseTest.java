package Test.Database;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Database.Adapters.CharacterDeleteFromDatabase;
import Database.Adapters.CharacterInsertIntoDatabase;

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
    }

}