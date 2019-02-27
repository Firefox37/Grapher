/*    */ package equationnodes;
/*    */ 
/*    */ public abstract class EquationNode
/*    */ {
/*    */   protected String type;
/*    */   protected String name;
/*    */ 
/*    */   public String getType()
/*    */   {
/* 29 */     return this.type;
/*    */   }
/*    */ 
/*    */   public abstract double getValue();
/*    */ 
/*    */   public abstract int getPriority();
/*    */ 
/*    */   public abstract int numChildren();
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.EquationNode
 * JD-Core Version:    0.6.2
 */