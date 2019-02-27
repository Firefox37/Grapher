/*    */ package equationnodes;
/*    */ 
/*    */ public abstract class BinOpNode extends EquationNode
/*    */ {
/*    */   protected EquationNode lchild;
/*    */   protected EquationNode rchild;
/*    */ 
/*    */   protected BinOpNode()
/*    */   {
/* 29 */     this.type = "b";
/*    */   }
/*    */ 
/*    */   public void setLChild(EquationNode _lsib)
/*    */   {
/* 36 */     this.lchild = _lsib;
/*    */   }
/*    */ 
/*    */   public void setRChild(EquationNode _rsib)
/*    */   {
/* 42 */     this.rchild = _rsib;
/*    */   }
/*    */ 
/*    */   public EquationNode getLChild()
/*    */   {
/* 48 */     return this.lchild;
/*    */   }
/*    */ 
/*    */   public EquationNode getRChild()
/*    */   {
/* 54 */     return this.rchild;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 60 */     if ((this.lchild != null) && (this.rchild != null))
/*    */     {
/* 62 */       return this.lchild + this.name + this.rchild;
/*    */     }
/* 64 */     return this.name;
/*    */   }
/*    */ 
/*    */   public int numChildren()
/*    */   {
/* 70 */     return 2;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.BinOpNode
 * JD-Core Version:    0.6.2
 */