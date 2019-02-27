/*     */ package expressions;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Expression
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7172720115921875406L;
/*     */   private String expression;
/*     */   private double value;
/*     */   private String angleUnits;
/*     */ 
/*     */   public Expression(String expression)
/*     */   {
/*  28 */     this.expression = formatExpression(expression);
/*     */   }
/*     */ 
/*     */   public static String formatExpression(String expression)
/*     */   {
/*  37 */     expression = expression.replace(" ", "");
/*     */ 
/*  39 */     for (int i = 0; i <= 9; i++) {
/*  40 */       expression = expression.replace(i + "(", i + "*(");
/*     */ 
/*  42 */       for (char c = 'a'; c <= 'z'; c = (char)(c + '\001')) {
/*  43 */         expression = expression.replace(i + String.valueOf(c), i + "*" + c);
/*  44 */         expression = expression.replace(i + String.valueOf(c).toUpperCase(), i + "*" + String.valueOf(c).toUpperCase());
/*     */       }
/*     */     }
/*     */ 
/*  48 */     expression = expression.replace(")(", ")*(");
/*     */ 
/*  50 */     return expression;
/*     */   }
/*     */ 
/*     */   public String getExpression()
/*     */   {
/*  58 */     return this.expression;
/*     */   }
/*     */ 
/*     */   public void setExpression(String expression)
/*     */   {
/*  66 */     this.expression = expression;
/*     */   }
/*     */ 
/*     */   public double getValue()
/*     */   {
/*  74 */     return this.value;
/*     */   }
/*     */ 
/*     */   public String getAngleUnits()
/*     */   {
/*  82 */     return this.angleUnits;
/*     */   }
/*     */ 
/*     */   public double evaluate()
/*     */     throws Exception
/*     */   {
/*  93 */     IEvaluator m = new EquationEvaluator(this.expression);
/*     */ 
/*  95 */     for (Variable var : VariableList.getVariables()) {
/*  96 */       m.addVariable(var.getVariableName(), Double.valueOf(var.getVariableValue()));
/*     */     }
/*     */ 
/*  99 */     if (m.getAngleUnits() == 1) {
/* 100 */       this.angleUnits = "RAD";
/*     */     }
/* 102 */     else if (m.getAngleUnits() == 2)
/* 103 */       this.angleUnits = "DEG";
/* 104 */     else if (m.getAngleUnits() == 3) {
/* 105 */       this.angleUnits = "GRAD";
/*     */     }
/* 107 */     this.value = m.getValue().doubleValue();
/* 108 */     return this.value;
/*     */   }
/*     */ 
/*     */   public static double evaluate(String expression)
/*     */     throws Exception
/*     */   {
/* 118 */     Expression expr = new Expression(expression);
/* 119 */     return expr.evaluate();
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.Expression
 * JD-Core Version:    0.6.2
 */