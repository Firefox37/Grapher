/*     */ package equationnodes;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class FuncNode extends OpNode
/*     */ {
/*     */   boolean radians;
/*     */ 
/*     */   public FuncNode(String func, boolean rad)
/*     */   {
/*  30 */     this.name = func;
/*  31 */     this.type = "f";
/*  32 */     this.radians = rad;
/*     */   }
/*     */   public double getValue() {
/*  35 */     if (this.name.equals("sin("))
/*     */     {
/*  37 */       System.out.print("Radians?: " + this.radians);
/*     */ 
/*  39 */       if (this.radians) {
/*  40 */         return Math.sin(this.child.getValue());
/*     */       }
/*  42 */       return Math.sin(Math.toRadians(this.child.getValue()));
/*     */     }
/*  44 */     if (this.name.equals("cos("))
/*     */     {
/*  46 */       if (this.radians) {
/*  47 */         return Math.cos(this.child.getValue());
/*     */       }
/*  49 */       return Math.cos(Math.toRadians(this.child.getValue()));
/*     */     }
/*  51 */     if (this.name.equals("tan("))
/*     */     {
/*  53 */       if (this.radians) {
/*  54 */         return Math.tan(this.child.getValue());
/*     */       }
/*  56 */       return Math.tan(Math.toRadians(this.child.getValue()));
/*     */     }
/*  58 */     if (this.name.equals("asin("))
/*     */     {
/*  60 */       if (this.radians) {
/*  61 */         return Math.asin(this.child.getValue());
/*     */       }
/*  63 */       return Math.toDegrees(Math.asin(this.child.getValue()));
/*     */     }
/*  65 */     if (this.name.equals("acos("))
/*     */     {
/*  67 */       if (this.radians) {
/*  68 */         return Math.acos(this.child.getValue());
/*     */       }
/*  70 */       return Math.toDegrees(Math.acos(this.child.getValue()));
/*     */     }
/*  72 */     if (this.name.equals("atan("))
/*     */     {
/*  74 */       if (this.radians) {
/*  75 */         return Math.atan(this.child.getValue());
/*     */       }
/*  77 */       return Math.toDegrees(Math.atan(this.child.getValue()));
/*     */     }
/*  79 */     if (this.name.equals("abs("))
/*     */     {
/*  81 */       return Math.abs(this.child.getValue());
/*     */     }
/*  83 */     if (this.name.equals("sqr("))
/*     */     {
/*  85 */       return Math.pow(this.child.getValue(), 2.0D);
/*     */     }
/*  87 */     if (this.name.equals("sqrt("))
/*     */     {
/*  89 */       return Math.sqrt(this.child.getValue());
/*     */     }
/*  91 */     if (this.name.equals("log("))
/*     */     {
/*  93 */       return Math.log(this.child.getValue()) / Math.log(10.0D);
/*     */     }
/*  95 */     if (this.name.equals("ln("))
/*     */     {
/*  97 */       return Math.log(this.child.getValue());
/*     */     }
/*  99 */     if (this.name.equals("exp("))
/*     */     {
/* 101 */       return Math.pow(2.718281828459045D, this.child.getValue());
/*     */     }
/* 103 */     if (this.name.equals("ceil("))
/*     */     {
/* 105 */       return Math.ceil(this.child.getValue());
/*     */     }
/* 107 */     if (this.name.equals("floor("))
/*     */     {
/* 109 */       return Math.floor(this.child.getValue());
/*     */     }
/* 111 */     if (this.name.equals("neg("))
/*     */     {
/* 113 */       return 0.0D - this.child.getValue();
/*     */     }
/* 115 */     if (this.name.equals("rnd("))
/*     */     {
/* 117 */       Random r = new Random((long)this.child.getValue());
/* 118 */       return r.nextDouble();
/*     */     }
/* 120 */     if (this.name.equals("("))
/*     */     {
/* 122 */       return this.child.getValue();
/*     */     }
/*     */ 
/* 127 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 133 */     if (this.child != null) {
/* 134 */       return this.name + this.child.toString() + ")";
/*     */     }
/* 136 */     return this.name;
/*     */   }
/*     */ 
/*     */   public int getPriority()
/*     */   {
/* 142 */     return 10;
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.FuncNode
 * JD-Core Version:    0.6.2
 */