package Test.Database;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Database.Adapters.AdduserToDataBase;
import Database.Adapters.CharacterDeleteFromDatabase;
import Database.Adapters.CharacterInsertIntoDatabase;
import Database.Adapters.SelectAllCharacterFromTableDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import java.lang.reflect.Array;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

class CharacterInsertIntoDatabaseTest {
    private Character character;
    private UserID userID;

    private String charname;

    private CharacterInsertIntoDatabase insert;

    @BeforeEach
    void setUp() {
        Stats stats = new Stats(10, 11, 12, 13, 14, 15);
        charname = "Bobby";
        character = new Character(stats, charname);
        userID = new UserID("Morten");
        AdduserToDataBase adduserToDataBase = new AdduserToDataBase();

        adduserToDataBase.addUser(userID);


        insert = new CharacterInsertIntoDatabase();
        insert.InsertCharacterIntoDatabase(character, userID);
    }

    @Test
    void insertCharacterIntoDatabase() {


        ArrayList<Character> characterArrayList;
        SelectAllCharacterFromTableDatabase select = new SelectAllCharacterFromTableDatabase();
        characterArrayList = select.getAllCharacterFromTableDatabase(userID);

        boolean testInsert = false;

        for (Character insertCharacter : characterArrayList) {
            if (insertCharacter.getName().equals(charname)) {
                testInsert = true;
            }
        }
        assertTrue(testInsert);
    }


    @Test
    void duplicateUserNameDifferentCharacterName() {

        Character allan = new Character(new Stats(2, 5, 2, 5, 5, 2), "Allan");
        insert.InsertCharacterIntoDatabase(allan,userID);
        ArrayList<Character> characterArrayList;
        SelectAllCharacterFromTableDatabase select = new SelectAllCharacterFromTableDatabase();
        characterArrayList = select.getAllCharacterFromTableDatabase(userID);


        boolean duplicateNameTest = false;

        for (Character character : characterArrayList) {
            if (character.getName().equals("Allan")) {
                duplicateNameTest = true;
            }
        }

        assertTrue(duplicateNameTest);

        CharacterDeleteFromDatabase deleteFromDatabase = new CharacterDeleteFromDatabase();
        deleteFromDatabase.deleteCharacterFromDatabase(userID, "Allan");
    }

    @Test
    void insertMultipleCharacterWithSameNameSameUserIdExpectedError()
    {
        Character user = new Character(new Stats(2, 5, 2, 5, 5, 2), charname);


        ArrayList<Character> characterArrayList;
        SelectAllCharacterFromTableDatabase select = new SelectAllCharacterFromTableDatabase();
        characterArrayList = select.getAllCharacterFromTableDatabase(userID);

        boolean insertMultipleCharacterWithSameNameSameUserId = false;

        for (Character character : characterArrayList) {
            if (character.getName().equals("Allan")) {
                insertMultipleCharacterWithSameNameSameUserId = true;
            }
        }

        assertTrue(insertMultipleCharacterWithSameNameSameUserId);

    }


    @AfterEach
    void removeDuplicateUsers()
    {
        CharacterDeleteFromDatabase deleteFromDatabase = new CharacterDeleteFromDatabase();
        deleteFromDatabase.deleteCharacterFromDatabase(userID, charname);
    }


}