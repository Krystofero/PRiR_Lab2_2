package Calkowanie_Lab2_2;

public class MetodaProstokatow extends Thread{
    double dx, calka;

    //funkcja dla której liczymy całkę
    private static double funkcja(double x) {
        return x*x+3*Math.pow(x, 3);
    }

    MetodaProstokatow(double a, double b, int n){
        dx = (b - a) / (double)n;

        calka = 0;
        for (int i=1; i<=n; i++) {
            calka += funkcja(a + i * dx);
        }
        calka *= dx;

        //System.out.println("(Metoda Prostokątów)Wartość całki wynosi w przyblizeniu " + calka);
    }

    public double getCalka(){
        return calka;
    }
}
