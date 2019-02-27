/*    */ package equationnodes;
/*    */ 
/*    */ public abstract class OpNode extends EquationNode
/*    */ {
/*    */   protected EquationNode child;
/*    */ 
/*    */   public void setChild(EquationNode _child)
/*    */   {
/* 30 */     this.child = _child;
/*    */   }
/*    */ 
/*    */   public int numChildren()
/*    */   {
/* 36 */     return 1;
/*    */   }
/*    */ 
/*    */   public EquationNode getChild()
/*    */   {
/* 42 */     return this.child;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 47 */     if (this.child != null)
/*    */     {
/* 49 */       return this.child + this.name;
/*    */     }
/* 51 */     return this.name;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.OpNode
 * JD-Core Version:    0.6.2
 */