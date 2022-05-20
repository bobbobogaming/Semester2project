package Application.MVVM.Model.character;

public class Modifier implements IModifier
{
  public int modifierValue(int value)
  {
    return Math.floorDiv(value, 2) - 5;
  }
}
