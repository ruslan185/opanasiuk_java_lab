import java.util.Arrays;

class Main {

    public static void main(String[] args) {

        Integer[][] A = {{1, 2, 3, 4}, {4, 5, 6, 7}, {7, 8, 9, 10}};
        Double[][] B = {{1., 2., 3.}, {4., 5., 6.}};
        String[][] C = {{"a", "b"}, {"c", "d"}};
        Double[][] E = {{1., 2., 3., 2., 1.}};

        ImmutableGenericMatrix<Integer> a = new ImmutableGenericMatrix<>(A);
        ImmutableGenericMatrix<Double> b = new ImmutableGenericMatrix<>(B);
        ImmutableGenericMatrix<String> c = new ImmutableGenericMatrix<>(C);
        ImmutableGenericMatrix<Double> e = new ImmutableGenericMatrix<>(E);
        a.show();
        b.show();
        c.show();

        ImmutableGenericMatrix<Double> x = new ImmutableGenericMatrix<>(2,3);
        x.show();

        ImmutableGenericMatrix<String> d = new ImmutableGenericMatrix<>(c);
        d.show();

        System.out.println(c.getElement(0, 1));
        a.getRow(2);
        a.getRow(2);
        a.getColumn(2);
        System.out.println(a.getDim());

        System.out.println(a.equals(b));
        System.out.println(c.equals(d));

        System.out.println(b.hashCode(e));
        System.out.println(c.hashCode(d));

        ImmutableGenericMatrix<Double> f = e.diagonal();
        f.show();

        ImmutableGenericMatrix<String> n = c.plus(d);
        n.show();

        ImmutableGenericMatrix<Integer> a2 = a.Multiplication(5);
        ImmutableGenericMatrix<Double> b2 = b.Multiplication(5);
        ImmutableGenericMatrix<String> c2 = c.Multiplication(5);
        a2.show();
        b2.show();
        c2.show();


    }
}
