/*    */ package equationnodes;
/*    */ 
/*    */ public class SubNode extends BinOpNode
/*    */ {
/*    */   public SubNode()
/*    */   {
/* 25 */     this.name = "-";
/*    */   }
/*    */ 
/*    */   public SubNode(EquationNode leftchild, EquationNode rightchild)
/*    */   {
/* 30 */     this.name = "-";
/* 31 */     this.lchild = leftchild;
/* 32 */     this.rchild = rightchild;
/*    */   }
/*    */ 
/*    */   public double getValue()
/*    */   {
/* 37 */     return this.lchild.getValue() - this.rchild.getValue();
/*    */   }
/*    */ 
/*    */   public int getPriority() {
/* 41 */     return 0;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.SubNode
 * JD-Core Version:    0.6.2
 */