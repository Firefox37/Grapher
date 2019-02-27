/*     */ package graphing;
/*     */ 
/*     */ import components.SmartTextField;
/*     */ import equations.EquationInput;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class DrawLineBetweenPointsDialog extends JFrame
/*     */   implements ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = -6004966477810832476L;
/*     */   private GraphingTab graphTab;
/*     */   private JComboBox<?> cbFrom;
/*     */   private JComboBox<?> cbTo;
/*     */   private JPanel inputPanel;
/*     */   private JPanel bottomPanel;
/*     */   private JButton btnDraw;
/*     */   private JButton btnClose;
/*  37 */   private DecimalFormat df = new DecimalFormat("#.##########");
/*     */ 
/*     */   public DrawLineBetweenPointsDialog(GraphingTab graphTab)
/*     */   {
/*  41 */     setLayout(new BorderLayout());
/*  42 */     setTitle("Draw Line");
/*     */ 
/*  44 */     this.graphTab = graphTab;
/*     */ 
/*  46 */     this.inputPanel = new JPanel(new GridLayout(0, 2));
/*  47 */     this.bottomPanel = new JPanel();
/*     */ 
/*  49 */     this.cbFrom = new JComboBox(GraphPanel.getPoints().keySet().toArray());
/*  50 */     this.cbTo = new JComboBox(GraphPanel.getPoints().keySet().toArray());
/*     */ 
/*  52 */     this.btnDraw = new JButton("Draw");
/*  53 */     this.btnClose = new JButton("Close");
/*  54 */     this.btnDraw.addActionListener(this);
/*  55 */     this.btnClose.addActionListener(this);
/*     */ 
/*  57 */     this.inputPanel.add(new JLabel("Point 1:"));
/*  58 */     this.inputPanel.add(this.cbFrom);
/*  59 */     this.inputPanel.add(new JLabel("Point 2:"));
/*  60 */     this.inputPanel.add(this.cbTo);
/*  61 */     this.bottomPanel.add(this.btnDraw);
/*  62 */     this.bottomPanel.add(this.btnClose);
/*     */ 
/*  64 */     add(this.inputPanel, "Center");
/*  65 */     add(this.bottomPanel, "South");
/*     */ 
/*  67 */     pack();
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/*  73 */     if (e.getSource() == this.btnDraw) {
/*  74 */       Point2D.Double pt1 = GraphPanel.getPoint((String)this.cbTo.getSelectedItem());
/*  75 */       Point2D.Double pt2 = GraphPanel.getPoint((String)this.cbFrom.getSelectedItem());
/*     */ 
/*  77 */       if (pt1.equals(pt2)) {
/*  78 */         JOptionPane.showMessageDialog(this, "Select different points.", "Error", 0);
/*  79 */         return;
/*     */       }
/*  81 */       if (pt1.getX() == pt2.getX()) {
/*  82 */         JOptionPane.showMessageDialog(this, "Can not draw vertical lines!", "Error", 0);
/*  83 */         return;
/*     */       }
/*     */ 
/*  86 */       double slope = (pt1.getY() - pt2.getY()) / (pt1.getX() - pt2.getX());
/*  87 */       double yIntercept = pt1.getY() - slope * pt1.getX();
/*  88 */       boolean foundEmpty = false;
/*  89 */       for (int i = 0; i < this.graphTab.getEquationCount(); i++) {
/*  90 */         if (((EquationInput)this.graphTab.getEquationPanel().getComponent(i)).getInput().getText().isEmpty()) {
/*  91 */           ((EquationInput)this.graphTab.getEquationPanel().getComponent(i)).getInput().setText(this.df.format(slope) + "x+(" + this.df.format(yIntercept) + ")");
/*  92 */           foundEmpty = true;
/*  93 */           break;
/*     */         }
/*     */       }
/*  96 */       if (!foundEmpty) {
/*  97 */         this.graphTab.getBtnAddEquation().doClick();
/*  98 */         ((EquationInput)this.graphTab.getEquationPanel().getComponent(this.graphTab.getEquationCount() - 1)).getInput().setText(this.df.format(slope) + "x+(" + this.df.format(yIntercept) + ")");
/*     */       }
/* 100 */       this.graphTab.getBtnGraph().doClick();
/* 101 */       dispose();
/*     */     }
/*     */ 
/* 105 */     if (e.getSource() == this.btnClose)
/* 106 */       dispose();
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     graphing.DrawLineBetweenPointsDialog
 * JD-Core Version:    0.6.2
 */