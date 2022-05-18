package Application.MVVM.Model.initWrapper;

import Application.MVVM.Model.monster.Monster;
import Application.MVVM.Model.character.Character;

import java.io.Serializable;
import java.util.Random;

public class InitWrapper implements Serializable, Comparable<InitWrapper> {
  private int init;
  private String name;
  private int hp;
  private int ac;

  public InitWrapper(Monster monster){
    init = new Random().nextInt(20);
    name = monster.getMonsterName();
    hp = monster.getMaxHP();
    ac = monster.getAc();
  }

  public InitWrapper(Character character){
    init = new Random().nextInt(20);
    name = character.getName();
    hp = 10;
    ac = 10;
  }

  public void setInit(int init) {
    this.init = init;
  }

  public int getInit() {
    return init;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public int getHp() {
    return hp;
  }

  public void setAc(int ac) {
    this.ac = ac;
  }

  public int getAc() {
    return ac;
  }

  @Override public boolean equals(Object obj) {
    if (!(obj instanceof InitWrapper)) {
      return false;
    }
    InitWrapper otherInit = (InitWrapper) obj;
    return (otherInit.init == init
        && otherInit.name.equals(name)
        && otherInit.hp == hp
        && otherInit.ac == ac);
  }

  @Override public int compareTo(InitWrapper o) {
    if (init > o.init) return 1;
    else if (init < o.init) return -1;
    return 0;
  }
}
