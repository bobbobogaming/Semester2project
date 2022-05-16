package Shared;

import Application.MVVM.Model.character.Character;
import Application.MVVM.Model.monster.Monster;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IClientModel extends Remote
{
  void makeCharacter(Character character) throws RemoteException;
  Character getCharacter(String name) throws RemoteException;
  String getUsername() throws RemoteException;
  void updateMonsterTable(ArrayList<Monster> monsters) throws RemoteException;
}
