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
        return minus1.toString() + " - " + minus2.toString();
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return new Minus(minus1, minus2);
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
    public double calculation(double num) throws MyException {
        HashSet<Variable> v = count_variable();
        if(v.size() > 1){
            throw new MyException("Больше, чем одна переменная");
        }
        else return minus1.calculation(num) - minus2.calculation(num);
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Minus(minus1.calculation(name, num), minus2.calculation(name, num));
    }
}
