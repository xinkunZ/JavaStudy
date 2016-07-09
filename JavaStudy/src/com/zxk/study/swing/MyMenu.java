package com.zxk.study.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class MyMenu extends JPanel {

  private static final long serialVersionUID = 1L;
  public static String proChangeName = "itemChoosed";

  private final String menu[][] = new String[][] {
      new String[] { "01", "02", "03" },
      new String[] { "04", "05", "06" },
      new String[] { "07", "08", "09" },
      new String[] { "11", "12", "13" },
      new String[] { "14", "15", "16" },
      new String[] { "21", "22", "23" },
      new String[] { "24", "25", null },
      new String[] { "31", "32", "33" },
      new String[] { "41", "42", "43" } };
  private final Font cell_font = new Font("宋体", 0, 20);
  private Font titleFont = new Font("宋体", 1, 32);

  private Color color_select_back = new Color(250, 223, 173);
  private Color color_back_wh = Color.WHITE;
  private Color color_back_blu = new Color(209, 242, 243);

  public MyMenu() {

  }

  public void init() {
    createUI();
    createEventHandlers();
    onShow();
  }

  private void createUI() {
    setLayout(new BorderLayout());

    add(createTable(), BorderLayout.CENTER);
    add(createTitle(), BorderLayout.NORTH);
  }

  private JComponent createTitle() {
    // JLabel titleLabel = new JLabel("这是标题");
    // titleLabel.setFont(titleFont);
    JPanel titlePanel = new JPanel(new BorderLayout());
    titlePanel.setPreferredSize(new Dimension(0, 70));
    titlePanel.setBorder(null);
    titlePanel.setBackground(new Color(116, 193, 210));
    // titlePanel.add(titleLabel, BorderLayout.WEST);
    return titlePanel;
  }

  private JTable table;

  private JComponent createTable() {
    setLayout(new BorderLayout());
    table = new JTable(new MyTableModel());
    table.setCellSelectionEnabled(true);
    table.setFont(cell_font);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setRowHeight(30);
    table.setOpaque(true);
    table.setShowGrid(false);
    DefaultTableCellRenderer headRenderer = new DefaultTableCellRenderer();
    headRenderer.setPreferredSize(new Dimension(0, 0));
    table.getTableHeader().setDefaultRenderer(headRenderer);
    table.setDefaultRenderer(Object.class, new MyRenderer());
    JScrollPane p = new JScrollPane(table);
    return p;
  }

  private void createEventHandlers() {
    table.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          e.consume();
          chooseMenuItem();
        } else {
          super.keyPressed(e);
        }
      }
    });
  }

  private void chooseMenuItem() {
    int row = table.getSelectedRow();
    int col = table.getSelectedColumn();
    String value = (String) table.getValueAt(row, col);
    firePropertyChange(proChangeName, null, value);
  }

  private class MyTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    @Override
    public int getRowCount() {
      return menu.length;
    }

    @Override
    public int getColumnCount() {
      return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      return menu[rowIndex][columnIndex];
    }

  }

  private class MyRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
        int row, int column) {
      JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      label.setBorder(null);
      if (value != null) {
        label.setText("  [" + value + "]");
        if (!isSelected) {
          label.setBackground((Integer.parseInt(((String) value).substring(0, 1)) + 1) % 2 == 1 ? color_back_wh
              : color_back_blu);
        } else if (isSelected) {
          label.setBackground(color_select_back);
        }
      } else {
        if (!isSelected) {
          value = table.getValueAt(row, 0);
          label.setText("");
          label.setBackground((Integer.parseInt(((String) value).substring(0, 1)) + 1) % 2 == 1 ? color_back_wh
              : color_back_blu);
        } else {
          label.setBackground(color_select_back);
        }
      }
      return label;
    }
  }

  private void onShow() {
    table.changeSelection(0, 0, false, false);
  }
}
