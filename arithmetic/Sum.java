package arithmetic;

import java.util.HashSet;
import java.util.Iterator;

public class Sum extends Expression{
    private Expression sum1, sum2;
    public Sum(Expression s1, Expression s2){
        sum1 = s1;
        sum2 = s2;
    }

    @Override
    public String toString() {
        String sum1_toString = sum1.toString();
        String sum2_toString = sum2.toString();
        boolean flag1, flag2;
        flag1 = this.isDouble(sum1_toString);
        flag2 = this.isDouble(sum2_toString);
        if(flag1 && flag2){
            return String.valueOf(Double.parseDouble(sum1_toString) + Double.parseDouble(sum2_toString));
        }
        if(flag1)
        {
            double s1 = Double.parseDouble(sum1_toString);
            if(s1 == 0.0)
                return sum2_toString;
        }
        if(flag2){
            double s2 = Double.parseDouble(sum2_toString);
            if(s2 == 0)
                return sum1_toString;
        }
        return sum1.toString() + " + " + sum2.toString();
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return this;
    }

    @Override
    public Expression derivative(String name) {
        return new Sum(sum1.derivative(name), sum2.derivative(name));
    }

    @Override
    public HashSet<Variable> count_variable() {
        HashSet<Variable> v = new HashSet<>();
        if(!(sum1 instanceof Num))
        {
            HashSet<Variable> temp = sum1.count_variable();
            Iterator<Variable> it = temp.iterator();
            while(it.hasNext()){
                v.add(it.next());
            }
        }
        if(!(sum2 instanceof Num))
        {
            HashSet<Variable> temp = sum2.count_variable();
            Iterator<Variable> it = temp.iterator();
            while(it.hasNext()){
                v.add(it.next());
            }
        }
        return v;
    }

    @Override
    public double calculation(double num){
        return sum1.calculation(num) + sum2.calculation(num);
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Sum(sum1.calculation(name, num), sum2.calculation(name, num));
    }
}
