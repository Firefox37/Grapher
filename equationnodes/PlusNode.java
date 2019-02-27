/*    */ package equationnodes;
/*    */ 
/*    */ public class PlusNode extends BinOpNode
/*    */ {
/*    */   public PlusNode()
/*    */   {
/* 25 */     this.name = "+";
/*    */   }
/*    */ 
/*    */   public PlusNode(EquationNode leftchild, EquationNode rightchild)
/*    */   {
/* 30 */     this.name = "+";
/* 31 */     this.lchild = leftchild;
/* 32 */     this.rchild = rightchild;
/*    */   }
/*    */ 
/*    */   public double getValue()
/*    */   {
/* 37 */     return this.lchild.getValue() + this.rchild.getValue();
/*    */   }
/*    */ 
/*    */   public int getPriority()
/*    */   {
/* 43 */     return 0;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.PlusNode
 * JD-Core Version:    0.6.2
 */