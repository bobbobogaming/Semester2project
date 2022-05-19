package Application.Client;

import Application.MVVM.Model.character.Character;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientCharacterSheet {
  void makeCharacter(Character character) throws SQLException;
  void setCurrentCharacter(Character character);
  ArrayList<Character> getCharacters();
}
