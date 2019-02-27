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
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class AddMinMaxPointDialog extends JFrame
/*     */   implements ActionListener, KeyListener
/*     */ {
/*     */   private static final long serialVersionUID = 5368511158338772299L;
/*     */   public static final int MIN = 1;
/*     */   public static final int MAX = 2;
/*     */   private GraphingTab graphTab;
/*     */   private SmartTextField txtLowX;
/*     */   private SmartTextField txtHighX;
/*     */   private JTextField txtPointName;
/*     */   private JComboBox<String> cbEquation;
/*     */   private JPanel inputPanel;
/*     */   private JPanel bottomPanel;
/*     */   private JButton btnDraw;
/*     */   private JButton btnClose;
/*     */   private String expression;
/*  48 */   private DecimalFormat df = new DecimalFormat("#.##########");
/*     */   private int MinOrMax;
/*     */ 
/*     */   public AddMinMaxPointDialog(GraphingTab graphTab, double x, double range, int MinOrMax)
/*     */   {
/*  53 */     setLayout(new BorderLayout());
/*  54 */     setTitle("Add Min/Max Point");
/*     */ 
/*  56 */     this.graphTab = graphTab;
/*  57 */     this.MinOrMax = MinOrMax;
/*     */ 
/*  59 */     this.inputPanel = new JPanel(new GridLayout(0, 2));
/*  60 */     this.bottomPanel = new JPanel();
/*     */ 
/*  62 */     this.cbEquation = new JComboBox();
/*  63 */     this.txtPointName = new JTextField();
/*  64 */     this.txtLowX = new SmartTextField(this.df.format(x - range / 20.0D));
/*  65 */     this.txtHighX = new SmartTextField(this.df.format(x + range / 20.0D));
/*     */ 
/*  67 */     for (Component eq : graphTab.getEquationPanel().getComponents()) {
/*  68 */       if (!((EquationInput)eq).getInput().getText().isEmpty()) {
/*  69 */         this.cbEquation.addItem(((EquationInput)eq).getBtnName().getText());
/*     */       }
/*     */     }
/*     */ 
/*  73 */     this.btnDraw = new JButton("Plot Point");
/*  74 */     this.btnClose = new JButton("Close");
/*  75 */     this.btnDraw.addActionListener(this);
/*  76 */     this.btnClose.addActionListener(this);
/*  77 */     this.txtLowX.addKeyListener(this);
/*  78 */     this.txtHighX.addKeyListener(this);
/*  79 */     this.txtPointName.addKeyListener(this);
/*  80 */     this.cbEquation.addKeyListener(this);
/*     */ 
/*  82 */     this.inputPanel.add(new JLabel("Point Name:"));
/*  83 */     this.inputPanel.add(this.txtPointName);
/*  84 */     this.inputPanel.add(new JLabel("Equation:"));
/*  85 */     this.inputPanel.add(this.cbEquation);
/*  86 */     this.inputPanel.add(new JLabel("Low X:"));
/*  87 */     this.inputPanel.add(this.txtLowX);
/*  88 */     this.inputPanel.add(new JLabel("High X:"));
/*  89 */     this.inputPanel.add(this.txtHighX);
/*  90 */     this.bottomPanel.add(this.btnDraw);
/*  91 */     this.bottomPanel.add(this.btnClose);
/*     */ 
/*  93 */     add(this.inputPanel, "Center");
/*  94 */     add(this.bottomPanel, "South");
/*     */ 
/*  96 */     pack();
/*  97 */     setMinimumSize(getSize());
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 103 */     if (e.getSource() == this.btnDraw) {
/* 104 */       for (int i = 0; i < this.graphTab.getEquationCount(); i++) {
/* 105 */         if (((EquationInput)this.graphTab.getEquationPanel().getComponent(i)).getBtnName().getText().equals(this.cbEquation.getSelectedItem())) {
/* 106 */           this.expression = ((EquationInput)this.graphTab.getEquationPanel().getComponent(i)).getInput().getText();
/*     */         }
/*     */       }
/*     */       double start;
/*     */       try
/*     */       {
/* 112 */         start = Math.min(Expression.evaluate(this.txtLowX.getText()), Expression.evaluate(this.txtHighX.getText()));
/*     */       }
/*     */       catch (Exception e2)
/*     */       {
/*     */         double start;
/* 114 */         start = (0.0D / 0.0D);
/*     */       }
/*     */       double finish;
/*     */       try {
/* 118 */         finish = Math.max(Expression.evaluate(this.txtLowX.getText()), Expression.evaluate(this.txtHighX.getText()));
/*     */       }
/*     */       catch (Exception e1)
/*     */       {
/*     */         double finish;
/* 120 */         finish = (0.0D / 0.0D);
/*     */       }
/* 122 */       double interval = Math.abs(finish - start) / 1000.0D;
/* 123 */       boolean foundMin = false;
/* 124 */       double xValue = start;
/* 125 */       double yValue = Equation.evaluate(this.expression, xValue, true);
/*     */ 
/* 127 */       double prev = Equation.evaluate(this.expression, start, true);
/* 128 */       double cur = Equation.evaluate(this.expression, start, true);
/* 129 */       double tracker = 0.0D;
/*     */ 
/* 131 */       while (interval > ConstValues.smallestNum)
/*     */       {
/* 133 */         for (double i = start + interval; i <= finish; i += interval) {
/* 134 */           double next = Equation.evaluate(this.expression, i, true);
/*     */ 
/* 136 */           if (this.MinOrMax == 1) {
/* 137 */             if ((prev > cur) && (cur < next)) {
/* 138 */               foundMin = true;
/* 139 */               xValue = i - interval;
/* 140 */               yValue = cur;
/* 141 */               break;
/*     */             }
/*     */           }
/* 144 */           else if ((prev < cur) && (cur > next)) {
/* 145 */             foundMin = true;
/* 146 */             xValue = i - interval;
/* 147 */             yValue = cur;
/* 148 */             break;
/*     */           }
/*     */ 
/* 152 */           prev = cur;
/* 153 */           cur = next;
/* 154 */           tracker = i;
/*     */         }
/*     */ 
/* 157 */         if (!foundMin)
/*     */         {
/*     */           break;
/*     */         }
/* 161 */         start = tracker - interval;
/* 162 */         interval /= 2.0D;
/*     */       }
/*     */ 
/* 165 */       if (foundMin) {
/* 166 */         GraphPanel.addPoint(this.txtPointName.getText(), xValue, yValue);
/*     */       }
/* 168 */       else if (this.MinOrMax == 1)
/* 169 */         JOptionPane.showMessageDialog(this, "Minimum point not found.");
/*     */       else {
/* 171 */         JOptionPane.showMessageDialog(this, "Maximum point not found.");
/*     */       }
/*     */ 
/* 174 */       this.graphTab.repaint();
/* 175 */       dispose();
/*     */     }
/*     */ 
/* 179 */     if (e.getSource() == this.btnClose)
/* 180 */       dispose();
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
/* 193 */     if (e.getKeyCode() == 10)
/* 194 */       this.btnDraw.doClick();
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     graphing.AddMinMaxPointDialog
 * JD-Core Version:    0.6.2
 */