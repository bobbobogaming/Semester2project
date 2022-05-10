package Application.MVVM.Model.character;

import java.io.Serializable;

public class Stats implements Serializable
{
  private final Modifier modifier;
  private int strength,dexterity,constitution,intelligence,wisdom,charisma;

  public Stats(int strength, int dexterity, int constitution, int intelligence,
      int wisdom, int charisma)
  {
    this.strength = strength;
    this.dexterity = dexterity;
    this.constitution = constitution;
    this.intelligence = intelligence;
    this.wisdom = wisdom;
    this.charisma = charisma;
    modifier = new Modifier();
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
}
