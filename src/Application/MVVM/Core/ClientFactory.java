package Application.MVVM.Core;

import Application.Client.Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientFactory
{
  private static ClientFactory instance;
  private static Lock lock = new ReentrantLock();
  private Client client;

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

  public Client getClient() {
    if (client == null)
    {
      synchronized (lock){
        if (client == null)
        {
          try
          {
            client = new Client();
          }
          catch (RemoteException | NotBoundException e)
          {
            e.printStackTrace();
          }
        }
      }
    }
    return client;
  }
}
