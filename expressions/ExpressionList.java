/*    */ package expressions;
/*    */ 
/*    */ import java.util.Vector;
/*    */ 
/*    */ public class ExpressionList
/*    */ {
/* 16 */   private static Vector<Expression> expressions = new Vector();
/*    */ 
/*    */   public static Vector<Expression> getExpressionList()
/*    */   {
/* 23 */     return expressions;
/*    */   }
/*    */ 
/*    */   public static void setExpressions(Vector<Expression> expressions)
/*    */   {
/* 31 */     expressions = expressions;
/*    */   }
/*    */ 
/*    */   public static void clearExpressionList()
/*    */   {
/* 38 */     expressions.clear();
/*    */   }
/*    */ 
/*    */   public static void addExpression(Expression expression)
/*    */     throws Exception
/*    */   {
/* 47 */     expression.evaluate();
/* 48 */     expressions.add(expression);
/*    */   }
/*    */ 
/*    */   public static void setExpression(int index, Expression expression)
/*    */   {
/* 57 */     expressions.set(index, expression);
/*    */   }
/*    */ 
/*    */   public static void removeExpression(Expression expression)
/*    */   {
/* 65 */     expressions.remove(expression);
/*    */   }
/*    */ 
/*    */   public static void removeExpression(int index)
/*    */   {
/* 72 */     expressions.remove(index);
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.ExpressionList
 * JD-Core Version:    0.6.2
 */