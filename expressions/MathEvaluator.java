/*     */ package expressions;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class MathEvaluator
/*     */   implements IEvaluator
/*     */ {
/*  34 */   protected static Operator[] operators = null;
/*  35 */   private Node node = null;
/*  36 */   private String expression = null;
/*  37 */   private HashMap variables = new HashMap();
/*  38 */   private static int angleUnits = 1;
/*     */ 
/*     */   public MathEvaluator()
/*     */   {
/*  45 */     init();
/*     */   }
/*     */ 
/*     */   public MathEvaluator(String s)
/*     */   {
/*  52 */     init();
/*  53 */     setExpression(s);
/*     */   }
/*     */ 
/*     */   private void init() {
/*  57 */     if (operators == null)
/*  58 */       initializeOperators();
/*     */   }
/*     */ 
/*     */   public void addVariable(String v, Double val)
/*     */   {
/*  67 */     this.variables.put(v, val);
/*     */   }
/*     */ 
/*     */   public void setExpression(String s)
/*     */   {
/*  74 */     this.expression = s;
/*     */   }
/*     */ 
/*     */   public void reset()
/*     */   {
/*  81 */     this.node = null;
/*  82 */     this.expression = null;
/*  83 */     this.variables = new HashMap();
/*     */   }
/*     */ 
/*     */   public Double getValue()
/*     */   {
/*  92 */     if (this.expression == null) {
/*  93 */       return null;
/*     */     }
/*     */     try
/*     */     {
/*  97 */       this.node = new Node(this.expression);
/*  98 */       return evaluate(this.node);
/*     */     } catch (Exception e) {
/* 100 */       e.printStackTrace();
/* 101 */     }return null;
/*     */   }
/*     */ 
/*     */   private static Double evaluate(Node n)
/*     */   {
/* 111 */     if (n.getLevel() == 0) n.trace();
/*     */ 
/* 113 */     if ((n.hasOperator()) && (n.hasChild())) {
/* 114 */       if (n.getOperator().getType() == 1)
/* 115 */         n.setValue(evaluateExpression(n.getOperator(), evaluate(n.getLeft()), null));
/* 116 */       else if (n.getOperator().getType() == 2) {
/* 117 */         n.setValue(evaluateExpression(n.getOperator(), evaluate(n.getLeft()), evaluate(n.getRight())));
/*     */       }
/*     */     }
/* 120 */     return n.getValue();
/*     */   }
/*     */ 
/*     */   private static Double evaluateExpression(Operator o, Double f1, Double f2)
/*     */   {
/* 130 */     String op = o.getOperator();
/* 131 */     Double res = null;
/*     */ 
/* 133 */     if ("+".equals(op))
/* 134 */       res = new Double(f1.doubleValue() + f2.doubleValue());
/* 135 */     else if ("-".equals(op))
/* 136 */       res = new Double(f1.doubleValue() - f2.doubleValue());
/* 137 */     else if ("*".equals(op))
/* 138 */       res = new Double(f1.doubleValue() * f2.doubleValue());
/* 139 */     else if ("/".equals(op))
/* 140 */       res = new Double(f1.doubleValue() / f2.doubleValue());
/* 141 */     else if ("^".equals(op))
/* 142 */       res = new Double(Math.pow(f1.doubleValue(), f2.doubleValue()));
/* 143 */     else if ("%".equals(op))
/* 144 */       res = new Double(f1.doubleValue() % f2.doubleValue());
/* 145 */     else if ("&".equals(op))
/* 146 */       res = new Double((int)f1.doubleValue() & (int)f2.doubleValue());
/* 147 */     else if ("|".equals(op))
/* 148 */       res = new Double((int)f1.doubleValue() | (int)f2.doubleValue());
/* 149 */     else if ("cos".equals(op))
/* 150 */       res = new Double(Math.cos(getAngleValue(f1.doubleValue())));
/* 151 */     else if ("sin".equals(op))
/* 152 */       res = new Double(Math.sin(getAngleValue(f1.doubleValue())));
/* 153 */     else if ("tan".equals(op))
/* 154 */       res = new Double(Math.tan(getAngleValue(f1.doubleValue())));
/* 155 */     else if ("acos".equals(op))
/* 156 */       res = new Double(Math.acos(getAngleValue(f1.doubleValue())));
/* 157 */     else if ("asin".equals(op))
/* 158 */       res = new Double(Math.asin(getAngleValue(f1.doubleValue())));
/* 159 */     else if ("atan".equals(op))
/* 160 */       res = new Double(Math.atan(getAngleValue(f1.doubleValue())));
/* 161 */     else if ("sqr".equals(op))
/* 162 */       res = new Double(f1.doubleValue() * f1.doubleValue());
/* 163 */     else if ("sqrt".equals(op))
/* 164 */       res = new Double(Math.sqrt(f1.doubleValue()));
/* 165 */     else if ("log".equals(op))
/* 166 */       res = new Double(Math.log10(f1.doubleValue()));
/* 167 */     else if ("ln".equals(op))
/* 168 */       res = new Double(Math.log(f1.doubleValue()));
/* 169 */     else if ("min".equals(op))
/* 170 */       res = new Double(Math.min(f1.doubleValue(), f2.doubleValue()));
/* 171 */     else if ("max".equals(op))
/* 172 */       res = new Double(Math.max(f1.doubleValue(), f2.doubleValue()));
/* 173 */     else if ("exp".equals(op))
/* 174 */       res = new Double(Math.exp(f1.doubleValue()));
/* 175 */     else if ("floor".equals(op))
/* 176 */       res = new Double(Math.floor(f1.doubleValue()));
/* 177 */     else if ("ceil".equals(op))
/* 178 */       res = new Double(Math.ceil(f1.doubleValue()));
/* 179 */     else if ("abs".equals(op))
/* 180 */       res = new Double(Math.abs(f1.doubleValue()));
/* 181 */     else if ("neg".equals(op))
/* 182 */       res = new Double(-f1.doubleValue());
/* 183 */     else if ("rnd".equals(op)) {
/* 184 */       res = new Double(Math.random() * f1.doubleValue());
/*     */     }
/*     */ 
/* 187 */     return res;
/*     */   }
/*     */ 
/*     */   private void initializeOperators()
/*     */   {
/* 194 */     operators = new Operator[26];
/* 195 */     operators[0] = new Operator("+", 2, 0);
/* 196 */     operators[1] = new Operator("-", 2, 0);
/* 197 */     operators[2] = new Operator("*", 2, 10);
/* 198 */     operators[3] = new Operator("/", 2, 10);
/* 199 */     operators[4] = new Operator("^", 2, 10);
/* 200 */     operators[5] = new Operator("%", 2, 10);
/* 201 */     operators[6] = new Operator("&", 2, 0);
/* 202 */     operators[7] = new Operator("|", 2, 0);
/* 203 */     operators[8] = new Operator("acos", 1, 20);
/* 204 */     operators[9] = new Operator("asin", 1, 20);
/* 205 */     operators[10] = new Operator("atan", 1, 20);
/* 206 */     operators[11] = new Operator("cos", 1, 20);
/* 207 */     operators[12] = new Operator("sin", 1, 20);
/* 208 */     operators[13] = new Operator("tan", 1, 20);
/* 209 */     operators[14] = new Operator("sqrt", 1, 20);
/* 210 */     operators[15] = new Operator("sqr", 1, 20);
/* 211 */     operators[16] = new Operator("log", 1, 20);
/* 212 */     operators[17] = new Operator("ln", 1, 20);
/* 213 */     operators[18] = new Operator("min", 2, 0);
/* 214 */     operators[19] = new Operator("max", 2, 0);
/* 215 */     operators[20] = new Operator("exp", 1, 20);
/* 216 */     operators[21] = new Operator("floor", 1, 20);
/* 217 */     operators[22] = new Operator("ceil", 1, 20);
/* 218 */     operators[23] = new Operator("abs", 1, 20);
/* 219 */     operators[24] = new Operator("neg", 1, 20);
/* 220 */     operators[25] = new Operator("rnd", 1, 20);
/*     */   }
/*     */ 
/*     */   public Double getVariable(String s)
/*     */   {
/* 227 */     return (Double)this.variables.get(s);
/*     */   }
/*     */ 
/*     */   private Double getDouble(String s) {
/* 231 */     if (s == null) {
/* 232 */       return null;
/*     */     }
/*     */ 
/* 235 */     Double res = null;
/*     */     try {
/* 237 */       res = new Double(Double.parseDouble(s));
/*     */     } catch (Exception e) {
/* 239 */       return getVariable(s);
/*     */     }
/*     */ 
/* 242 */     return res;
/*     */   }
/*     */ 
/*     */   protected Operator[] getOperators()
/*     */   {
/* 250 */     return operators;
/*     */   }
/*     */ 
/*     */   protected static void _D(String s)
/*     */   {
/* 603 */     System.err.println(s);
/*     */   }
/*     */ 
/*     */   public int getAngleUnits()
/*     */   {
/* 610 */     return angleUnits;
/*     */   }
/*     */ 
/*     */   public void setAngleUnits(int angleUnits)
/*     */   {
/* 618 */     angleUnits = angleUnits;
/*     */   }
/*     */ 
/*     */   private static double getAngleValue(double angle)
/*     */   {
/* 628 */     if (angleUnits == 1) {
/* 629 */       return angle;
/*     */     }
/*     */ 
/* 632 */     if (angleUnits == 3) {
/* 633 */       return Math.toRadians(angle) * 0.0D;
/*     */     }
/*     */ 
/* 637 */     return Math.toRadians(angle);
/*     */   }
/*     */ 
/*     */   public void trace()
/*     */   {
/*     */     try
/*     */     {
/* 646 */       this.node = new Node(this.expression);
/* 647 */       this.node.trace();
/*     */     } catch (Exception e) {
/* 649 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected int checkBrackets(String s)
/*     */   {
/* 658 */     int sLength = s.length();
/* 659 */     int inBracket = 0;
/*     */ 
/* 661 */     for (int i = 0; i < sLength; i++) {
/* 662 */       if ((s.charAt(i) == '(') && (inBracket >= 0))
/* 663 */         inBracket++;
/* 664 */       else if (s.charAt(i) == ')') {
/* 665 */         inBracket--;
/*     */       }
/*     */     }
/*     */ 
/* 669 */     return inBracket;
/*     */   }
/*     */ 
/*     */   protected class Node
/*     */   {
/* 311 */     public String nString = null;
/* 312 */     public MathEvaluator.Operator nOperator = null;
/* 313 */     public Node nLeft = null;
/* 314 */     public Node nRight = null;
/* 315 */     public Node nParent = null;
/* 316 */     public int nLevel = 0;
/* 317 */     public Double nValue = null;
/*     */ 
/*     */     public Node(String s)
/*     */       throws Exception
/*     */     {
/* 325 */       init(null, s, 0);
/*     */     }
/*     */ 
/*     */     public Node(Node parent, String s, int level)
/*     */       throws Exception
/*     */     {
/* 336 */       init(parent, s, level);
/*     */     }
/*     */ 
/*     */     private void init(Node parent, String s, int level)
/*     */       throws Exception
/*     */     {
/* 347 */       s = removeIllegalCharacters(s);
/* 348 */       s = removeBrackets(s);
/* 349 */       s = addZero(s);
/* 350 */       if (MathEvaluator.this.checkBrackets(s) != 0) {
/* 351 */         throw new Exception("Wrong number of brackets in [" + s + "]");
/*     */       }
/*     */ 
/* 354 */       this.nParent = parent;
/* 355 */       this.nString = s;
/* 356 */       this.nValue = MathEvaluator.this.getDouble(s);
/* 357 */       this.nLevel = level;
/* 358 */       int sLength = s.length();
/* 359 */       int inBrackets = 0;
/* 360 */       int startOperator = 0;
/*     */ 
/* 362 */       for (int i = 0; i < sLength; i++) {
/* 363 */         if (s.charAt(i) == '(') {
/* 364 */           inBrackets++;
/* 365 */         } else if (s.charAt(i) == ')') {
/* 366 */           inBrackets--;
/*     */         }
/* 369 */         else if (inBrackets == 0) {
/* 370 */           MathEvaluator.Operator o = getOperator(this.nString, i);
/* 371 */           if (o != null)
/*     */           {
/* 373 */             if ((this.nOperator == null) || (this.nOperator.getPriority() >= o.getPriority())) {
/* 374 */               this.nOperator = o;
/* 375 */               startOperator = i;
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 382 */       if (this.nOperator != null)
/*     */       {
/* 384 */         if ((startOperator == 0) && (this.nOperator.getType() == 1))
/*     */         {
/* 386 */           if (MathEvaluator.this.checkBrackets(s.substring(this.nOperator.getOperator().length())) == 0) {
/* 387 */             this.nLeft = new Node(MathEvaluator.this, this, s.substring(this.nOperator.getOperator().length()), this.nLevel + 1);
/* 388 */             this.nRight = null;
/* 389 */             return;
/*     */           }
/* 391 */           throw new Exception("Error during parsing... missing brackets in [" + s + "]");
/*     */         }
/*     */ 
/* 394 */         if ((startOperator > 0) && (this.nOperator.getType() == 2)) {
/* 395 */           this.nOperator = this.nOperator;
/* 396 */           this.nLeft = new Node(MathEvaluator.this, this, s.substring(0, startOperator), this.nLevel + 1);
/* 397 */           this.nRight = new Node(MathEvaluator.this, this, s.substring(startOperator + this.nOperator.getOperator().length()), this.nLevel + 1);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     private MathEvaluator.Operator getOperator(String s, int start)
/*     */     {
/* 409 */       MathEvaluator.Operator[] operators = MathEvaluator.this.getOperators();
/* 410 */       String temp = s.substring(start);
/* 411 */       temp = getNextWord(temp);
/* 412 */       for (int i = 0; i < operators.length; i++) {
/* 413 */         if (temp.startsWith(operators[i].getOperator())) {
/* 414 */           return operators[i];
/*     */         }
/*     */       }
/* 417 */       System.out.println(s);
/* 418 */       return null;
/*     */     }
/*     */ 
/*     */     private String getNextWord(String s)
/*     */     {
/* 427 */       int sLength = s.length();
/* 428 */       for (int i = 1; i < sLength; i++) {
/* 429 */         char c = s.charAt(i);
/* 430 */         if (((c > 'z') || (c < 'a')) && ((c > '9') || (c < '0'))) {
/* 431 */           return s.substring(0, i);
/*     */         }
/*     */       }
/* 434 */       return s;
/*     */     }
/*     */ 
/*     */     protected String addZero(String s)
/*     */     {
/* 443 */       if ((s.startsWith("+")) || (s.startsWith("-"))) {
/* 444 */         int sLength = s.length();
/* 445 */         for (int i = 0; i < sLength; i++) {
/* 446 */           if (getOperator(s, i) != null) {
/* 447 */             return "0" + s;
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 452 */       return s;
/*     */     }
/*     */ 
/*     */     public void trace()
/*     */     {
/* 459 */       String op = getOperator() == null ? " " : getOperator().getOperator();
/* 460 */       _D(op + " : " + getString());
/* 461 */       if (hasChild()) {
/* 462 */         if (hasLeft()) {
/* 463 */           getLeft().trace();
/*     */         }
/* 465 */         if (hasRight())
/* 466 */           getRight().trace();
/*     */       }
/*     */     }
/*     */ 
/*     */     protected boolean hasChild()
/*     */     {
/* 476 */       return (this.nLeft != null) || (this.nRight != null);
/*     */     }
/*     */ 
/*     */     protected boolean hasOperator()
/*     */     {
/* 484 */       return this.nOperator != null;
/*     */     }
/*     */ 
/*     */     protected boolean hasLeft()
/*     */     {
/* 492 */       return this.nLeft != null;
/*     */     }
/*     */ 
/*     */     protected Node getLeft()
/*     */     {
/* 500 */       return this.nLeft;
/*     */     }
/*     */ 
/*     */     protected boolean hasRight()
/*     */     {
/* 508 */       return this.nRight != null;
/*     */     }
/*     */ 
/*     */     protected Node getRight()
/*     */     {
/* 516 */       return this.nRight;
/*     */     }
/*     */ 
/*     */     protected MathEvaluator.Operator getOperator()
/*     */     {
/* 524 */       return this.nOperator;
/*     */     }
/*     */ 
/*     */     protected int getLevel()
/*     */     {
/* 532 */       return this.nLevel;
/*     */     }
/*     */ 
/*     */     protected Double getValue()
/*     */     {
/* 540 */       return this.nValue;
/*     */     }
/*     */ 
/*     */     protected void setValue(Double f)
/*     */     {
/* 548 */       this.nValue = f;
/*     */     }
/*     */ 
/*     */     protected String getString()
/*     */     {
/* 556 */       return this.nString;
/*     */     }
/*     */ 
/*     */     public String removeBrackets(String s)
/*     */     {
/* 563 */       String res = s;
/* 564 */       if ((s.length() > 2) && (res.startsWith("(")) && (res.endsWith(")")) && (MathEvaluator.this.checkBrackets(s.substring(1, s.length() - 1)) == 0)) {
/* 565 */         res = res.substring(1, res.length() - 1);
/*     */       }
/* 567 */       if (!res.equals(s)) {
/* 568 */         return removeBrackets(res);
/*     */       }
/* 570 */       return res;
/*     */     }
/*     */ 
/*     */     public String removeIllegalCharacters(String s)
/*     */     {
/* 578 */       char[] illegalCharacters = { ' ' };
/* 579 */       String res = s;
/*     */ 
/* 581 */       for (int j = 0; j < illegalCharacters.length; j++) {
/* 582 */         int i = res.lastIndexOf(illegalCharacters[j], res.length());
/* 583 */         while (i != -1) {
/* 584 */           String temp = res;
/* 585 */           res = temp.substring(0, i);
/* 586 */           res = res + temp.substring(i + 1);
/* 587 */           i = res.lastIndexOf(illegalCharacters[j], s.length());
/*     */         }
/*     */       }
/* 590 */       return res;
/*     */     }
/*     */ 
/*     */     protected void _D(String s) {
/* 594 */       String nbSpaces = "";
/* 595 */       for (int i = 0; i < this.nLevel; i++) {
/* 596 */         nbSpaces = nbSpaces + "  ";
/*     */       }
/* 598 */       System.out.println(nbSpaces + "|" + s);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected class Operator
/*     */   {
/*     */     private String op;
/*     */     private int type;
/*     */     private int priority;
/*     */ 
/*     */     public Operator(String o, int t, int p)
/*     */     {
/* 270 */       this.op = o;
/* 271 */       this.type = t;
/* 272 */       this.priority = p;
/*     */     }
/*     */ 
/*     */     public String getOperator()
/*     */     {
/* 279 */       return this.op;
/*     */     }
/*     */ 
/*     */     public void setOperator(String o)
/*     */     {
/* 286 */       this.op = o;
/*     */     }
/*     */ 
/*     */     public int getType()
/*     */     {
/* 294 */       return this.type;
/*     */     }
/*     */ 
/*     */     public int getPriority()
/*     */     {
/* 302 */       return this.priority;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.MathEvaluator
 * JD-Core Version:    0.6.2
 */