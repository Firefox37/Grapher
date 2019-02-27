/*     */ package calculator;
/*     */ 
/*     */ import Settings.GenSettings;
/*     */ import components.ExpressionTablePane;
/*     */ import components.SmartTextField;
/*     */ import components.VariableTablePane;
/*     */ import exceptions.InvalidVariableNameException;
/*     */ import expressions.Expression;
/*     */ import expressions.ExpressionList;
/*     */ import expressions.VariableList;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.Clipboard;
/*     */ import java.awt.datatransfer.ClipboardOwner;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.io.Serializable;
/*     */ import java.util.Vector;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.table.TableModel;
/*     */ import javax.swing.text.DefaultEditorKit.CopyAction;
/*     */ import javax.swing.text.DefaultEditorKit.CutAction;
/*     */ import javax.swing.text.DefaultEditorKit.PasteAction;
/*     */ 
/*     */ public class CalculatorTab extends JPanel
/*     */   implements ActionListener, Serializable, MouseListener, ClipboardOwner, KeyListener
/*     */ {
/*     */   private static final long serialVersionUID = 1837255541427330578L;
/*     */   int targetRow;
/*     */   JScrollPane exprScrollPane;
/*     */   JScrollPane varScrollPane;
/*     */   JPanel controlPanel;
/*     */   JPanel controlPanelEast;
/*     */   JPanel centerPanel;
/*     */   JPanel centerPanelEast;
/*     */   JPanel centerPanelWest;
/*     */   JPanel exprControlPanel;
/*     */   JPanel varControlPanel;
/*     */   SmartTextField txtInput;
/*     */   JButton btnEnter;
/*     */   JButton btnAddVariable;
/*     */   JButton btnRemoveVariable;
/*     */   JButton btnClearExpressions;
/*     */   JButton btnAppendToInput;
/*     */   JTable varTable;
/*     */   JTable exprTable;
/*     */   JTable targetTable;
/*     */   JPopupMenu mnuRightClick;
/*     */   JMenuItem miCopyExpression;
/*     */   JMenuItem miCopyValue;
/*     */   JMenuItem miRemoveRow;
/*     */   Clipboard clipBoard;
/*     */ 
/*     */   public CalculatorTab()
/*     */   {
/*  64 */     setLayout(new BorderLayout());
/*  65 */     createControlPanel();
/*  66 */     createCenterPanel();
/*  67 */     createPopupMenu();
/*     */ 
/*  69 */     add(this.centerPanel, "Center");
/*  70 */     add(this.controlPanel, "South");
/*     */     try {
/*  72 */       createTables();
/*     */     } catch (InvalidVariableNameException ex) {
/*  74 */       Logger.getLogger(CalculatorTab.class.getName()).log(Level.SEVERE, null, ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void createControlPanel()
/*     */   {
/*  82 */     this.controlPanel = new JPanel();
/*  83 */     this.controlPanelEast = new JPanel();
/*  84 */     this.controlPanel.setLayout(new BorderLayout());
/*  85 */     this.controlPanelEast.setLayout(new FlowLayout());
/*     */ 
/*  87 */     this.txtInput = new SmartTextField();
/*     */ 
/*  89 */     this.btnEnter = new JButton("Enter");
/*  90 */     this.btnEnter.addActionListener(this);
/*     */ 
/*  92 */     this.controlPanelEast.add(this.btnEnter);
/*  93 */     this.controlPanel.add(this.txtInput, "Center");
/*  94 */     this.controlPanel.add(this.controlPanelEast, "East");
/*     */ 
/*  96 */     this.txtInput.addMouseListener(this);
/*  97 */     this.txtInput.addKeyListener(this);
/*     */   }
/*     */ 
/*     */   private void createPopupMenu()
/*     */   {
/* 104 */     this.mnuRightClick = new JPopupMenu();
/* 105 */     this.miCopyExpression = new JMenuItem("Copy Expression");
/* 106 */     this.miCopyValue = new JMenuItem("Copy Value");
/* 107 */     this.miRemoveRow = new JMenuItem("Remove Row");
/*     */ 
/* 109 */     this.miCopyExpression.addActionListener(this);
/* 110 */     this.miCopyValue.addActionListener(this);
/* 111 */     this.miRemoveRow.addActionListener(this);
/*     */   }
/*     */ 
/*     */   private void createCenterPanel()
/*     */   {
/* 118 */     this.centerPanel = new JPanel();
/* 119 */     this.centerPanelEast = new JPanel();
/* 120 */     this.centerPanelWest = new JPanel();
/* 121 */     this.exprControlPanel = new JPanel();
/* 122 */     this.varControlPanel = new JPanel();
/*     */ 
/* 124 */     this.centerPanel.setLayout(new GridLayout(1, 2));
/* 125 */     this.centerPanelWest.setLayout(new BorderLayout());
/* 126 */     this.centerPanelEast.setLayout(new BorderLayout());
/* 127 */     this.varControlPanel.setLayout(new FlowLayout());
/* 128 */     this.exprControlPanel.setLayout(new FlowLayout());
/*     */ 
/* 131 */     this.varTable = new VariableTablePane();
/* 132 */     this.varTable.setSelectionMode(0);
/* 133 */     this.exprTable = new ExpressionTablePane();
/* 134 */     this.exprTable.setSelectionMode(0);
/*     */ 
/* 137 */     this.exprScrollPane = new JScrollPane(this.exprTable);
/* 138 */     this.varScrollPane = new JScrollPane(this.varTable);
/*     */ 
/* 141 */     this.btnAddVariable = new JButton(GenSettings.getImageIcon("/images/addSmall.png"));
/* 142 */     this.btnRemoveVariable = new JButton(GenSettings.getImageIcon("/images/removeSmall.png"));
/* 143 */     this.btnClearExpressions = new JButton("Clear", GenSettings.getImageIcon("/images/clear.png"));
/* 144 */     this.btnAppendToInput = new JButton("Append To Input", GenSettings.getImageIcon("/images/copy.png"));
/*     */ 
/* 147 */     this.btnAddVariable.addActionListener(this);
/* 148 */     this.btnRemoveVariable.addActionListener(this);
/* 149 */     this.btnClearExpressions.addActionListener(this);
/* 150 */     this.btnAppendToInput.addActionListener(this);
/*     */ 
/* 153 */     this.exprTable.addMouseListener(this);
/* 154 */     this.varTable.addMouseListener(this);
/*     */ 
/* 157 */     this.varControlPanel.add(new JLabel("Variable:"));
/* 158 */     this.varControlPanel.add(this.btnRemoveVariable);
/* 159 */     this.varControlPanel.add(this.btnAddVariable);
/* 160 */     this.exprControlPanel.add(this.btnClearExpressions);
/* 161 */     this.exprControlPanel.add(this.btnAppendToInput);
/*     */ 
/* 164 */     this.centerPanelEast.add(this.varScrollPane, "Center");
/* 165 */     this.centerPanelEast.add(this.varControlPanel, "South");
/*     */ 
/* 168 */     this.centerPanelWest.add(this.exprScrollPane, "Center");
/* 169 */     this.centerPanelWest.add(this.exprControlPanel, "South");
/*     */ 
/* 172 */     this.centerPanel.add(this.centerPanelWest);
/* 173 */     this.centerPanel.add(this.centerPanelEast);
/*     */   }
/*     */ 
/*     */   private void createTables()
/*     */     throws InvalidVariableNameException
/*     */   {
/* 182 */     ExpressionTablePane.refreshTable();
/* 183 */     VariableTablePane.refreshTable();
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 188 */     if (e.getSource() == this.btnEnter) {
/* 189 */       Expression expr = new Expression(this.txtInput.getText());
/*     */       try {
/* 191 */         ExpressionList.addExpression(expr);
/* 192 */         ExpressionTablePane.refreshTable();
/* 193 */         this.txtInput.setText("");
/*     */       } catch (Exception exc) {
/* 195 */         ExpressionList.removeExpression(expr);
/* 196 */         JOptionPane.showMessageDialog(this, exc, "Error", 0);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 201 */     if (e.getSource() == this.btnAddVariable) {
/* 202 */       JFrame window = new AddVariableDialog();
/* 203 */       window.setLocationRelativeTo(this);
/* 204 */       window.setVisible(true);
/* 205 */       window.pack();
/*     */     }
/*     */ 
/* 209 */     if (e.getSource() == this.btnRemoveVariable) {
/* 210 */       if (this.varTable.getSelectedRow() >= 0) {
/*     */         try {
/* 212 */           VariableList.removeVariable(this.varTable.getSelectedRow());
/* 213 */           VariableTablePane.refreshTable();
/*     */         } catch (InvalidVariableNameException ex) {
/* 215 */           JOptionPane.showMessageDialog(this, "Invalid variable name.", "Error", 0);
/*     */         }
/*     */       }
/*     */       else {
/* 219 */         JOptionPane.showMessageDialog(this, "Please select a variable to remove.");
/*     */       }
/*     */     }
/*     */ 
/* 223 */     if (e.getSource() == this.btnClearExpressions) {
/*     */       try {
/* 225 */         ExpressionList.clearExpressionList();
/* 226 */         ExpressionTablePane.refreshTable();
/*     */       } catch (Exception exc) {
/* 228 */         JOptionPane.showMessageDialog(this, exc, "Error", 0);
/*     */       }
/*     */     }
/*     */ 
/* 232 */     if (e.getSource() == this.btnAppendToInput) {
/* 233 */       if (this.exprTable.getSelectedRow() >= 0) {
/* 234 */         String copy = ((Expression)ExpressionList.getExpressionList().get(this.exprTable.getSelectedRow())).getExpression();
/* 235 */         this.txtInput.setText(this.txtInput.getText() + "(" + copy + ")");
/*     */       } else {
/* 237 */         JOptionPane.showMessageDialog(this, "Please select an expression to copy.");
/*     */       }
/*     */     }
/*     */ 
/* 241 */     if (e.getSource() == this.miCopyValue) {
/* 242 */       String value = this.targetTable.getModel().getValueAt(this.targetRow, 1).toString();
/* 243 */       StringSelection strS = new StringSelection(value);
/* 244 */       this.clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 245 */       this.clipBoard.setContents(strS, this);
/*     */     }
/*     */ 
/* 248 */     if (e.getSource() == this.miCopyExpression) {
/* 249 */       String value = this.targetTable.getModel().getValueAt(this.targetRow, 0).toString();
/* 250 */       StringSelection strS = new StringSelection(value);
/* 251 */       this.clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 252 */       this.clipBoard.setContents(strS, this);
/*     */     }
/*     */ 
/* 255 */     if (e.getSource() == this.miRemoveRow) {
/* 256 */       if (this.targetTable.equals(this.varTable)) {
/*     */         try {
/* 258 */           VariableList.removeVariable(this.targetRow);
/* 259 */           VariableTablePane.refreshTable();
/*     */         } catch (InvalidVariableNameException ex) {
/* 261 */           JOptionPane.showMessageDialog(this, "Invalid variable name.", "Error", 0);
/*     */         }
/*     */       }
/* 264 */       if (this.targetTable.equals(this.exprTable)) {
/* 265 */         ExpressionList.removeExpression(this.targetRow);
/* 266 */         ExpressionTablePane.refreshTable();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseClicked(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 277 */     if ((e.isPopupTrigger()) || (e.getModifiers() == 4)) {
/* 278 */       if (e.getSource() == this.exprTable) {
/* 279 */         this.targetTable = this.exprTable;
/* 280 */         this.targetRow = this.exprTable.rowAtPoint(e.getPoint());
/* 281 */         this.exprTable.addRowSelectionInterval(this.targetRow, this.targetRow);
/*     */ 
/* 283 */         this.miCopyExpression.setText("Copy Expression");
/* 284 */         this.mnuRightClick.removeAll();
/* 285 */         this.mnuRightClick.add(this.miCopyExpression);
/* 286 */         this.mnuRightClick.add(this.miCopyValue);
/* 287 */         this.mnuRightClick.add(this.miRemoveRow);
/*     */ 
/* 289 */         this.mnuRightClick.show(this.exprTable, e.getX() + 10, e.getY() + 5);
/*     */       }
/* 291 */       if (e.getSource() == this.varTable) {
/* 292 */         this.targetTable = this.varTable;
/* 293 */         this.targetRow = this.varTable.rowAtPoint(e.getPoint());
/* 294 */         this.varTable.addRowSelectionInterval(this.targetRow, this.targetRow);
/*     */ 
/* 296 */         this.miCopyExpression.setText("Copy Variable Name");
/* 297 */         this.mnuRightClick.removeAll();
/* 298 */         this.mnuRightClick.add(this.miCopyExpression);
/* 299 */         this.mnuRightClick.add(this.miCopyValue);
/* 300 */         this.mnuRightClick.add(this.miRemoveRow);
/*     */ 
/* 302 */         this.mnuRightClick.show(this.varTable, e.getX() + 10, e.getY() + 5);
/*     */       }
/*     */ 
/* 305 */       if (e.getSource() == this.txtInput) {
/* 306 */         this.txtInput.requestFocus();
/*     */ 
/* 308 */         this.mnuRightClick.removeAll();
/*     */ 
/* 310 */         JMenuItem mnuItem = new JMenuItem(new DefaultEditorKit.CutAction());
/* 311 */         mnuItem.setText("Cut");
/* 312 */         this.mnuRightClick.add(mnuItem);
/* 313 */         mnuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
/* 314 */         mnuItem.setText("Copy");
/* 315 */         this.mnuRightClick.add(mnuItem);
/* 316 */         mnuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
/* 317 */         mnuItem.setText("Paste");
/* 318 */         this.mnuRightClick.add(mnuItem);
/*     */ 
/* 321 */         this.mnuRightClick.show(this.txtInput, e.getX() + 10, e.getY());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseEntered(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseExited(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void lostOwnership(Clipboard clipboard, Transferable contents)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent e) {
/* 347 */     if ((e.getSource() == this.txtInput) && 
/* 348 */       (e.getKeyCode() == 10))
/* 349 */       this.btnEnter.doClick();
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent e)
/*     */   {
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     calculator.CalculatorTab
 * JD-Core Version:    0.6.2
 */