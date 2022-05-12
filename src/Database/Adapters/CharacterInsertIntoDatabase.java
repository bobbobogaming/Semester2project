package Database.Adapters;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Database.DataBaseConnector;
import Database.IDatabaseConnector;

import java.util.ArrayList;

public class CharacterInsertIntoDatabase {

    private IDatabaseConnector iDatabaseConnector;

    public CharacterInsertIntoDatabase() {
        iDatabaseConnector = new DataBaseConnector();
    }


    public void InsertCharacterIntoDatabase(Character character, UserID user)
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


        //wrapper in an Arraylist
        ArrayList<Object> characterInfo = new ArrayList<>();
        characterInfo.add(constitution);
        characterInfo.add(wisdom);
        characterInfo.add(dexterity);
        characterInfo.add(charisma);
        characterInfo.add(intelligence);
        characterInfo.add(strength);
        characterInfo.add(user.getName());
        characterInfo.add(characterName);
        characterInfo.add(level);
        characterInfo.add(clas);
        characterInfo.add(maxHp);

        iDatabaseConnector.addDataToDataBase("character",characterInfo);
    }
}
