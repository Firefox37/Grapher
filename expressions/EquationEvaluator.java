/*     */ package expressions;
/*     */ 
/*     */ import exceptions.InvalidExpressionException;
/*     */ 
/*     */ public class EquationEvaluator
/*     */   implements IEvaluator
/*     */ {
/*     */   EquationTokenizer et;
/*     */   EquationScanner es;
/*     */   EquationTreeBuilder etb;
/*  38 */   private boolean radians = true;
/*     */ 
/*     */   public EquationEvaluator()
/*     */   {
/*  45 */     this.et = EquationTokenizer.getInstance();
/*     */   }
/*     */ 
/*     */   public EquationEvaluator(String expression)
/*     */     throws Exception
/*     */   {
/*  55 */     this.et = EquationTokenizer.getInstance();
/*  56 */     this.es = new EquationScanner(this.et.tokenize("#" + expression + "#"));
/*  57 */     this.etb = new EquationTreeBuilder(this.es);
/*     */   }
/*     */ 
/*     */   public void addVariable(String v, Double val)
/*     */   {
/*  65 */     this.etb.setVariable(v, val.doubleValue());
/*     */   }
/*     */ 
/*     */   public int getAngleUnits()
/*     */   {
/*  73 */     if (this.radians) {
/*  74 */       return 1;
/*     */     }
/*  76 */     return 2;
/*     */   }
/*     */ 
/*     */   public void setAngleUnits(int units)
/*     */   {
/*  84 */     if (this.etb == null) {
/*     */       try
/*     */       {
/*  87 */         this.es = new EquationScanner(new String[0]);
/*  88 */         this.etb = new EquationTreeBuilder(this.es);
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*  92 */         e.printStackTrace();
/*  93 */         System.exit(0);
/*     */       }
/*     */     }
/*  96 */     if ((units == 1) || (units == 3))
/*     */     {
/*  98 */       this.etb.setRadians(true);
/*  99 */       this.radians = true;
/*     */     }
/*     */     else
/*     */     {
/* 103 */       this.etb.setRadians(false);
/* 104 */       this.radians = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public Double getValue()
/*     */     throws InvalidExpressionException, NumberFormatException
/*     */   {
/* 113 */     return new Double(this.etb.getValue());
/*     */   }
/*     */ 
/*     */   public Double getVariable(String varname)
/*     */   {
/* 120 */     return this.etb.getVariable(varname);
/*     */   }
/*     */ 
/*     */   public void setExpression(String expression)
/*     */     throws Exception
/*     */   {
/* 128 */     if (this.es == null) this.es = new EquationScanner(this.et.tokenize("#" + expression + "#")); else
/* 129 */       this.es.newExpression(this.et.tokenize("#" + expression + "#"));
/* 130 */     this.etb = new EquationTreeBuilder(this.es);
/* 131 */     this.etb.setRadians(this.radians);
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.EquationEvaluator
 * JD-Core Version:    0.6.2
 */