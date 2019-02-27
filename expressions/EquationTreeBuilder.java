/*     */ package expressions;
/*     */ 
/*     */ import equationnodes.BiFuncNode;
/*     */ import equationnodes.BinOpNode;
/*     */ import equationnodes.BitwiseAndNode;
/*     */ import equationnodes.BitwiseOrNode;
/*     */ import equationnodes.DigitNode;
/*     */ import equationnodes.DivNode;
/*     */ import equationnodes.EquationNode;
/*     */ import equationnodes.FactorialNode;
/*     */ import equationnodes.FuncNode;
/*     */ import equationnodes.ModNode;
/*     */ import equationnodes.MultNode;
/*     */ import equationnodes.OpNode;
/*     */ import equationnodes.PlusNode;
/*     */ import equationnodes.PowerNode;
/*     */ import equationnodes.SubNode;
/*     */ import equationnodes.VarNode;
/*     */ import exceptions.InvalidExpressionException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class EquationTreeBuilder
/*     */ {
/*  40 */   private final int TABLE_SIZE = 17;
/*     */   private String instr;
/*     */   private String ctok;
/*  42 */   private int index = -1; private int cstate = 0;
/*     */   private EquationScanner myScan;
/*     */   private Production p;
/*  45 */   private EquationNode root = null;
/*     */   private String[] headings;
/*     */   private String[][] table;
/*  48 */   private ArrayList<Production> prods = new ArrayList();
/*  49 */   private ArrayList<String> stack = new ArrayList(); private ArrayList<String> steps = new ArrayList();
/*  50 */   private EquationStack eqstack = new EquationStack();
/*  51 */   private boolean radians = true;
/*     */ 
/*     */   public EquationTreeBuilder(EquationScanner s)
/*     */     throws IOException
/*     */   {
/*  61 */     this.myScan = s;
/*  62 */     loadTable();
/*  63 */     createProductions();
/*     */   }
/*     */ 
/*     */   public boolean process()
/*     */     throws IOException, InvalidExpressionException, NumberFormatException
/*     */   {
/*  75 */     this.stack.add("0");
/*  76 */     this.ctok = this.myScan.scanNext();
/*     */     while (true)
/*     */     {
/*  79 */       this.index = -1;
/*  80 */       for (int i = 0; i < this.headings.length; i++)
/*     */       {
/*  82 */         if (this.headings[i].equals(this.ctok))
/*     */         {
/*  84 */           this.index = i;
/*     */         }
/*     */       }
/*  87 */       if (this.index == -1)
/*     */       {
/*  89 */         throw new InvalidExpressionException("This is not a well formed expression, unknown token");
/*     */       }
/*  91 */       this.instr = this.table[this.index][this.cstate];
/*     */ 
/*  93 */       System.out.println(this.ctok + " " + this.cstate + " " + this.instr);
/*  94 */       if (this.instr.equals(""))
/*     */       {
/*  96 */         throw new InvalidExpressionException("This is not a well formed expression");
/*     */       }
/*     */ 
/*  99 */       if (this.instr.equals("acc"))
/*     */       {
/* 102 */         this.root = balanceTree(this.eqstack.pop());
/* 103 */         return true;
/*     */       }
/*     */ 
/* 106 */       if (this.instr.charAt(0) == 's')
/*     */       {
/* 108 */         shift(this.ctok, this.cstate, Integer.parseInt(this.instr.substring(1)));
/*     */       }
/*     */       else {
/* 111 */         if (this.instr.charAt(0) != 'r')
/*     */           break;
/* 113 */         reduce(this.ctok, this.cstate, Integer.parseInt(this.instr.substring(1)));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 118 */     throw new InvalidExpressionException("This is not a well formed expression.");
/*     */   }
/*     */ 
/*     */   private void shift(String currenttok, int currentstate, int nextstate)
/*     */     throws IOException
/*     */   {
/* 133 */     this.stack.add(currenttok);
/* 134 */     this.stack.add(currentstate);
/*     */ 
/* 136 */     if ((this.myScan.getRef() != -1) && (this.myScan.getReferenceValue(this.myScan.getRef()) != (-1.0D / 0.0D)))
/*     */     {
/* 138 */       this.eqstack.push(createNode(this.myScan.getRef()));
/*     */     }
/*     */ 
/* 144 */     this.cstate = Integer.parseInt(this.instr.substring(1));
/* 145 */     this.ctok = this.myScan.scanNext();
/* 146 */     if (this.ctok == null)
/*     */     {
/* 148 */       this.ctok = "$";
/*     */     }
/*     */   }
/*     */ 
/*     */   private void reduce(String currenttok, int currentstate, int rule)
/*     */     throws IOException
/*     */   {
/* 161 */     this.p = ((Production)this.prods.get(rule));
/* 162 */     handleEqstack(this.p);
/* 163 */     int start = this.stack.size() - 1; int finish = this.stack.size() - 2 * this.p.plength();
/* 164 */     for (int i = start; i >= finish; i--)
/*     */     {
/* 166 */       if (i == finish + 1)
/*     */       {
/* 168 */         this.cstate = Integer.parseInt((String)this.stack.get(i));
/*     */       }
/* 170 */       this.stack.remove(i);
/*     */     }
/* 172 */     this.stack.add(this.p.getLeft());
/* 173 */     this.stack.add(this.cstate);
/* 174 */     for (int i = 0; i < this.headings.length; i++)
/*     */     {
/* 176 */       if (this.headings[i].equals(this.p.getLeft()))
/*     */       {
/* 178 */         this.index = i;
/*     */       }
/*     */     }
/* 181 */     this.steps.add(this.p.description);
/* 182 */     this.cstate = Integer.parseInt(this.table[this.index][this.cstate]);
/*     */   }
/*     */ 
/*     */   private void handleEqstack(Production p)
/*     */   {
/* 191 */     int pnum = getIndex(p);
/*     */ 
/* 193 */     if (pnum == 1)
/*     */     {
/* 195 */       EquationNode rchild = this.eqstack.pop();
/* 196 */       BinOpNode b = (BinOpNode)this.eqstack.pop();
/* 197 */       EquationNode lchild = this.eqstack.pop();
/* 198 */       b.setLChild(lchild);
/* 199 */       b.setRChild(rchild);
/* 200 */       this.eqstack.push(b);
/*     */     }
/* 202 */     if (pnum == 2)
/*     */     {
/* 204 */       EquationNode child = this.eqstack.pop();
/* 205 */       FuncNode f = (FuncNode)this.eqstack.pop();
/* 206 */       f.setChild(child);
/* 207 */       this.eqstack.push(f);
/*     */     }
/* 209 */     if (pnum == 3)
/*     */     {
/* 211 */       EquationNode rchild = this.eqstack.pop();
/* 212 */       EquationNode lchild = this.eqstack.pop();
/* 213 */       BiFuncNode b = (BiFuncNode)this.eqstack.pop();
/* 214 */       b.setLChild(lchild);
/* 215 */       b.setRChild(rchild);
/* 216 */       this.eqstack.push(b);
/*     */     }
/*     */ 
/* 219 */     if (pnum == 6)
/*     */     {
/* 221 */       OpNode op = (OpNode)this.eqstack.pop();
/* 222 */       EquationNode child = this.eqstack.pop();
/* 223 */       op.setChild(child);
/* 224 */       this.eqstack.push(op);
/*     */     }
/*     */   }
/*     */ 
/*     */   private int getIndex(Production p)
/*     */   {
/* 235 */     for (int i = 0; i < this.prods.size(); i++)
/*     */     {
/* 237 */       if (((Production)this.prods.get(i)).equals(p))
/*     */       {
/* 239 */         return i;
/*     */       }
/*     */     }
/*     */ 
/* 243 */     System.out.println("Invalid Production");
/* 244 */     return -1;
/*     */   }
/*     */ 
/*     */   private EquationNode createNode(int ref)
/*     */   {
/* 254 */     if (this.myScan.getReferenceType(ref).equals("f"))
/*     */     {
/* 257 */       return new FuncNode(this.myScan.getReferenceName(ref), this.radians);
/*     */     }
/*     */ 
/* 260 */     if (this.myScan.getReferenceType(ref).equals("d"))
/*     */     {
/* 262 */       return new DigitNode(this.myScan.getReferenceValue(ref));
/*     */     }
/*     */ 
/* 265 */     if (this.myScan.getReferenceType(ref).equals("v"))
/*     */     {
/* 267 */       return new VarNode(this.myScan.getReferenceName(ref), this.myScan.getReferenceValue(ref));
/*     */     }
/* 269 */     if (this.myScan.getReferenceType(ref).equals("u"))
/*     */     {
/* 271 */       return new FactorialNode();
/*     */     }
/*     */ 
/* 274 */     if (this.myScan.getReferenceType(ref).equals("n"))
/*     */     {
/* 276 */       return new BiFuncNode(this.myScan.getReferenceName(ref));
/*     */     }
/*     */ 
/* 279 */     if (this.myScan.getReferenceType(ref).equals("b"))
/*     */     {
/* 281 */       char op = this.myScan.getReferenceName(ref).charAt(0);
/* 282 */       switch (op) {
/*     */       case '+':
/* 284 */         return new PlusNode();
/*     */       case '-':
/* 285 */         return new SubNode();
/*     */       case '*':
/* 286 */         return new MultNode();
/*     */       case '/':
/* 287 */         return new DivNode();
/*     */       case '%':
/* 288 */         return new ModNode();
/*     */       case '^':
/* 289 */         return new PowerNode();
/*     */       case '|':
/* 290 */         return new BitwiseOrNode();
/*     */       case '&':
/* 291 */         return new BitwiseAndNode();
/*     */       }
/*     */     }
/* 294 */     return null;
/*     */   }
/*     */ 
/*     */   public double getValue()
/*     */     throws InvalidExpressionException, NumberFormatException
/*     */   {
/*     */     try
/*     */     {
/* 304 */       process();
/* 305 */       return this.root.getValue();
/*     */     }
/*     */     catch (IOException e) {
/* 308 */       e.printStackTrace();
/* 309 */     }return 0.0D;
/*     */   }
/*     */ 
/*     */   public void setVariable(String var, double val)
/*     */   {
/* 320 */     this.myScan.setVariable(var, val);
/* 321 */     updateTreeVar(var, val, this.root);
/*     */   }
/*     */ 
/*     */   private void updateTreeVar(String var, double val, EquationNode node)
/*     */   {
/* 329 */     if (((node instanceof VarNode)) && (((VarNode)node).getName().equals(var)))
/*     */     {
/* 331 */       ((VarNode)node).setValue(val);
/*     */     }
/* 335 */     else if ((node != null) && (node.numChildren() == 1))
/*     */     {
/* 337 */       updateTreeVar(var, val, ((OpNode)node).getChild());
/*     */     }
/* 340 */     else if ((node != null) && (node.numChildren() == 2))
/*     */     {
/* 342 */       updateTreeVar(var, val, ((BinOpNode)node).getLChild());
/* 343 */       updateTreeVar(var, val, ((BinOpNode)node).getRChild());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void loadTable()
/*     */     throws IOException
/*     */   {
/* 355 */     BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/parsedata.txt")));
/*     */ 
/* 360 */     String[] terms = br.readLine().split("&");
/*     */ 
/* 362 */     String[][] t1 = new String[terms.length][17];
/* 363 */     for (int i = 0; i < 17; i++)
/*     */     {
/* 365 */       String temp = br.readLine();
/* 366 */       String[] t = temp.split("&");
/*     */ 
/* 368 */       for (int h = 1; h < terms.length + 1; h++)
/*     */       {
/* 370 */         t1[(h - 1)][i] = t[h];
/*     */       }
/*     */     }
/* 373 */     String[] vars = br.readLine().split("&");
/*     */ 
/* 375 */     this.table = new String[vars.length + terms.length][17];
/* 376 */     for (int i = 0; i < 17; i++)
/*     */     {
/* 378 */       String temp = br.readLine();
/* 379 */       String[] t = temp.split("&");
/* 380 */       for (int h = 1; h < vars.length + 1; h++)
/*     */       {
/* 383 */         this.table[(h - 1)][i] = t1[(h - 1)][i];
/* 384 */         this.table[(h + terms.length - 1)][i] = t[h];
/*     */       }
/* 386 */       for (int k = vars.length; k < terms.length; k++)
/*     */       {
/* 388 */         this.table[k][i] = t1[k][i];
/*     */       }
/*     */     }
/*     */ 
/* 392 */     this.headings = new String[vars.length + terms.length];
/*     */ 
/* 394 */     for (int i = 0; i < terms.length; i++)
/*     */     {
/* 396 */       this.headings[i] = terms[i];
/*     */     }
/* 398 */     for (int j = 0; j < vars.length; j++)
/*     */     {
/* 400 */       this.headings[(j + terms.length)] = vars[j];
/*     */     }
/*     */   }
/*     */ 
/*     */   private void createProductions()
/*     */     throws IOException
/*     */   {
/* 410 */     this.prods.add(new Production(null, null, null));
/* 411 */     String[] tem = { "S", "b", "S" };
/* 412 */     this.prods.add(new Production("S", tem, "(1) <Segment> > <Segment> <binop> <Segment>"));
/* 413 */     String[] tem2 = { "f", "S", ")" };
/* 414 */     this.prods.add(new Production("S", tem2, "(2) <Segment> > function( <Segment> )"));
/* 415 */     String[] tem3 = { "n", "S", ",", "S", ")" };
/* 416 */     this.prods.add(new Production("S", tem3, "(3) <Segment> > bifunction( <Segment> , <Segment> )"));
/* 417 */     String[] tem4 = { "d" };
/* 418 */     this.prods.add(new Production("S", tem4, "(4) <Segment> > double"));
/* 419 */     String[] tem5 = { "v" };
/* 420 */     this.prods.add(new Production("S", tem5, "(5) <Segment> > variable"));
/* 421 */     String[] tem6 = { "S", "u" };
/* 422 */     this.prods.add(new Production("S", tem6, "(6) <Segment> > <Segment> <unop>"));
/*     */   }
/*     */ 
/*     */   private EquationNode balanceTree(EquationNode node)
/*     */   {
/* 431 */     if (node.numChildren() == 0) return node;
/*     */ 
/* 433 */     if (node.numChildren() == 1)
/*     */     {
/* 435 */       ((OpNode)node).setChild(balanceTree(((OpNode)node).getChild()));
/*     */     }
/*     */     else
/*     */     {
/* 439 */       ((BinOpNode)node).setLChild(balanceTree(((BinOpNode)node).getLChild()));
/* 440 */       ((BinOpNode)node).setRChild(balanceTree(((BinOpNode)node).getRChild()));
/* 441 */       EquationNode lchild = ((BinOpNode)node).getLChild();
/* 442 */       EquationNode rchild = ((BinOpNode)node).getRChild();
/* 443 */       if (lchild.getPriority() < node.getPriority())
/*     */       {
/* 445 */         EquationNode nlchild = ((BinOpNode)lchild).getRChild();
/* 446 */         ((BinOpNode)lchild).setRChild(node);
/* 447 */         ((BinOpNode)node).setLChild(nlchild);
/* 448 */         return lchild;
/*     */       }
/* 450 */       if (rchild.getPriority() < node.getPriority())
/*     */       {
/* 452 */         EquationNode nrchild = ((BinOpNode)rchild).getRChild();
/* 453 */         ((BinOpNode)rchild).setLChild(node);
/* 454 */         ((BinOpNode)node).setRChild(nrchild);
/* 455 */         return rchild;
/*     */       }
/*     */     }
/* 458 */     return node;
/*     */   }
/*     */ 
/*     */   public void setRadians(boolean rad)
/*     */   {
/* 466 */     this.radians = rad;
/*     */   }
/*     */ 
/*     */   public Double getVariable(String varname)
/*     */   {
/* 473 */     return this.myScan.getVarValue(varname);
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.EquationTreeBuilder
 * JD-Core Version:    0.6.2
 */