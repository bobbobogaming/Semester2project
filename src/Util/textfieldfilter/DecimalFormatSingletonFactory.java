package Util.textfieldfilter;

import java.text.DecimalFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DecimalFormatSingletonFactory {
  private static DecimalFormatSingletonFactory instance;
  private static Lock lock = new ReentrantLock();;

  private DecimalFormat format;

  private DecimalFormatSingletonFactory(){}

  public static DecimalFormatSingletonFactory getInstance(){
    if (instance == null){
      synchronized (lock){
        if (instance == null) instance = new DecimalFormatSingletonFactory();
      }
    }
    return instance;
  }

  public DecimalFormat getFormat() {
    if (format == null){
      synchronized (lock){
        if (format == null) format = new DecimalFormat("#");
      }
    }
    return format;
  }
}
