/*    */ package exceptions;
/*    */ 
/*    */ public class InvalidVariableNameException extends Throwable
/*    */ {
/*    */   private static final long serialVersionUID = 4944091275744603344L;
/* 18 */   private String message = "";
/*    */ 
/*    */   public InvalidVariableNameException(String string) {
/* 21 */     this.message = string;
/*    */   }
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 26 */     return this.message;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     exceptions.InvalidVariableNameException
 * JD-Core Version:    0.6.2
 */