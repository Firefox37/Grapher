/*    */ package expressions;
/*    */ 
/*    */ import equationnodes.EquationNode;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EquationStack
/*    */ {
/*    */   private ArrayList<EquationNode> stack;
/*    */ 
/*    */   public EquationStack()
/*    */   {
/* 38 */     this.stack = new ArrayList();
/*    */   }
/*    */ 
/*    */   public EquationNode pop()
/*    */   {
/* 44 */     return (EquationNode)this.stack.remove(this.stack.size() - 1);
/*    */   }
/*    */ 
/*    */   public void push(EquationNode node)
/*    */   {
/* 50 */     this.stack.add(node);
/*    */   }
/*    */ 
/*    */   public EquationNode peek()
/*    */   {
/* 56 */     EquationNode node = (EquationNode)this.stack.get(this.stack.size() - 1);
/* 57 */     return node;
/*    */   }
/*    */ 
/*    */   public boolean isEmpty()
/*    */   {
/* 63 */     return this.stack.isEmpty();
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 69 */     return this.stack.toString();
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.EquationStack
 * JD-Core Version:    0.6.2
 */