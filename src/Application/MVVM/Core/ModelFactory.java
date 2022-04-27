package Application.MVVM.Core;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ModelFactory
{
  private static ModelFactory instance;
  private static Lock lock = new ReentrantLock();

  private ModelFactory(){}

  public static ModelFactory getInstance()
  {
    if (instance == null){
      synchronized (lock){
        if (instance == null) instance = new ModelFactory();
      }
    }
    return instance;
  }


}
