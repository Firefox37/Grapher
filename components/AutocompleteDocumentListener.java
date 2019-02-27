/*    */ package components;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import javax.swing.event.DocumentEvent;
/*    */ import javax.swing.event.DocumentListener;
/*    */ 
/*    */ public class AutocompleteDocumentListener
/*    */   implements DocumentListener
/*    */ {
/*    */   public void insertUpdate(DocumentEvent e)
/*    */   {
/* 20 */     e.getDocument();
/* 21 */     System.out.println("Insert Update: title");
/* 22 */     System.out.println("Offset: " + e.getOffset());
/*    */   }
/*    */ 
/*    */   public void removeUpdate(DocumentEvent e)
/*    */   {
/* 27 */     e.getDocument();
/* 28 */     System.out.println("Remove Update: title");
/*    */   }
/*    */ 
/*    */   public void changedUpdate(DocumentEvent e)
/*    */   {
/* 33 */     e.getDocument();
/* 34 */     System.out.println("Change Update:title");
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     components.AutocompleteDocumentListener
 * JD-Core Version:    0.6.2
 */