package Application.MVVM.View.Lobby.Dm.charactersheet;

import Application.Client.UserID;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DMCharacterSheetViewFactory {

  private final Map<String, DMCharacterSheetViewModel> dmCharacterSheetViewModels;
  private static final Lock lock = new ReentrantLock();

  private static DMCharacterSheetViewFactory instance;

  public static DMCharacterSheetViewFactory getInstance(){
    if (instance == null){
      synchronized (lock){
        if (instance == null) instance = new DMCharacterSheetViewFactory();
      }
    }
    return instance;
  }

  private DMCharacterSheetViewFactory() {
    dmCharacterSheetViewModels = new HashMap<>();
  }

  public DMCharacterSheetViewModel getCharacterSheetViewModelInstance(UserID userID) {
    String key = userID.getCurrentCharacter().getName() + userID.getName();
    if (dmCharacterSheetViewModels.get(key) == null)
    {
      synchronized (lock){
        if (dmCharacterSheetViewModels.get(key) == null)
        {
          dmCharacterSheetViewModels.put(key, new DMCharacterSheetViewModel(userID));
        }
      }
    }
    return dmCharacterSheetViewModels.get(key);
  }
}
