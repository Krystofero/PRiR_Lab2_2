package Calkowanie_Lab2_2;

public class MetodaTrapezow extends Thread{
    double h, calka;

    //funkcja dla której liczymy całkę
    private static double funkcja(double x) {
        return x*x+3*Math.pow(x, 3);
    }

    MetodaTrapezow(double a, double b, int n){
        calka = 0;

        h = (b-a)/n;  //przedział <a,b> podzielony na n równych części (h = odleglość między xi a xi+1)

        double xi[]= new double[n+1]; //wartości funkcji punktów w przedziale(skoro mamy n przedziałów to jest n+1 punktów xi)
        xi[0] = a; //pierwszy równy a
        xi[n] = b; //ostatni równy b

        for(int i=1;i<n;i++){ //wyznaczam punkty xi:
            xi[i] = a + i*h;
        }
        for(int i=0;i<n;i++){
            calka += funkcja(xi[i]) + funkcja(xi[i+1]);
        }
        calka = h/2 * calka;

        //System.out.println("(Metoda Trapezów)Wartość całki wynosi w przyblizeniu " + calka);
    }

    public double getCalka(){
        return calka;
    }
}
