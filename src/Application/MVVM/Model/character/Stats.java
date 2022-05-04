package Application.MVVM.Model.character;

public class Stats
{
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
    return Modifier.modifyValue(charisma);
  }

  public int getConstitutionModifier()
  {
    return Modifier.modifyValue(constitution);
  }

  public int getDexterityModifier()
  {
    return Modifier.modifyValue(dexterity);
  }

  public int getIntelligenceModifier()
  {
    return Modifier.modifyValue(intelligence);
  }

  public int getStrengthModifier()
  {
    return Modifier.modifyValue(strength);
  }

  public int getWisdomModifier()
  {
    return Modifier.modifyValue(wisdom);
  }
}
