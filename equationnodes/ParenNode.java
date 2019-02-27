/*    */ package equationnodes;
/*    */ 
/*    */ public class ParenNode extends OpNode
/*    */ {
/*    */   public ParenNode()
/*    */   {
/* 25 */     this.name = "()";
/* 26 */     this.type = "f";
/*    */   }
/*    */ 
/*    */   public double getValue()
/*    */   {
/* 31 */     return this.child.getValue();
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 36 */     return "(" + this.child.toString() + ")";
/*    */   }
/*    */ 
/*    */   public int getPriority()
/*    */   {
/* 44 */     return 10;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.ParenNode
 * JD-Core Version:    0.6.2
 */