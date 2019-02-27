/*    */ package equationnodes;
/*    */ 
/*    */ public class DigitNode extends EquationNode
/*    */ {
/*    */   double val;
/*    */ 
/*    */   public DigitNode(double value)
/*    */   {
/* 28 */     this.type = "d";
/* 29 */     this.name = "value";
/* 30 */     this.val = value;
/*    */   }
/*    */ 
/*    */   public int getPriority()
/*    */   {
/* 37 */     return 100;
/*    */   }
/*    */ 
/*    */   public double getValue()
/*    */   {
/* 43 */     return this.val;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 48 */     return this.name;
/*    */   }
/*    */ 
/*    */   public int numChildren()
/*    */   {
/* 54 */     return 0;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.DigitNode
 * JD-Core Version:    0.6.2
 */