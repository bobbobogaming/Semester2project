package Application.Client;

import Application.MVVM.Model.character.Character;
import Util.IClientRMI;
import Util.IServer;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientRMI implements IClientRMI
{
  private final IServer server;

  public ClientRMI() throws RemoteException, NotBoundException
  {
    UnicastRemoteObject.exportObject(this,0);
    Registry registry = LocateRegistry.getRegistry("localhost",1099);
    System.out.println(registry.lookup("Server"));
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
