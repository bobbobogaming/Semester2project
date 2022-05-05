package Application.MVVM.Model.CharacterSheet;

import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.character.Stats;
import Util.IClientRMI;

import java.rmi.RemoteException;

public class CharacterSheetModel implements ICharacterSheetModel
{
  IClientRMI clientRMI;

  public CharacterSheetModel(IClientRMI clientRMI)
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
