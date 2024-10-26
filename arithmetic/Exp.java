package arithmetic;

import java.util.HashSet;
import java.util.Iterator;

public class Exp extends Expression{
    Expression pow;
    public Exp(Expression pow){
        this.pow = pow;
    }

    @Override
    public String toString() {
        if(pow instanceof Num || pow instanceof Variable)
            return "e^" + pow.toString();
        return "e^(" + pow.toString()+")";
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return new Exp(pow);
    }

    @Override
    public Expression derivative(String name) {
        return new Multiplication(pow.derivative(name), this);
    }

    @Override
    public double calculation(double num) throws MyException {
        return Math.exp(pow.calculation(num));
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Exp(pow.calculation(name, num));
    }

    @Override
    public HashSet<Variable> count_variable() {
        HashSet<Variable> v = new HashSet<>();
        if(!(pow instanceof Num))
        {
            HashSet<Variable> temp = pow.count_variable();
            Iterator<Variable> it = temp.iterator();
            while(it.hasNext()){
                v.add(it.next());
            }
        }
        return v;
    }
}
