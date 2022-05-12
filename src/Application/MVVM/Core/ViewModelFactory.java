package Application.MVVM.Core;

import Application.MVVM.View.CharacterSheet.CharacterViewModel;
import Application.MVVM.View.Lobby.Dm.DMLobbyViewModel;
import Application.MVVM.View.Lobby.Player.PlayerLobbyViewModel;
import Application.MVVM.View.Lobby.Root.LobbyViewModel;
import Application.MVVM.View.Login.LoginViewModel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewModelFactory
{
  private final PlayerLobbyViewModel playerLobbyViewModel;
  private CharacterViewModel characterViewModel;
  private LoginViewModel loginViewModel;
  private LobbyViewModel lobbyViewModel;
  private DMLobbyViewModel dmLobbyViewModel;

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
        clientFactory.getClientRMI()),clientFactory.getClientRMI());
    loginViewModel = new LoginViewModel(clientFactory.getClientRMI());
    lobbyViewModel = new LobbyViewModel(clientFactory.getClientRMI());
    dmLobbyViewModel = new DMLobbyViewModel(clientFactory.getClientRMI());
    playerLobbyViewModel = new PlayerLobbyViewModel(clientFactory.getClientRMI());
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
}
