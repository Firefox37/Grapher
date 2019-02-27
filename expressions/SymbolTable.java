/*     */ package expressions;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class SymbolTable
/*     */ {
/*     */   private ArrayList[] sym;
/*     */ 
/*     */   public SymbolTable()
/*     */   {
/*  37 */     this.sym = new ArrayList[3];
/*  38 */     this.sym[0] = new ArrayList();
/*  39 */     this.sym[1] = new ArrayList();
/*  40 */     this.sym[2] = new ArrayList();
/*     */   }
/*     */ 
/*     */   public void add(String type, String name, double value)
/*     */   {
/*  51 */     this.sym[0].add(type);
/*  52 */     this.sym[1].add(name);
/*  53 */     if (value == -1.0D)
/*     */     {
/*  55 */       this.sym[2].add(null);
/*     */     }
/*     */     else
/*  58 */       this.sym[2].add(new Double(value));
/*     */   }
/*     */ 
/*     */   public boolean contains(String a)
/*     */   {
/*  68 */     return this.sym[1].contains(a);
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  76 */     return this.sym[0].size();
/*     */   }
/*     */ 
/*     */   public String getType(int x)
/*     */   {
/*  85 */     return (String)this.sym[0].get(x);
/*     */   }
/*     */ 
/*     */   public String getName(int x)
/*     */   {
/*  94 */     return (String)this.sym[1].get(x);
/*     */   }
/*     */ 
/*     */   public double getValue(int x)
/*     */   {
/* 103 */     if ((Double)this.sym[2].get(x) != null) {
/* 104 */       return ((Double)this.sym[2].get(x)).doubleValue();
/*     */     }
/* 106 */     return -1.0D;
/*     */   }
/*     */ 
/*     */   public void setValue(int ref, double value)
/*     */   {
/* 115 */     this.sym[2].set(ref, new Double(value));
/*     */   }
/*     */ 
/*     */   public double getVarValue(String var)
/*     */   {
/* 125 */     for (int i = 0; i < size(); i++)
/*     */     {
/* 127 */       if (getName(i).equals(var))
/*     */       {
/* 129 */         return getValue(i);
/*     */       }
/*     */     }
/* 132 */     return -1.0D;
/*     */   }
/*     */ 
/*     */   public void setVarValue(String var, double val)
/*     */   {
/* 142 */     for (int i = 0; i < size(); i++)
/*     */     {
/* 144 */       if (getName(i).equals(var))
/*     */       {
/* 146 */         this.sym[2].set(i, Double.valueOf(val));
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.SymbolTable
 * JD-Core Version:    0.6.2
 */