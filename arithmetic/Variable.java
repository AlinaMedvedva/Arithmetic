package arithmetic;

import java.util.HashSet;

public class Variable extends Expression{
    private String name;
    public Variable(String name){
        this.name = name;
    }
    public Variable(char c){
        name = String.valueOf(c);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return this;
    }

    @Override
    public Expression derivative(String name) {
        if(this.name.equals(name))
            return new Num(1);
        return new Num(0);
    }

    @Override
    public HashSet<Variable> count_variable() {
        HashSet<Variable> v = new HashSet<>();
        v.add(this);
        return v;
    }

    @Override
    public double calculation(double num) {
        return num;
    }

    @Override
    public Expression calculation(String name, double num) {
        if (this.name.equals( name)){
            return new Num(num);
        }
        return this;
    }
}
