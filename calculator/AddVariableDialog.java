/*    */ package calculator;
/*    */ 
/*    */ import components.SmartTextField;
/*    */ import components.VariableTablePane;
/*    */ import exceptions.InvalidVariableNameException;
/*    */ import expressions.Expression;
/*    */ import expressions.Variable;
/*    */ import expressions.VariableList;
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.GridLayout;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JOptionPane;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public class AddVariableDialog extends JFrame
/*    */   implements ActionListener
/*    */ {
/*    */   private static final long serialVersionUID = 2791185538174056163L;
/*    */   private JLabel lblVariableName;
/*    */   private JLabel lblVariableValue;
/*    */   private JTextField txtVariableName;
/*    */   private SmartTextField txtVariableValue;
/*    */   private JButton btnAdd;
/*    */   private JButton btnClose;
/*    */   private JPanel topPanel;
/*    */   private JPanel middlePanel;
/*    */   private JPanel bottomPanel;
/*    */ 
/*    */   public AddVariableDialog()
/*    */   {
/* 42 */     setLayout(new GridLayout(0, 1));
/* 43 */     setTitle("Add Variable");
/*    */ 
/* 45 */     this.topPanel = new JPanel(new BorderLayout());
/* 46 */     this.middlePanel = new JPanel(new BorderLayout());
/* 47 */     this.bottomPanel = new JPanel(new FlowLayout());
/*    */ 
/* 49 */     this.lblVariableName = new JLabel("Variable Name:");
/* 50 */     this.txtVariableName = new JTextField(10);
/* 51 */     this.lblVariableValue = new JLabel("Variable Value:");
/* 52 */     this.txtVariableValue = new SmartTextField(10);
/*    */ 
/* 54 */     this.btnAdd = new JButton("Add");
/* 55 */     this.btnClose = new JButton("Close");
/* 56 */     this.btnAdd.addActionListener(this);
/* 57 */     this.btnClose.addActionListener(this);
/*    */ 
/* 59 */     this.topPanel.add(this.lblVariableName, "West");
/* 60 */     this.topPanel.add(this.txtVariableName, "East");
/*    */ 
/* 62 */     this.middlePanel.add(this.lblVariableValue, "West");
/* 63 */     this.middlePanel.add(this.txtVariableValue, "East");
/*    */ 
/* 65 */     this.bottomPanel.add(this.btnAdd);
/* 66 */     this.bottomPanel.add(this.btnClose);
/*    */ 
/* 68 */     add(this.topPanel);
/* 69 */     add(this.middlePanel);
/* 70 */     add(this.bottomPanel);
/*    */   }
/*    */ 
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/*    */     try {
/* 76 */       if (e.getSource() == this.btnAdd) {
/* 77 */         boolean nameExists = false;
/* 78 */         Variable var = new Variable(this.txtVariableName.getText(), Expression.evaluate(this.txtVariableValue.getText()));
/*    */ 
/* 80 */         if ((Double.isInfinite(var.getVariableValue())) || (Double.isNaN(var.getVariableValue()))) {
/* 81 */           JOptionPane.showMessageDialog(this, "Variable value is invalid.", "Error", 0);
/* 82 */           return;
/*    */         }
/*    */ 
/* 85 */         for (Variable curVar : VariableList.getVariables()) {
/* 86 */           if (var.getVariableName().equals(curVar.getVariableName())) {
/* 87 */             curVar.setVariableValue(var.getVariableValue());
/* 88 */             nameExists = true;
/* 89 */             break;
/*    */           }
/*    */         }
/* 92 */         if (!nameExists) {
/* 93 */           VariableList.addVariable(var);
/*    */         }
/* 95 */         VariableTablePane.refreshTable();
/* 96 */         this.txtVariableName.setText("");
/* 97 */         this.txtVariableValue.setText("");
/*    */       }
/*    */ 
/* 101 */       if (e.getSource() == this.btnClose)
/* 102 */         dispose();
/*    */     }
/*    */     catch (InvalidVariableNameException ex) {
/* 105 */       JOptionPane.showMessageDialog(this, ex.getMessage());
/*    */     } catch (Exception x) {
/* 107 */       JOptionPane.showMessageDialog(this, x.getMessage());
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     calculator.AddVariableDialog
 * JD-Core Version:    0.6.2
 */