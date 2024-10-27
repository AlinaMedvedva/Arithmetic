package arithmetic;

import java.util.HashSet;
import java.util.Iterator;

public class Ln extends Expression{
    private Expression e;

    public Ln(Expression e){
        this.e = e;
    }

    @Override
    public String toString() {
        String e_toString = e.toString();
        if(this.isDouble(e_toString)) {
            double p = Double.parseDouble(e_toString);
            if(p == 1.0)
                return "0";
        }
        return "ln(" + e_toString + ")";
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return this;
    }

    @Override
    public Expression derivative(String name) {
        return new Splitting(new Num(1), e);
    }

    @Override
    public HashSet<Variable> count_variable() {
        HashSet<Variable> v = new HashSet<>();
        if(!(e instanceof Num))
        {
            HashSet<Variable> temp = e.count_variable();
            Iterator<Variable> it = temp.iterator();
            while(it.hasNext()){
                v.add(it.next());
            }
        }
        return v;
    }

    @Override
    public double calculation(double num) {
        return Math.log(e.calculation(num));
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Ln(e.calculation(name, num));
    }
}
