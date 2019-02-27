/*    */ package expressions;
/*    */ 
/*    */ public class Production
/*    */ {
/*    */   String left;
/*    */   String description;
/*    */   String[] right;
/*    */ 
/*    */   public Production(String l, String[] r, String descr)
/*    */   {
/* 40 */     this.left = l;
/* 41 */     this.right = r;
/* 42 */     this.description = descr;
/*    */   }
/*    */ 
/*    */   public boolean isValid(String[] stack)
/*    */   {
/* 50 */     if (stack.length != this.right.length)
/*    */     {
/* 52 */       return false;
/*    */     }
/*    */ 
/* 55 */     for (int i = 0; i < this.right.length; i++)
/*    */     {
/* 57 */       if (i >= stack.length)
/* 58 */         return false;
/* 59 */       if (!stack[i].equals(this.right[i]))
/*    */       {
/* 62 */         return false;
/*    */       }
/*    */     }
/* 65 */     return true;
/*    */   }
/*    */ 
/*    */   public int plength()
/*    */   {
/* 74 */     return this.right.length;
/*    */   }
/*    */ 
/*    */   public String getLeft()
/*    */   {
/* 83 */     return this.left;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.Production
 * JD-Core Version:    0.6.2
 */