package Application.MVVM.Core;

import Application.MVVM.View.CharacterSheet.CharacterViewModel;
import Application.MVVM.View.Lobby.Dm.DMLobbyViewModel;
import Application.MVVM.View.Lobby.Dm.MonsterSearch.SelectMonsterViewModel;
import Application.MVVM.View.Lobby.Player.PlayerLobbyViewModel;
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

  private static ViewModelFactory instance;
  private static final Lock lock = new ReentrantLock();

  public static ViewModelFactory getInstance()
  {
    if (instance == null){
      synchronized (lock){
        if (instance == null) instance = new ViewModelFactory(ClientFactory.getInstance());
      }
    }
    return instance;
  }

  private ViewModelFactory(ClientFactory clientFactory)
  {
    characterViewModel = new CharacterViewModel(clientFactory.getClient());
    loginViewModel = new LoginViewModel(clientFactory.getClient());
    lobbyViewModel = new LobbyViewModel(clientFactory.getClient());
    dmLobbyViewModel = new DMLobbyViewModel(clientFactory.getClient());
    playerLobbyViewModel = new PlayerLobbyViewModel(clientFactory.getClient());
    selectMonsterViewModel = new SelectMonsterViewModel(clientFactory.getClient());
    tabViewModel = new TabViewModel(this,clientFactory.getClient());
  }

  public CharacterViewModel getCharacterViewModel()
  {
    return characterViewModel;
  }

  public LoginViewModel getLoginViewModel()
  {
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

  public TabViewModel getTabViewModel() {
    return tabViewModel;
  }
}
