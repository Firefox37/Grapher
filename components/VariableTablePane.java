/*    */ package components;
/*    */ 
/*    */ import exceptions.InvalidVariableNameException;
/*    */ import expressions.Variable;
/*    */ import expressions.VariableList;
/*    */ import java.util.Iterator;
/*    */ import java.util.Vector;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ 
/*    */ public class VariableTablePane extends JTable
/*    */ {
/*    */   private static final long serialVersionUID = 8945708880818610966L;
/* 25 */   private static DefaultTableModel tableModel = new DefaultTableModel();
/*    */ 
/*    */   public VariableTablePane()
/*    */   {
/* 31 */     setModel(tableModel);
/* 32 */     tableModel.addColumn("Variable");
/* 33 */     tableModel.addColumn("Value");
/* 34 */     tableModel.setColumnCount(2);
/*    */   }
/*    */ 
/*    */   public boolean isCellEditable(int row, int column)
/*    */   {
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   public static void addRow(Vector<String> row) {
/* 43 */     tableModel.addRow(row);
/*    */   }
/*    */ 
/*    */   public static void setRowCount(int rowCount)
/*    */   {
/* 48 */     tableModel.setRowCount(rowCount);
/*    */   }
/*    */ 
/*    */   public static void refreshTable() throws InvalidVariableNameException
/*    */   {
/* 53 */     VariableList.createIfEmpty();
/*    */ 
/* 56 */     setRowCount(0);
/*    */ 
/* 58 */     Iterator itr = VariableList.getVariables().iterator();
/*    */ 
/* 60 */     while (itr.hasNext()) {
/* 61 */       Variable curVariable = (Variable)itr.next();
/* 62 */       Vector row = new Vector(2);
/* 63 */       row.add(curVariable.getVariableName());
/* 64 */       row.add(Double.valueOf(curVariable.getVariableValue()));
/*    */ 
/* 66 */       addRow(row);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     components.VariableTablePane
 * JD-Core Version:    0.6.2
 */