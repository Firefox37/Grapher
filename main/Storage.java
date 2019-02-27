/*    */ package main;
/*    */ 
/*    */ import expressions.Expression;
/*    */ import expressions.Variable;
/*    */ import graphing.GraphingTab;
/*    */ import java.io.Serializable;
/*    */ import java.util.Vector;
/*    */ import javax.swing.JMenuBar;
/*    */ 
/*    */ public class Storage
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3970532039953352429L;
/*    */   private Vector<Expression> expressions;
/*    */   private Vector<Variable> variables;
/*    */   private GraphingTab graphTab;
/*    */ 
/*    */   public Storage(Vector<Expression> expressions, Vector<Variable> variables, GraphingTab graphingTab, JMenuBar menuBar)
/*    */   {
/* 28 */     this.expressions = expressions;
/* 29 */     this.variables = variables;
/* 30 */     this.graphTab = graphingTab;
/*    */   }
/*    */ 
/*    */   public Vector<Expression> getExpressions()
/*    */   {
/* 38 */     return this.expressions;
/*    */   }
/*    */ 
/*    */   public void setExpressions(Vector<Expression> expressions)
/*    */   {
/* 46 */     this.expressions = expressions;
/*    */   }
/*    */ 
/*    */   public Vector<Variable> getVariables()
/*    */   {
/* 53 */     return this.variables;
/*    */   }
/*    */ 
/*    */   public void setVariables(Vector<Variable> variables)
/*    */   {
/* 60 */     this.variables = variables;
/*    */   }
/*    */ 
/*    */   public void setGraphingTab(GraphingTab graphTab)
/*    */   {
/* 68 */     this.graphTab = graphTab;
/*    */   }
/*    */ 
/*    */   public GraphingTab getGraphingTab()
/*    */   {
/* 76 */     return this.graphTab;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     main.Storage
 * JD-Core Version:    0.6.2
 */