/*     */ package graphing;
/*     */ 
/*     */ import components.SmartTextField;
/*     */ import components.UneditableTable;
/*     */ import equations.Equation;
/*     */ import equations.EquationInput;
/*     */ import expressions.Expression;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ 
/*     */ public class EquationValueTableWindow extends JFrame
/*     */   implements ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = 6814774794494656725L;
/*  39 */   private DefaultTableModel tableModel = new DefaultTableModel();
/*  40 */   private UneditableTable table = new UneditableTable(this.tableModel);
/*     */   private SmartTextField txtLowX;
/*     */   private SmartTextField txtHighX;
/*     */   private SmartTextField txtInterval;
/*  42 */   private JScrollPane scrollPane = new JScrollPane(this.table);
/*     */   private JButton btnRefresh;
/*     */   private JButton btnClose;
/*     */   private JPanel buttonPanel;
/*     */   private JPanel optionsPanel;
/*     */   private JPanel equationPanel;
/*     */   private JComboBox<String> cbEquation;
/*  46 */   private DecimalFormat df = new DecimalFormat("#.##########");
/*     */ 
/*     */   public EquationValueTableWindow(JPanel equationPanel) {
/*  49 */     setTitle("Table of Points");
/*  50 */     setLayout(new BorderLayout());
/*     */ 
/*  52 */     this.equationPanel = equationPanel;
/*     */ 
/*  54 */     this.txtLowX = new SmartTextField();
/*  55 */     this.txtHighX = new SmartTextField();
/*  56 */     this.txtInterval = new SmartTextField();
/*  57 */     this.scrollPane = new JScrollPane(this.table);
/*  58 */     this.buttonPanel = new JPanel();
/*  59 */     this.optionsPanel = new JPanel(new GridLayout(0, 2));
/*  60 */     this.cbEquation = new JComboBox();
/*  61 */     this.btnRefresh = new JButton("Refresh");
/*  62 */     this.btnClose = new JButton("Close");
/*     */ 
/*  64 */     for (Component eq : equationPanel.getComponents()) {
/*  65 */       if (!((EquationInput)eq).getInput().getText().isEmpty()) {
/*  66 */         this.cbEquation.addItem(((EquationInput)eq).getBtnName().getText());
/*     */       }
/*     */     }
/*     */ 
/*  70 */     this.optionsPanel.add(new JLabel("Equation:"));
/*  71 */     this.optionsPanel.add(this.cbEquation);
/*  72 */     this.optionsPanel.add(new JLabel("From X ="));
/*  73 */     this.optionsPanel.add(this.txtLowX);
/*  74 */     this.optionsPanel.add(new JLabel("To X ="));
/*  75 */     this.optionsPanel.add(this.txtHighX);
/*  76 */     this.optionsPanel.add(new JLabel("Interval:"));
/*  77 */     this.optionsPanel.add(this.txtInterval);
/*     */ 
/*  79 */     this.buttonPanel.add(this.btnRefresh);
/*  80 */     this.buttonPanel.add(this.btnClose);
/*     */ 
/*  82 */     add(this.optionsPanel, "North");
/*  83 */     add(this.scrollPane, "Center");
/*  84 */     add(this.buttonPanel, "South");
/*  85 */     setMinimumSize(new Dimension(200, 300));
/*     */ 
/*  87 */     this.btnRefresh.addActionListener(this);
/*  88 */     this.btnClose.addActionListener(this);
/*     */ 
/*  90 */     this.tableModel.addColumn("X Value");
/*  91 */     this.tableModel.addColumn("Y Value");
/*     */   }
/*  95 */   private void refreshTable() { this.tableModel.setRowCount(0);
/*  96 */     this.tableModel.setColumnCount(0);
/*  97 */     this.tableModel.addColumn("X Value");
/*  98 */     this.tableModel.addColumn("Y Value");
/*     */     double start;
/*     */     try {
/* 103 */       start = Expression.evaluate(this.txtLowX.getText());
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */       double start;
/* 106 */       start = (0.0D / 0.0D);
/*     */     }double finish;
/*     */     try {
/* 109 */       finish = Expression.evaluate(this.txtHighX.getText());
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */       double finish;
/* 112 */       finish = (0.0D / 0.0D);
/*     */     }double interval;
/*     */     try {
/* 115 */       interval = Expression.evaluate(this.txtInterval.getText());
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */       double interval;
/* 118 */       interval = (0.0D / 0.0D);
/*     */     }
/* 120 */     System.out.println(start);
/* 121 */     System.out.println(finish);
/* 122 */     System.out.println(interval);
/*     */ 
/* 124 */     String equation = "";
/*     */ 
/* 126 */     for (Component eq : this.equationPanel.getComponents()) {
/* 127 */       ((EquationInput)eq).getInput().getText();
/* 128 */       if (((EquationInput)eq).getBtnName().getText().equals(this.cbEquation.getSelectedItem())) {
/* 129 */         equation = ((EquationInput)eq).getInput().getText();
/* 130 */         equation = Expression.formatExpression(equation);
/* 131 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 135 */     for (double i = start; i <= finish; i += interval) {
/* 136 */       Vector row = new Vector(2);
/* 137 */       row.add(this.df.format(i));
/* 138 */       row.add(this.df.format(Equation.evaluate(equation, i, false)));
/*     */ 
/* 140 */       this.tableModel.addRow(row);
/*     */     }
/* 142 */     repaint(); }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 146 */     if (e.getSource() == this.btnRefresh) {
/* 147 */       refreshTable();
/*     */     }
/* 149 */     if (e.getSource() == this.btnClose)
/* 150 */       dispose();
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     graphing.EquationValueTableWindow
 * JD-Core Version:    0.6.2
 */