package Test.TestrelatedFiles;

import Application.Client.ClientCharacterSheet;
import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;

import java.util.ArrayList;

public class ClientCharacterSheetTestClass implements ClientCharacterSheet {

    ArrayList<Character> characters;
    UserID userIDS;

    public ClientCharacterSheetTestClass() {
        this.characters = new ArrayList<>();
        this.userIDS = new UserID("test");
    }

    @Override
    public void makeCharacter(String name, int str, int dex, int con, int intel, int wis, int cha, int lvl, String cClass, int maxHp) {
        characters.add(new Character(new Stats(str,dex,con,intel,wis,cha,maxHp),name,maxHp,cClass));
    }

    @Override
    public void setCurrentCharacter(Character character) {

    }

    @Override
    public UserID getUserID() {
        return userIDS;
    }

    @Override
    public ArrayList<Character> getCharacters() {
        return characters;
    }

    @Override
    public void deleteCharacter(Character character) {
    characters.remove(character);
    }
}
