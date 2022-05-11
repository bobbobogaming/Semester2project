package Application.MVVM.Model.CharacterSheet;

import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Util.IClientModel;

import java.rmi.RemoteException;

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
      clientRMI.makeCharacter(new Character(new Stats(str,dex,con,intel,wis,cha),name));
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }
}
