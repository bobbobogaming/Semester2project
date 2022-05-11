package Server;

import Application.Client.Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TestClient {
  public static void main(String[] args) throws RemoteException,
      NotBoundException
  {
    Client dungeonMaster = new Client();
    Client player1 = new Client();
    player1.setUsername("Simon C");
    Client player2 = new Client();
    player2.setUsername("Simon L");

    dungeonMaster.createLobby(dungeonMaster);
    player1.connectToLobby(0, player1);
    player2.connectToLobby(0, player2);
  }
}
