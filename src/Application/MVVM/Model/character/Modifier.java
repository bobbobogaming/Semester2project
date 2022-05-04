package Application.MVVM.Model.character;

public class Modifier
{
  private Modifier(){}

  public static int modifyValue(int value)
  {
    return value / 2 - 5;
  }
}
