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
        return sum1.toString() + " + " + sum2.toString();
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return new Sum(sum1, sum2);
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
    public double calculation(double num) throws MyException{
        HashSet<Variable> v = count_variable();
        if(v.size() > 1){
            throw new MyException("Больше, чем одна переменная");
        }
        else return sum1.calculation(num) + sum2.calculation(num);
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Sum(sum1.calculation(name, num), sum2.calculation(name, num));
    }
}
