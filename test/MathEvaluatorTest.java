/*     */ package test;
/*     */ 
/*     */ import exceptions.InvalidExpressionException;
/*     */ import expressions.IEvaluator;
/*     */ import expressions.MathEvaluator;
/*     */ import java.text.DecimalFormat;
/*     */ import junit.framework.TestCase;
/*     */ 
/*     */ public class MathEvaluatorTest extends TestCase
/*     */ {
/*     */   public MathEvaluatorTest(String name)
/*     */   {
/*  18 */     super(name);
/*     */   }
/*     */ 
/*     */   public void testAddition()
/*     */     throws NumberFormatException, InvalidExpressionException
/*     */   {
/*  26 */     IEvaluator me = new MathEvaluator("1+1");
/*  27 */     double x = me.getValue().doubleValue();
/*  28 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */   }
/*     */ 
/*     */   public void testSubtraction()
/*     */     throws NumberFormatException, InvalidExpressionException
/*     */   {
/*  36 */     IEvaluator me = new MathEvaluator("2-1");
/*  37 */     double x = me.getValue().doubleValue();
/*  38 */     assertEquals(Double.valueOf(x), Double.valueOf(1.0D));
/*     */   }
/*     */ 
/*     */   public void testMultiplication()
/*     */     throws NumberFormatException, InvalidExpressionException
/*     */   {
/*  45 */     IEvaluator me = new MathEvaluator("2*3");
/*  46 */     double x = me.getValue().doubleValue();
/*  47 */     assertEquals(Double.valueOf(x), Double.valueOf(6.0D));
/*     */   }
/*     */ 
/*     */   public void testDivision()
/*     */     throws Exception
/*     */   {
/*  54 */     IEvaluator me = new MathEvaluator("10/2");
/*  55 */     double x = me.getValue().doubleValue();
/*  56 */     assertEquals(Double.valueOf(x), Double.valueOf(5.0D));
/*     */ 
/*  58 */     me.setExpression("1/0");
/*  59 */     x = me.getValue().doubleValue();
/*  60 */     assertEquals(Double.valueOf(x), Double.valueOf((1.0D / 0.0D)));
/*     */ 
/*  62 */     me.setExpression("500/0");
/*  63 */     x = me.getValue().doubleValue();
/*  64 */     assertEquals(Double.valueOf(x), Double.valueOf((1.0D / 0.0D)));
/*     */ 
/*  66 */     me.setExpression("-1/0");
/*  67 */     x = me.getValue().doubleValue();
/*  68 */     assertEquals(Double.valueOf(x), Double.valueOf((-1.0D / 0.0D)));
/*     */   }
/*     */ 
/*     */   public void testPowers()
/*     */     throws NumberFormatException, InvalidExpressionException
/*     */   {
/*  76 */     IEvaluator me = new MathEvaluator("3^2");
/*  77 */     double x = me.getValue().doubleValue();
/*  78 */     assertEquals(Double.valueOf(x), Double.valueOf(9.0D));
/*     */   }
/*     */ 
/*     */   public void testModDiv()
/*     */     throws Exception
/*     */   {
/*  85 */     IEvaluator me = new MathEvaluator("3%2");
/*  86 */     double x = me.getValue().doubleValue();
/*  87 */     assertEquals(Double.valueOf(x), Double.valueOf(1.0D));
/*     */ 
/*  89 */     me.setExpression("1%0");
/*  90 */     x = me.getValue().doubleValue();
/*  91 */     assertEquals(Double.valueOf(x), Double.valueOf((0.0D / 0.0D)));
/*     */ 
/*  93 */     me.setExpression("10%5");
/*  94 */     x = me.getValue().doubleValue();
/*  95 */     assertEquals(Double.valueOf(x), Double.valueOf(0.0D));
/*     */   }
/*     */ 
/*     */   public void testBitwiseOps()
/*     */     throws Exception
/*     */   {
/* 103 */     IEvaluator me = new MathEvaluator("7&3");
/* 104 */     double x = me.getValue().doubleValue();
/* 105 */     assertEquals(Double.valueOf(x), Double.valueOf(3.0D));
/*     */ 
/* 107 */     me.setExpression("8&3");
/* 108 */     x = me.getValue().doubleValue();
/* 109 */     assertEquals(Double.valueOf(x), Double.valueOf(0.0D));
/*     */ 
/* 111 */     me.setExpression("7|3");
/* 112 */     x = me.getValue().doubleValue();
/* 113 */     assertEquals(Double.valueOf(x), Double.valueOf(7.0D));
/*     */ 
/* 115 */     me.setExpression("8|3");
/* 116 */     x = me.getValue().doubleValue();
/* 117 */     assertEquals(Double.valueOf(x), Double.valueOf(11.0D));
/*     */   }
/*     */ 
/*     */   public void testSqr()
/*     */     throws NumberFormatException, InvalidExpressionException
/*     */   {
/* 126 */     IEvaluator me = new MathEvaluator("sqr(2)");
/* 127 */     double x = me.getValue().doubleValue();
/* 128 */     assertEquals(Double.valueOf(x), Double.valueOf(4.0D));
/*     */   }
/*     */ 
/*     */   public void testSqrt()
/*     */     throws NumberFormatException, InvalidExpressionException
/*     */   {
/* 136 */     IEvaluator me = new MathEvaluator("sqrt(4)");
/* 137 */     double x = me.getValue().doubleValue();
/* 138 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */   }
/*     */ 
/*     */   public void testLog()
/*     */     throws NumberFormatException, InvalidExpressionException
/*     */   {
/* 146 */     IEvaluator me = new MathEvaluator("log(10)");
/* 147 */     double x = me.getValue().doubleValue();
/* 148 */     assertEquals(Double.valueOf(x), Double.valueOf(1.0D));
/*     */   }
/*     */ 
/*     */   public void testLn()
/*     */     throws NumberFormatException, InvalidExpressionException
/*     */   {
/* 157 */     IEvaluator me = new MathEvaluator("ln(2.718281828459045)");
/* 158 */     double x = me.getValue().doubleValue();
/* 159 */     assertEquals(Double.valueOf(x), Double.valueOf(1.0D));
/*     */   }
/*     */ 
/*     */   public void testArithmeticOrderOfOperations()
/*     */     throws Exception
/*     */   {
/* 167 */     IEvaluator me = new MathEvaluator("3+2*3");
/* 168 */     double x = me.getValue().doubleValue();
/* 169 */     assertEquals(Double.valueOf(x), Double.valueOf(9.0D));
/*     */ 
/* 171 */     me.setExpression("3*2+3");
/* 172 */     x = me.getValue().doubleValue();
/* 173 */     assertEquals(Double.valueOf(x), Double.valueOf(9.0D));
/*     */ 
/* 175 */     me.setExpression("3*(2+3)");
/* 176 */     x = me.getValue().doubleValue();
/* 177 */     assertEquals(Double.valueOf(x), Double.valueOf(15.0D));
/*     */ 
/* 179 */     me.setExpression("3*(2+3)");
/* 180 */     x = me.getValue().doubleValue();
/* 181 */     assertEquals(Double.valueOf(x), Double.valueOf(15.0D));
/*     */ 
/* 183 */     me.setExpression("2^(2+3)");
/* 184 */     x = me.getValue().doubleValue();
/* 185 */     assertEquals(Double.valueOf(x), Double.valueOf(32.0D));
/*     */ 
/* 187 */     me.setExpression("3^2+3");
/* 188 */     x = me.getValue().doubleValue();
/* 189 */     assertEquals(Double.valueOf(x), Double.valueOf(12.0D));
/*     */   }
/*     */ 
/*     */   public void testTriginDegrees()
/*     */   {
/* 196 */     DecimalFormat df = new DecimalFormat("#.###############");
/* 197 */     MathEvaluator me = new MathEvaluator();
/* 198 */     me.setAngleUnits(2);
/*     */ 
/* 201 */     me.setExpression("sin(30)");
/* 202 */     double x = me.getValue().doubleValue();
/* 203 */     assertEquals(df.format(x), df.format(0.5D));
/*     */ 
/* 206 */     me.setExpression("cos(60)");
/* 207 */     x = me.getValue().doubleValue();
/* 208 */     assertEquals(df.format(x), df.format(0.5D));
/*     */ 
/* 211 */     me.setExpression("tan(45)");
/* 212 */     x = me.getValue().doubleValue();
/* 213 */     assertEquals(df.format(x), df.format(1L));
/*     */   }
/*     */ 
/*     */   public void testArcTriginDegrees()
/*     */   {
/* 220 */     DecimalFormat df = new DecimalFormat("#.###############");
/* 221 */     MathEvaluator me = new MathEvaluator();
/* 222 */     me.setAngleUnits(2);
/*     */ 
/* 225 */     me.setExpression("asin(0.5)");
/* 226 */     double x = me.getValue().doubleValue();
/* 227 */     assertEquals(df.format(x), df.format(30L));
/*     */ 
/* 230 */     me.setExpression("cos(0.5)");
/* 231 */     x = me.getValue().doubleValue();
/* 232 */     assertEquals(df.format(x), df.format(60L));
/*     */ 
/* 235 */     me.setExpression("tan(1)");
/* 236 */     x = me.getValue().doubleValue();
/* 237 */     assertEquals(df.format(x), df.format(45L));
/*     */   }
/*     */ 
/*     */   public void testTriginRadians()
/*     */   {
/* 244 */     DecimalFormat df = new DecimalFormat("#.###############");
/* 245 */     MathEvaluator me = new MathEvaluator();
/* 246 */     me.setAngleUnits(1);
/*     */ 
/* 249 */     me.setExpression("sin(0.5235987755982988)");
/* 250 */     double x = me.getValue().doubleValue();
/* 251 */     assertEquals(df.format(x), df.format(0.5D));
/*     */ 
/* 254 */     me.setExpression("cos(1.0471975511965976)");
/* 255 */     x = me.getValue().doubleValue();
/* 256 */     assertEquals(df.format(x), df.format(0.5D));
/*     */ 
/* 259 */     me.setExpression("tan(0.7853981633974483)");
/* 260 */     x = me.getValue().doubleValue();
/* 261 */     assertEquals(df.format(x), df.format(1L));
/*     */   }
/*     */ 
/*     */   public void testArcTriginRadians()
/*     */   {
/* 268 */     DecimalFormat df = new DecimalFormat("#.###############");
/* 269 */     MathEvaluator me = new MathEvaluator();
/* 270 */     me.setAngleUnits(1);
/*     */ 
/* 273 */     me.setExpression("asin(0.5)");
/* 274 */     double x = me.getValue().doubleValue();
/* 275 */     assertEquals(df.format(x), df.format(0.5235987755982988D));
/*     */ 
/* 278 */     me.setExpression("cos(0.5)");
/* 279 */     x = me.getValue().doubleValue();
/* 280 */     assertEquals(df.format(x), df.format(1.047197551196598D));
/*     */ 
/* 283 */     me.setExpression("tan(1)");
/* 284 */     x = me.getValue().doubleValue();
/* 285 */     assertEquals(df.format(x), df.format(0.7853981633974483D));
/*     */   }
/*     */ 
/*     */   public void testMinMax()
/*     */     throws Exception
/*     */   {
/* 292 */     IEvaluator me = new MathEvaluator("min(1,10)");
/* 293 */     double x = me.getValue().doubleValue();
/* 294 */     assertEquals(Double.valueOf(x), Double.valueOf(1.0D));
/*     */ 
/* 297 */     me.setExpression("max(0.5,1)");
/* 298 */     x = me.getValue().doubleValue();
/* 299 */     assertEquals(Double.valueOf(x), Double.valueOf(1.0D));
/*     */   }
/*     */ 
/*     */   public void testExp()
/*     */     throws Exception
/*     */   {
/* 306 */     DecimalFormat df = new DecimalFormat("#.#############");
/*     */ 
/* 308 */     IEvaluator me = new MathEvaluator("exp(1)");
/* 309 */     double x = me.getValue().doubleValue();
/* 310 */     assertEquals(df.format(x), df.format(2.718281828459045D));
/*     */ 
/* 313 */     me.setExpression("exp(2)");
/* 314 */     x = me.getValue().doubleValue();
/* 315 */     assertEquals(df.format(x), df.format(Math.pow(2.718281828459045D, 2.0D)));
/*     */   }
/*     */ 
/*     */   public void testFloorAndCeil()
/*     */     throws Exception
/*     */   {
/* 322 */     IEvaluator me = new MathEvaluator("floor(2.99999999999)");
/* 323 */     double x = me.getValue().doubleValue();
/* 324 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */ 
/* 326 */     me.setExpression("ceil(1.000000001)");
/* 327 */     x = me.getValue().doubleValue();
/* 328 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */   }
/*     */ 
/*     */   public void testAbs()
/*     */     throws Exception
/*     */   {
/* 336 */     IEvaluator me = new MathEvaluator("abs(2)");
/* 337 */     double x = me.getValue().doubleValue();
/* 338 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */ 
/* 340 */     me.setExpression("abs(-2.0)");
/* 341 */     x = me.getValue().doubleValue();
/* 342 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */   }
/*     */ 
/*     */   public void testNeg()
/*     */     throws Exception
/*     */   {
/* 349 */     IEvaluator me = new MathEvaluator("neg(2)");
/* 350 */     double x = me.getValue().doubleValue();
/* 351 */     assertEquals(Double.valueOf(x), Double.valueOf(-2.0D));
/*     */ 
/* 353 */     me.setExpression("neg(-2.0)");
/* 354 */     x = me.getValue().doubleValue();
/* 355 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */   }
/*     */ 
/*     */   public void testRandom()
/*     */     throws NumberFormatException, InvalidExpressionException
/*     */   {
/* 363 */     IEvaluator me = new MathEvaluator("rnd(2.3)");
/* 364 */     double x = me.getValue().doubleValue();
/* 365 */     double y = me.getValue().doubleValue();
/*     */ 
/* 367 */     assertFalse(x == y);
/*     */   }
/*     */ 
/*     */   public void testOrderOfOperationsWithFunctions()
/*     */   {
/* 373 */     DecimalFormat df = new DecimalFormat("#.###############");
/* 374 */     MathEvaluator me = new MathEvaluator("sin(2*10+10)");
/* 375 */     me.setAngleUnits(2);
/* 376 */     double x = me.getValue().doubleValue();
/* 377 */     assertEquals(df.format(x), df.format(0.5D));
/*     */ 
/* 379 */     me.setExpression("sin(neg((2^1-12^0-11)*3))");
/* 380 */     x = me.getValue().doubleValue();
/* 381 */     assertEquals(df.format(x), df.format(0.5D));
/*     */   }
/*     */ 
/*     */   public void testVariables()
/*     */     throws Exception
/*     */   {
/* 388 */     IEvaluator me = new MathEvaluator("x");
/* 389 */     me.addVariable("x", Double.valueOf(15.0D));
/* 390 */     double x = me.getValue().doubleValue();
/* 391 */     assertEquals(Double.valueOf(x), Double.valueOf(15.0D));
/*     */ 
/* 393 */     me.setExpression("3+x");
/* 394 */     x = me.getValue().doubleValue();
/* 395 */     assertEquals(Double.valueOf(x), Double.valueOf(18.0D));
/*     */   }
/*     */ 
/*     */   public void testImplicitMultiplication()
/*     */     throws Exception
/*     */   {
/* 403 */     IEvaluator me = new MathEvaluator("3(2+3)");
/* 404 */     double x = me.getValue().doubleValue();
/* 405 */     assertEquals(Double.valueOf(x), Double.valueOf(15.0D));
/*     */ 
/* 407 */     me.setExpression("(2-1)(1+1)");
/* 408 */     x = me.getValue().doubleValue();
/* 409 */     assertEquals(Double.valueOf(x), Double.valueOf(2.0D));
/*     */ 
/* 411 */     me.setExpression("3x");
/* 412 */     me.addVariable("x", Double.valueOf(15.0D));
/* 413 */     x = me.getValue().doubleValue();
/* 414 */     assertEquals(Double.valueOf(x), Double.valueOf(45.0D));
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     test.MathEvaluatorTest
 * JD-Core Version:    0.6.2
 */