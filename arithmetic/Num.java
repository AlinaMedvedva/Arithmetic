package arithmetic;

import java.util.HashSet;

public class Num extends Expression{
    private double num;

    public Num(double num) {

        this.num = num;
    }

    @Override
    public String toString() {
        if(num == 0.0)
            return "0";
        return String.valueOf(num);
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return this;
    }

    @Override
    public Expression derivative(String name) {
        return new Num(0);
    }

    @Override
    public HashSet<Variable> count_variable() {
        return null;
    }

    @Override
    public double calculation(double num){
        return this.num;
    }

    @Override
    public Expression calculation(String name, double num) {
        return this;
    }
}
