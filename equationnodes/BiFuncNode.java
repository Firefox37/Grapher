/*    */ package equationnodes;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class BiFuncNode extends BinOpNode
/*    */ {
/*    */   public BiFuncNode(String _name)
/*    */   {
/* 28 */     this.type = "n";
/* 29 */     this.name = _name;
/*    */   }
/*    */ 
/*    */   public BiFuncNode(String _name, EquationNode leftchild, EquationNode rightchild)
/*    */   {
/* 34 */     this.type = "n";
/* 35 */     this.name = _name;
/* 36 */     this.lchild = leftchild;
/* 37 */     this.rchild = rightchild;
/*    */   }
/*    */ 
/*    */   public double getValue()
/*    */   {
/* 43 */     if (this.name.equals("max("))
/*    */     {
/* 45 */       return Math.max(this.lchild.getValue(), this.rchild.getValue());
/*    */     }
/* 47 */     if (this.name.equals("min("))
/*    */     {
/* 49 */       return Math.min(this.lchild.getValue(), this.rchild.getValue());
/*    */     }
/* 51 */     if (this.name.equals("perm("))
/*    */     {
/* 53 */       double value = factorial(this.lchild.getValue()) / factorial(this.lchild.getValue() - this.rchild.getValue());
/*    */ 
/* 55 */       return value;
/*    */     }
/* 57 */     if (this.name.equals("comb("))
/*    */     {
/* 59 */       double value = factorial(this.lchild.getValue()) / (factorial(this.rchild.getValue()) * factorial(this.lchild.getValue() - this.rchild.getValue()));
/*    */ 
/* 61 */       return value;
/*    */     }
/* 63 */     return 0.0D;
/*    */   }
/*    */ 
/*    */   public double factorial(double x)
/*    */   {
/* 68 */     int a = (int)x; int z = 1;
/* 69 */     while (a > 0)
/*    */     {
/* 71 */       z *= a;
/* 72 */       a--;
/*    */     }
/* 74 */     System.out.println(x + " " + z);
/* 75 */     return z;
/*    */   }
/*    */ 
/*    */   public int getPriority()
/*    */   {
/* 81 */     return 10;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 86 */     if ((this.lchild != null) && (this.rchild != null))
/*    */     {
/* 88 */       if (this.name.equals("max("))
/* 89 */         return "max(" + this.lchild.toString() + "," + this.rchild.toString() + ")";
/* 90 */       if (this.name.equals("min(")) {
/* 91 */         return "min(" + this.lchild.toString() + "," + this.rchild.toString() + ")";
/*    */       }
/*    */     }
/* 94 */     return this.name;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     equationnodes.BiFuncNode
 * JD-Core Version:    0.6.2
 */