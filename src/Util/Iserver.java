package Util;

import MVVM.Model.Charachter;

public interface Iserver {
    void makeCharacter(Character character);
    Charachter getCharacter(String name);
}
