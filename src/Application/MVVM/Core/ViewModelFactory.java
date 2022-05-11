package Application.MVVM.Core;

import Application.MVVM.View.CharacterSheet.CharacterViewModel;
import Application.MVVM.View.Login.LoginViewModel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewModelFactory
{
  private CharacterViewModel characterViewModel;
  private LoginViewModel loginViewModel;

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
  }

  public CharacterViewModel getCharacterViewModel()
  {
    return characterViewModel;
  }

  public LoginViewModel getLoginViewModel(ViewHandler viewHandler)
  {
    System.out.println(loginViewModel);
    loginViewModel.addPropertyChangeListener(viewHandler);
    return loginViewModel;
  }
}
