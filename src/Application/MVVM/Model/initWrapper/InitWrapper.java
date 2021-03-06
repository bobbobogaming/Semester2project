package Application.MVVM.Model.initWrapper;

import Application.MVVM.Model.monster.Monster;
import Application.MVVM.Model.character.Character;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class InitWrapper implements Serializable, Comparable<InitWrapper> {
  private int init;
  private String name;
  private int hp;
  private int ac;
  private final boolean isMonster;
  private final IStatFormat source;

  public InitWrapper(Monster monster){
    init =Math.max(0,ThreadLocalRandom.current().nextInt(20) + monster.getMonsterStats().getDexterityModifier());
    name = monster.getMonsterName();
    hp = monster.getMaxHP();
    ac = monster.getAc();
    isMonster = true;
    source = monster;
  }

  public InitWrapper(Character character){
    init = Math.max(0,ThreadLocalRandom.current().nextInt(20) + character.getStats().getDexterityModifier());
    name = character.getName();
    hp = character.getStats().getMaxHP();
    ac = 10 + character.getStats().getDexterityModifier();
    isMonster = false;
    source = character;
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

  public boolean isMonster() {
    return isMonster;
  }

  public String getFormattedStats(){
    return source.getFormattedStats();
  }

  @Override public boolean equals(Object obj) {
    if (!(obj instanceof InitWrapper)) {
      return false;
    }
    InitWrapper otherInit = (InitWrapper) obj;

    if (isMonster != otherInit.isMonster) return false;

    if (isMonster)
    return (otherInit.init == init
        && otherInit.name.equals(name)
        && otherInit.ac == ac);
    else return (otherInit.name.equals(name)
        && otherInit.ac == ac);
  }

  @Override public int compareTo(InitWrapper o) {
    if (init > o.init) return 1;
    else if (init < o.init) return -1;
    return name.compareTo(o.name);
  }
}
