package Application.MVVM.View.CharacterSheet;

import Application.Client.ClientCharacterSheet;

import Application.MVVM.Model.character.Character;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class CharacterViewModel
{
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
  private final BooleanProperty isPlayAsCharacterDisabled;
  private final StringProperty playAsCharacterText;
  private final StringProperty saveStatusText;
  private final ObjectProperty<Paint> saveStatusColor;

  private final BooleanProperty removeCharacterButtonDisabled;
  private final BooleanProperty characterInfoVisible;
  private final BooleanProperty playAsCharacterVisible;

  private final ListProperty<Character> characters;

  private final ClientCharacterSheet client;
  private final IntegerProperty indexProperty;

  public CharacterViewModel(ClientCharacterSheet client)
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

    characterClass = new SimpleStringProperty();
    level = new SimpleStringProperty();
    maxHp = new SimpleStringProperty();

    isPlayAsCharacterDisabled = new SimpleBooleanProperty(false);
    playAsCharacterText = new SimpleStringProperty("Play as character");

    removeCharacterButtonDisabled = new SimpleBooleanProperty();
    characterInfoVisible = new SimpleBooleanProperty(false);
    playAsCharacterVisible = new SimpleBooleanProperty(false);

    saveStatusText = new SimpleStringProperty();
    saveStatusColor = new SimpleObjectProperty<>(Color.BLACK);

    indexProperty = new SimpleIntegerProperty();

    this.client = client;

    characters = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
  }

  private String setModStat(String stat)
  {
    if (!stat.isEmpty()){
      int mod = Math.floorDiv(Integer.parseInt(stat) - 10, 2);
      return (mod >= 0)?("+" + mod):(mod + "");
    }
    return "";
  }

  public void createCharacterSheet(){
    if (characterName.getValue().isEmpty() ||
        strength.getValue().isEmpty() ||
        dexterity.getValue().isEmpty() ||
        constitution.getValue().isEmpty() ||
        intelligence.getValue().isEmpty() ||
        wisdom.getValue().isEmpty() ||
        charisma.getValue().isEmpty() ||
        level.getValue().isEmpty() ||
        characterClass.getValue().isEmpty() ||
        maxHp.getValue().isEmpty()) {
      saveStatusColor.set(Color.RED);
      saveStatusText.setValue("All values must be set before saving");
      return;
    }

    client.makeCharacter(
        characterName.getValue(),
        Integer.parseInt(strength.getValue()),
        Integer.parseInt(dexterity.getValue()),
        Integer.parseInt(constitution.getValue()),
        Integer.parseInt(intelligence.getValue()),
        Integer.parseInt(wisdom.getValue()),
        Integer.parseInt(charisma.getValue()),
        Integer.parseInt(level.getValue()),
        characterClass.getValue(),
        Integer.parseInt(maxHp.getValue()));

    characters.clear();
    ArrayList<Character> newCharacters = client.getCharacters();
    characters.addAll(newCharacters);
    for (int i = 0; i < newCharacters.size(); i++) {
      if (newCharacters.get(i).getName().equals(characterName.getValue())) indexProperty.set(i);
    }
  }

  public void updateCharacterInfo(Character character) {
    characterInfoVisible.set(true);
    saveStatusText.setValue("");

    characterName.set(character.getName());
    characterClass.set(character.getcClass());
    level.set(character.getLevel() + "");
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
    characterClass.set("");
    level.set("");
    strength.set("");
    dexterity.set("");
    constitution.set("");
    intelligence.set("");
    wisdom.set("");
    charisma.set("");
    maxHp.set("");

    indexProperty.set(-1);
    characterInfoVisible.set(true);
  }

  public void playAsCharacter(Character character) {
    if (character != null) {
      client.setCurrentCharacter(character);
    }
  }

  public void updatePlayAsCharacterButton(Character character) {
    if (character != null) {
      if (character.equals(client.getUserID().getCurrentCharacter())) {
        isPlayAsCharacterDisabled.set(true);
        playAsCharacterText.set("Playing as character");
      }
      else {
        isPlayAsCharacterDisabled.set(false);
        playAsCharacterText.set("Play as character");
      }
      playAsCharacterVisible.set(true);
    }
  }

  public void removeCharacter(Character character){
    if (character != null) {
      client.deleteCharacter(character);
      characters.clear();
      characters.addAll(client.getCharacters());
    }
  }

  public void bindBidirectionalIndexProperty(
      MultipleSelectionModel<Character> selectionModel){
    selectionModel.selectedIndexProperty().addListener((observableValue, oldIndex, newIndex) -> {
      if (newIndex.intValue() != indexProperty.get()) indexProperty.setValue(newIndex.intValue());
    });

    indexProperty.addListener((observableValue, oldNumber, newNumber) -> {
      if (selectionModel.getSelectedIndex() != newNumber.intValue()) selectionModel.clearAndSelect(newNumber.intValue());
    });
  }

  public void onTableSelectionChanged(ObservableValue<? extends Character> obs, Character oldValue,Character newValue){
    if (newValue != null){
      removeCharacterButtonDisabled.set(false);
      updatePlayAsCharacterButton(newValue);
      updateCharacterInfo(newValue);
    } else {
      removeCharacterButtonDisabled.set(true);
      characterInfoVisible.set(false);
      playAsCharacterVisible.set(false);
    }
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

  public BooleanProperty isPlayAsCharacterDisabledProperty() {
    return isPlayAsCharacterDisabled;
  }

  public StringProperty playAsCharacterTextProperty() {
    return playAsCharacterText;
  }

  public StringProperty saveStatusTextProperty() {
    return saveStatusText;
  }

  public ObjectProperty<Paint> saveStatusColorProperty() {
    return saveStatusColor;
  }

  public ListProperty<Character> charactersProperty() {
    return characters;
  }

  public BooleanProperty removeCharacterButtonDisabledProperty() {
    return removeCharacterButtonDisabled;
  }

  public BooleanProperty characterInfoVisibleProperty() {
    return characterInfoVisible;
  }

  public BooleanProperty playAsCharacterVisibleProperty() {
    return playAsCharacterVisible;
  }

  public void initCharactersList() {
    characters.addAll(client.getCharacters());
  }
}
