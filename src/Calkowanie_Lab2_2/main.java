package Calkowanie_Lab2_2;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws Exception {
        double a, b;
        int n;
        double calka = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj poczatek przedzialu calkowania (a): ");
        a = sc.nextDouble();

        System.out.println("Podaj koniec przedzialu calkowania (b): ");
        b = sc.nextDouble();

        System.out.println("Podaj dokladnosc calkowania (n - czyli na ile części dzielimy przedział): ");
        n = sc.nextInt();

        double odcinek = (b - a) / (double)n; //przedział pojedyńczego odcinka

        //Metoda Prostokątów
        ArrayList<MetodaProstokatow> calkaP = new ArrayList<>();

        for (double i = a; i < b - odcinek; i += odcinek) { //każdy wątek liczy całkę dla swojego odcinka
            MetodaProstokatow t = new MetodaProstokatow(i, i+odcinek, n);
            calkaP.add(t);
            t.start();
        }

        try {
            for(MetodaProstokatow t : calkaP) {
                t.join();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        for(MetodaProstokatow t : calkaP) { //sumowanie całek z każdego odcinka(sumowanie wyników poszczególnych wątków)
            calka += t.getCalka();
        }

        System.out.println("(Metoda Prostokątów)Wartość całki wynosi w przyblizeniu " + calka);

        //Metoda Trapezów
        calka = 0;
        ArrayList<MetodaTrapezow> calkaT = new ArrayList<>();

        for (double i = a; i < b - odcinek; i += odcinek) {//każdy wątek liczy całkę dla swojego odcinka
            MetodaTrapezow t = new MetodaTrapezow(i, i+odcinek, n);
            calkaT.add(t);
            t.start();
        }

        try {
            for(MetodaTrapezow t : calkaT) {
                t.join();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        for(MetodaTrapezow t : calkaT) {//sumowanie całek z każdego odcinka(sumowanie wyników poszczególnych wątków)
            calka += t.getCalka();
        }

        System.out.println("(Metoda Trapezów)Wartość całki wynosi w przyblizeniu " + calka);

        //Metoda Simpsona
        calka = 0;
        ArrayList<MetodaSimpsona> calkaS = new ArrayList<>();

        for (double i = a; i < b - odcinek; i += odcinek) {//każdy wątek liczy całkę dla swojego odcinka
            MetodaSimpsona t = new MetodaSimpsona(i, i+odcinek, n);
            calkaS.add(t);
            t.start();
        }

        try {
            for(MetodaSimpsona t : calkaS) {
                t.join();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        for(MetodaSimpsona t : calkaS) {//sumowanie całek z każdego odcinka(sumowanie wyników poszczególnych wątków)
            calka += t.getCalka();
        }

        System.out.println("(Metoda Simpsona)Wartość całki wynosi w przyblizeniu " + calka);

    }
}
