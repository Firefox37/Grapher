/*    */ package equationnodes;
/*    */ 
/*    */ public class DivNode extends BinOpNode
/*    */ {
/*    */   public DivNode()
/*    */   {
/* 26 */     this.name = "/";
/*    */   }
/*    */ 
/*    */   public DivNode(EquationNode leftchild, EquationNode rightchild)
/*    */   {
/* 31 */     this.name = "/";
/* 32 */     this.lchild = leftchild;
/* 33 */     this.rchild = rightchild;
/*    */   }
/*    */ 
/*    */   public double getValue()
/*    */   {
/* 38 */     return this.lchild.getValue() / this.rchild.getValue();
/*    */   }
/*    */ 
/*    */   public int getPriority()
/*    */   {
/* 44 */     return 5;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.DivNode
 * JD-Core Version:    0.6.2
 */