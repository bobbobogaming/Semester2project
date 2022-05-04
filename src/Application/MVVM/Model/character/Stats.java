package Application.MVVM.Model.character;

public class Stats
{
  private int charisma,constitution, dexterity, intelligence,strength,wisdom;
  private IModifier modifier;

  public Stats(int charisma, int constitution, int dexterity, int intelligence,
      int strength, int wisdom)
  {
    this.charisma = charisma;
    this.constitution = constitution;
    this.dexterity = dexterity;
    this.intelligence = intelligence;
    this.strength = strength;
    this.wisdom = wisdom;
    this.modifier = new Modifer();
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
