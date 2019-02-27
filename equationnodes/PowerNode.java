/*    */ package equationnodes;
/*    */ 
/*    */ public class PowerNode extends BinOpNode
/*    */ {
/*    */   public PowerNode()
/*    */   {
/* 25 */     this.name = "^";
/*    */   }
/*    */ 
/*    */   public int getPriority()
/*    */   {
/* 30 */     return 7;
/*    */   }
/*    */ 
/*    */   public double getValue()
/*    */   {
/* 35 */     return Math.pow(this.lchild.getValue(), this.rchild.getValue());
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.PowerNode
 * JD-Core Version:    0.6.2
 */