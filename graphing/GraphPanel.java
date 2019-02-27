/*     */ package graphing;
/*     */ 
/*     */ import Settings.GraphSettings;
/*     */ import equations.Equation;
/*     */ import exceptions.InvalidBoundsException;
/*     */ import expressions.Expression;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D.Double;
/*     */ import java.io.PrintStream;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class GraphPanel extends JPanel
/*     */   implements Runnable
/*     */ {
/*     */   private static final long serialVersionUID = -8880798842884968375L;
/*  33 */   private double minX = -10.0D;
/*  34 */   private double maxX = 10.0D;
/*  35 */   private double minY = -10.0D;
/*  36 */   private double maxY = 10.0D;
/*     */   private double xInterval;
/*     */   private double yInterval;
/*  39 */   private int xAxis = 0;
/*  40 */   private int yAxis = 0;
/*  41 */   private static Vector<Equation> equations = new Vector();
/*  42 */   private static Vector<GeneralPath> polylines = new Vector();
/*  43 */   private Vector<Thread> threads = new Vector();
/*  44 */   private boolean stopThreads = false;
/*  45 */   private static int currentEq = 0;
/*  46 */   private static HashMap<String, Point2D.Double> points = new HashMap();
/*     */   private static Graphics2D g2;
/*     */ 
/*     */   public GraphPanel()
/*     */   {
/*  53 */     setBackground(GraphSettings.getBgColor());
/*     */   }
/*     */ 
/*     */   public void paintComponent(Graphics g)
/*     */   {
/*  60 */     setBackground(GraphSettings.getBgColor());
/*     */ 
/*  62 */     g2 = (Graphics2D)g;
/*     */ 
/*  64 */     super.paintComponent(g2);
/*     */ 
/*  66 */     DecimalFormat df = new DecimalFormat("#.###");
/*     */ 
/*  68 */     this.yAxis = UnitToPixelX(0.0D);
/*  69 */     this.xAxis = UnitToPixelY(0.0D);
/*     */ 
/*  72 */     if (GraphSettings.isDrawGrid()) {
/*  73 */       g2.setColor(Color.GRAY);
/*  74 */       this.xInterval = Math.pow(10.0D, String.valueOf((int)(this.maxX - this.minX) / 4).length() - 1);
/*  75 */       this.yInterval = Math.pow(10.0D, String.valueOf((int)(this.maxY - this.minY) / 4).length() - 1);
/*     */ 
/*  77 */       for (double i = 0.0D + this.xInterval; i <= this.maxX; i += this.xInterval) {
/*  78 */         g2.drawLine(UnitToPixelX(i), 0, UnitToPixelX(i), getHeight());
/*     */       }
/*  80 */       for (double i = 0.0D - this.xInterval; i >= this.minX; i -= this.xInterval) {
/*  81 */         g2.drawLine(UnitToPixelX(i), 0, UnitToPixelX(i), getHeight());
/*     */       }
/*  83 */       for (double i = 0.0D + this.yInterval; i <= this.maxY; i += this.yInterval) {
/*  84 */         g2.drawLine(0, UnitToPixelY(i), getWidth(), UnitToPixelY(i));
/*     */       }
/*  86 */       for (double i = 0.0D - this.yInterval; i >= this.minY; i -= this.yInterval) {
/*  87 */         g2.drawLine(0, UnitToPixelY(i), getWidth(), UnitToPixelY(i));
/*     */       }
/*     */     }
/*     */ 
/*  91 */     g2.setColor(Color.black);
/*     */ 
/*  94 */     g2.drawLine(getWidth() / 2 - 5, getHeight() / 2, getWidth() / 2 + 5, getHeight() / 2);
/*  95 */     g2.drawLine(getWidth() / 2, getHeight() / 2 - 5, getWidth() / 2, getHeight() / 2 + 5);
/*     */ 
/*  98 */     g2.drawLine(0, this.xAxis, getWidth(), this.xAxis);
/*  99 */     g2.drawLine(this.yAxis, 0, this.yAxis, getHeight());
/*     */ 
/* 102 */     if (GraphSettings.isAntialiased()) {
/* 103 */       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     }
/*     */ 
/* 107 */     g2.drawString("0", this.yAxis + 2, this.xAxis - 1);
/* 108 */     g2.drawString(df.format(this.minX), 5, this.xAxis - 1);
/* 109 */     g2.drawString(df.format(this.maxX), getWidth() - 
/* 110 */       df.format(this.maxX).length() * 7, this.xAxis - 1);
/* 111 */     g2.drawString(df.format(this.minY), this.yAxis + 2, getHeight() - 5);
/* 112 */     g2.drawString(df.format(this.maxY), this.yAxis + 2, 15);
/*     */ 
/* 114 */     g2.setStroke(new BasicStroke(GraphSettings.getLineWidth()));
/*     */ 
/* 116 */     for (int i = 0; i < polylines.size(); i++) {
/* 117 */       g2.setColor(((Equation)equations.get(i)).getColor());
/* 118 */       g2.draw((Shape)polylines.get(i));
/*     */     }
/*     */ 
/* 125 */     g2.setColor(Color.BLACK);
/* 126 */     for (String key : points.keySet()) {
/* 127 */       Point2D.Double pt = (Point2D.Double)points.get(key);
/* 128 */       int x = UnitToPixelX(pt.getX());
/* 129 */       int y = UnitToPixelY(pt.getY());
/* 130 */       g2.fillOval(x - 2, y - 2, 5, 5);
/* 131 */       g2.drawString(key + "(" + df.format(pt.getX()) + "," + 
/* 132 */         df.format(pt.getY()) + ")", x + 5, y);
/*     */     }
/* 134 */     g2.dispose();
/*     */   }
/*     */ 
/*     */   public double getxInterval()
/*     */   {
/* 143 */     return this.xInterval;
/*     */   }
/*     */ 
/*     */   public double getyInterval()
/*     */   {
/* 152 */     return this.yInterval;
/*     */   }
/*     */ 
/*     */   public static void addPoint(String key, double x, double y)
/*     */   {
/* 163 */     Point2D.Double pt = new Point2D.Double(x, y);
/* 164 */     points.put(key, pt);
/*     */   }
/*     */ 
/*     */   public static void removePoint(String key)
/*     */   {
/* 173 */     points.remove(key);
/*     */   }
/*     */ 
/*     */   public static HashMap<String, Point2D.Double> getPoints()
/*     */   {
/* 182 */     return points;
/*     */   }
/*     */ 
/*     */   public static Point2D.Double getPoint(String key)
/*     */   {
/* 192 */     return (Point2D.Double)points.get(key);
/*     */   }
/*     */ 
/*     */   public double getMaxX()
/*     */   {
/* 201 */     return this.maxX;
/*     */   }
/*     */ 
/*     */   public void setMaxX(double maxX)
/*     */     throws InvalidBoundsException
/*     */   {
/* 211 */     if (maxX <= this.minX) {
/* 212 */       throw new InvalidBoundsException("Max X must be greater then Min X");
/*     */     }
/* 214 */     this.maxX = maxX;
/*     */ 
/* 216 */     startDrawing();
/*     */   }
/*     */ 
/*     */   public double getMaxY()
/*     */   {
/* 225 */     return this.maxY;
/*     */   }
/*     */ 
/*     */   public void setMaxY(double maxY)
/*     */     throws InvalidBoundsException
/*     */   {
/* 235 */     if (maxY <= this.minY) {
/* 236 */       throw new InvalidBoundsException("Max Y must be greater than Min Y");
/*     */     }
/* 238 */     this.maxY = maxY;
/*     */ 
/* 240 */     startDrawing();
/*     */   }
/*     */ 
/*     */   public double getMinX()
/*     */   {
/* 249 */     return this.minX;
/*     */   }
/*     */ 
/*     */   public void setMinX(double minX)
/*     */     throws InvalidBoundsException
/*     */   {
/* 259 */     if (minX >= this.maxX) {
/* 260 */       throw new InvalidBoundsException("Max X must be greater then Min X");
/*     */     }
/* 262 */     this.minX = minX;
/*     */ 
/* 264 */     startDrawing();
/*     */   }
/*     */ 
/*     */   public double getMinY()
/*     */   {
/* 273 */     return this.minY;
/*     */   }
/*     */ 
/*     */   public void setMinY(double minY)
/*     */     throws InvalidBoundsException
/*     */   {
/* 283 */     if (minY >= this.maxY) {
/* 284 */       throw new InvalidBoundsException("Max Y must be greater then Min Y");
/*     */     }
/* 286 */     this.minY = minY;
/*     */ 
/* 288 */     startDrawing();
/*     */   }
/*     */ 
/*     */   public int getxAxis()
/*     */   {
/* 297 */     return this.xAxis;
/*     */   }
/*     */ 
/*     */   public int getyAxis()
/*     */   {
/* 306 */     return this.yAxis;
/*     */   }
/*     */ 
/*     */   public synchronized int UnitToPixelX(double x)
/*     */   {
/* 318 */     double pixelsPerUnit = getWidth() / (this.maxX - this.minX);
/* 319 */     double pos = (x - this.minX) * pixelsPerUnit;
/* 320 */     return (int)pos;
/*     */   }
/*     */ 
/*     */   public synchronized int UnitToPixelY(double y)
/*     */   {
/* 332 */     double pixelsPerUnit = getHeight() / (this.maxY - this.minY);
/* 333 */     double pos = (y - this.minY) * pixelsPerUnit;
/* 334 */     pos = -pos + getHeight();
/* 335 */     return (int)pos;
/*     */   }
/*     */ 
/*     */   public double PixelToUnitX(int pix)
/*     */   {
/* 346 */     double unitsPerPixel = (this.maxY - this.minY) / getWidth();
/* 347 */     double x = pix * unitsPerPixel + this.minX;
/* 348 */     return x;
/*     */   }
/*     */ 
/*     */   public double PixelToUnitY(int pix)
/*     */   {
/* 359 */     double unitsPerPixel = (this.maxY - this.minY) / getHeight();
/* 360 */     double y = (getHeight() - pix) * unitsPerPixel + this.minY;
/* 361 */     return y;
/*     */   }
/*     */ 
/*     */   void drawGraph(Vector<Equation> eq)
/*     */   {
/* 370 */     equations = new Vector();
/* 371 */     for (Equation e : eq) {
/* 372 */       Expression expr = new Expression(e.getExpression());
/* 373 */       equations.add(new Equation(expr.getExpression(), e.getColor()));
/*     */     }
/* 375 */     startDrawing();
/*     */   }
/*     */ 
/*     */   void drawGrid()
/*     */   {
/* 382 */     equations = new Vector();
/* 383 */     startDrawing();
/*     */   }
/*     */ 
/*     */   public void zoom(double percent)
/*     */   {
/* 393 */     double xCenter = (this.maxX + this.minX) / 2.0D;
/* 394 */     double yCenter = (this.maxY + this.minY) / 2.0D;
/* 395 */     double mult = percent / 100.0D;
/*     */ 
/* 397 */     double xSpan = this.maxX - this.minX;
/* 398 */     double ySpan = this.maxY - this.minY;
/*     */ 
/* 400 */     this.minX = (xCenter - (xSpan + xSpan * mult) / 2.0D);
/* 401 */     this.maxX = (xCenter + (xSpan + xSpan * mult) / 2.0D);
/* 402 */     this.minY = (yCenter - (ySpan + ySpan * mult) / 2.0D);
/* 403 */     this.maxY = (yCenter + (ySpan + ySpan * mult) / 2.0D);
/*     */ 
/* 405 */     startDrawing();
/*     */   }
/*     */ 
/*     */   public void moveHorizontal(double percent)
/*     */   {
/* 414 */     double move = (this.maxX - this.minX) * (percent / 100.0D);
/* 415 */     this.minX += move;
/* 416 */     this.maxX += move;
/* 417 */     startDrawing();
/*     */   }
/*     */ 
/*     */   public void moveVertical(double percent)
/*     */   {
/* 426 */     double move = (this.maxY - this.minY) * (percent / 100.0D);
/* 427 */     this.minY += move;
/* 428 */     this.maxY += move;
/* 429 */     startDrawing();
/*     */   }
/*     */ 
/*     */   public void center()
/*     */   {
/* 436 */     double x = this.maxX - this.minX;
/* 437 */     double y = this.maxY - this.minY;
/*     */ 
/* 439 */     this.minX = (-(x / 2.0D));
/* 440 */     this.maxX = (x / 2.0D);
/* 441 */     this.minY = (-(y / 2.0D));
/* 442 */     this.maxY = (y / 2.0D);
/*     */ 
/* 444 */     startDrawing();
/*     */   }
/*     */ 
/*     */   private static synchronized int getNextEQ()
/*     */   {
/* 453 */     if (currentEq > equations.size() - 1) {
/* 454 */       currentEq = 0;
/*     */     }
/*     */ 
/* 457 */     System.out.println(currentEq);
/* 458 */     return currentEq++;
/*     */   }
/*     */ 
/*     */   private static synchronized void drawLineSync(int x1, int y1, int x2, int y2, Color c)
/*     */   {
/* 472 */     g2.setColor(c);
/*     */ 
/* 474 */     g2.drawLine(x1, y1, x2, y2);
/*     */   }
/*     */ 
/*     */   private synchronized void increasePolylineNumber(int eqNumber) {
/* 478 */     while (polylines.size() < eqNumber + 1)
/* 479 */       polylines.add(new GeneralPath(0, getWidth()));
/*     */   }
/*     */ 
/*     */   private synchronized void startDrawing()
/*     */   {
/* 484 */     this.stopThreads = true;
/* 485 */     for (Thread t : this.threads) {
/* 486 */       t.stop();
/*     */     }
/* 488 */     this.threads.clear();
/* 489 */     polylines.clear();
/* 490 */     this.stopThreads = false;
/* 491 */     repaint();
/* 492 */     for (Equation eq : equations) {
/* 493 */       this.threads.add(new Thread(this));
/* 494 */       ((Thread)this.threads.lastElement()).start();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     try
/*     */     {
/* 503 */       int eqNumber = getNextEQ();
/* 504 */       Equation eq = (Equation)equations.get(eqNumber);
/*     */ 
/* 506 */       increasePolylineNumber(eqNumber);
/* 507 */       GeneralPath polyline = new GeneralPath(0, getWidth());
/*     */ 
/* 509 */       boolean firstPoint = true;
/*     */ 
/* 512 */       Double eqPrev = Double.valueOf(0.0D);
/* 513 */       String expr = eq.getExpression();
/*     */       try
/*     */       {
/* 519 */         eqPrev = Double.valueOf(Equation.evaluate(expr, this.minX, false));
/*     */       } catch (Exception exc) {
/* 521 */         equations.clear();
/* 522 */         JOptionPane.showMessageDialog(this, "Invalid Argument.", "Error", 0);
/* 523 */         return;
/*     */       }
/*     */ 
/* 528 */       polyline.moveTo(UnitToPixelX(this.minX), UnitToPixelY(eqPrev.doubleValue()));
/* 529 */       System.out.println("\neqNumber:" + eqNumber);
/* 530 */       System.out.println("Size:" + polylines.size());
/* 531 */       polylines.set(eqNumber, polyline);
/*     */       double intervalFormula;
/* 532 */       double interval = intervalFormula = (this.maxX - this.minX) / getWidth();
/*     */ 
/* 535 */       int loop = 0;
/* 536 */       for (double x = this.minX; 
/* 537 */         !this.stopThreads; x += interval)
/*     */       {
/* 542 */         Double eqVal = Double.valueOf(Equation.evaluate(expr, x, false));
/* 543 */         int pixValX = UnitToPixelX(x);
/*     */ 
/* 545 */         if ((eqVal.isNaN()) || (eqVal.isInfinite())) {
/* 546 */           firstPoint = true;
/* 547 */         } else if (firstPoint)
/*     */         {
/* 550 */           polyline.moveTo(pixValX, UnitToPixelY(eqVal.doubleValue()));
/* 551 */           polylines.set(eqNumber, polyline);
/* 552 */           firstPoint = false;
/*     */         }
/*     */         else
/*     */         {
/* 558 */           polyline.lineTo(pixValX, UnitToPixelY(eqVal.doubleValue()));
/* 559 */           polylines.set(eqNumber, polyline);
/*     */         }
/*     */ 
/* 563 */         double slope = Math.abs((eqVal.doubleValue() - eqPrev.doubleValue()) / (x - (x - interval)));
/* 564 */         if (slope > GraphSettings.getMinCalcPerPixel()) {
/* 565 */           if (slope > GraphSettings.getMaxCalcPerPixel()) {
/* 566 */             slope = GraphSettings.getMaxCalcPerPixel();
/*     */           }
/* 568 */           interval = intervalFormula / slope;
/*     */         } else {
/* 570 */           interval = intervalFormula;
/*     */         }
/* 572 */         eqPrev = eqVal;
/*     */ 
/* 574 */         if ((loop++ % 10 == 0) || (x >= this.maxX)) {
/* 575 */           repaint();
/*     */         }
/* 577 */         if (x >= this.maxX)
/*     */           break;
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 582 */       JOptionPane.showMessageDialog(this, e, "Error", 0);
/* 583 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     graphing.GraphPanel
 * JD-Core Version:    0.6.2
 */