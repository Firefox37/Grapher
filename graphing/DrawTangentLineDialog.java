/*     */ package graphing;
/*     */ 
/*     */ import Constants.ConstValues;
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
/*     */ import java.text.DecimalFormat;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class DrawTangentLineDialog extends JFrame
/*     */   implements ActionListener, KeyListener
/*     */ {
/*     */   private static final long serialVersionUID = 3048756512755408252L;
/*     */   private GraphingTab graphTab;
/*     */   private SmartTextField txtInput;
/*     */   private JComboBox<String> cbEquation;
/*     */   private JPanel inputPanel;
/*     */   private JPanel bottomPanel;
/*     */   private JButton btnDraw;
/*     */   private JButton btnClose;
/*     */   private String expression;
/*  43 */   private DecimalFormat df = new DecimalFormat("#.##########");
/*     */ 
/*     */   public DrawTangentLineDialog(GraphingTab graphTab)
/*     */   {
/*  47 */     setLayout(new BorderLayout());
/*  48 */     setTitle("Draw Tangent Line");
/*     */ 
/*  50 */     this.graphTab = graphTab;
/*     */ 
/*  52 */     this.inputPanel = new JPanel(new GridLayout(0, 2));
/*  53 */     this.bottomPanel = new JPanel();
/*     */ 
/*  55 */     this.cbEquation = new JComboBox();
/*  56 */     this.txtInput = new SmartTextField();
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
/*  68 */     this.txtInput.addKeyListener(this);
/*  69 */     this.cbEquation.addKeyListener(this);
/*     */ 
/*  71 */     this.inputPanel.add(new JLabel("Equation:"));
/*  72 */     this.inputPanel.add(this.cbEquation);
/*  73 */     this.inputPanel.add(new JLabel("At X Value:"));
/*  74 */     this.inputPanel.add(this.txtInput);
/*  75 */     this.bottomPanel.add(this.btnDraw);
/*  76 */     this.bottomPanel.add(this.btnClose);
/*     */ 
/*  78 */     add(this.inputPanel, "Center");
/*  79 */     add(this.bottomPanel, "South");
/*     */ 
/*  81 */     pack();
/*  82 */     setMinimumSize(getSize());
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/*  88 */     if (e.getSource() == this.btnDraw) {
/*  89 */       for (Component eq : this.graphTab.getEquationPanel().getComponents()) {
/*  90 */         if (((EquationInput)eq).getBtnName().getText().equals(this.cbEquation.getSelectedItem())) {
/*  91 */           this.expression = ((EquationInput)eq).getInput().getText();
/*     */         }
/*     */       }
/*     */       double pt1;
/*     */       try
/*     */       {
/*  97 */         pt1 = Expression.evaluate(this.txtInput.getText()) - ConstValues.smallestNum;
/*     */       }
/*     */       catch (Exception e1)
/*     */       {
/*     */         double pt1;
/*  99 */         pt1 = (0.0D / 0.0D);
/*     */       }
/*     */       double pt2;
/*     */       try {
/* 103 */         pt2 = Expression.evaluate(this.txtInput.getText()) + ConstValues.smallestNum;
/*     */       }
/*     */       catch (Exception e2)
/*     */       {
/*     */         double pt2;
/* 105 */         pt2 = (0.0D / 0.0D);
/*     */       }
/*     */ 
/* 108 */       double slope = (Equation.evaluate(this.expression, pt1, true) - Equation.evaluate(this.expression, pt2, true)) / (pt1 - pt2);
/* 109 */       double yIntercept = Equation.evaluate(this.expression, pt1, true) - slope * pt1;
/* 110 */       boolean foundEmpty = false;
/* 111 */       for (int i = 0; i < this.graphTab.getEquationCount(); i++) {
/* 112 */         if (((EquationInput)this.graphTab.getEquationPanel().getComponent(i)).getInput().getText().isEmpty()) {
/* 113 */           ((EquationInput)this.graphTab.getEquationPanel().getComponent(i)).getInput().setText(this.df.format(slope) + "x+(" + this.df.format(yIntercept) + ")");
/* 114 */           foundEmpty = true;
/* 115 */           break;
/*     */         }
/*     */       }
/* 118 */       if (!foundEmpty) {
/* 119 */         this.graphTab.getBtnAddEquation().doClick();
/* 120 */         ((EquationInput)this.graphTab.getEquationPanel().getComponent(this.graphTab.getEquationCount() - 1)).getInput().setText(this.df.format(slope) + "x+(" + this.df.format(yIntercept) + ")");
/*     */       }
/* 122 */       this.graphTab.getBtnGraph().doClick();
/* 123 */       dispose();
/*     */     }
/*     */ 
/* 127 */     if (e.getSource() == this.btnClose)
/* 128 */       dispose();
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
/* 141 */     if (e.getKeyCode() == 10)
/* 142 */       this.btnDraw.doClick();
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     graphing.DrawTangentLineDialog
 * JD-Core Version:    0.6.2
 */