package Application.MVVM.Model;

import Application.MVVM.Model.character.Stats;

public class Character
{

  private Stats stats;
  private String name;

  public Character(Stats stats, String name)
  {
    this.stats = stats;
    this.name = name;
  }

  public Stats getStats()
  {
    return stats;
  }

  public String getName()
  {
    return name;
  }
}
