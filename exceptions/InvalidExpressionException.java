/*    */ package exceptions;
/*    */ 
/*    */ public class InvalidExpressionException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 3157576367852769255L;
/*    */   String message;
/*    */ 
/*    */   public InvalidExpressionException(String _message)
/*    */   {
/* 30 */     this.message = _message;
/*    */   }
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 35 */     return this.message;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     exceptions.InvalidExpressionException
 * JD-Core Version:    0.6.2
 */