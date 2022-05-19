package Application.MVVM.Model.CharacterSheet;

import Application.MVVM.Model.character.Character;

import java.util.ArrayList;

public interface ICharacterSheetModel
{
  void makeCharacter(String name,int str,int dex,int con,int intel,int wis,int cha,int maxHp);
  ArrayList<Character> getCharacters();
}
