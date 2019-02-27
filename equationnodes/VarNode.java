/*    */ package equationnodes;
/*    */ 
/*    */ public class VarNode extends EquationNode
/*    */ {
/*    */   double val;
/*    */ 
/*    */   public VarNode(String _name)
/*    */   {
/* 27 */     this.type = "v";
/* 28 */     this.name = _name;
/*    */   }
/*    */ 
/*    */   public VarNode(String _name, double value)
/*    */   {
/* 33 */     this.type = "v";
/* 34 */     this.name = _name;
/* 35 */     this.val = value;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 40 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setValue(double value)
/*    */   {
/* 47 */     this.val = value;
/*    */   }
/*    */ 
/*    */   public int getPriority()
/*    */   {
/* 54 */     return 90;
/*    */   }
/*    */ 
/*    */   public double getValue()
/*    */   {
/* 60 */     return this.val;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 66 */     return this.name;
/*    */   }
/*    */ 
/*    */   public int numChildren()
/*    */   {
/* 72 */     return 0;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.VarNode
 * JD-Core Version:    0.6.2
 */