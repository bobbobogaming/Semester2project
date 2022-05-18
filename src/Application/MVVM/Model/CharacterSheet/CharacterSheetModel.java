package Application.MVVM.Model.CharacterSheet;

import Application.Client.UserID;
import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Shared.IClientModel;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class CharacterSheetModel implements ICharacterSheetModel
{
  IClientModel clientRMI;

  public CharacterSheetModel(IClientModel clientRMI)
  {
    this.clientRMI = clientRMI;
  }

  @Override public void makeCharacter(String name,int str, int dex, int con, int intel,
      int wis, int cha)
  {
    try
    {
      clientRMI.makeCharacter(new Character(new Stats(str,dex,con,intel,wis,cha,15),name));
    }
    catch (RemoteException | SQLException e)
    {
      e.printStackTrace();
    }
  }
}
