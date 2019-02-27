/*    */ package equationnodes;
/*    */ 
/*    */ public class ModNode extends BinOpNode
/*    */ {
/*    */   public ModNode()
/*    */   {
/* 26 */     this.name = "%";
/*    */   }
/*    */ 
/*    */   public ModNode(EquationNode leftchild, EquationNode rightchild)
/*    */   {
/* 31 */     this.name = "%";
/* 32 */     this.lchild = leftchild;
/* 33 */     this.rchild = rightchild;
/*    */   }
/*    */ 
/*    */   public double getValue()
/*    */   {
/* 38 */     return this.lchild.getValue() % this.rchild.getValue();
/*    */   }
/*    */ 
/*    */   public int getPriority()
/*    */   {
/* 45 */     return 6;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.ModNode
 * JD-Core Version:    0.6.2
 */