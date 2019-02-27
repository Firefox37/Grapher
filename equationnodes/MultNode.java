/*    */ package equationnodes;
/*    */ 
/*    */ public class MultNode extends BinOpNode
/*    */ {
/*    */   public MultNode()
/*    */   {
/* 26 */     this.name = "*";
/*    */   }
/*    */ 
/*    */   public MultNode(EquationNode leftchild, EquationNode rightchild)
/*    */   {
/* 31 */     this.name = "*";
/* 32 */     this.lchild = leftchild;
/* 33 */     this.rchild = rightchild;
/*    */   }
/*    */ 
/*    */   public double getValue()
/*    */   {
/* 39 */     return this.lchild.getValue() * this.rchild.getValue();
/*    */   }
/*    */ 
/*    */   public int getPriority()
/*    */   {
/* 47 */     return 5;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.MultNode
 * JD-Core Version:    0.6.2
 */