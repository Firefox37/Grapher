/*    */ package exceptions;
/*    */ 
/*    */ public class UnsetVariableException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -738450447401979940L;
/*    */   String message;
/*    */ 
/*    */   public UnsetVariableException(String _message)
/*    */   {
/* 31 */     this.message = _message;
/*    */   }
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 36 */     return this.message;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     exceptions.UnsetVariableException
 * JD-Core Version:    0.6.2
 */