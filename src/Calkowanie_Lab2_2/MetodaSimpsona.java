package Calkowanie_Lab2_2;

public class MetodaSimpsona extends Thread{
    double h, calka;

    //funkcja dla której liczymy całkę
    private static double funkcja(double x) {
        return x*x+3*Math.pow(x, 3);
    }

    MetodaSimpsona(double a, double b, int n){

        h = (b - a) / (2 * n); // h = odleglosc miedzy xi a ti ;(odleglość między xi a xi+1 dzielona na 2)

        double x[] = new double[2 * n + 1]; //mamy 2 razy więcej punktów w porównaniu z metodą trapezów, gdyż dochodzą jeszcze punkty ti pomiędzy xi
        x[0] = a; //pierwszy równy a
        x[2 * n] = b; //ostatni równy b
        calka = 0;
        calka += funkcja(x[0]);
        calka += funkcja(x[2 * n]);

        for (int i = 1; i < 2 * n; i++) {//wyznaczam punkty xi:
            x[i] = x[i - 1] + h;
        }
        for (int i = 1; i < 2 * n; i++) {
            if ((i % 2) != 0) { //jeśli punktem jest ti
                calka += 4 * funkcja(x[i]);
            } else if ((i % 2) == 0) {//jeśli punktem jest xi
                calka += 2 * funkcja(x[i]);
            }
        }
        calka = h / 3 * calka;

        //System.out.println("(Metoda Simpsona)Wartość całki wynosi w przyblizeniu " + calka);
    }

    public double getCalka(){
        return calka;
    }
}
