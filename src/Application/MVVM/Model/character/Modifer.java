package Application.MVVM.Model.character;

public class Modifer implements IModifier
{
  @Override public int modifierValue(int value)
  {
    return value / 2 - 5;
  }


}
