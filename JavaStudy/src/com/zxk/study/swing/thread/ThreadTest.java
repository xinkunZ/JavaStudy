package com.zxk.study.swing.thread;

public class ThreadTest {
  public static Object obj = new Object();
  public static int number = 10;

  public static void main(String[] args) {
    MyThread a = new MyThread();
    MyThread b = new MyThread();
    Thread t1 = new Thread(a, "a号窗口");
    Thread t2 = new Thread(b, "b号窗口");
    t1.start();
    t2.start();
  }

  static class MyThread implements Runnable {

    public void run() {
      while (true)
        synchronized (obj) {
          if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "正在卖票," + "还剩" + number + "张票");
            number--;
            obj.notifyAll();
            try {
              obj.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }

          } else {
            break;
          }
        }

    }
  }
}