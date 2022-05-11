package Application.Client;

import Application.MVVM.Model.character.Character;
import Util.ClientRMI;
import Util.IServer;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client implements ClientRMI
{
  private final IServer server;
  
  public Client() throws RemoteException, NotBoundException
  {
    UnicastRemoteObject.exportObject(this,0);
    Registry registry = LocateRegistry.getRegistry("localhost",1099);
    server = (IServer) registry.lookup("Server");
  }

  @Override public void makeCharacter(Character character)
      throws RemoteException
  {
    server.saveCharacter(character);
  }

  @Override public Character getCharacter(String name) throws RemoteException
  {
    return server.getCharacter(name);
  }
}
