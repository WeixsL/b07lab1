import java.io.*;
import java.util.Arrays;

public class Polynomial {
    double[] coef;
    int[] exponents;

    public Polynomial() {
        this.coef = new double[1];
        this.exponents = new int[1];
    }

    public Polynomial(double[] coefficients, int[] exponent) {
        this.coef = new double[coefficients.length];
        System.arraycopy(coefficients, 0, coef, 0, coefficients.length);
        this.exponents = new int[exponent.length];
        System.arraycopy(exponent, 0, exponents, 0, exponent.length);
    }

    // public Polynomial add(Polynomial other) {
    //     double[] res_coef = new double[coef.length + other.coef.length];
    //     int[] ex_res = new int[exponents.length + other.exponents.length];

    //     int i = 0, j = 0, k = 0;
    //     while (i < coef.length && j < other.coef.length) {
    //         if (exponents[i] == other.exponents[j]) {
    //             res_coef[k] = coef[i] + other.coef[j];
    //             ex_res[k] = exponents[i];
    //             i++;
    //             j++;
    //         } else if (exponents[i] > other.exponents[j]) {
    //             res_coef[k] = coef[i];
    //             ex_res[k] = exponents[i];
    //             i++;
    //         } else {
    //             res_coef[k] = other.coef[j];
    //             ex_res[k] = other.exponents[j];
    //             j++;
    //         }
    //         k++;
    //     }

    //     while (i < coef.length) {
    //         res_coef[k] = coef[i];
    //         ex_res[k] = exponents[i];
    //         i++;
    //         k++;
    //     }

    //     while (j < other.coef.length) {
    //         res_coef[k] = other.coef[j];
    //         ex_res[k] = other.exponents[j];
    //         j++;
    //         k++;
    //     }

    //     return new Polynomial(res_coef, ex_res);
    // }

    public Polynomial add(Polynomial other) {
        int maxDegree = Math.max(this.exponents[this.exponents.length - 1], other.exponents[other.exponents.length - 1]);
        double[] resultCoeffs = new double[maxDegree + 1];
    
        for (int i = 0; i < this.coef.length; i++) {
            resultCoeffs[this.exponents[i]] += this.coef[i];
        }
    
        for (int i = 0; i < other.coef.length; i++) {
            resultCoeffs[other.exponents[i]] += other.coef[i];
        }
    
        int resultDegree = maxDegree;
        while (resultDegree >= 0 && resultCoeffs[resultDegree] == 0) {
            resultDegree--;
        }
    
        double[] trimmedCoeffs = Arrays.copyOf(resultCoeffs, resultDegree + 1);
        int[] trimmedExponents = new int[resultDegree + 1];
    
        for (int i = 0; i <= resultDegree; i++) {
            trimmedExponents[i] = i;
        }
    
        return new Polynomial(resultCoeffs, trimmedExponents);
    }
    

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coef.length; i++) {
            result += coef[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

    public Polynomial multiply(Polynomial p) {
        int len = p.coef.length + this.coef.length - 1;

        double[] res_coef = new double[len];
        int[] ex_res = new int[len];

        for (int i = 0; i < p.coef.length; i++) {
            for (int j = 0; j < this.coef.length; j++) {
                res_coef[i + j] += p.coef[i] * this.coef[j];
                ex_res[i + j] = p.exponents[i] + this.exponents[j];
            }
        }

        return new Polynomial(res_coef, ex_res);
    }

    public Polynomial(File file) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader(file));
        String line = b.readLine();
        b.close();

        if (line != null && !line.isEmpty()) {
            this.initiallizer(line);
        } else {
            System.out.println("File is empty or contains no valid polynomial.");
        }
    }

    private void initiallizer(String file_string) {
        String[] elements = file_string.split("(?=[+-])");
        coef = new double[elements.length];
        exponents = new int[elements.length];

        for (int i = 0; i < elements.length; i++) {
            if (elements[i].indexOf(120) == -1) {
                exponents[i] = 0;
                if (elements[i].startsWith("-")) {
                    coef[i] = Double.parseDouble(elements[i]);
                } else {
                    coef[i] = Double.parseDouble(elements[i].substring(1));
                }
            }
            else {
                String[] tmp = elements[i].split("x");
                exponents[i] = Integer.parseInt(tmp[1]);
                if (tmp[0].startsWith("-")) {
                    coef[i] = Double.parseDouble(tmp[0]);
                } else {
                    coef[i] = Double.parseDouble(tmp[0].substring(1));
                }
            }
        }
    }

    public void saveToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(this.toString());
        writer.close();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < coef.length; i++) {
            if (coef[i] > 0 && i > 0) {
                s.append("+");
            }
            s.append(coef[i]);
            if (exponents[i] > 0) {
                s.append("x");
                if (exponents[i] > 1) {
                    s.append(exponents[i]);
                }
            }
        }
        return s.toString();
    }

}