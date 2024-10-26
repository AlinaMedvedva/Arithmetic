package arithmetic;

public class Num extends Expression{
    private double num;

    public Num(double num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    @Override
    protected Expression clone() throws CloneNotSupportedException {
        return new Num(num);
    }

    @Override
    public Expression derivative(String name) {
        return new Num(0);
    }

    @Override
    public double calculation(double num) throws MyException {
        return this.num;
    }

    @Override
    public Expression calculation(String name, double num) {
        return this;
    }
}
