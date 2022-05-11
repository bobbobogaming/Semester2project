package Database;

import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;

import java.util.ArrayList;

public class CharacterInsertIntoDatabase {

    private IDatabaseConnector IDatabaseConnector;

    public CharacterInsertIntoDatabase() {
        IDatabaseConnector = new DataBaseConnector();
    }


    public void InsertCharacterIntoDatabase(Character character, String user)
    {   //this is an adapter that converts a user and character into an array that can be saved in the database

        int constitution, wisdom, dexterity, charisma, intelligence, strength;
        String characterName;

        Stats stats= character.getStats();

        //temp value
        int level= 0;
        int maxHp = 0;
        String clas = "Warrior";

        //unwrapper stats
        characterName = character.getName();
        constitution = stats.getConstitution();
        wisdom = stats.getWisdom();
        dexterity = stats.getDexterity();
        charisma = stats.getCharisma();
        intelligence = stats.getIntelligence();
        strength = stats.getStrength();


        //wrapper in a Arraylist
        ArrayList<Object> characterInfo = new ArrayList<>();
        characterInfo.add(constitution);
        characterInfo.add(wisdom);
        characterInfo.add(dexterity);
        characterInfo.add(charisma);
        characterInfo.add(intelligence);
        characterInfo.add(strength);
        characterInfo.add(user);
        characterInfo.add(characterName);
        characterInfo.add(level);
        characterInfo.add(clas);
        characterInfo.add(maxHp);

        IDatabaseConnector.addDataToDataBase("character",characterInfo);
    }
}
