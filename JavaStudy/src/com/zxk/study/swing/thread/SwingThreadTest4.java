package com.zxk.study.swing.thread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SwingThreadTest4 extends JFrame implements ActionListener {
  JTextField label = new JTextField();
  JButton button = new JButton("Go!");
  private Runnable run = null;
  int i = 0;

  public SwingThreadTest4() {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    label.setText("0");
    label.setFont(new Font("楷体_GB2312", 1, 22));
    setMinimumSize(new Dimension(400, 300));
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(label, BorderLayout.CENTER);
    panel.add(button, BorderLayout.SOUTH);
    button.addActionListener(this);
    run = new Runnable() {

      @Override
      public void run() {
        label.setText(i + "");
      }
    };
    add(panel);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    MyThread run = new MyThread();
    new Thread(run).start();
  }

  private class MyThread implements Runnable {

    @Override
    public void run() {
      doGo();
    }
  }

  private void doGo() {
    while (i < 60) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      SwingUtilities.invokeLater(run);
      i++;
    }

  }

  public static void main(String[] args) {
    SwingThreadTest4 test4 = new SwingThreadTest4();
    test4.setVisible(true);
  }
}
