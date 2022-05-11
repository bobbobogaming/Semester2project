package Application.MVVM.Core;

import Application.Client.ClientRMI;
import Util.IClientRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientFactory
{
  private static ClientFactory instance;
  private static Lock lock = new ReentrantLock();
  private ClientRMI clientRMI;

  private ClientFactory(){  }

  public static ClientFactory getInstance()
  {
    if (instance == null){
      synchronized (lock){
        if (instance == null) instance = new ClientFactory();
      }
    }
    return instance;
  }

  public ClientRMI getClientRMI()
  {
    if (clientRMI == null)
    {
      synchronized (lock){
        if (clientRMI == null)
        {
          try
          {
            clientRMI = new ClientRMI();
          }
          catch (RemoteException | NotBoundException e)
          {
            e.printStackTrace();
          }
        }
      }
    }
    return clientRMI;
  }
}
