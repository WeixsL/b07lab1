import java.io.*;

public class Driver {
    public Driver() {
    }
    public static void main(String[] args) {
        double[] c1 = {5,3,7};
        int[] e1 = {0, 2, 8};
        double[] c2 = {3,2};
        int[] e2 = {2,5};
        Polynomial poly1 = new Polynomial(c1, e1);
        Polynomial poly2 = new Polynomial(c2, e2);


        System.out.println("Poly1: " + poly1);
        System.out.println("Poly2: " + poly2);

        double result = poly1.evaluate(2);
        System.out.println("Poly1 evaluated at x=2: " + result);

        boolean hasRoot = poly1.hasRoot(1);
        System.out.println("Poly1 has root at x=1: " + hasRoot);

        Polynomial product = poly1.multiply(poly2);
        System.out.println("Product: " + product);

        try {
            poly1.saveToFile("poly1.txt");
            Polynomial loadedPoly1 = new Polynomial(new File("poly1.txt"));
            System.out.println("Loaded Poly1: " + loadedPoly1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}