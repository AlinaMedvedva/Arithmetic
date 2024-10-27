package arithmetic;

import java.util.HashSet;
import java.util.Iterator;

public class Minus extends Expression{
    private Expression minus1, minus2;

    public Minus(Expression minus1, Expression minus2) {
        this.minus1 = minus1;
        this.minus2 = minus2;
    }

    @Override
    public String toString() {
        String minus1_toString = minus1.toString();
        String minus2_toString = minus2.toString();
        boolean flag1 = false, flag2 = false;
        if(minus2_toString.equals(minus1_toString))
            return "0";
        flag1 = this.isDouble(minus1_toString);
        flag2 = this.isDouble(minus2_toString);
        if(flag1 && flag2)
        {
            double m1 = Double.parseDouble(minus1_toString);
            double m2 = Double.parseDouble(minus2_toString);
            if(m1 - m2 == 0.0)
                return "0";
            return String.valueOf(m1 - m2);
        }
        if(flag1){
            double m1 = Double.parseDouble(minus1_toString);
            if(m1 == 0.0)
                return "-" + minus2_toString;
            return m1 + " - " + minus2_toString;
        }
        if(flag2){
            double m2 = Double.parseDouble(minus2_toString);
            if(m2 == 0)
                return minus1_toString;
            return minus1_toString + " - " +m2;
        }
        return minus1_toString + " - " + minus2_toString;
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return this;
    }


    @Override
    public Expression derivative(String name) {
        return new Minus(minus1.derivative(name), minus2.derivative(name));
    }

    @Override
    public HashSet<Variable> count_variable() {
        HashSet<Variable> v = new HashSet<>();
        if(!(minus1 instanceof Num))
        {
            HashSet<Variable> temp = minus1.count_variable();
            Iterator<Variable> it = temp.iterator();
            while(it.hasNext()){
                v.add(it.next());
            }
        }
        if(!(minus2 instanceof Num))
        {
            HashSet<Variable> temp = minus2.count_variable();
            Iterator<Variable> it = temp.iterator();
            while(it.hasNext()){
                v.add(it.next());
            }
        }
        return v;
    }

    @Override
    public double calculation(double num){
        return minus1.calculation(num) - minus2.calculation(num);
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Minus(minus1.calculation(name, num), minus2.calculation(name, num));
    }
}
