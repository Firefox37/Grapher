/*     */ package expressions;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class EquationTokenizer
/*     */ {
/*  35 */   private static final EquationTokenizer ET = new EquationTokenizer();
/*  36 */   private boolean debug = false;
/*     */ 
/*     */   public static EquationTokenizer getInstance()
/*     */   {
/*  52 */     return ET;
/*     */   }
/*     */ 
/*     */   public String[] tokenize(String eq)
/*     */   {
/*  62 */     Pattern number = Pattern.compile("^-?\\d+\\.?\\d*");
/*  63 */     Pattern space = Pattern.compile("^\\s+");
/*  64 */     Pattern operator = Pattern.compile("^[\\+\\-\\*/!%^&|,)\\[\\]#]");
/*  65 */     Pattern unop = Pattern.compile("^!");
/*  66 */     Pattern function = Pattern.compile("^\\w*\\(");
/*  67 */     Pattern variable = Pattern.compile("^\\w+\\d*");
/*  68 */     ArrayList list = new ArrayList();
/*     */     Matcher u;
/*  70 */     while (eq.length() > 0)
/*     */     {
/*  72 */       if (this.debug)
/*     */       {
/*  74 */         System.out.println("Equation: " + eq);
/*  75 */         System.out.println("Token List " + list);
/*     */       }
/*     */ 
/*  79 */       Matcher num = number.matcher(eq);
/*  80 */       if (num.find())
/*     */       {
/*  82 */         double n = Double.parseDouble(eq.substring(0, num.end()));
/*     */ 
/*  84 */         String last = null;
/*  85 */         if (!list.isEmpty()) {
/*  86 */           last = (String)list.get(list.size() - 1);
/*     */         }
/*  88 */         if ((n < 0.0D) && (last != null) && ((last.equals(")")) || (number.matcher(last).matches()) || (variable.matcher(last).matches())))
/*     */         {
/*  90 */           list.add("-");
/*  91 */           list.add(-n);
/*     */         }
/*     */         else
/*     */         {
/*  95 */           list.add(n);
/*     */         }
/*  97 */         eq = eq.substring(num.end());
/*     */       }
/*     */       else {
/* 100 */         Matcher spc = space.matcher(eq);
/* 101 */         if (spc.find())
/*     */         {
/* 103 */           eq = eq.substring(spc.end());
/*     */         }
/*     */         else {
/* 106 */           Matcher op = operator.matcher(eq);
/* 107 */           if (op.find())
/*     */           {
/* 109 */             list.add(eq.substring(0, op.end()));
/* 110 */             eq = eq.substring(op.end());
/*     */           }
/*     */           else {
/* 113 */             u = unop.matcher(eq);
/* 114 */             if (u.find())
/*     */             {
/* 116 */               list.add(eq.substring(0, u.end()));
/* 117 */               eq = eq.substring(u.end());
/*     */             }
/*     */             else
/*     */             {
/* 121 */               Matcher func = function.matcher(eq);
/* 122 */               if (func.find())
/*     */               {
/* 124 */                 list.add(eq.substring(0, func.end()));
/* 125 */                 eq = eq.substring(func.end());
/*     */               }
/*     */               else {
/* 128 */                 Matcher var = variable.matcher(eq);
/* 129 */                 if (var.find())
/*     */                 {
/* 131 */                   list.add(eq.substring(0, var.end()));
/* 132 */                   eq = eq.substring(var.end());
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 137 */     if (this.debug)
/*     */     {
/* 139 */       System.out.println("Equation: " + eq);
/* 140 */       System.out.println("Token List " + list);
/*     */     }
/* 142 */     addImplicitMult(list, number, variable, function);
/* 143 */     String[] tokens = new String[list.size()];
/* 144 */     int i = 0;
/* 145 */     for (Object t : list)
/*     */     {
            /*    if(t instanceof String)
               {
 
                   tokens[i] = t;
                   i++;
                }
 
            }
/* 150 */     return tokens;
/*     */   }
/*     */ 
/*     */   private void addImplicitMult(ArrayList<String> list, Pattern number, Pattern var, Pattern func)
/*     */   {
/* 160 */     for (int i = 0; i < list.size() - 1; i++)
/*     */     {
/* 162 */       String a = (String)list.get(i);
/* 163 */       String b = (String)list.get(i + 1);
/*     */ 
/* 165 */       if ((number.matcher(a).matches()) && (
/* 166 */         (var.matcher(b).matches()) || (func.matcher(b).matches())))
/*     */       {
/* 168 */         list.add(i + 1, "*");
/*     */       }
/* 170 */       if ((a.equals(")")) && 
/* 171 */         (func.matcher(b).matches()))
/*     */       {
/* 173 */         list.add(i + 1, "*");
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.EquationTokenizer
 * JD-Core Version:    0.6.2
 */