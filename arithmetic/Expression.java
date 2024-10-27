package arithmetic;

import java.util.HashSet;

public abstract class Expression implements Cloneable{

    public Expression(){};
    abstract public String toString();

    @Override
    abstract protected  Expression clone() throws CloneNotSupportedException;

    abstract public Expression derivative(String name);
    public double calculate_expression(double num) throws MyException{
        if(this.count_variable().size() > 1){
            throw new MyException("Больше одной переменной");
        }
        return this.calculation(num);
    }

    abstract public HashSet<Variable> count_variable();

    abstract public double calculation(double num) ;

    abstract public Expression calculation(String name, double num);

    public Expression add(Expression e2){
        return new Sum(this,e2);
    }

    public Expression mult(Expression e2){
        return new Multiplication(this, e2);
    }

    public Expression difference(Expression e2){
        return new Minus(this, e2);
    }

    public Expression div(Expression e2){
        return new Splitting(this, e2);
    }

    public Expression pow(Expression e2){
        return new Pow(this, e2);
    }

    public boolean isDouble(String s){
        try{
            Double p = Double.parseDouble(s);
            return true;
        }catch (NumberFormatException e)
        {
            return false;
        }
    }
}
