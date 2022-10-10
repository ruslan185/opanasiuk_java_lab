import java.util.Arrays;

final class Matrix {
    private final int M; //кількість рядочків
    private final int N; //кількість стовпчиків
    private final double[][] data; //матриця

    //створення матриці способом задання розмірності
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    //створення матриці способом задання елементів
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                this.data[i][j] = data[i][j];
    }

    //копіювання матриці
    public Matrix(Matrix A) {this(A.data);}

    //виведення матриці
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) System.out.printf("%4.1f ", data[i][j]);
            System.out.println();
        }
    }

    //отримання елемента матриці
    public void getElement(int n, int m) {System.out.println(this.data[n][m]);}

    //отримання рядочку матриці
    public void getRow (int n){
        for(int i = 0; i<N; i++) System.out.printf("%4.1f ", this.data[n][i]);
        System.out.println();
    }

    //отримання стовпчика матриці
    public void getColumn (int m){
        for(int i = 0; i<M; i++) System.out.printf("%4.1f ", this.data[i][m]);
        System.out.println();
    }

    //виведення розмірності матриці
    public void getDim (){System.out.printf("%dx%d\n", N, M);}

    //порівняння матриці
    private boolean eq(Matrix B) {
        if (B.M != this.M || B.N != this.N) return false;
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (this.data[i][j] != B.data[i][j]) return false;
        return true;
    }

    //виведення результату порівнння матриці
    public void equals(Matrix B){System.out.println(eq(B));}

    //порівння матриці за допомогою hashCode
    public void hashCode(Matrix B) {
        int retValB = 0;
        int retValThis = 0;
        for (int i = 0; i < B.M; i++) retValB += Arrays.hashCode(B.data[i]);
        for (int j = 0; j < this.M; j++) retValThis += Arrays.hashCode(this.data[j]);
        System.out.println(retValB == retValThis);
    }

    //створення діагональної матриці
    public void diagonal(){
        int n = this.N;
        Matrix A = new Matrix(n, n);
        for (int i = 0; i<n; i++){
            A.data[i][i] = this.data[0][i];
        }
        A.show();
    }

    //сума матриць
    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }

    //множення матриці на скаляр
    public Matrix Multiplication(int num) {
        Matrix A = this;
        Matrix B = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                B.data[i][j] = A.data[i][j] * num;
        return B;
    }
}
