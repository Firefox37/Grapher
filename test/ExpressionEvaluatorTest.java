/*     */ package test;
/*     */ 
/*     */ import expressions.EquationEvaluator;
/*     */ import expressions.IEvaluator;
/*     */ import java.text.DecimalFormat;
/*     */ import junit.framework.TestCase;
/*     */ 
/*     */ public class ExpressionEvaluatorTest extends TestCase
/*     */ {
/*     */   public ExpressionEvaluatorTest(String name)
/*     */   {
/*  36 */     super(name);
/*     */   }
/*     */ 
/*     */   public void testAddition()
/*     */     throws Exception
/*     */   {
/*  43 */     IEvaluator me = new EquationEvaluator("1+1");
/*  44 */     double x = me.getValue().doubleValue();
/*  45 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */   }
/*     */ 
/*     */   public void testSubtraction()
/*     */     throws Exception
/*     */   {
/*  52 */     IEvaluator me = new EquationEvaluator("2-1");
/*  53 */     double x = me.getValue().doubleValue();
/*  54 */     assertEquals(Double.valueOf(x), Double.valueOf(1.0D));
/*     */   }
/*     */ 
/*     */   public void testMultiplication()
/*     */     throws Exception
/*     */   {
/*  60 */     IEvaluator me = new EquationEvaluator("2*3");
/*  61 */     double x = me.getValue().doubleValue();
/*  62 */     assertEquals(Double.valueOf(x), Double.valueOf(6.0D));
/*     */   }
/*     */ 
/*     */   public void testDivision()
/*     */     throws Exception
/*     */   {
/*  69 */     IEvaluator me = new EquationEvaluator("10/2");
/*  70 */     double x = me.getValue().doubleValue();
/*  71 */     assertEquals(Double.valueOf(x), Double.valueOf(5.0D));
/*     */ 
/*  73 */     me.setExpression("1/0");
/*  74 */     x = me.getValue().doubleValue();
/*  75 */     assertEquals(Double.valueOf(x), Double.valueOf((1.0D / 0.0D)));
/*     */ 
/*  77 */     me.setExpression("500/0");
/*  78 */     x = me.getValue().doubleValue();
/*  79 */     assertEquals(Double.valueOf(x), Double.valueOf((1.0D / 0.0D)));
/*     */ 
/*  81 */     me.setExpression("-1/0");
/*  82 */     x = me.getValue().doubleValue();
/*  83 */     assertEquals(Double.valueOf(x), Double.valueOf((-1.0D / 0.0D)));
/*     */   }
/*     */ 
/*     */   public void testPowers()
/*     */     throws Exception
/*     */   {
/*  90 */     IEvaluator me = new EquationEvaluator("3^2");
/*  91 */     double x = me.getValue().doubleValue();
/*  92 */     assertEquals(Double.valueOf(x), Double.valueOf(9.0D));
/*     */   }
/*     */ 
/*     */   public void testModDiv()
/*     */     throws Exception
/*     */   {
/*  99 */     IEvaluator me = new EquationEvaluator("3%2");
/* 100 */     double x = me.getValue().doubleValue();
/* 101 */     assertEquals(Double.valueOf(x), Double.valueOf(1.0D));
/*     */ 
/* 103 */     me.setExpression("1%0");
/* 104 */     x = me.getValue().doubleValue();
/* 105 */     assertEquals(Double.valueOf(x), Double.valueOf((0.0D / 0.0D)));
/*     */ 
/* 107 */     me.setExpression("10%5");
/* 108 */     x = me.getValue().doubleValue();
/* 109 */     assertEquals(Double.valueOf(x), Double.valueOf(0.0D));
/*     */   }
/*     */ 
/*     */   public void testBitwiseOps()
/*     */     throws Exception
/*     */   {
/* 117 */     IEvaluator me = new EquationEvaluator("7&3");
/* 118 */     double x = me.getValue().doubleValue();
/* 119 */     assertEquals(Double.valueOf(x), Double.valueOf(3.0D));
/*     */ 
/* 121 */     me.setExpression("8&3");
/* 122 */     x = me.getValue().doubleValue();
/* 123 */     assertEquals(Double.valueOf(x), Double.valueOf(0.0D));
/*     */ 
/* 125 */     me.setExpression("7|3");
/* 126 */     x = me.getValue().doubleValue();
/* 127 */     assertEquals(Double.valueOf(x), Double.valueOf(7.0D));
/*     */ 
/* 129 */     me.setExpression("8|3");
/* 130 */     x = me.getValue().doubleValue();
/* 131 */     assertEquals(Double.valueOf(x), Double.valueOf(11.0D));
/*     */   }
/*     */ 
/*     */   public void testSqr()
/*     */     throws Exception
/*     */   {
/* 139 */     IEvaluator me = new EquationEvaluator("sqr(2)");
/* 140 */     double x = me.getValue().doubleValue();
/* 141 */     assertEquals(Double.valueOf(x), Double.valueOf(4.0D));
/*     */   }
/*     */ 
/*     */   public void testSqrt()
/*     */     throws Exception
/*     */   {
/* 148 */     IEvaluator me = new EquationEvaluator("sqrt(4)");
/* 149 */     double x = me.getValue().doubleValue();
/* 150 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */   }
/*     */ 
/*     */   public void testLog()
/*     */     throws Exception
/*     */   {
/* 157 */     IEvaluator me = new EquationEvaluator("log(10)");
/* 158 */     double x = me.getValue().doubleValue();
/* 159 */     assertEquals(Double.valueOf(x), Double.valueOf(1.0D));
/*     */   }
/*     */ 
/*     */   public void testLn()
/*     */     throws Exception
/*     */   {
/* 167 */     IEvaluator me = new EquationEvaluator("ln(2.718281828459045)");
/* 168 */     double x = me.getValue().doubleValue();
/* 169 */     assertEquals(Double.valueOf(x), Double.valueOf(1.0D));
/*     */   }
/*     */ 
/*     */   public void testArithmeticOrderOfOperations()
/*     */     throws Exception
/*     */   {
/* 177 */     IEvaluator me = new EquationEvaluator("3+2*3");
/* 178 */     double x = me.getValue().doubleValue();
/* 179 */     assertEquals(Double.valueOf(x), Double.valueOf(9.0D));
/*     */ 
/* 181 */     me.setExpression("3*2+3");
/* 182 */     x = me.getValue().doubleValue();
/* 183 */     assertEquals(Double.valueOf(x), Double.valueOf(9.0D));
/*     */ 
/* 185 */     me.setExpression("3*(2+3)");
/* 186 */     x = me.getValue().doubleValue();
/* 187 */     assertEquals(Double.valueOf(x), Double.valueOf(15.0D));
/*     */ 
/* 189 */     me.setExpression("3*(2+3)");
/* 190 */     x = me.getValue().doubleValue();
/* 191 */     assertEquals(Double.valueOf(x), Double.valueOf(15.0D));
/*     */ 
/* 193 */     me.setExpression("2^(2+3)");
/* 194 */     x = me.getValue().doubleValue();
/* 195 */     assertEquals(Double.valueOf(x), Double.valueOf(32.0D));
/*     */ 
/* 197 */     me.setExpression("3^2+3");
/* 198 */     x = me.getValue().doubleValue();
/* 199 */     assertEquals(Double.valueOf(x), Double.valueOf(12.0D));
/*     */   }
/*     */ 
/*     */   public void testTriginDegrees()
/*     */     throws Exception
/*     */   {
/* 207 */     DecimalFormat df = new DecimalFormat("#.###############");
/* 208 */     EquationEvaluator me = new EquationEvaluator();
/* 209 */     me.setAngleUnits(2);
/*     */ 
/* 212 */     me.setExpression("sin(30)");
/* 213 */     double x = me.getValue().doubleValue();
/* 214 */     assertEquals(df.format(x), df.format(0.5D));
/*     */ 
/* 217 */     me.setExpression("cos(60)");
/* 218 */     x = me.getValue().doubleValue();
/* 219 */     assertEquals(df.format(x), df.format(0.5D));
/*     */ 
/* 222 */     me.setExpression("tan(45)");
/* 223 */     x = me.getValue().doubleValue();
/* 224 */     assertEquals(df.format(x), df.format(1L));
/*     */   }
/*     */ 
/*     */   public void testArcTriginDegrees()
/*     */     throws Exception
/*     */   {
/* 232 */     DecimalFormat df = new DecimalFormat("#.#############");
/* 233 */     EquationEvaluator me = new EquationEvaluator();
/* 234 */     me.setAngleUnits(2);
/*     */ 
/* 237 */     me.setExpression("asin(0.5)");
/* 238 */     double x = me.getValue().doubleValue();
/* 239 */     assertEquals(df.format(x), df.format(30L));
/*     */ 
/* 242 */     me.setExpression("acos(0.5)");
/* 243 */     x = me.getValue().doubleValue();
/* 244 */     assertEquals(df.format(x), df.format(60L));
/*     */ 
/* 247 */     me.setExpression("atan(1)");
/* 248 */     x = me.getValue().doubleValue();
/* 249 */     assertEquals(df.format(x), df.format(45L));
/*     */   }
/*     */ 
/*     */   public void testTriginRadians()
/*     */     throws Exception
/*     */   {
/* 257 */     DecimalFormat df = new DecimalFormat("#.###############");
/* 258 */     EquationEvaluator me = new EquationEvaluator();
/* 259 */     me.setAngleUnits(1);
/*     */ 
/* 262 */     me.setExpression("sin(0.5235987755982988)");
/* 263 */     double x = me.getValue().doubleValue();
/* 264 */     assertEquals(df.format(x), df.format(0.5D));
/*     */ 
/* 267 */     me.setExpression("cos(1.0471975511965976)");
/* 268 */     x = me.getValue().doubleValue();
/* 269 */     assertEquals(df.format(x), df.format(0.5D));
/*     */ 
/* 272 */     me.setExpression("tan(0.7853981633974483)");
/* 273 */     x = me.getValue().doubleValue();
/* 274 */     assertEquals(df.format(x), df.format(1L));
/*     */   }
/*     */ 
/*     */   public void testArcTriginRadians()
/*     */     throws Exception
/*     */   {
/* 282 */     DecimalFormat df = new DecimalFormat("#.###############");
/* 283 */     EquationEvaluator me = new EquationEvaluator();
/* 284 */     me.setAngleUnits(1);
/*     */ 
/* 287 */     me.setExpression("asin(0.5)");
/* 288 */     double x = me.getValue().doubleValue();
/* 289 */     assertEquals(df.format(x), df.format(0.5235987755982988D));
/*     */ 
/* 292 */     me.setExpression("acos(0.5)");
/* 293 */     x = me.getValue().doubleValue();
/* 294 */     assertEquals(df.format(x), df.format(1.047197551196598D));
/*     */ 
/* 297 */     me.setExpression("atan(1)");
/* 298 */     x = me.getValue().doubleValue();
/* 299 */     assertEquals(df.format(x), df.format(0.7853981633974483D));
/*     */   }
/*     */ 
/*     */   public void testMinMax()
/*     */     throws Exception
/*     */   {
/* 306 */     IEvaluator me = new EquationEvaluator("min(1,10)");
/* 307 */     double x = me.getValue().doubleValue();
/* 308 */     assertEquals(Double.valueOf(x), Double.valueOf(1.0D));
/*     */ 
/* 311 */     me.setExpression("max(0.5,1)");
/* 312 */     x = me.getValue().doubleValue();
/* 313 */     assertEquals(Double.valueOf(x), Double.valueOf(1.0D));
/*     */   }
/*     */ 
/*     */   public void testExp()
/*     */     throws Exception
/*     */   {
/* 320 */     DecimalFormat df = new DecimalFormat("#.#############");
/*     */ 
/* 322 */     IEvaluator me = new EquationEvaluator("exp(1)");
/* 323 */     double x = me.getValue().doubleValue();
/* 324 */     assertEquals(df.format(x), df.format(2.718281828459045D));
/*     */ 
/* 327 */     me.setExpression("exp(2)");
/* 328 */     x = me.getValue().doubleValue();
/* 329 */     assertEquals(df.format(x), df.format(Math.pow(2.718281828459045D, 2.0D)));
/*     */   }
/*     */ 
/*     */   public void testFloorAndCeil()
/*     */     throws Exception
/*     */   {
/* 336 */     IEvaluator me = new EquationEvaluator("floor(2.99999999999)");
/* 337 */     double x = me.getValue().doubleValue();
/* 338 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */ 
/* 340 */     me.setExpression("ceil(1.000000001)");
/* 341 */     x = me.getValue().doubleValue();
/* 342 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */   }
/*     */ 
/*     */   public void testAbs()
/*     */     throws Exception
/*     */   {
/* 350 */     IEvaluator me = new EquationEvaluator("abs(2)");
/* 351 */     double x = me.getValue().doubleValue();
/* 352 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */ 
/* 354 */     me.setExpression("abs(-2.0)");
/* 355 */     x = me.getValue().doubleValue();
/* 356 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */   }
/*     */ 
/*     */   public void testNeg()
/*     */     throws Exception
/*     */   {
/* 363 */     IEvaluator me = new EquationEvaluator("neg(2)");
/* 364 */     double x = me.getValue().doubleValue();
/* 365 */     assertEquals(Double.valueOf(x), Double.valueOf(-2.0D));
/*     */ 
/* 367 */     me.setExpression("neg(-2.0)");
/* 368 */     x = me.getValue().doubleValue();
/* 369 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */   }
/*     */ 
/*     */   public void testRandom()
/*     */     throws Exception
/*     */   {
/* 376 */     IEvaluator me = new EquationEvaluator("rnd(2.3)");
/* 377 */     double x = me.getValue().doubleValue();
/* 378 */     double y = me.getValue().doubleValue();
/*     */ 
/* 380 */     assertNotSame(Double.valueOf(x), Double.valueOf(y));
/*     */   }
/*     */ 
/*     */   public void testOrderOfOperationsWithFunctions()
/*     */     throws Exception
/*     */   {
/* 387 */     DecimalFormat df = new DecimalFormat("#.###############");
/* 388 */     EquationEvaluator me = new EquationEvaluator("abs(2+10*10)");
/*     */ 
/* 390 */     double x = me.getValue().doubleValue();
/* 391 */     assertEquals(df.format(x), df.format(102.0D));
/*     */ 
/* 393 */     me.setExpression("(neg((2^1-12^0-11)*3))");
/* 394 */     x = me.getValue().doubleValue();
/* 395 */     assertEquals(df.format(x), df.format(30L));
/*     */   }
/*     */ 
/*     */   public void testVariables()
/*     */     throws Exception
/*     */   {
/* 402 */     IEvaluator me = new EquationEvaluator("x");
/* 403 */     me.addVariable("x", Double.valueOf(15.0D));
/* 404 */     double x = me.getValue().doubleValue();
/* 405 */     assertEquals(Double.valueOf(x), Double.valueOf(15.0D));
/*     */ 
/* 407 */     me.setExpression("3+x");
/* 408 */     x = me.getValue().doubleValue();
/* 409 */     assertEquals(Double.valueOf(x), Double.valueOf(18.0D));
/*     */   }
/*     */ 
/*     */   public void testImplicitMultiplication()
/*     */     throws Exception
/*     */   {
/* 417 */     IEvaluator me = new EquationEvaluator("3(2+3)");
/* 418 */     double x = me.getValue().doubleValue();
/* 419 */     assertEquals(Double.valueOf(x), Double.valueOf(15.0D));
/*     */ 
/* 421 */     me.setExpression("(2-1)(1+1)");
/* 422 */     x = me.getValue().doubleValue();
/* 423 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */ 
/* 425 */     me.setExpression("3x");
/* 426 */     me.addVariable("x", Double.valueOf(15.0D));
/* 427 */     x = me.getValue().doubleValue();
/* 428 */     assertEquals(Double.valueOf(x), Double.valueOf(45.0D));
/*     */   }
/*     */ 
/*     */   public void testFactorial()
/*     */     throws Exception
/*     */   {
/* 435 */     IEvaluator me = new EquationEvaluator("3!");
/* 436 */     double x = me.getValue().doubleValue();
/* 437 */     assertEquals(Double.valueOf(x), Double.valueOf(6.0D));
/*     */ 
/* 439 */     me.setExpression("10!");
/* 440 */     x = me.getValue().doubleValue();
/* 441 */     assertEquals(Double.valueOf(x), Double.valueOf(3628800.0D));
/*     */   }
/*     */ 
/*     */   public void testCombinations()
/*     */     throws Exception
/*     */   {
/* 448 */     IEvaluator me = new EquationEvaluator("Comb(3,2)");
/* 449 */     double x = me.getValue().doubleValue();
/* 450 */     assertEquals(Double.valueOf(x), Double.valueOf(3.0D));
/*     */ 
/* 452 */     me.setExpression("Comb(4,2)");
/* 453 */     x = me.getValue().doubleValue();
/* 454 */     assertEquals(Double.valueOf(x), Double.valueOf(6.0D));
/*     */   }
/*     */ 
/*     */   public void testPermutation()
/*     */     throws Exception
/*     */   {
/* 462 */     IEvaluator me = new EquationEvaluator("Perm(3,2)");
/* 463 */     double x = me.getValue().doubleValue();
/* 464 */     assertEquals(Double.valueOf(x), Double.valueOf(6.0D));
/*     */ 
/* 466 */     me.setExpression("Perm(4,2)");
/* 467 */     x = me.getValue().doubleValue();
/* 468 */     assertEquals(Double.valueOf(x), Double.valueOf(12.0D));
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     test.ExpressionEvaluatorTest
 * JD-Core Version:    0.6.2
 */