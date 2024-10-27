package arithmetic;

import java.util.HashSet;
import java.util.Iterator;

public class Pow extends Expression{
    private Expression num, pow;

    public Pow(Expression pow1, Expression pow2) {
        num = pow1;
        pow = pow2;
    }

    @Override
    public String toString() {
        String num_toString = num.toString();
        String pow_toString = pow.toString();
        boolean flag1, flag2;
        flag1 = this.isDouble(num_toString);
        flag2 = this.isDouble(pow_toString);
        if(flag2){
            double p =Double.parseDouble(pow_toString);
            if(p==0) return "1";
            if(flag1){
                double n = Double.parseDouble(num_toString);
                return String.valueOf(Math.pow(n, p));
            }
            return "(" + num_toString + ")^" + p;
        }
        return "(" + num_toString + ")^(" + pow_toString + ")";
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return this;
    }

    @Override
    public Expression derivative(String name) {
        return new Sum(new Multiplication((new Exp(pow).derivative(name)), num), new Multiplication(new Exp(pow), num.derivative(name)));
    }

    @Override
    public HashSet<Variable> count_variable() {
        HashSet<Variable> v = new HashSet<>();
        if(!(num instanceof Num))
        {
            HashSet<Variable> temp = num.count_variable();
            Iterator<Variable> it = temp.iterator();
            while(it.hasNext()){
                v.add(it.next());
            }
        }
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

    @Override
    public double calculation(double num) {
        return Math.pow( this.num.calculation(num),  pow.calculation(num));
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Pow(this.num.calculation(name, num), pow.calculation(name, num));
    }
}
