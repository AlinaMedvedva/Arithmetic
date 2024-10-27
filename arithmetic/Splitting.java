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
        String split1_toString = split1.toString();
        String split2_toString = split2.toString();
        boolean flag1, flag2;
        flag1 = this.isDouble(split1_toString);
        flag2 = this.isDouble(split2_toString);
        if(flag1){
            double s1 = Double.parseDouble(split1_toString);
            if(s1 == 0.0)
                return "0";
            if(flag2){
                double s2 = Double.parseDouble(split2_toString);
                return String.valueOf(s1/s2);
            }
            return s1 + "/(" + split2_toString + ")";
        }
        return "("+ split1_toString + ")/(" + split2_toString + ")";
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return this;
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
    public double calculation(double num) {
        return (split1.calculation(num) / split2.calculation(num));
    }

    @Override
    public Expression calculation(String name, double num) {
        return new Splitting(split1.calculation(name, num), split2.calculation(name , num));
    }
}
