package Test.Database;

import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;

import static org.junit.jupiter.api.Assertions.*;

class CharacterInsertIntoDatabaseTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Stats stats =new Stats(10,11,12,13,14,15);
        Character character = new Character(stats,"something");
    }

    @org.junit.jupiter.api.Test
    void insertCharacterIntoDatabase() {


    }
}