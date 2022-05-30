package Test.TestFiles.Character;

import Application.MVVM.Core.ClientFactory;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Application.MVVM.View.CharacterSheet.CharacterViewModel;
import Test.TestrelatedFiles.ClientCharacterSheetTestClass;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class CharacterViewModelTest {
    private ClientCharacterSheetTestClass clientCharacterSheetTestClass;
    private CharacterViewModel characterViewModel;
    StringProperty characterClass, characterName, strength, level, dexterity, constitution, intelligence, wisdom, charisma, maxHp;


    @BeforeEach
    public void Setup() {
        clientCharacterSheetTestClass = new ClientCharacterSheetTestClass();
        characterViewModel = new CharacterViewModel(clientCharacterSheetTestClass);

        //Arrange
        characterClass = new SimpleStringProperty();
        characterName = new SimpleStringProperty();
        strength = new SimpleStringProperty();
        level = new SimpleStringProperty();
        dexterity = new SimpleStringProperty();
        constitution = new SimpleStringProperty();
        intelligence = new SimpleStringProperty();
        wisdom = new SimpleStringProperty();
        charisma = new SimpleStringProperty();
        maxHp = new SimpleStringProperty();

        characterViewModel.characterClassProperty().bindBidirectional(characterClass);
        characterViewModel.characterNameProperty().bindBidirectional(characterName);
        characterViewModel.strengthProperty().bindBidirectional(strength);
        characterViewModel.levelProperty().bindBidirectional(level);
        characterViewModel.dexterityProperty().bindBidirectional(dexterity);
        characterViewModel.constitutionProperty().bindBidirectional(constitution);
        characterViewModel.intelligenceProperty().bindBidirectional(intelligence);
        characterViewModel.wisdomProperty().bindBidirectional(wisdom);
        characterViewModel.charismaProperty().bindBidirectional(charisma);
        characterViewModel.maxHpProperty().bindBidirectional(maxHp);
    }


    @Test
    public void modifyerViewWithMinus1() {

        Method method = null;
        try {
            method = CharacterViewModel.class.getDeclaredMethod("setModStat", String.class);
        } catch (NoSuchMethodException e) {
            fail();
            throw new RuntimeException(e);
        }

        method.setAccessible(true);
        String result = null;
        try {
            result = (String) method.invoke(characterViewModel, "9");
        } catch (IllegalAccessException e) {
            fail();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals("-1", result);
    }

    @Test
    public void modifyerViewWithMinus19() {

        Method method = null;
        try {
            method = CharacterViewModel.class.getDeclaredMethod("setModStat", String.class);
        } catch (NoSuchMethodException e) {
            fail();
            throw new RuntimeException(e);
        }

        method.setAccessible(true);
        String result = null;
        try {
            result = (String) method.invoke(characterViewModel, "19");
        } catch (IllegalAccessException e) {
            fail();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals("+4", result);
    }

    @Test
    public void modifyerViewWithZero() {

        Method method = null;
        try {
            method = CharacterViewModel.class.getDeclaredMethod("setModStat", String.class);
        } catch (NoSuchMethodException e) {
            fail();
            throw new RuntimeException(e);
        }

        method.setAccessible(true);
        String result = null;
        try {
            result = (String) method.invoke(characterViewModel, "10");
        } catch (IllegalAccessException e) {
            fail();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals("+0", result);
    }

    @Test
    public void modifyerViewWith20() {

        Method method = null;
        try {
            method = CharacterViewModel.class.getDeclaredMethod("setModStat", String.class);
        } catch (NoSuchMethodException e) {
            fail();
            throw new RuntimeException(e);
        }

        method.setAccessible(true);
        String result = null;
        try {
            result = (String) method.invoke(characterViewModel, "20");
        } catch (IllegalAccessException e) {
            fail();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals("+5", result);
    }

    @Test
    public void modifyerViewWith19() {

        Method method = null;
        try {
            method = CharacterViewModel.class.getDeclaredMethod("setModStat", String.class);
        } catch (NoSuchMethodException e) {
            fail();
            throw new RuntimeException(e);
        }

        method.setAccessible(true);
        String result = null;
        try {
            result = (String) method.invoke(characterViewModel, "19");
        } catch (IllegalAccessException e) {
            fail();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals("+4", result);
    }

    @Test
    public void saveCharacteremptyinViewModel() { //Z

        //act
        strength.setValue("");
        level.setValue("");
        characterClass.setValue("");
        characterName.setValue("");
        dexterity.setValue("");
        constitution.setValue("");
        intelligence.setValue("");
        wisdom.setValue("");
        charisma.setValue("");
        maxHp.setValue("");


        characterViewModel.createCharacterSheet();
        ArrayList<Character> result = new ArrayList<>();

        //assert
        assertEquals(result, clientCharacterSheetTestClass.getCharacters());
    }

    @Test
    public void saveCharacterInDatabase() {

        //act
        strength.setValue("2");
        level.setValue("4");
        characterClass.setValue("Monk");
        characterName.setValue("BOB");
        dexterity.setValue("0");
        constitution.setValue("10");
        intelligence.setValue("44");
        wisdom.setValue("39");
        charisma.setValue("10");
        maxHp.setValue("50");


        characterViewModel.createCharacterSheet();
        ArrayList<Character> result = new ArrayList<>();
        result.add(new Character(new Stats(2, 0, 10, 44, 39, 10, 50), "BOB", 4, "Monk"));

        //assert
        assertEquals(result, clientCharacterSheetTestClass.getCharacters());
    }


    @Test
    public void saveManyCharacterTestWithSameName() {
        //act
        strength.setValue("2");
        level.setValue("4");
        characterClass.setValue("Monk");
        characterName.setValue("BOB");
        dexterity.setValue("0");
        constitution.setValue("10");
        intelligence.setValue("44");
        wisdom.setValue("39");
        charisma.setValue("10");
        maxHp.setValue("50");


        characterViewModel.createCharacterSheet();
        ArrayList<Character> result = new ArrayList<>();
        result.add(new Character(new Stats(2, 0, 10, 44, 39, 10, 50), "BOB", 4, "Monk"));

        strength.setValue("3");
        level.setValue("10");
        characterClass.setValue("Monk");
        characterName.setValue("BOB");
        dexterity.setValue("10");
        constitution.setValue("15");
        intelligence.setValue("44");
        wisdom.setValue("39");
        charisma.setValue("10");
        maxHp.setValue("50");


        characterViewModel.createCharacterSheet();
        //result.add(new Character(new Stats(3,10,15,44,39,10,50),"BOB",10,"Monk"));

        //assert
        assertEquals(result, clientCharacterSheetTestClass.getCharacters());

    }

    @Test
    public void deleteACharacter() {
        //act
        strength.setValue("2");
        level.setValue("4");
        characterClass.setValue("Monk");
        characterName.setValue("BOB");
        dexterity.setValue("0");
        constitution.setValue("10");
        intelligence.setValue("44");
        wisdom.setValue("39");
        charisma.setValue("10");
        maxHp.setValue("50");

        characterViewModel.createCharacterSheet();


        characterViewModel.removeCharacter(new Character(new Stats(2, 0, 10, 44, 39, 10, 50), "BOB", 4, "Monk"));

        ArrayList<Character> restult = new ArrayList<>();

        assertEquals(restult, clientCharacterSheetTestClass.getCharacters());
    }

    @Test
    public void deletemany() {
        //act
        strength.setValue("2");
        level.setValue("4");
        characterClass.setValue("Monk");
        characterName.setValue("BOB");
        dexterity.setValue("0");
        constitution.setValue("10");
        intelligence.setValue("44");
        wisdom.setValue("39");
        charisma.setValue("10");
        maxHp.setValue("50");

        characterViewModel.createCharacterSheet();




        strength.setValue("3");
        level.setValue("10");
        characterClass.setValue("Monk");
        characterName.setValue("BOB1");
        dexterity.setValue("10");
        constitution.setValue("15");
        intelligence.setValue("44");
        wisdom.setValue("39");
        charisma.setValue("10");
        maxHp.setValue("50");

        characterViewModel.createCharacterSheet();


        characterViewModel.removeCharacter(new Character(new Stats(2, 0, 10, 44, 39, 10, 50), "BOB", 4, "Monk"));
        characterViewModel.removeCharacter(new Character(new Stats(3, 10, 15, 44, 39, 10, 50), "BOB1", 10, "Monk"));

        //expected because it has removed the two
        ArrayList<Character> result = new ArrayList<>();

        assertEquals(result, clientCharacterSheetTestClass.getCharacters());
    }

    @Test
    public void forgotToAddAValue(){
        //act z because this is where you forgot a value
        strength.setValue("2");
        level.setValue("4");
        characterClass.setValue("Monk");
        characterName.setValue("BOB");
        dexterity.setValue("0");
        constitution.setValue("10");
        intelligence.setValue("44");
        wisdom.setValue("39");
        charisma.setValue("");
        maxHp.setValue("50");

        characterViewModel.createCharacterSheet();



        ArrayList<Character> result = new ArrayList<>();

        assertEquals(result, clientCharacterSheetTestClass.getCharacters());
    }

    @Test
    public void playAsCharacterStringNotChoosen(){
        //act
        strength.setValue("2");
        level.setValue("4");
        characterClass.setValue("Monk");
        characterName.setValue("BOB");
        dexterity.setValue("0");
        constitution.setValue("10");
        intelligence.setValue("44");
        wisdom.setValue("39");
        charisma.setValue("10");
        maxHp.setValue("50");

        characterViewModel.createCharacterSheet();

        ArrayList<Character> characterArrayList =  clientCharacterSheetTestClass.getCharacters();


        characterViewModel.updatePlayAsCharacterButton(characterArrayList.get(0));
    }


}