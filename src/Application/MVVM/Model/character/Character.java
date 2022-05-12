package Application.MVVM.Model.character;

import java.io.Serializable;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Character)) return false;
    Character character = (Character) o;
    return Objects.equals(stats, character.stats) && Objects.equals(name, character.name);
  }

  @Override
  public String toString() {
    return "Character{" +
            "stats=" + stats +
            ", name='" + name + '\'' +
            '}';
  }
}
