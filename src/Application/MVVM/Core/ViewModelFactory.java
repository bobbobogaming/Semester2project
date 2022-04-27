package Application.MVVM.Core;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewModelFactory
{
  private static ViewModelFactory instance;
  private static Lock lock = new ReentrantLock();

  public static ViewModelFactory getInstance()
  {
    if (instance == null){
      synchronized (lock){
        if (instance == null) instance = new ViewModelFactory(ModelFactory.getInstance());
      }
    }
    return instance;
  }

  private ViewModelFactory(ModelFactory modelFactory)
  {
  }
}
