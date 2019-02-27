/*     */ package Settings;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class GraphSettings
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1240146121530921728L;
/*  20 */   private static boolean antialiased = true;
/*  21 */   private static float lineWidth = 1.0F;
/*  22 */   private static Color bgColor = Color.gray;
/*  23 */   private static boolean drawGrid = false;
/*  24 */   private static int minCalcPerPixel = 1;
/*  25 */   private static int maxCalcPerPixel = 10;
/*     */ 
/*     */   public static boolean isDrawGrid()
/*     */   {
/*  32 */     return drawGrid;
/*     */   }
/*     */ 
/*     */   public static void setDrawGrid(boolean drawGrid)
/*     */   {
/*  40 */     drawGrid = drawGrid;
/*     */   }
/*     */ 
/*     */   public static boolean isAntialiased()
/*     */   {
/*  50 */     return antialiased;
/*     */   }
/*     */ 
/*     */   public static void setAntialiased(boolean antialiased)
/*     */   {
/*  58 */     antialiased = antialiased;
/*     */   }
/*     */ 
/*     */   public static float getLineWidth()
/*     */   {
/*  66 */     return lineWidth;
/*     */   }
/*     */ 
/*     */   public static void setLineWidth(float lineWidth)
/*     */   {
/*  74 */     lineWidth = lineWidth;
/*     */   }
/*     */ 
/*     */   public static Color getBgColor()
/*     */   {
/*  82 */     return bgColor;
/*     */   }
/*     */ 
/*     */   public static void setBgColor(Color bgColor)
/*     */   {
/*  90 */     bgColor = bgColor;
/*     */   }
/*     */ 
/*     */   public static int getMaxCalcPerPixel() {
/*  94 */     return maxCalcPerPixel;
/*     */   }
/*     */ 
/*     */   public static void setMaxCalcPerPixel(int maxCalcPerPixel) {
/*  98 */     maxCalcPerPixel = maxCalcPerPixel;
/*     */   }
/*     */ 
/*     */   public static int getMinCalcPerPixel() {
/* 102 */     return minCalcPerPixel;
/*     */   }
/*     */ 
/*     */   public static void setMinCalcPerPixel(int minCalcPerPixel) {
/* 106 */     minCalcPerPixel = minCalcPerPixel;
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     Settings.GraphSettings
 * JD-Core Version:    0.6.2
 */