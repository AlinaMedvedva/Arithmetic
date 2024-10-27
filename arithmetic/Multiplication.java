package arithmetic;

import java.util.HashSet;
import java.util.Iterator;

public class Multiplication extends Expression{
    private Expression mult1, mult2;

    public Multiplication(Expression mult1, Expression mult2) {
        this.mult1 = mult1;
        this.mult2 = mult2;
    }

    @Override
    public String toString() {
        String mult1_toString = mult1.toString();
        String mult2_toString = mult2.toString();
        boolean flag1 = false, flag2 = false;
        flag1 = this.isDouble(mult1_toString);
        flag2 = this.isDouble(mult2_toString);
        if(flag1 && flag2){
            double m1 = Double.parseDouble(mult1_toString);
            double m2 = Double.parseDouble(mult2_toString);
            if(m1*m2 == 0.0)
                return "0";
            return String.valueOf(m1*m2);
        }
        if(flag1){
            double m1 = Double.parseDouble(mult1_toString);
            if(m1 == 0.0)
                return "0";
            if(m1 == 1.0)
                return mult2_toString;
            return m1 + "*(" + mult2_toString + ")";
        }
        if(flag2){
            double m2 = Double.parseDouble(mult2_toString);
            if(m2 == 0.0)
                return "0";
            if(m2 == 1.0)
                return mult1_toString;
            return m2 + "*(" + mult1_toString + ")";
        }
        return "("+mult1_toString+")*("+mult2_toString+")";
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return this;
    }

    @Override
    public Expression derivative(String name) {
        return new Sum(new Multiplication(mult1.derivative(name), mult2), new Multiplication(mult1, mult2.derivative(name)));
    }

    @Override
    public HashSet<Variable> count_variable() {
        HashSet<Variable> v = new HashSet<>();
        if(!(mult1 instanceof Num))
        {
            HashSet<Variable> temp = mult1.count_variable();
            Iterator<Variable> it = temp.iterator();
            while(it.hasNext()){
                v.add(it.next());
            }
        }
        if(!(mult2 instanceof Num))
        {
            HashSet<Variable> temp = mult2.count_variable();
            Iterator<Variable> it = temp.iterator();
            while(it.hasNext()){
                v.add(it.next());
            }
        }
        return v;
    }

    @Override
    public double calculation(double num){
        return mult1.calculation(num) * mult2.calculation(num);
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Multiplication(mult1.calculation(name, num), mult2.calculation(name, num));
    }
}
