package arithmetic;

import java.util.HashSet;

public class Main {
    public static void main(String []args) {
        //Введём две переменные и одно число
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Num two = new Num(2);

        //x + 2
        Expression s = x.add(two);

        //y - 2
        Expression m = y.difference(two);

        //(x+2)^(y-2)
        Expression pow = s.pow(m);

        //(y-2)/(x+2)
        Expression split = m.div(s);

        System.out.println("sum = " + s);
        System.out.println("minus = " + m);
        System.out.println("pow = " + pow);
        System.out.println("split = " + split);

        //создаём копию разности
        try {
            Expression copy = m.clone();
            System.out.println("Копия minus = " + copy);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }


        //Производная от pow по y
        Expression derivate = pow.derivative("y");
        System.out.println("derivate pow dy = " + derivate.toString());

        //вычисляем значение суммы
        try {
            double n = 1.0;
            double result = s.calculate_expression(n);
            System.out.println(s + " = " + result + ", x = " + n);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        HashSet<Variable> var_pow = pow.count_variable();
        System.out.println("Var = " + var_pow);

        Expression calculate = split.calculation("y", 2.0);
        System.out.println("Expression split, y = 2.0 : = " + calculate);
    }
}
