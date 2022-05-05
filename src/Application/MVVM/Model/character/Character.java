package Application.MVVM.Model.character;

import java.io.Serializable;

public class Character implements Serializable
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
