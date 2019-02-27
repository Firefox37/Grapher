/*     */ package graphing;
/*     */ 
/*     */ import components.SmartTextField;
/*     */ import expressions.Expression;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.text.DecimalFormat;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class AddPointDialog extends JFrame
/*     */   implements ActionListener, KeyListener
/*     */ {
/*     */   private static final long serialVersionUID = 350325605840668713L;
/*     */   private JTextField txtPointName;
/*     */   private SmartTextField txtXValue;
/*     */   private SmartTextField txtYValue;
/*     */   private JButton btnAdd;
/*     */   private JButton btnClose;
/*     */   private JPanel topPanel;
/*     */   private JPanel bottomPanel;
/*     */   private JPanel caller;
/*     */ 
/*     */   public AddPointDialog(JPanel caller)
/*     */   {
/*  45 */     this.caller = caller;
/*  46 */     makeLayout();
/*  47 */     pack();
/*     */   }
/*     */ 
/*     */   public AddPointDialog(JPanel caller, double x, double y)
/*     */   {
/*  52 */     makeLayout();
/*     */ 
/*  54 */     this.caller = caller;
/*  55 */     DecimalFormat df = new DecimalFormat("#.##########");
/*  56 */     this.txtXValue.setText(df.format(x));
/*  57 */     this.txtYValue.setText(df.format(y));
/*  58 */     pack();
/*  59 */     setMinimumSize(getSize());
/*     */   }
/*     */ 
/*     */   private void makeLayout() {
/*  63 */     setLayout(new BorderLayout());
/*  64 */     setTitle("Add Point");
/*     */ 
/*  66 */     this.topPanel = new JPanel(new GridLayout(0, 2));
/*  67 */     this.bottomPanel = new JPanel(new FlowLayout());
/*     */ 
/*  69 */     this.txtPointName = new JTextField();
/*  70 */     this.txtXValue = new SmartTextField();
/*  71 */     this.txtYValue = new SmartTextField();
/*     */ 
/*  73 */     this.btnAdd = new JButton("Add");
/*  74 */     this.btnClose = new JButton("Close");
/*  75 */     this.btnAdd.addActionListener(this);
/*  76 */     this.btnClose.addActionListener(this);
/*  77 */     this.txtPointName.addKeyListener(this);
/*  78 */     this.txtXValue.addKeyListener(this);
/*  79 */     this.txtYValue.addKeyListener(this);
/*     */ 
/*  81 */     this.topPanel.add(new JLabel("Point Name:"));
/*  82 */     this.topPanel.add(this.txtPointName);
/*     */ 
/*  84 */     this.topPanel.add(new JLabel("X:"));
/*  85 */     this.topPanel.add(this.txtXValue);
/*     */ 
/*  87 */     this.topPanel.add(new JLabel("Y:"));
/*  88 */     this.topPanel.add(this.txtYValue);
/*     */ 
/*  90 */     this.bottomPanel.add(this.btnAdd);
/*  91 */     this.bottomPanel.add(this.btnClose);
/*     */ 
/*  93 */     add(this.topPanel, "Center");
/*  94 */     add(this.bottomPanel, "South");
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/*     */     try
/*     */     {
/* 101 */       if (e.getSource() == this.btnAdd) {
/* 102 */         String name = this.txtPointName.getText();
/* 103 */         double x = Expression.evaluate(this.txtXValue.getText());
/* 104 */         double y = Expression.evaluate(this.txtYValue.getText());
/* 105 */         GraphPanel.addPoint(name, x, y);
/*     */ 
/* 107 */         this.caller.repaint();
/* 108 */         dispose();
/*     */       }
/*     */ 
/* 112 */       if (e.getSource() == this.btnClose)
/* 113 */         dispose();
/*     */     }
/*     */     catch (Exception ex) {
/* 116 */       JOptionPane.showMessageDialog(this, ex.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent e)
/*     */   {
/* 130 */     if (e.getKeyCode() == 10)
/* 131 */       this.btnAdd.doClick();
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     graphing.AddPointDialog
 * JD-Core Version:    0.6.2
 */