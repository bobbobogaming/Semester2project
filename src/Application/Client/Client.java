package Application.Client;

import Application.MVVM.Model.character.Character;
import Util.IClientModel;
import Util.IServerModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client implements IClientModel
{
  private final IServerModel server;
  private String username;

  public Client() throws RemoteException, NotBoundException
  {
    UnicastRemoteObject.exportObject(this,0);
    Registry registry = LocateRegistry.getRegistry("localhost",1099);
    server = (IServerModel) registry.lookup("Server");
  }
  @Override public String getUsername() throws RemoteException {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  @Override public void createLobby(IClientModel client) throws RemoteException {
    server.createLobby(this);
  }

  @Override public void connectToLobby(int lobbyId, IClientModel client) throws RemoteException {
    server.connectToLobby(lobbyId, client);
  }

}
