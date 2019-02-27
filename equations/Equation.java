/*    */ package equations;
/*    */ 
/*    */ import expressions.EquationEvaluator;
/*    */ import expressions.Expression;
/*    */ import expressions.IEvaluator;
/*    */ import expressions.Variable;
/*    */ import expressions.VariableList;
/*    */ import java.awt.Color;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class Equation
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 443195241156639504L;
/*    */   private String expression;
/*    */   private Color color;
/*    */ 
/*    */   public Equation(String expression, Color color)
/*    */   {
/* 29 */     this.expression = expression;
/* 30 */     this.color = color;
/*    */   }
/*    */ 
/*    */   public Color getColor() {
/* 34 */     return this.color;
/*    */   }
/*    */ 
/*    */   public void setColor(Color color) {
/* 38 */     this.color = color;
/*    */   }
/*    */ 
/*    */   public String getExpression() {
/* 42 */     return this.expression;
/*    */   }
/*    */ 
/*    */   public void setExpression(String expression) {
/* 46 */     this.expression = expression;
/*    */   }
/*    */ 
/*    */   public static synchronized double evaluate(String expression, double x, boolean formatFirst) {
/* 50 */     if (formatFirst)
/* 51 */       expression = Expression.formatExpression(expression);
/*    */     try
/*    */     {
/* 54 */       IEvaluator m = new EquationEvaluator(expression);
/*    */ 
/* 56 */       for (Variable var : VariableList.getVariables()) {
/* 57 */         m.addVariable(var.getVariableName(), Double.valueOf(var.getVariableValue()));
/*    */       }
/*    */ 
/* 60 */       m.addVariable("x", Double.valueOf(x));
/*    */ 
/* 63 */       return m.getValue().doubleValue(); } catch (Exception e) {
/* 64 */     }return 0.0D;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equations.Equation
 * JD-Core Version:    0.6.2
 */