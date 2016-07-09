package com.zxk.study.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame implements PropertyChangeListener {

  private static final long serialVersionUID = 1L;

  private MyMenu myMenu;

  public MyFrame() {

    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(800, 450));
    myMenu = new MyMenu();
    myMenu.init();
    myMenu.addPropertyChangeListener(MyMenu.proChangeName, this);
    contentPanel = new JPanel(new BorderLayout());
    contentPanel.setBorder(null);
    workingPanel = myMenu;
    contentPanel.add(workingPanel, BorderLayout.CENTER);
    setContentPane(contentPanel);
  }

  private JPanel contentPanel;
  private JPanel workingPanel;

  public MyFrame(JPanel panel) {
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(800, 450));
    workingPanel = panel;
    contentPanel = new JPanel();
    contentPanel.add(panel);
    add(panel, BorderLayout.CENTER);
  }

  public void onShow() {
    setVisible(true);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    setPanel();
  }

  private void setPanel() {
    MyPanel myPanel = new MyPanel();

    contentPanel.remove(workingPanel);
    workingPanel = myPanel;
    contentPanel.add(workingPanel);
    add(myPanel, BorderLayout.CENTER);
    doLayout();
    contentPanel.updateUI();
  }
}
