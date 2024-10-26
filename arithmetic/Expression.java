package arithmetic;

import java.util.HashSet;

public abstract class Expression{
    public String toString() {
        return super.toString();
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return null;
    }

    public Expression derivative(String name){
        return null;
    }

    public HashSet<Variable> count_variable(){
        return null;
    }

    public double calculation(double num) throws MyException {
        return Double.parseDouble(null);
    }

    public Expression calculation(String name, double num){
        return null;
    }
}
