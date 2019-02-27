/*    */ package main;
/*    */ 
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.UnsupportedLookAndFeelException;
/*    */ 
/*    */ public class Main
/*    */ {
/*    */   public static void showMainWindow()
/*    */   {
/* 21 */     MainWindow window = new MainWindow();
/* 22 */     window.setVisible(true);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 30 */     SwingUtilities.invokeLater(new Runnable()
/*    */     {
/*    */       public void run() {
/*    */         try {
/* 34 */           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*    */         }
/*    */         catch (ClassNotFoundException e) {
/* 37 */           e.printStackTrace();
/*    */         }
/*    */         catch (InstantiationException e) {
/* 40 */           e.printStackTrace();
/*    */         }
/*    */         catch (IllegalAccessException e) {
/* 43 */           e.printStackTrace();
/*    */         }
/*    */         catch (UnsupportedLookAndFeelException e) {
/* 46 */           e.printStackTrace();
/*    */         }
/* 48 */         Main.showMainWindow();
/*    */       }
/*    */     });
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     main.Main
 * JD-Core Version:    0.6.2
 */