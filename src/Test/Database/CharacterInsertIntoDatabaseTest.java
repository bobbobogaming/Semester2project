package Test.Database;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Database.Adapters.AdduserToDataBase;
import Database.Adapters.CharacterDeleteFromDatabase;
import Database.Adapters.CharacterInsertIntoDatabase;
import Database.Adapters.SelectAllCharacterFromTableDatabase;
import org.junit.jupiter.api.AfterAll;
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
    private Stats stats;
    private String clas;

    @BeforeEach
    void setUp() {
        stats = new Stats(10, 11, 12, 13, 14, 15,91);
        charname = "Bobby";
        clas = "Monk";
        character = new Character(stats, charname,2,clas);
        userID = new UserID("Morten");
        AdduserToDataBase adduserToDataBase = new AdduserToDataBase();

        try {
            adduserToDataBase.addUser(userID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        insert = new CharacterInsertIntoDatabase();
        try {
            insert.InsertCharacterIntoDatabase(character, userID);
        } catch (SQLException e) {
            System.out.println("kj");
            throw new RuntimeException(e);
        }
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

        Character allan = new Character(new Stats(2, 5, 2, 5, 5, 2,15), "Allan",1,"Monk");
        try {
            insert.InsertCharacterIntoDatabase(allan,userID);
        } catch (SQLException e) {
            fail();
            throw new RuntimeException(e);
        }
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
        Character user = new Character(new Stats(2, 5, 2, 5, 5, 2,14), charname,2,"Wizard");

        assertThrows(org.postgresql.util.PSQLException.class,()->insert.InsertCharacterIntoDatabase(user,userID));

/*
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
*/
    }

    @Test
    void insertIntoDifferentUsersSameChacter() {

        String userename = "hej forskel navn";

        UserID userID1 = new UserID(userename);

        AdduserToDataBase adduserToDataBase = new AdduserToDataBase();

        try {
            adduserToDataBase.addUser(userID1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            insert.InsertCharacterIntoDatabase(character, userID1);
            assertTrue(true);
            CharacterDeleteFromDatabase deleteFromDatabase = new CharacterDeleteFromDatabase();
            deleteFromDatabase.deleteCharacterFromDatabase(userID1, charname);
        } catch (SQLException e) {
            e.printStackTrace();
            CharacterDeleteFromDatabase deleteFromDatabase = new CharacterDeleteFromDatabase();
            deleteFromDatabase.deleteCharacterFromDatabase(userID1, charname);
            fail();
            throw new RuntimeException(e);
        }

    }

    @Test
    void outOfBoundsTestOnName(){
        String username = "";
        for (int i = 0; i <256 ; i++) {
            username += "a";
        }

        Character character1 = new Character(stats,username, 1,"Monk");

        assertThrows(PSQLException.class,()->insert.InsertCharacterIntoDatabase(character1,userID));

    }

    @Test
    void maxInBoundName(){
        String charename = "";
        for (int i = 0; i <255 ; i++) {
            charename += "m";
        }
        Character character1 = new Character(stats,charename, 1,"Monk");

        try {
            insert.InsertCharacterIntoDatabase(character1,userID);
            assertTrue(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CharacterDeleteFromDatabase deleteFromDatabase = new CharacterDeleteFromDatabase();
        deleteFromDatabase.deleteCharacterFromDatabase(userID, charename);
    }

    @Test
    void minInBoundName(){
        String charenam = "";

        Character character1 = new Character(stats,charenam, 1, "Monk");

        try {
            insert.InsertCharacterIntoDatabase(character1,userID);
            assertTrue(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CharacterDeleteFromDatabase deleteFromDatabase = new CharacterDeleteFromDatabase();
        deleteFromDatabase.deleteCharacterFromDatabase(userID, charenam);

    }

    @Test
    void insertedhasclass(){  //TDD
        ArrayList<Character> characterArrayList;
        SelectAllCharacterFromTableDatabase select = new SelectAllCharacterFromTableDatabase();
        characterArrayList = select.getAllCharacterFromTableDatabase(userID);

        boolean testInsert = false;

        for (Character insertCharacter : characterArrayList) {
            if (insertCharacter.getName().equals(charname) && insertCharacter.getClas().equals(clas)) {
                testInsert = true;
            }
        }
        assertTrue(testInsert);
    }



    @AfterEach
    void removeDuplicateUsers()
    {
        CharacterDeleteFromDatabase deleteFromDatabase = new CharacterDeleteFromDatabase();
        deleteFromDatabase.deleteCharacterFromDatabase(userID, charname);
    }


}