package expressions;

import exceptions.InvalidExpressionException;

public abstract interface IEvaluator
{
  public static final int RADIANS = 1;
  public static final int DEGREES = 2;
  public static final int GRADIANS = 3;

  public abstract void addVariable(String paramString, Double paramDouble);

  public abstract void setExpression(String paramString)
    throws Exception;

  public abstract Double getValue()
    throws InvalidExpressionException, NumberFormatException;

  public abstract Double getVariable(String paramString);

  public abstract int getAngleUnits();

  public abstract void setAngleUnits(int paramInt);
}

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     expressions.IEvaluator
 * JD-Core Version:    0.6.2
 */