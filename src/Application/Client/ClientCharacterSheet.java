package Application.Client;

import Application.MVVM.Model.character.Character;

import java.util.ArrayList;

public interface ClientCharacterSheet {
  void makeCharacter(String name,int str, int dex, int con, int intel,
      int wis, int cha, int lvl, String cClass, int maxHp);
  void setCurrentCharacter(Character character);
  UserID getUserID();
  ArrayList<Character> getCharacters();
  void deleteCharacter(Character character);
}
