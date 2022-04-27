package Server.ServerModel;

import MVVM.Model.Charachter;

public interface IServerModel {
    void makeCharcter(Character character);
    Charachter getCharcter(String name);
}
