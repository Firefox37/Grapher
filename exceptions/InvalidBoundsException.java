/*    */ package exceptions;
/*    */ 
/*    */ public class InvalidBoundsException extends Throwable
/*    */ {
/*    */   private static final long serialVersionUID = 6753692050820737068L;
/* 17 */   private String message = "Invalid Bounds: ";
/*    */ 
/*    */   public InvalidBoundsException(String string) {
/* 20 */     this.message += string;
/*    */   }
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 25 */     return this.message;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     exceptions.InvalidBoundsException
 * JD-Core Version:    0.6.2
 */