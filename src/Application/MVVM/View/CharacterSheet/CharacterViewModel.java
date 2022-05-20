package Application.MVVM.View.CharacterSheet;

import Application.Client.ClientCharacterSheet;
import Application.MVVM.Model.CharacterSheet.ICharacterSheetModel;
import Application.MVVM.Model.character.Character;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

public class CharacterViewModel
{
  private final StringProperty characterName;
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
  private final BooleanProperty isPlayAsCharacterDisabled;
  private final StringProperty playAsCharacterText;

  private final ListProperty<Character> characters;

  private final ClientCharacterSheet client;
  private final ICharacterSheetModel characterSheetModel;

  public CharacterViewModel(ICharacterSheetModel characterSheetModel,
      ClientCharacterSheet client)
  {
    characterName = new SimpleStringProperty();
    strength = new SimpleStringProperty();
    strengthMod = new SimpleStringProperty();
    strength.addListener((obs,oldValue,newValue) -> strengthMod.setValue(setModStat(newValue)));

    dexterity = new SimpleStringProperty();
    dexterityMod = new SimpleStringProperty();
    dexterity.addListener((obs,oldValue,newValue) -> dexterityMod.setValue(setModStat(newValue)));

    constitution = new SimpleStringProperty();
    constitutionMod = new SimpleStringProperty();
    constitution.addListener((obs,oldValue,newValue) -> constitutionMod.setValue(setModStat(newValue)));

    intelligence = new SimpleStringProperty();
    intelligenceMod = new SimpleStringProperty();
    intelligence.addListener((obs,oldValue,newValue) -> intelligenceMod.setValue(setModStat(newValue)));

    wisdom = new SimpleStringProperty();
    wisdomMod = new SimpleStringProperty();
    wisdom.addListener((obs,oldValue,newValue) -> wisdomMod.setValue(setModStat(newValue)));

    charisma = new SimpleStringProperty();
    charismaMod = new SimpleStringProperty();
    charisma.addListener((obs,oldValue,newValue) -> charismaMod.setValue(setModStat(newValue)));

    maxHp = new SimpleStringProperty();

    isPlayAsCharacterDisabled = new SimpleBooleanProperty(false);
    playAsCharacterText = new SimpleStringProperty("Play as character");


    this.characterSheetModel = characterSheetModel;
    this.client = client;

    characters = new SimpleListProperty<>(FXCollections.observableArrayList(client.getCharacters()));
  }

  public StringProperty characterNameProperty() {
    return characterName;
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

  public BooleanProperty isPlayAsCharacterDisabledProperty() {
    return isPlayAsCharacterDisabled;
  }

  public StringProperty playAsCharacterTextProperty() {
    return playAsCharacterText;
  }

  public ListProperty<Character> charactersProperty() {
    return characters;
  }

  private String setModStat(String stat)
  {
    if (!stat.isEmpty()){
      int mod = Math.floorDiv(Integer.parseInt(stat) - 10, 2);
      return (mod >= 0)?("+" + mod):(mod + "");
    }
    return "";
  }

  public void createCharacterSheet(String name,String str, String dex, String con, String intel,
      String wis, String cha, String maxHp){
    characterSheetModel.makeCharacter(
        name,
        Integer.parseInt(str),
        Integer.parseInt(dex),
        Integer.parseInt(con),
        Integer.parseInt(intel),
        Integer.parseInt(wis),
        Integer.parseInt(cha),
        Integer.parseInt(maxHp));
    characters.clear();
    characters.addAll(client.getCharacters());
  }

  public void saveCharacterSheet(Character character, String name,String str, String dex, String con, String intel,
      String wis, String cha, String maxHp) {

  }

  public void updateCharacterInfo(Character character) {
    characterName.set(character.getName());
    strength.set(character.getStats().getStrength() + "");
    dexterity.set(character.getStats().getDexterity() + "");
    constitution.set(character.getStats().getConstitution() + "");
    intelligence.set(character.getStats().getIntelligence() + "");
    wisdom.set(character.getStats().getWisdom() + "");
    charisma.set(character.getStats().getCharisma() + "");
    maxHp.setValue(character.getStats().getMaxHP() + "");
  }

  public void clearCharacterInfo() {
    characterName.set("");
    strength.set("");
    dexterity.set("");
    constitution.set("");
    intelligence.set("");
    wisdom.set("");
    charisma.set("");
    maxHp.set("");
  }

  public void playAsCharacter(Character character) {
    client.setCurrentCharacter(character);
  }

  public void updatePlayAsCharacterButton(Character character) {
    if (character.equals(client.getUserID().getCurrentCharacter())) {
      isPlayAsCharacterDisabled.set(true);
      playAsCharacterText.set("Playing as character");
    }
    else {
      isPlayAsCharacterDisabled.set(false);
      playAsCharacterText.set("Play as character");
    }
  }
}
