package Application.MVVM.Core;

import Application.Client.ClientCharacterSheet;
import Application.MVVM.Model.CharacterSheet.CharacterSheetModel;
import Application.MVVM.Model.CharacterSheet.ICharacterSheetModel;
import Shared.IClientModel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ModelFactory
{
  private static ModelFactory instance;
  private static Lock lock = new ReentrantLock();
  private ICharacterSheetModel characterSheetModel;

  private ModelFactory(){}

  public static ModelFactory getInstance()
  {
    if (instance == null){
      synchronized (lock){
        if (instance == null) instance = new ModelFactory();
      }
    }
    return instance;
  }

  public ICharacterSheetModel getCharacterSheetModel(
      ClientCharacterSheet client)
  {
    if (characterSheetModel == null){
      synchronized (lock){
        if (characterSheetModel == null) characterSheetModel = new CharacterSheetModel(client);
      }
    }
    return characterSheetModel;
  }
}
