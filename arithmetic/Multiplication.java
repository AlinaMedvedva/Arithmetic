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
        if((mult1 instanceof Num || mult1 instanceof Variable) && !((mult2 instanceof Num)||(mult2 instanceof Variable))){
            return mult1.toString() + "*(" + mult2.toString() + ")";
        }
        if((mult2 instanceof Num || mult2 instanceof Variable) && !((mult1 instanceof Num)||(mult1 instanceof Variable))){
            return "(" +mult1.toString() + ")*" + mult2.toString();
        }
        if((mult1 instanceof Num || mult1 instanceof Variable) && ((mult2 instanceof Num)||(mult2 instanceof Variable)))
            return mult1.toString() + "*" + mult2.toString();
        return "("+mult1.toString()+")*("+mult2.toString()+")";
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return new Multiplication(mult1, mult2);
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
    public double calculation(double num) throws MyException {
        HashSet<Variable> v = count_variable();
        if(v.size() > 1){
            throw new MyException("Больше, чем одна переменная");
        }
        else return mult1.calculation(num) * mult2.calculation(num);
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Multiplication(mult1.calculation(name, num), mult2.calculation(name, num));
    }
}
