//gra kółko i krzyżyk
package pl.chizynski.kolkokrzyzyk;

import java.util.Scanner;

public class KolkoKrzyzyk {

    static char[][] plansza = new char[3][3];
    static char puste = ' ';
    static char kolko = 'O';
    static char krzyzyk = 'X';
    static int numerRuchu = 1;
    static char wynik = 0;

    public static void main(String[] args) {
        int wiersz = 0;
        int kolumna = 0;
        boolean value = true;
        boolean endofgame = false;

        ustawianie_stanu_poczatkowego();
        wyswietl_stan();
        System.out.println("wybierz kto zaczyna?:");
        wynik = wybierz_znak();

        String wybor = "";
        System.out.println("wybor gracza, z kim chcesz zagrac? [K / C]:");
        wybor = scanner.nextLine();
        if (wybor.equals("K")) {
            System.out.println("rozpoczynasz nowa  gre z Computer:");

        } else if (wybor.equals("C")) {
            System.out.println("rozpoczynasz nowa gre z graczem:");
        }

        while (true) {

            if (sprawdz_wygrana(krzyzyk)) {
                endofgame = true;
                System.out.println("wygrywa X:");
            }
            if (sprawdz_wygrana(kolko)) {
                endofgame = true;
                System.out.println("wygrywa O:");

            }

            if (sprawdz_remis()) {
                endofgame = true;
                System.out.println("remis");

            }

            if (endofgame == true) {
                String T = "";
                System.out.println("czy chcesz rozpoczac nowa gre? [T/n]");
                T = scanner.nextLine();
                if (T.equals("T")) {

                    numerRuchu = 0;
                    ustawianie_stanu_poczatkowego();
                    endofgame = false;
                } else {
                    break;
                }
            }

            if (wybor.equals("K") && numerRuchu % 2 != 0) {
                while (true) {
                    int x = (int) Math.floor(Math.random() * 3);
                    int y = (int) Math.floor(Math.random() * 3);
                    if (puste == plansza[x][y]) {
                        if (numerRuchu % 2 != 0) {
                            plansza[x][y] = wynik;
                        } else if (numerRuchu % 2 == 0) {
                            if (wynik == kolko) {
                                plansza[x][y] = krzyzyk;
                            } else if (wynik == krzyzyk) {
                                plansza[x][y] = kolko;
                            }

                        }
                        break;
                    }

                }

            } else {
                value = pobierz_wartosc();
            }

            wyswietl_stan();
            if (value == true) {
                numerRuchu++;
            } else {
                System.out.println("brak zwiekszania tury:");
            }

        }
    }

    public static void ustawianie_stanu_poczatkowego() {
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza[i].length; j++) {
                plansza[i][j] = puste;
            }
        }
    }

    public static void wyswietl_stan() {
        System.out.println("ponizej stan planszy:");

        for (int i = 0; i < plansza.length; i++) {
            System.out.print("|");
            for (int j = 0; j < plansza[i].length; j++) {
                System.out.print(plansza[i][j] + "|");
            }
            System.out.println("");
        }
    }
    public static Scanner scanner = new Scanner(System.in);

    public static boolean pobierz_wartosc() {
        int wiersz = 0;
        int kolumna = 0;
        boolean value = true;
        System.out.println("wykonaj ruch:");

        try {
            System.out.println("pobierz wartosc wiersza:");
            wiersz = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exc) {
            value = false;
            System.out.println("podales niepoprawna wartosc:");
        } catch (ArrayIndexOutOfBoundsException exc) {
            value = false;
            System.out.println("wyszedles poza zakres:");
        }

        try {
            System.out.println("pobierz wartosc kolumny:");
            kolumna = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exc) {
            value = false;
            System.out.println("podales niepoprawna wartosc:");
        } catch (ArrayIndexOutOfBoundsException exc) {
            value = false;
            System.out.println("wyszedles poza zakres:");
        }

        try {
            if (puste == plansza[wiersz][kolumna]) {

                if (numerRuchu % 2 != 0) {
                    plansza[wiersz][kolumna] = wynik;
                } else if (numerRuchu % 2 == 0) {
                    if (wynik == kolko) {
                        plansza[wiersz][kolumna] = krzyzyk;
                    } else if (wynik == krzyzyk) {
                        plansza[wiersz][kolumna] = kolko;
                    }
                }
            } else {
                System.out.println("to pole jest zajete wybierz inne:");
                value = false;
            }

        } catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println("wyszedles poza zakres:");
            value = false;
        }
        return value;
    }

    public static boolean sprawdz_wygrana(char znak) {
        if (sprawdz_wygrana_w_kolumnie(znak, 0) || sprawdz_wygrana_w_kolumnie(znak, 1) || sprawdz_wygrana_w_kolumnie(znak, 2)) {
            return true;
        }
        if (sprawdz_wygrana_w_wierszu(znak, 0) || sprawdz_wygrana_w_wierszu(znak, 1) || sprawdz_wygrana_w_wierszu(znak, 2)) {
            return true;
        }
        if (sprawdz_wygrana_na_skos(znak)) {
            return true;
        }
        return false;
    }

    public static char wybierz_znak() {
        String wybor = "";
        wybor = scanner.nextLine();
        if (wybor.equals("O")) {
            System.out.println("zaczyna kolko:");
            return wybor.charAt(0);

        } else if (wybor.equals("X")) {
            System.out.println("zaczyna krzyzyk:");
            return wybor.charAt(0);
        }
        return puste;
    }

    public static boolean sprawdz_wygrana_w_kolumnie(char znak, int kolumna) {
        return plansza[kolumna][0] == znak && plansza[kolumna][1] == znak && plansza[kolumna][2] == znak;
    }
    
    /**
     * sprawdzanie wygranej w wierszu
     * @param znak wybor czy jest kolko czy krzyzyk
     * @param wiersz numer wiersza w ktorym nastapila wygrana
     * @return jesli wygrywa kolko/krzyzyk zwraca true jesli nie wygrywa false
     */
    public static boolean sprawdz_wygrana_w_wierszu(char znak, int wiersz) {
        return plansza[0][wiersz] == znak && plansza[1][wiersz] == znak && plansza[2][wiersz] == znak;
    }

    public static boolean sprawdz_wygrana_na_skos(char znak) {
        return (plansza[0][0] == znak && plansza[1][1] == znak && plansza[2][2] == znak)
                || (plansza[2][0] == znak && plansza[1][1] == znak && plansza[0][2] == znak);
    }

    public static boolean sprawdz_remis() {
        return numerRuchu == 10 && !sprawdz_wygrana(kolko) && !sprawdz_wygrana(krzyzyk);
    }
}
