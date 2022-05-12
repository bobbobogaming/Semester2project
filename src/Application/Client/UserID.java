package Application.Client;

import java.io.Serializable;

public class UserID implements Serializable
{
  private boolean isDM;
  private String name;

  public UserID(String name)
  {
    this.name = name;
  }

  public void setDM(boolean DM)
  {
    isDM = DM;
  }

  public boolean isDM()
  {
    return isDM;
  }

  public String getName() {
    return name;
  }

  @Override public String toString()
  {
    return "UserID{" + "isDM=" + isDM + ", name='" + name + '\'' + '}';
  }
}
