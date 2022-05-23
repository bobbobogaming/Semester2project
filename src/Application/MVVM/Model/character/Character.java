package Application.MVVM.Model.character;

import Application.MVVM.Model.initWrapper.IStatFormat;

import java.io.Serializable;
import java.util.Objects;

public class Character implements Serializable, IStatFormat
{


  private Stats stats;
  private String name;

  private int level;

  private String cClass; //I have written it with a single s because double s is something else, it's now cClass, short for Character class

  public Character(Stats stats, String name,int level,String cClass)
  {
    this.stats = stats;
    this.name = name;
    this.cClass = cClass;
    this.level = level;
  }
  public Character(Stats stats, String name){
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

  public int getLevel() {
    return level;
  }

  public String getcClass() {
    return cClass;
  }

  @Override
  public String getFormattedStats(){
    return String.format("Max HP: %d",stats.getMaxHP()) + "\n"
        + String.format("STR: %d (%d)",stats.getStrength(),stats.getStrengthModifier()) + "\n"
        + String.format("DEX: %d (%d)",stats.getDexterity(),stats.getDexterityModifier()) +"\n"
        + String.format("CON: %d (%d)",stats.getConstitution(),stats.getConstitutionModifier()) +"\n"
        + String.format("INT: %d (%d)",stats.getIntelligence(),stats.getIntelligenceModifier()) +"\n"
        + String.format("WIS: %d (%d)",stats.getWisdom(),stats.getWisdomModifier()) +"\n"
        + String.format("CHA: %d (%d)",stats.getCharisma(),stats.getCharismaModifier());
  }
  public Character getCharacter(){
    return this;
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
    return name;
  }
}
