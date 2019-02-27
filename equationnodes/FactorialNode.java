/*    */ package equationnodes;
/*    */ 
/*    */ public class FactorialNode extends OpNode
/*    */ {
/*    */   public FactorialNode()
/*    */   {
/*  7 */     this.name = "!";
/*    */   }
/*    */ 
/*    */   public double getValue()
/*    */   {
/* 12 */     int x = 1;
/* 13 */     for (int i = 1; i <= getChild().getValue(); i++)
/*    */     {
/* 15 */       x *= i;
/*    */     }
/* 17 */     return x;
/*    */   }
/*    */ 
/*    */   public int getPriority()
/*    */   {
/* 23 */     return 6;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.FactorialNode
 * JD-Core Version:    0.6.2
 */