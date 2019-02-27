/*    */ package components;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ import javax.swing.table.TableModel;
/*    */ 
/*    */ public class UneditableTable extends JTable
/*    */ {
/*    */   private static final long serialVersionUID = 9014866183495581121L;
/* 17 */   protected DefaultTableModel tableModel = new DefaultTableModel();
/*    */ 
/*    */   public UneditableTable(TableModel tableModel)
/*    */   {
/* 23 */     super(tableModel);
/* 24 */     this.tableModel = ((DefaultTableModel)tableModel);
/*    */   }
/*    */ 
/*    */   public boolean isCellEditable(int row, int column)
/*    */   {
/* 29 */     return false;
/*    */   }
/*    */ 
/*    */   public void addRow(Vector<String> row)
/*    */   {
/* 38 */     this.tableModel.addRow(row);
/*    */   }
/*    */ 
/*    */   public void setRowCount(int rowCount)
/*    */   {
/* 46 */     this.tableModel.setRowCount(rowCount);
/*    */   }
/*    */ 
/*    */   public static void refreshTable()
/*    */   {
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     components.UneditableTable
 * JD-Core Version:    0.6.2
 */