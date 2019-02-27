/*     */ package graphing;
/*     */ 
/*     */ import components.SmartTextField;
/*     */ import equations.Equation;
/*     */ import equations.EquationInput;
/*     */ import expressions.Expression;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class AddPointAtXValueDialog extends JFrame
/*     */   implements ActionListener, KeyListener
/*     */ {
/*     */   private static final long serialVersionUID = 2895224375533289494L;
/*     */   private GraphingTab graphTab;
/*     */   private SmartTextField txtValue;
/*     */   private JTextField txtPointName;
/*     */   private JComboBox<String> cbEquation;
/*     */   private JPanel inputPanel;
/*     */   private JPanel bottomPanel;
/*     */   private JButton btnDraw;
/*     */   private JButton btnClose;
/*     */   private String expression;
/*     */ 
/*     */   public AddPointAtXValueDialog(GraphingTab graphTab)
/*     */   {
/*  46 */     setLayout(new BorderLayout());
/*  47 */     setTitle("Add Point to Equation");
/*     */ 
/*  49 */     this.graphTab = graphTab;
/*     */ 
/*  51 */     this.inputPanel = new JPanel(new GridLayout(0, 2));
/*  52 */     this.bottomPanel = new JPanel();
/*     */ 
/*  54 */     this.cbEquation = new JComboBox();
/*  55 */     this.txtValue = new SmartTextField();
/*  56 */     this.txtPointName = new JTextField();
/*     */ 
/*  58 */     for (Component eq : graphTab.getEquationPanel().getComponents()) {
/*  59 */       if (!((EquationInput)eq).getInput().getText().isEmpty()) {
/*  60 */         this.cbEquation.addItem(((EquationInput)eq).getBtnName().getText());
/*     */       }
/*     */     }
/*     */ 
/*  64 */     this.btnDraw = new JButton("Draw");
/*  65 */     this.btnClose = new JButton("Close");
/*  66 */     this.btnDraw.addActionListener(this);
/*  67 */     this.btnClose.addActionListener(this);
/*  68 */     this.txtValue.addKeyListener(this);
/*  69 */     this.txtPointName.addActionListener(this);
/*  70 */     this.cbEquation.addKeyListener(this);
/*     */ 
/*  72 */     this.inputPanel.add(new JLabel("Point Name:"));
/*  73 */     this.inputPanel.add(this.txtPointName);
/*  74 */     this.inputPanel.add(new JLabel("Equation:"));
/*  75 */     this.inputPanel.add(this.cbEquation);
/*  76 */     this.inputPanel.add(new JLabel("At X Value:"));
/*  77 */     this.inputPanel.add(this.txtValue);
/*  78 */     this.bottomPanel.add(this.btnDraw);
/*  79 */     this.bottomPanel.add(this.btnClose);
/*     */ 
/*  81 */     add(this.inputPanel, "Center");
/*  82 */     add(this.bottomPanel, "South");
/*     */ 
/*  84 */     pack();
/*  85 */     setMinimumSize(getSize());
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/*  92 */     if (e.getSource() == this.btnDraw) {
/*     */       double x;
/*     */       try {
/*  95 */         x = Expression.evaluate(this.txtValue.getText());
/*     */       }
/*     */       catch (Exception e1)
/*     */       {
/*     */         double x;
/*  97 */         x = (0.0D / 0.0D);
/*     */       }
/*     */ 
/* 100 */       for (Component eq : this.graphTab.getEquationPanel().getComponents()) {
/* 101 */         if (((EquationInput)eq).getBtnName().getText().equals(this.cbEquation.getSelectedItem())) {
/* 102 */           this.expression = ((EquationInput)eq).getInput().getText();
/*     */         }
/*     */       }
/*     */ 
/* 106 */       GraphPanel.addPoint(this.txtPointName.getText(), x, Equation.evaluate(this.expression, x, true));
/* 107 */       this.graphTab.repaint();
/*     */     }
/*     */ 
/* 111 */     if (e.getSource() == this.btnClose)
/* 112 */       dispose();
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
/* 125 */     if (e.getKeyCode() == 10)
/* 126 */       this.btnDraw.doClick();
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     graphing.AddPointAtXValueDialog
 * JD-Core Version:    0.6.2
 */