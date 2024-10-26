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
        if((num instanceof Num || num instanceof Variable) && !((pow instanceof Num)||(pow instanceof Variable))){
            return num.toString() + "^(" + pow.toString() + ")";
        }
        if((pow instanceof Num || pow instanceof Variable) && !((num instanceof Num)||(num instanceof Variable))){
            return "(" + num.toString() + ")^" + pow.toString();
        }
        if((num instanceof Num || num instanceof Variable) && ((pow instanceof Num)||(pow instanceof Variable)))
            return num.toString() + "^" + pow.toString();
        return "("+num.toString()+")^("+pow.toString()+")";
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return new Pow(num, pow);
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
    public double calculation(double num) throws MyException {
        HashSet<Variable> v = count_variable();
        if(v.size() > 1){
            throw new MyException("Больше, чем одна переменная");
        }
        else return Math.pow( this.num.calculation(num),  pow.calculation(num));
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Pow(this.num.calculation(name, num), pow.calculation(name, num));
    }
}
