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
        String pow_toString = pow.toString();
        if(this.isDouble(pow_toString))
        {
            double p = Double.parseDouble(pow_toString);
            if(p == 0.0)
                return "1";
            return String.valueOf(Math.exp(p));
        }
        return "e^("+ pow_toString + ")";
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return this;
    }

    @Override
    public Expression derivative(String name) {
        return new Multiplication(pow.derivative(name), this);
    }

    @Override
    public double calculation(double num) {
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
