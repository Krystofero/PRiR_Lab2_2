# PRiR_Lab2_2
Programowanie równolegle i rozproszone - projekt 2 labolatorium 2 Całkowanie funkcji z wykorzystaniem wielu wątków

Projekt składa  się z czterech plików. Trzy pliki to konkretne metody całkowania, natomiast plik main odpowiada za ich wywołanie na wielu wątkach.

Klasy MetodaSimpsona, MetodaProstokatow, MetodaTrapezow rozszerzają klasę Thread
Każda z metod ma domyślnie ustawioną funkcję dla której liczone są całki: x*x+3*x**3 

    private static double funkcja(double x) {
        return x*x+3*Math.pow(x, 3);
    }

Każda z tych metod wymaga podania trzech argumentów: 
a - początek przedziału całkowania,
b - koniec przedziału całkowania,
n - na ile części dzielimy przedział

Całka z danego obszaru wyliczana jest konkretną metodą(dostępne metody to: Metoda prostokątów, Metoda Trapezów, Metoda Simpsona) przy wywołaniu konstruktora, natomiast jej wartość można zwrócić funkcją getCalka() :

    public double getCalka(){
        return calka;
    }
    
W klasie main na początku pobierane są wartości wejściowe od użytkownika:

        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj poczatek przedzialu calkowania (a): ");
        a = sc.nextDouble();

        System.out.println("Podaj koniec przedzialu calkowania (b): ");
        b = sc.nextDouble();

        System.out.println("Podaj dokladnosc calkowania (n - czyli na ile części dzielimy przedział): ");
        n = sc.nextInt();
        
Następnie wyliczany jest przedział pojedyńczego odcinka:

        double odcinek = (b - a) / (double)n;
        
Tworzone są listy obiektów klas dotyczących konkretnych metod całkowania:

  ArrayList<MetodaProstokatow> calkaP = new ArrayList<>();
  ArrayList<MetodaTrapezow> calkaT = new ArrayList<>();
  ArrayList<MetodaSimpsona> calkaS = new ArrayList<>();

Następnie każdy wątek liczy całkę da swojego odcinka wywołując konstruktor:
  
        for (double i = a; i < b - odcinek; i += odcinek) { //każdy wątek liczy całkę dla swojego odcinka
            MetodaProstokatow t = new MetodaProstokatow(i, i+odcinek, n);
            calkaP.add(t);
            t.start();
        }
                                                     
Następnie sprawdzamy czy wszystkie wątki zakończyły swoją pracę:
                                                     
         try {
            for(MetodaProstokatow t : calkaP) {
                t.join();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
                                                     
Na koniec sumujemy całki z każdego odcinka otrzymując całkę z całego obszaru - co stanowi ostateczny wynik
                                                     
        for(MetodaProstokatow t : calkaP) {
            calka += t.getCalka();
        }
