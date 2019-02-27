/*     */ package equations;
/*     */ 
/*     */ import components.SmartTextField;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.text.DefaultEditorKit.CopyAction;
/*     */ import javax.swing.text.DefaultEditorKit.CutAction;
/*     */ import javax.swing.text.DefaultEditorKit.PasteAction;
/*     */ 
/*     */ public class EquationInput extends JPanel
/*     */   implements ActionListener, MouseListener
/*     */ {
/*     */   private static final long serialVersionUID = -4217107958439218081L;
/*     */   private JButton btnName;
/*     */   private JPanel labelPanel;
/*     */   private SmartTextField input;
/*     */   private JPopupMenu mnuRightClick;
/*     */   private Color color;
/*     */ 
/*     */   public EquationInput(String name, Color color)
/*     */   {
/*  42 */     setLayout(new FlowLayout());
/*  43 */     this.btnName = new JButton(name);
/*  44 */     this.input = new SmartTextField(20);
/*  45 */     this.labelPanel = new JPanel();
/*  46 */     this.color = color;
/*     */ 
/*  48 */     this.btnName.setForeground(color);
/*  49 */     this.btnName.setBorderPainted(true);
/*  50 */     this.btnName.setContentAreaFilled(false);
/*  51 */     this.btnName.setHorizontalAlignment(4);
/*  52 */     this.btnName.setPreferredSize(new Dimension(60, 20));
/*  53 */     this.labelPanel.setBackground(Color.lightGray);
/*     */ 
/*  55 */     this.mnuRightClick = new JPopupMenu();
/*     */ 
/*  57 */     this.btnName.addActionListener(this);
/*  58 */     this.input.addMouseListener(this);
/*     */ 
/*  60 */     add(this.labelPanel);
/*  61 */     this.labelPanel.add(this.btnName);
/*  62 */     this.labelPanel.add(this.input);
/*     */   }
/*     */ 
/*     */   public SmartTextField getInput() {
/*  66 */     return this.input;
/*     */   }
/*     */ 
/*     */   public void setInput(SmartTextField input) {
/*  70 */     this.input = input;
/*     */   }
/*     */ 
/*     */   public JButton getBtnName() {
/*  74 */     return this.btnName;
/*     */   }
/*     */ 
/*     */   public void setBtnName(JButton name) {
/*  78 */     this.btnName = name;
/*     */   }
/*     */ 
/*     */   public Color getColor() {
/*  82 */     return this.color;
/*     */   }
/*     */ 
/*     */   public void setColor(Color color) {
/*  86 */     this.color = color;
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/*  91 */     if (e.getSource() == this.btnName) {
/*  92 */       Color clr = JColorChooser.showDialog(this.btnName, "Color Chooser", this.btnName.getForeground());
/*  93 */       if (clr != null) {
/*  94 */         this.btnName.setForeground(clr);
/*  95 */         this.color = clr;
/*     */       }
/*  97 */       revalidate();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseClicked(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 108 */     if ((e.getSource() == this.input) && ((e.isPopupTrigger()) || (e.getModifiers() == 4))) {
/* 109 */       this.input.requestFocus();
/*     */ 
/* 111 */       this.mnuRightClick.removeAll();
/*     */ 
/* 113 */       JMenuItem mnuItem = new JMenuItem(new DefaultEditorKit.CutAction());
/* 114 */       mnuItem.setText("Cut");
/* 115 */       this.mnuRightClick.add(mnuItem);
/* 116 */       mnuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
/* 117 */       mnuItem.setText("Copy");
/* 118 */       this.mnuRightClick.add(mnuItem);
/* 119 */       mnuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
/* 120 */       mnuItem.setText("Paste");
/* 121 */       this.mnuRightClick.add(mnuItem);
/*     */ 
/* 123 */       this.mnuRightClick.show(this.input, e.getX() + 10, e.getY());
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
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equations.EquationInput
 * JD-Core Version:    0.6.2
 */