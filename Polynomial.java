public class Polynomial {
    double[] coef;

    public Polynomial() {
        this.coef = new double[1];
    }

    public Polynomial(double[] coefficients) {
        int length = coefficients.length;
        this.coef = new double[length];
        System.arraycopy(coefficients, 0, coef, 0, length);
    }

    public Polynomial add(Polynomial other) {
        int len1 = this.coef.length;
        int len2 = other.coef.length;
        int max = Math.max(len1, len2);
        double[] count = new double[max];

        int i = 0;
        while (i < len1) {
            count[i] += this.coef[i];
            i++;
        }

        for (int j = 0; j < len2; j++) {
            count[j] += other.coef[j];
        }

        return new Polynomial(count);
    }

    public double evaluate(double x) {
        int length = coef.length;
        double res = 0;
        
        int i = 0;
        while (i < length) {
            res += coef[i] * Math.pow(x, i);
            i++;
        }
        return res;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

}