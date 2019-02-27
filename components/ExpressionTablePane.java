/*    */ package components;
/*    */ 
/*    */ import expressions.Expression;
/*    */ import expressions.ExpressionList;
/*    */ import java.text.DecimalFormat;
/*    */ import java.util.Iterator;
/*    */ import java.util.Vector;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ 
/*    */ public class ExpressionTablePane extends JTable
/*    */ {
/*    */   private static final long serialVersionUID = -4737506904301505173L;
/* 22 */   private static DefaultTableModel tableModel = new DefaultTableModel();
/*    */ 
/*    */   public ExpressionTablePane()
/*    */   {
/* 28 */     setModel(tableModel);
/* 29 */     tableModel.addColumn("Expression");
/* 30 */     tableModel.addColumn("Value");
/* 31 */     tableModel.addColumn("Angle Units");
/* 32 */     tableModel.setColumnCount(3);
/*    */   }
/*    */ 
/*    */   public boolean isCellEditable(int row, int column)
/*    */   {
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */   public static void addRow(Vector<String> row)
/*    */   {
/* 46 */     tableModel.addRow(row);
/*    */   }
/*    */ 
/*    */   public static void setRowCount(int rowCount)
/*    */   {
/* 54 */     tableModel.setRowCount(rowCount);
/*    */   }
/*    */ 
/*    */   public static void refreshTable()
/*    */   {
/* 63 */     setRowCount(0);
/*    */ 
/* 66 */     Iterator itr = ExpressionList.getExpressionList().iterator();
/*    */ 
/* 68 */     while (itr.hasNext()) {
/* 69 */       Expression curExpression = (Expression)itr.next();
/* 70 */       double curValue = curExpression.getValue();
/* 71 */       Vector row = new Vector(2);
/* 72 */       row.add(curExpression.getExpression());
/* 73 */       if ((!Double.isInfinite(curValue)) && (!Double.isNaN(curValue)))
/* 74 */         row.add(new DecimalFormat("#.##########").format(curExpression.getValue()));
/*    */       else {
/* 76 */         row.add("NaN");
/*    */       }
/* 78 */       row.add(curExpression.getAngleUnits());
/*    */ 
/* 80 */       addRow(row);
/*    */     }
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     components.ExpressionTablePane
 * JD-Core Version:    0.6.2
 */