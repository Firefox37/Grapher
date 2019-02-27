/*     */ package expressions;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Scanner;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class EquationScanner
/*     */ {
/*     */   private BufferedReader br;
/*     */   private String tok;
/*  27 */   private String currenttok = "x";
/*     */   private int currentref;
/*  29 */   private ArrayList<String> tokens = new ArrayList();
/*     */   private ArrayList<String> functions;
/*     */   private ArrayList<String> operators;
/*     */   private ArrayList<String> bifunc;
/*     */   private ArrayList<String> punc;
/*     */   private ArrayList<String> unop;
/*  31 */   private SymbolTable sym = new SymbolTable();
/*     */ 
/*     */   public EquationScanner(String[] tokenarray)
/*     */     throws FileNotFoundException
/*     */   {
/*  41 */     newExpression(tokenarray);
/*     */ 
/*  43 */     Scanner f = new Scanner(getClass().getResourceAsStream("/functions.txt"));
/*     */ 
/*  45 */     this.functions = new ArrayList();
/*  46 */     while (f.hasNext()) {
/*  47 */       this.functions.add(f.next());
/*     */     }
/*  49 */     Scanner o = new Scanner(getClass().getResourceAsStream("/operators.txt"));
/*  50 */     this.operators = new ArrayList();
/*  51 */     while (o.hasNext()) {
/*  52 */       this.operators.add(o.next());
/*     */     }
/*     */ 
/*  55 */     Scanner b = new Scanner(getClass().getResourceAsStream("/bifunc.txt"));
/*  56 */     this.bifunc = new ArrayList();
/*  57 */     while (b.hasNext()) {
/*  58 */       this.bifunc.add(b.next());
/*     */     }
/*     */ 
/*  61 */     Scanner p = new Scanner(getClass().getResourceAsStream("/punc.txt"));
/*  62 */     this.punc = new ArrayList();
/*  63 */     while (p.hasNext()) {
/*  64 */       this.punc.add(p.next());
/*     */     }
/*     */ 
/*  67 */     Scanner u = new Scanner(getClass().getResourceAsStream("/unaryoperators.txt"));
/*  68 */     this.unop = new ArrayList();
/*  69 */     while (u.hasNext()) {
/*  70 */       String x = u.next();
/*  71 */       this.unop.add(x);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void newExpression(String[] tokenarray)
/*     */   {
/*  79 */     this.tokens = new ArrayList();
/*  80 */     for (String x : tokenarray)
/*  81 */       this.tokens.add(x);
/*     */   }
/*     */ 
/*     */   public String scanNext()
/*     */     throws IOException
/*     */   {
/*  92 */     this.tok = nextToken();
/*  93 */     if (this.tok == null) {
/*  94 */       return null;
/*     */     }
/*     */ 
/*  97 */     if (this.functions.contains(this.tok.toLowerCase()))
/*     */     {
/*  99 */       this.currenttok = "f";
/* 100 */       if (this.sym.contains(this.tok.toLowerCase())) {
/* 101 */         for (int i = 0; i < this.sym.size(); i++) {
/* 102 */           if (this.sym.getName(i).equals(this.tok.toLowerCase())) {
/* 103 */             this.currentref = i;
/*     */           }
/*     */         }
/* 106 */         return this.currenttok;
/*     */       }
/* 108 */       this.sym.add("f", this.tok.toLowerCase(), 0.0D);
/* 109 */       this.currentref = (this.sym.size() - 1);
/* 110 */       return this.currenttok;
/*     */     }
/*     */ 
/* 114 */     if (this.bifunc.contains(this.tok.toLowerCase()))
/*     */     {
/* 117 */       this.currenttok = "n";
/* 118 */       if (this.sym.contains(this.tok.toLowerCase())) {
/* 119 */         for (int i = 0; i < this.sym.size(); i++) {
/* 120 */           if (this.sym.getName(i).equals(this.tok.toLowerCase())) {
/* 121 */             this.currentref = i;
/*     */           }
/*     */         }
/* 124 */         return this.currenttok;
/*     */       }
/* 126 */       this.sym.add("n", this.tok.toLowerCase(), 0.0D);
/* 127 */       this.currentref = (this.sym.size() - 1);
/* 128 */       return this.currenttok;
/*     */     }
/*     */ 
/* 132 */     if (this.operators.contains(this.tok.toLowerCase()))
/*     */     {
/* 134 */       this.currenttok = "b";
/* 135 */       if (this.sym.contains(this.tok.toLowerCase())) {
/* 136 */         for (int i = 0; i < this.sym.size(); i++) {
/* 137 */           if (this.sym.getName(i).equals(this.tok.toLowerCase())) {
/* 138 */             this.currentref = i;
/*     */           }
/*     */         }
/* 141 */         return this.currenttok;
/*     */       }
/* 143 */       this.sym.add("b", this.tok.toLowerCase(), 0.0D);
/* 144 */       this.currentref = (this.sym.size() - 1);
/* 145 */       return this.currenttok;
/*     */     }
/*     */ 
/* 148 */     if (this.unop.contains(this.tok.toLowerCase()))
/*     */     {
/* 150 */       this.currenttok = "u";
/* 151 */       if (this.sym.contains(this.tok.toLowerCase())) {
/* 152 */         for (int i = 0; i < this.sym.size(); i++) {
/* 153 */           if (this.sym.getName(i).equals(this.tok.toLowerCase())) {
/* 154 */             this.currentref = i;
/*     */           }
/*     */         }
/* 157 */         return this.currenttok;
/*     */       }
/* 159 */       this.sym.add("u", this.tok.toLowerCase(), 0.0D);
/* 160 */       this.currentref = (this.sym.size() - 1);
/* 161 */       return this.currenttok;
/*     */     }
/*     */ 
/* 167 */     Pattern p1 = Pattern.compile("^\\-?\\d+\\.\\d+$");
/* 168 */     Pattern p2 = Pattern.compile("^\\-?\\.\\d+$");
/* 169 */     Pattern p3 = Pattern.compile("^\\-?\\d+$");
/*     */ 
/* 171 */     if ((p1.matcher(this.tok).matches()) || (p2.matcher(this.tok).matches()) || 
/* 172 */       (p3.matcher(this.tok).matches())) {
/* 173 */       this.currenttok = "d";
/* 174 */       if (this.sym.contains(this.tok.toLowerCase())) {
/* 175 */         for (int i = 0; i < this.sym.size(); i++) {
/* 176 */           if (this.sym.getName(i).equals(this.tok.toLowerCase())) {
/* 177 */             this.currentref = i;
/*     */           }
/*     */         }
/* 180 */         return this.currenttok;
/*     */       }
/* 182 */       this.sym.add("d", this.tok, Double.parseDouble(this.tok));
/* 183 */       this.currentref = (this.sym.size() - 1);
/* 184 */       return this.currenttok;
/*     */     }
/*     */ 
/* 188 */     if (this.punc.contains(this.tok.toLowerCase()))
/*     */     {
/* 190 */       this.currenttok = this.tok;
/* 191 */       if (this.sym.contains(this.tok.toLowerCase())) {
/* 192 */         for (int i = 0; i < this.sym.size(); i++) {
/* 193 */           if (this.sym.getName(i).equals(this.tok.toLowerCase())) {
/* 194 */             this.currentref = i;
/*     */           }
/*     */         }
/* 197 */         return this.currenttok;
/*     */       }
/* 199 */       this.sym.add(this.tok, this.tok, (-1.0D / 0.0D));
/* 200 */       this.currentref = (this.sym.size() - 1);
/*     */ 
/* 202 */       return this.currenttok;
/*     */     }
/*     */ 
/* 205 */     if (isVar(this.tok.toLowerCase())) {
/* 206 */       this.currenttok = "v";
/* 207 */       if (this.sym.contains(this.tok.toLowerCase())) {
/* 208 */         for (int i = 0; i < this.sym.size(); i++) {
/* 209 */           if (this.sym.getName(i).equals(this.tok.toLowerCase())) {
/* 210 */             this.currentref = i;
/*     */           }
/*     */         }
/* 213 */         return this.currenttok;
/*     */       }
/* 215 */       this.sym.add("v", this.tok.toLowerCase(), 0.0D);
/* 216 */       this.currentref = (this.sym.size() - 1);
/*     */ 
/* 218 */       return this.currenttok;
/*     */     }
/* 220 */     return errorHandle(this.tok);
/*     */   }
/*     */ 
/*     */   public String nextToken()
/*     */   {
/* 232 */     if (!this.tokens.isEmpty())
/*     */     {
/* 234 */       return (String)this.tokens.remove(0);
/*     */     }
/*     */ 
/* 237 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean hasTokens()
/*     */     throws IOException
/*     */   {
/* 248 */     return (!this.tokens.isEmpty()) || (this.br.ready());
/*     */   }
/*     */ 
/*     */   public int getRef()
/*     */   {
/* 257 */     return this.currentref;
/*     */   }
/*     */ 
/*     */   private String errorHandle(String token)
/*     */   {
/* 266 */     return "Error: Invalid token - " + token;
/*     */   }
/*     */ 
/*     */   private boolean isVar(String token)
/*     */   {
/* 276 */     for (int i = 0; i < token.length(); i++) {
/* 277 */       Character.isLetter(token.charAt(i));
/*     */     }
/*     */ 
/* 280 */     return true;
/*     */   }
/*     */ 
/*     */   public double getReferenceValue(int ref)
/*     */   {
/* 285 */     return this.sym.getValue(ref);
/*     */   }
/*     */ 
/*     */   public String getReferenceType(int ref)
/*     */   {
/* 290 */     return this.sym.getType(ref);
/*     */   }
/*     */ 
/*     */   public String getReferenceName(int ref)
/*     */   {
/* 295 */     return this.sym.getName(ref);
/*     */   }
/*     */ 
/*     */   public void setVariable(String var, double val)
/*     */   {
/* 303 */     if (this.sym.contains(var))
/* 304 */       this.sym.setVarValue(var, val);
/*     */     else
/* 306 */       this.sym.add("v", var, val);
/*     */   }
/*     */ 
/*     */   public Double getVarValue(String varname)
/*     */   {
/* 312 */     return Double.valueOf(this.sym.getVarValue(varname));
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.EquationScanner
 * JD-Core Version:    0.6.2
 */