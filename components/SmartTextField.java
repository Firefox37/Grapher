/*     */ package components;
/*     */ 
/*     */ import Constants.BlackLists;
/*     */ import expressions.Variable;
/*     */ import expressions.VariableList;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.text.Caret;
/*     */ 
/*     */ public class SmartTextField extends JTextField
/*     */   implements KeyListener, ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = 5457545908621424875L;
/*  30 */   private static ArrayList<String> list = new ArrayList();
/*  31 */   private JPopupMenu autoCompleteMenu = new JPopupMenu();
/*     */   private String curString;
/*     */ 
/*     */   public SmartTextField(String text, int columns)
/*     */   {
/*  35 */     super(text, columns);
/*  36 */     setUp();
/*     */   }
/*     */ 
/*     */   public SmartTextField(int columns) {
/*  40 */     super(columns);
/*  41 */     setUp();
/*     */   }
/*     */ 
/*     */   public SmartTextField(String text) {
/*  45 */     super(text);
/*  46 */     setUp();
/*     */   }
/*     */ 
/*     */   public SmartTextField() {
/*  50 */     setUp();
/*     */   }
/*     */ 
/*     */   private void setUp() {
/*  54 */     list.clear();
/*  55 */     addKeyListener(this);
/*  56 */     getCaret().setMagicCaretPosition(new Point(0, 0));
/*  57 */     this.curString = "";
/*     */ 
/*  59 */     rebuildList();
/*     */   }
/*     */ 
/*     */   public static void rebuildList() {
/*  63 */     list.clear();
/*  64 */     for (String str : BlackLists.variableBlackList) {
/*  65 */       list.add(str);
/*     */     }
/*  67 */     for (Variable var : VariableList.getVariables())
/*  68 */       list.add(var.getVariableName());
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent e)
/*     */   {
/*  74 */     int position = getCaretPosition();
/*  75 */     this.autoCompleteMenu.setVisible(false);
/*  76 */     this.autoCompleteMenu.removeAll();
/*  77 */     if (e.getKeyChar() == '(') {
/*  78 */       setText(getText().substring(0, position) + ")" + getText().substring(position));
/*  79 */       setCaretPosition(position);
/*  80 */       this.curString = "";
/*     */     }
/*     */     else
/*     */     {
/*     */       int right;
/*  81 */       if ((getText().length() != position) && (e.getKeyChar() == ')') && (getText().substring(position, position + 1).equals(")")))
/*     */       {
/*  83 */         int left = 0;
/*  84 */         right = 0;
/*  85 */         String txt = getText();
/*  86 */         for (int i = 0; i < txt.length(); i++) {
/*  87 */           if ((txt.charAt(i) == '(') && (i < position)) {
/*  88 */             left++;
/*     */           }
/*  90 */           if (txt.charAt(i) == ')') {
/*  91 */             right++;
/*     */           }
/*     */         }
/*  94 */         if (left >= right) {
/*  95 */           setText(getText().substring(0, position) + getText().substring(position + 1));
/*  96 */           setCaretPosition(position);
/*     */         }
/*     */       }
/*  99 */       else if (((e.getKeyChar() >= 'a') && (e.getKeyChar() <= 'z')) || ((e.getKeyChar() >= 'A') && (e.getKeyChar() <= 'Z'))) {
/* 100 */         this.curString += e.getKeyChar();
/* 101 */         for (String str : list) {
/* 102 */           if (str.startsWith(this.curString)) {
/* 103 */             JMenuItem mi = new JMenuItem(str);
/* 104 */             mi.addActionListener(this);
/* 105 */             mi.addKeyListener(this);
/* 106 */             this.autoCompleteMenu.add(mi);
/*     */           }
/*     */         }
/* 109 */         if (this.autoCompleteMenu.getComponents().length > 0) {
/* 110 */           this.autoCompleteMenu.show(this, getCaret().getMagicCaretPosition().x + 7, (int)(getHeight() * 0.75D));
/* 111 */           requestFocusInWindow();
/* 112 */           int pos = getSelectionEnd();
/* 113 */           select(pos, pos);
/* 114 */           setCaretPosition(pos);
/*     */         }
/*     */       } else {
/* 117 */         this.curString = "";
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/* 125 */     if (e.getKeyCode() == 8) {
/* 126 */       int position = getCaretPosition();
/* 127 */       if ((position != 0) && (position != getText().length())) {
/* 128 */         String prev = getText().substring(position - 1, position);
/* 129 */         String next = getText().substring(position, position + 1);
/* 130 */         if ((prev.equals("(")) && (next.equals(")"))) {
/* 131 */           setText(getText().substring(0, position - 1) + getText().substring(position));
/* 132 */           setCaretPosition(position);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent e)
/*     */   {
/* 140 */     if (e.getSource() == this) {
/* 141 */       int[] validCodes = { 16, 17, 18, 38, 40 };
/* 142 */       if ((e.getKeyCode() < 65) || (e.getKeyCode() > 90)) {
/* 143 */         boolean valid = false;
/* 144 */         for (int i : validCodes) {
/* 145 */           if (e.getKeyCode() == i) {
/* 146 */             valid = true;
/*     */           }
/*     */         }
/* 149 */         if (!valid) {
/* 150 */           this.curString = "";
/* 151 */           this.autoCompleteMenu.setVisible(false);
/*     */         }
/*     */       }
/* 154 */       if (e.getKeyCode() == 40)
/* 155 */         this.autoCompleteMenu.requestFocusInWindow();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 162 */     if (e.getSource().getClass() == JMenuItem.class) {
/* 163 */       int position = getCaretPosition();
/* 164 */       String str = getText();
/* 165 */       String toInsert = ((JMenuItem)e.getSource()).getText();
/* 166 */       setText(str.substring(0, position - this.curString.length()) + toInsert + str.substring(position));
/* 167 */       setCaretPosition(position + toInsert.length() - this.curString.length());
/* 168 */       this.curString = "";
/*     */     }
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     components.SmartTextField
 * JD-Core Version:    0.6.2
 */