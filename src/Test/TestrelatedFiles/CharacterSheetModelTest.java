package Test.TestrelatedFiles;

import Application.MVVM.Model.CharacterSheet.ICharacterSheetModel;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;

import java.util.ArrayList;

public class CharacterSheetModelTest implements ICharacterSheetModel {

    ArrayList<Character> characterArrayList;

    public CharacterSheetModelTest() {
        this.characterArrayList = new ArrayList<>();
    }

    @Override
    public void makeCharacter(String name, int str, int dex, int con, int intel, int wis, int cha, int lvl, String cClass, int maxHp) {
        Stats stats = new Stats(str,dex,con,intel,wis,cha,maxHp);
        characterArrayList.add(new Character(stats,name,lvl,cClass));
    }

    @Override
    public ArrayList<Character> getCharacters() {
        return getCharacters();
    }
}
