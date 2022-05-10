package Application.MVVM.Model.character;

public class Modifier implements IModifier
{
  public int modifierValue(int value)
  {
    return value / 2 - 5;
  }
}
