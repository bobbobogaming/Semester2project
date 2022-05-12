package Application.MVVM.Model.character;

import java.io.Serializable;

public interface IModifier extends Serializable
{
  int modifierValue(int value);
}
