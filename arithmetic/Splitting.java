package arithmetic;

import java.util.HashSet;
import java.util.Iterator;

public class Splitting extends Expression{
    private Expression split1, split2;

    public Splitting(Expression split1, Expression split2) {
        this.split1 = split1;
        this.split2 = split2;
    }

    @Override
    public String toString() {
        if((split1 instanceof Num || split1 instanceof Variable) && !((split2 instanceof Num)||(split2 instanceof Variable))){
            return split1.toString() + "/(" + split2.toString() + ")";
        }
        if((split2 instanceof Num || split2 instanceof Variable) && !((split1 instanceof Num)||(split1 instanceof Variable))){
            return "(" +split1.toString() + ")/" + split2.toString();
        }
        if((split1 instanceof Num || split1 instanceof Variable) && ((split2 instanceof Num)||(split2 instanceof Variable)))
            return split1.toString() + "/" + split2.toString();
        return "("+split1.toString()+")/("+split2.toString()+")";
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return new Splitting(split1, split2);
    }

    @Override
    public Expression derivative(String name) {
        return new Splitting(new Minus(new Multiplication(split1.derivative(name), split2), new Multiplication(split1, split2.derivative(name))), new Pow(split2, new Num(2)));
    }

    @Override
    public HashSet<Variable> count_variable() {
        HashSet<Variable> v = new HashSet<>();
        if(!(split1 instanceof Num))
        {
            HashSet<Variable> temp = split1.count_variable();
            Iterator<Variable> it = temp.iterator();
            while(it.hasNext()){
                v.add(it.next());
            }
        }
        if(!(split2 instanceof Num))
        {
            HashSet<Variable> temp = split2.count_variable();
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
        if(split2.calculation(num) == 0.0){
            throw new MyException("Знаменатель равен нулю");
        }
        else return (split1.calculation(num) / split2.calculation(num));
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Splitting(split1.calculation(name, num), split2.calculation(name , num));
    }
}
