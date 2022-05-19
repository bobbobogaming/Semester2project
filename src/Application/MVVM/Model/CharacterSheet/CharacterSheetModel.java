package Application.MVVM.Model.CharacterSheet;

import Application.Client.ClientCharacterSheet;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Shared.IClientModel;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CharacterSheetModel implements ICharacterSheetModel
{
  ClientCharacterSheet client;

  public CharacterSheetModel(ClientCharacterSheet client)
  {
    this.client = client;
  }

  @Override public void makeCharacter(String name,int str, int dex, int con, int intel,
      int wis, int cha, int maxHp)
  {
    try
    {
      client.makeCharacter(new Character(new Stats(str,dex,con,intel,wis,cha,maxHp),name));
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public ArrayList<Character> getCharacters() {
    return client.getCharacters();
  }
}
