import java.util.Arrays;

public class MutableGenericMatrix<T> {
    private int M; //кількість рядочків
    private int N; //кількість стовпчиків
    T[][] data; //матриця

    public MutableGenericMatrix(int m, int n) {
        this.M = m;
        this.N = n;
        data = (T[][])new Object[m][n];
    }

    public MutableGenericMatrix(T[][] temp) {
        M = temp.length;
        N = temp[0].length;
        this.data = (T[][])new Object[M][N];
        for (int i = 0; i < M; i++)
            System.arraycopy(temp[i], 0, this.data[i], 0, N);
    }

    //    копіювання матриці
    public MutableGenericMatrix(MutableGenericMatrix<T> temp) {this(temp.data);}


    //виведення матриці
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) System.out.print(data[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public T getElement(int n, int m) {return this.data[n][m];}

    //отримання рядочка матриці
    public void getRow (int n){
        for(int i = 0; i<N; i++) System.out.print(this.data[n][i] + " ");
        System.out.println();
    }

    //отримання стовпчика матриці
    public void getColumn (int m){
        for(int i = 0; i<M; i++) System.out.printf(this.data[i][m] + " ");
        System.out.println();
    }

    //виведення розмірності матриці
    public String getDim (){return (M+"x"+N);}

    //порівняння матриці
    public boolean equals(MutableGenericMatrix<T> temp) {
        if (temp.M != this.M || temp.N != this.N) return false;
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (this.data[i][j] != temp.data[i][j]) return false;
        return true;
    }

    //порівння матриці за допомогою hashCode
    public boolean hashCode(MutableGenericMatrix<T> temp) {
        int retValtemp = 0;
        int retValThis = 0;
        for (int i = 0; i < temp.M; i++) retValtemp += Arrays.hashCode(temp.data[i]);
        for (int j = 0; j < this.M; j++) retValThis += Arrays.hashCode(this.data[j]);
        return retValtemp == retValThis;
    }
    //    public int hashCode2(ImmutableGenericMatrix<T> temp) {
//        int retValThis = 0;
//        for (int j = 0; j < this.M; j++) retValThis += Arrays.hashCode(this.data[j]);
//        return retValThis;
//    }

    //створення діагональної матриці
    public MutableGenericMatrix<T> diagonal(){
        int n = this.N;
        MutableGenericMatrix<T> temp = new MutableGenericMatrix<>(n, n);
        for (int i = 0; i<n; i++){
            temp.data[i][i] = this.data[0][i];
        }
        return temp;
    }

    private static<T> T add(T x, T y) {
        if (x instanceof Integer && y instanceof Integer) {
            return (T) (Integer) ((Integer) x + (Integer) y);
        } else if (x instanceof Double && y instanceof Double) {
            return (T) (Double) ((Double) x + (Double) y);
        } else if (x instanceof String && y instanceof String) {
            return (T) ((String) x + (String) y);
        } else
            return (T) null;
    }
    //    сума матриць
    public MutableGenericMatrix<T> plus(MutableGenericMatrix<T> B) {
        MutableGenericMatrix<T> A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        MutableGenericMatrix<T> C = new MutableGenericMatrix<>(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = add(A.data[i][j], B.data[i][j]);
        return C;
    }

    private static<T> T mult(T x, int y) {
        if (x instanceof Integer) {
            return (T) (Integer) ((Integer) x * y);
        } else if (x instanceof Double) {
            return (T) (Double) ((Double) x * y);
        } else if (x instanceof String) {
            return (T) ((String) x+"*"+y);
        } else
            return (T) null;
    }

    //множення матриці на скаляр
    public MutableGenericMatrix<T> Multiplication(int num) {
        MutableGenericMatrix<T> A = this;
        MutableGenericMatrix<T> B = new MutableGenericMatrix<>(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                B.data[i][j] =mult(A.data[i][j], num);
        return B;
    }
}