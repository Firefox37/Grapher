/*    */ package graphing;
/*    */ 
/*    */ import components.UneditableTable;
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.geom.Point2D.Double;
/*    */ import java.text.DecimalFormat;
/*    */ import java.util.HashMap;
/*    */ import java.util.Vector;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ 
/*    */ public class PointValuesTableWindow extends JFrame
/*    */   implements ActionListener
/*    */ {
/*    */   private static final long serialVersionUID = 7855337057420942613L;
/* 31 */   private DefaultTableModel tableModel = new DefaultTableModel();
/* 32 */   private UneditableTable table = new UneditableTable(this.tableModel);
/* 33 */   private JScrollPane scrollPane = new JScrollPane(this.table);
/*    */   private JButton btnRefresh;
/*    */   private JButton btnClose;
/*    */   private JPanel buttonPanel;
/* 36 */   private DecimalFormat df = new DecimalFormat("#.##########");
/*    */ 
/*    */   public PointValuesTableWindow() {
/* 39 */     setTitle("Table of Points");
/* 40 */     setLayout(new BorderLayout());
/* 41 */     this.scrollPane = new JScrollPane(this.table);
/* 42 */     this.buttonPanel = new JPanel();
/* 43 */     this.btnRefresh = new JButton("Refresh");
/* 44 */     this.btnClose = new JButton("Close");
/*    */ 
/* 46 */     this.buttonPanel.add(this.btnRefresh);
/* 47 */     this.buttonPanel.add(this.btnClose);
/*    */ 
/* 49 */     refreshTable();
/*    */ 
/* 51 */     add(this.scrollPane, "Center");
/* 52 */     add(this.buttonPanel, "South");
/* 53 */     setMinimumSize(new Dimension(200, 300));
/*    */ 
/* 55 */     this.btnRefresh.addActionListener(this);
/* 56 */     this.btnClose.addActionListener(this);
/*    */   }
/*    */ 
/*    */   private void refreshTable()
/*    */   {
/* 61 */     this.tableModel.setRowCount(0);
/* 62 */     this.tableModel.setColumnCount(0);
/* 63 */     this.tableModel.addColumn("Name");
/* 64 */     this.tableModel.addColumn("X Value");
/* 65 */     this.tableModel.addColumn("Y Value");
/* 66 */     for (String key : GraphPanel.getPoints().keySet()) {
/* 67 */       Vector row = new Vector(3);
/* 68 */       row.add(key);
/* 69 */       row.add(this.df.format(GraphPanel.getPoint(key).getX()));
/* 70 */       row.add(this.df.format(GraphPanel.getPoint(key).getY()));
/*    */ 
/* 72 */       this.tableModel.addRow(row);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void actionPerformed(ActionEvent e) {
/* 77 */     if (e.getSource() == this.btnRefresh) {
/* 78 */       refreshTable();
/*    */     }
/* 80 */     if (e.getSource() == this.btnClose)
/* 81 */       dispose();
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     graphing.PointValuesTableWindow
 * JD-Core Version:    0.6.2
 */