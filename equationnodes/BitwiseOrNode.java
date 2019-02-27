/*    */ package equationnodes;
/*    */ 
/*    */ public class BitwiseOrNode extends BinOpNode
/*    */ {
/*    */   public BitwiseOrNode()
/*    */   {
/* 26 */     this.name = "|";
/*    */   }
/*    */ 
/*    */   public int getPriority()
/*    */   {
/* 31 */     return 6;
/*    */   }
/*    */ 
/*    */   public double getValue()
/*    */   {
/* 36 */     return (int)this.lchild.getValue() | (int)this.rchild.getValue();
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.BitwiseOrNode
 * JD-Core Version:    0.6.2
 */