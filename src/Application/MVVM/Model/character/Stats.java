package Application.MVVM.Model.character;

import java.io.Serializable;
import java.util.Objects;

public class Stats implements Serializable
{
  private final IModifier modifier;
  private int strength,dexterity,constitution,intelligence,wisdom,charisma, maxHP;

  public Stats(int strength, int dexterity, int constitution, int intelligence,
      int wisdom, int charisma, int maxHP)
  {
    this.strength = strength;
    this.dexterity = dexterity;
    this.constitution = constitution;
    this.intelligence = intelligence;
    this.wisdom = wisdom;
    this.charisma = charisma;
    modifier = new Modifier();
    this.maxHP = maxHP;
  }

  public int getCharisma()
  {
    return charisma;
  }

  public int getConstitution()
  {
    return constitution;
  }

  public int getDexterity()
  {
    return dexterity;
  }

  public int getMaxHP(){return maxHP;}

  public int getIntelligence()
  {
    return intelligence;
  }

  public int getStrength()
  {
    return strength;
  }

  public int getWisdom()
  {
    return wisdom;
  }

  public int getCharismaModifier()
  {
    return modifier.modifierValue(charisma);
  }

  public int getConstitutionModifier()
  {
    return modifier.modifierValue(constitution);
  }

  public int getDexterityModifier()
  {
    return modifier.modifierValue(dexterity);
  }

  public int getIntelligenceModifier()
  {
    return modifier.modifierValue(intelligence);
  }

  public int getStrengthModifier()
  {
    return modifier.modifierValue(strength);
  }

  public int getWisdomModifier()
  {
    return modifier.modifierValue(wisdom);
  }

  @Override
  public String toString() {
    return "Stats{" +
            "maxHP: " + maxHP +
            ", strength=" + strength +
            ", dexterity=" + dexterity +
            ", constitution=" + constitution +
            ", intelligence=" + intelligence +
            ", wisdom=" + wisdom +
            ", charisma=" + charisma +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Stats)) return false;
    Stats stats = (Stats) o;
    return strength == stats.strength
        && dexterity == stats.dexterity
        && constitution == stats.constitution
        && intelligence == stats.intelligence
        && wisdom == stats.wisdom
        && charisma == stats.charisma;
  }
}
