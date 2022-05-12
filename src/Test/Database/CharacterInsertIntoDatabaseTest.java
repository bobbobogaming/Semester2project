package Test.Database;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Database.Adapters.CharacterInsertIntoDatabase;

class CharacterInsertIntoDatabaseTest {
    private Character character;
    private UserID userID;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Stats stats =new Stats(10,11,12,13,14,15);
        character = new Character(stats,"something");
        Double random = Math.random();
        userID = new UserID("Morten");
    }

    @org.junit.jupiter.api.Test
    void insertCharacterIntoDatabase() {
        CharacterInsertIntoDatabase characterInsertIntoDatabase = new CharacterInsertIntoDatabase();
        characterInsertIntoDatabase.InsertCharacterIntoDatabase(character,userID);
    }
}