package Application.MVVM.Core;

import Application.MVVM.View.CharacterSheet.CharacterViewModel;
import Application.MVVM.View.Lobby.Dm.DMLobbyViewModel;
import Application.MVVM.View.Lobby.Dm.MonsterSearch.SelectMonsterViewModel;
import Application.MVVM.View.Lobby.Player.PlayerLobbyViewModel;
import Application.MVVM.View.Lobby.Player.characterselect.SelectCharacterViewModel;
import Application.MVVM.View.Lobby.Root.LobbyViewModel;
import Application.MVVM.View.Login.LoginViewModel;
import Application.MVVM.View.TabPane.TabViewModel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewModelFactory
{
  private final PlayerLobbyViewModel playerLobbyViewModel;
  private final CharacterViewModel characterViewModel;
  private final LoginViewModel loginViewModel;
  private final LobbyViewModel lobbyViewModel;
  private final DMLobbyViewModel dmLobbyViewModel;
  private final SelectMonsterViewModel selectMonsterViewModel;
  private final TabViewModel tabViewModel;

  private final SelectCharacterViewModel selectCharacterViewModel;

  private static ViewModelFactory instance;
  private static Lock lock = new ReentrantLock();

  public static ViewModelFactory getInstance()
  {
    if (instance == null){
      synchronized (lock){
        if (instance == null) instance = new ViewModelFactory(ModelFactory.getInstance(),ClientFactory.getInstance());
      }
    }
    return instance;
  }

  private ViewModelFactory(ModelFactory modelFactory, ClientFactory clientFactory)
  {
    characterViewModel = new CharacterViewModel(modelFactory.getCharacterSheetModel(
        clientFactory.getClient()),clientFactory.getClient());
    loginViewModel = new LoginViewModel(clientFactory.getClient());
    lobbyViewModel = new LobbyViewModel(clientFactory.getClient());
    dmLobbyViewModel = new DMLobbyViewModel(clientFactory.getClient());
    playerLobbyViewModel = new PlayerLobbyViewModel(clientFactory.getClient());
    selectMonsterViewModel = new SelectMonsterViewModel(clientFactory.getClient());
    selectCharacterViewModel = new SelectCharacterViewModel(clientFactory.getClient());
    tabViewModel = new TabViewModel(this);
  }

  public CharacterViewModel getCharacterViewModel()
  {
    return characterViewModel;
  }

  public LoginViewModel getLoginViewModel(ViewHandler viewHandler)
  {
    loginViewModel.addPropertyChangeListener(viewHandler);
    return loginViewModel;
  }

  public LobbyViewModel getLobbyViewModel()
  {
    return lobbyViewModel;
  }

  public DMLobbyViewModel getDmLobbyViewModel()
  {
    return dmLobbyViewModel;
  }

  public PlayerLobbyViewModel getPlayerLobbyViewModel() {
    return playerLobbyViewModel;
  }

  public SelectMonsterViewModel getSelectMonsterViewModel() {
    return selectMonsterViewModel;
  }

  public SelectCharacterViewModel getSelectCharacterViewModel() {
    return selectCharacterViewModel;
  }

  public TabViewModel getTabViewModel() {
    return tabViewModel;
  }
}
