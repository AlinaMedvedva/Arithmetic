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
        return "ln(" + e.toString() + ")";
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return new Ln(e);
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
    public double calculation(double num) throws MyException {
        HashSet<Variable> v = count_variable();
        if(v.size() > 1){
            throw new MyException("Больше, чем одна переменная");
        }
        else return Math.log(e.calculation(num));
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Ln(e.calculation(name, num));
    }
}
