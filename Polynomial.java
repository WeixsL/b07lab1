public class Polynomial {
    double[] coefficients;

    public Polynomial() {
        coefficients = new double[0];
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial other) {
        double[] coef;

        int len1 = this.coefficients.length;
        int len2 = other.coefficients.length;

        coef = new double[Math.max(len1, len2)];

        for (int i = 0; i < len1; i++) {
            coef[i] += this.coefficients[i];
        }

        for (int i = 0; i < len2; i++) {
            coef[i] += other.coefficients[i];
        }

        return new Polynomial(coef);
    }

    public double evaluate(double x) {
        double res = 0.0;
        for (int i = 0; i < this.coefficients.length; i++) {
            res += this.coefficients[i] * Math.pow(x, i);
        }
        return res;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0.0;
    }

}