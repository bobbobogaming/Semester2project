package Test.Character;

import Application.MVVM.Model.character.IModifier;
import Application.MVVM.Model.character.Modifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModifierTest
{
  @Test
  void testModifier(){
    IModifier modifier = new Modifier();

    assertEquals(0,modifier.modifierValue(10));
    assertEquals(0,modifier.modifierValue(11));
    assertEquals(5,modifier.modifierValue(20));
  }
}