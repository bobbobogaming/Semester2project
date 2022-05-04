package Server.ServerModel;

import Application.MVVM.Model.Character;

import java.rmi.RemoteException;

public interface IServerModel {
    void makeCharacter(Character character);
    Character getCharacter(String name);
}
