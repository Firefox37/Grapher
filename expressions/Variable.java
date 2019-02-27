/*    */ package expressions;
/*    */ 
/*    */ import Constants.BlackLists;
/*    */ import exceptions.InvalidVariableNameException;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class Variable
/*    */   implements Serializable
/*    */ {
/*    */   private String variableName;
/*    */   private double variableValue;
/*    */ 
/*    */   public Variable(String variableName, double variableValue)
/*    */     throws InvalidVariableNameException
/*    */   {
/* 28 */     setVariableName(variableName);
/* 29 */     this.variableValue = variableValue;
/*    */   }
/*    */ 
/*    */   public String getVariableName()
/*    */   {
/* 37 */     return this.variableName;
/*    */   }
/*    */ 
/*    */   public void setVariableName(String variableName)
/*    */     throws InvalidVariableNameException
/*    */   {
/* 46 */     String tempVar = variableName.toLowerCase();
/*    */ 
/* 48 */     for (String s : BlackLists.variableBlackList) {
/* 49 */       if (tempVar.equals(s)) {
/* 50 */         throw new InvalidVariableNameException("Invalid Variable Name: " + s);
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 55 */     for (char i = 'a'; i <= 'z'; i = (char)(i + '\001')) {
/* 56 */       tempVar = tempVar.replace(String.valueOf(i), "");
/*    */     }
/*    */ 
/* 59 */     if (!tempVar.isEmpty()) {
/* 60 */       throw new InvalidVariableNameException("Invalid Characters In Variable: " + tempVar);
/*    */     }
/* 62 */     this.variableName = variableName;
/*    */   }
/*    */ 
/*    */   public double getVariableValue()
/*    */   {
/* 70 */     return this.variableValue;
/*    */   }
/*    */ 
/*    */   public void setVariableValue(double variableValue)
/*    */   {
/* 78 */     this.variableValue = variableValue;
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.Variable
 * JD-Core Version:    0.6.2
 */