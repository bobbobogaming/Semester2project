package Application.MVVM.View.Lobby.Dm.charactersheet;

import Application.Client.UserID;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DMCharacterSheetViewModel {
  private final StringProperty characterName;
  private final StringProperty characterClass;
  private final StringProperty level;
  private final StringProperty strength;
  private final StringProperty strengthMod;
  private final StringProperty dexterity;
  private final StringProperty dexterityMod;
  private final StringProperty constitution;
  private final StringProperty constitutionMod;
  private final StringProperty intelligence;
  private final StringProperty intelligenceMod;
  private final StringProperty wisdom;
  private final StringProperty wisdomMod;
  private final StringProperty charisma;
  private final StringProperty charismaMod;
  private final StringProperty maxHp;
  private final StringProperty playerName;

  public DMCharacterSheetViewModel(UserID userID) {
    playerName = new SimpleStringProperty("Player: " + userID.getName());
    characterName = new SimpleStringProperty(userID.getCurrentCharacter().getName());
    strength = new SimpleStringProperty(userID.getCurrentCharacter().getStats().getStrength() + "");
    strengthMod = new SimpleStringProperty(formatStatsString(userID.getCurrentCharacter().getStats().getStrengthModifier()));

    dexterity = new SimpleStringProperty(userID.getCurrentCharacter().getStats().getDexterity() + "");
    dexterityMod = new SimpleStringProperty(formatStatsString(userID.getCurrentCharacter().getStats().getDexterityModifier()));

    constitution = new SimpleStringProperty(userID.getCurrentCharacter().getStats().getConstitution() + "");
    constitutionMod = new SimpleStringProperty(formatStatsString(userID.getCurrentCharacter().getStats().getConstitutionModifier()));

    intelligence = new SimpleStringProperty(userID.getCurrentCharacter().getStats().getIntelligence() + "");
    intelligenceMod = new SimpleStringProperty(formatStatsString(userID.getCurrentCharacter().getStats().getIntelligenceModifier()));

    wisdom = new SimpleStringProperty(userID.getCurrentCharacter().getStats().getWisdom() + "");
    wisdomMod = new SimpleStringProperty(formatStatsString(userID.getCurrentCharacter().getStats().getWisdomModifier()));

    charisma = new SimpleStringProperty(userID.getCurrentCharacter().getStats().getCharisma() + "");
    charismaMod = new SimpleStringProperty(formatStatsString(userID.getCurrentCharacter().getStats().getCharismaModifier()));

    characterClass = new SimpleStringProperty(userID.getCurrentCharacter().getcClass());
    level = new SimpleStringProperty(userID.getCurrentCharacter().getLevel() + "");
    maxHp = new SimpleStringProperty(userID.getCurrentCharacter().getStats().getMaxHP() + "");

  }
  public StringProperty characterNameProperty() {
    return characterName;
  }

  public StringProperty characterClassProperty() {
    return characterClass;
  }

  public StringProperty levelProperty() {
    return level;
  }

  public StringProperty strengthProperty() {
    return strength;
  }

  public StringProperty strengthModProperty()
  {
    return strengthMod;
  }

  public StringProperty dexterityProperty() {
    return dexterity;
  }

  public StringProperty dexterityModProperty()
  {
    return dexterityMod;
  }

  public StringProperty constitutionProperty() {
    return constitution;
  }

  public StringProperty constitutionModProperty()
  {
    return constitutionMod;
  }

  public StringProperty intelligenceProperty() {
    return intelligence;
  }

  public StringProperty intelligenceModProperty()
  {
    return intelligenceMod;
  }

  public StringProperty wisdomProperty() {
    return wisdom;
  }

  public StringProperty wisdomModProperty()
  {
    return wisdomMod;
  }

  public StringProperty charismaProperty() {
    return charisma;
  }

  public StringProperty charismaModProperty()
  {
    return charismaMod;
  }

  public StringProperty maxHpProperty() {
    return maxHp;
  }

  private String formatStatsString(int stat)
  {
    return (stat >= 0)?("+" + stat):(stat + "");
  }

  public StringProperty playerNameProperty() {
    return playerName;
  }
}
