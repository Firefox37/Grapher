/*    */ package expressions;
/*    */ 
/*    */ import components.SmartTextField;
/*    */ import exceptions.InvalidVariableNameException;
/*    */ import java.util.Vector;
/*    */ 
/*    */ public class VariableList
/*    */ {
/* 18 */   private static Vector<Variable> variables = new Vector();
/*    */ 
/*    */   public static void createIfEmpty() throws InvalidVariableNameException {
/* 21 */     if (variables.isEmpty()) {
/* 22 */       variables.add(new Variable("pi", 3.141592653589793D));
/* 23 */       variables.add(new Variable("e", 2.718281828459045D));
/* 24 */       sort();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static Vector<Variable> getVariables() {
/* 29 */     return variables;
/*    */   }
/*    */ 
/*    */   public static void setVariables(Vector<Variable> variables) {
/* 33 */     variables = variables;
/*    */   }
/*    */ 
/*    */   public static void clearVariableList() {
/* 37 */     variables = new Vector();
/* 38 */     sort();
/*    */   }
/*    */ 
/*    */   public static void addVariable(Variable variable) {
/* 42 */     variables.add(variable);
/* 43 */     sort();
/*    */   }
/*    */ 
/*    */   public static void setVariable(int index, Variable variable) {
/* 47 */     variables.set(index, variable);
/* 48 */     sort();
/*    */   }
/*    */ 
/*    */   public static void removeVariable(Variable variable) {
/* 52 */     variables.remove(variable);
/* 53 */     sort();
/*    */   }
/*    */ 
/*    */   public static void removeVariable(int var) {
/* 57 */     variables.remove(var);
/* 58 */     sort();
/*    */   }
/*    */ 
/*    */   private static void sort()
/*    */   {
/*    */     boolean swapped;
/*    */     do {
/* 65 */       swapped = false;
/* 66 */       for (int i = 0; i < variables.size() - 1; i++) {
/* 67 */         String var1 = ((Variable)variables.get(i)).getVariableName();
/* 68 */         String var2 = ((Variable)variables.get(i + 1)).getVariableName();
/*    */ 
/* 70 */         if (var1.length() < var2.length()) {
/* 71 */           Variable toSwap = (Variable)variables.get(i);
/* 72 */           variables.set(i, (Variable)variables.get(i + 1));
/* 73 */           variables.set(i + 1, toSwap);
/* 74 */           swapped = true;
/*    */         }
/*    */       }
/*    */     }
/* 64 */     while (
/* 77 */       swapped);
/* 78 */     SmartTextField.rebuildList();
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.VariableList
 * JD-Core Version:    0.6.2
 */