package arithmetic;

import java.util.HashSet;

public class Main {
    public static void main(String []args) {

        //Введём две переменные и одно число
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Num two = new Num(2);

        //x + 2
        Sum s = new Sum(x, two);

        //y - 2
        Minus m = new Minus(y, two);

        //(x+2)^(y-2)
        Pow pow = new Pow(s, m);

        //(y-2)/(x+2)
        Splitting split = new Splitting(m,s);

        System.out.println("sum = " + s.toString());
        System.out.println("minus = " + m.toString());
        System.out.println("pow = " + pow.toString());
        System.out.println("split = " + split.toString());

        //создаём копию разности
        try {
            Expression copy = m.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Копия minus = " + m.toString());


        //Производная от pow по y
        Expression derivate = pow.derivative("y");
        System.out.println(derivate.toString());
        //TODO

        //вычисляем значение суммы
        try {
            double n = 1.0;
            double result = s.calculation(n);
            System.out.println(s.toString() + " = " + result + ", x = " + n);
        } catch (MyException e) {

            System.out.println(e.getMessage());
        }

        HashSet<Variable> var_pow = pow.count_variable();
        System.out.println("Var = " + var_pow);

        Expression calculate = split.calculation("y", 2.0);
        System.out.println("Expression split,  y = 2.0 : = " + calculate.toString());
    }
}
