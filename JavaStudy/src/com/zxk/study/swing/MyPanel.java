package com.zxk.study.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;

public class MyPanel extends JPanel implements ActionListener, PropertyChangeListener {

  private static final long serialVersionUID = 1L;

  private Font titleFont = new Font("楷体_GB2312", 1, 32);
  private Font hintFont = new Font("楷体_GB2312", 1, 17);

  private String eventName = "changed";
  private String command_save = "save";
  private String command_cancel = "cancel";

  public MyPanel() {
    setSize(300, 400);
    setLayout(new BorderLayout());
    createUI();
    createEventHanedlers();
  }

  private void createUI() {

    add(createTitle(), BorderLayout.NORTH);
    add(createCenter(), BorderLayout.CENTER);
    add(createKeyHintPanel(), BorderLayout.SOUTH);
  }

  JPanel createTitle() {
    JLabel titleLabel = new JLabel("这是标题");
    titleLabel.setFont(titleFont);
    JPanel titlePanel = new JPanel(new BorderLayout());
    titlePanel.setMinimumSize(new Dimension(0, 61));
    titlePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    titlePanel.add(titleLabel, BorderLayout.WEST);
    return titlePanel;

  }

  private JTextField name;
  private JComboBox sex;

  JPanel createCenter() {

    JLabel nameLabel = new JLabel("name ");
    name = new JTextField(12);
    JLabel sexLabel = new JLabel("gender ");
    sex = new JComboBox(new String[] { "男", "女" });
    sex.setMinimumSize(new Dimension(30, 24));
    JPanel cp = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.insets.bottom = 10;
    cp.add(nameLabel, c);
    c.gridx++;
    cp.add(name, c);
    c.gridy++;
    c.gridx = 0;
    cp.add(sexLabel, c);
    c.gridx++;
    cp.add(sex, c);
    return cp;
  }

  private JButton save;
  private JButton cancel;

  private JPanel createKeyHintPanel() {
    save = createButton(new String[] { "保存", "F1" }, command_save);
    cancel = createButton(new String[] { "取消", "F3" }, command_cancel);
    save.setBorder(null);
    cancel.setBorder(null);
    save.setActionCommand("save");
    save.registerKeyboardAction(this, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    JPanel panel = new JPanel();
    FlowLayout layout = new FlowLayout(3);
    panel.setLayout(layout);
    panel.add(save);
    panel.add(cancel);
    panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    return panel;
  }

  private JButton createButton(String[] nameKey, String command) {
    JButton button = new JButton(nameKey[0] + "[" + nameKey[1] + "]");
    button.setActionCommand(command);
    button.addActionListener(this);
    button.registerKeyboardAction(this, KeyStroke.getKeyStroke(nameKey[1]), WHEN_FOCUSED);
    this.registerKeyboardAction(this, KeyStroke.getKeyStroke(nameKey[1]), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    button.setBackground(new Color(0xeeeeee));
    button.setFocusable(false);
    button.setFont(hintFont);
    return button;
  }

  String oldValue = "";

  private void createEventHanedlers() {
    this.addPropertyChangeListener(eventName, this);
    name.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (oldValue.equals(name.getText())) {
          return;
        }
        firePropertyChange(eventName, oldValue, name.getText());
        oldValue.equals(name.getText());
      }
    });

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("触发啦！");
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    System.out.println("改变啦！");
  }

  public void onShow() {
    name.requestFocus();
    setVisible(true);
  }

}
