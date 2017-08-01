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
        String wybor_planszy = "";
        System.out.println("Wybierz wielkosc planszy M [3x3]  D [5x5] BD [7x7]:");
        wybor_planszy = scanner.nextLine();

        if (wybor_planszy.equals("M")) {
            System.out.println("wybrales plansze mala 3x3:");
        } else if (wybor_planszy.equals("D")) {
            System.out.println("wybrales plansze duza 5x5:");
            plansza = new char[5][5];

        } else if (wybor_planszy.equals("BD")) {
            System.out.println("wybrales plansze duza 7x7:");
            plansza = new char[7][7];
        }

        ustawianie_stanu_poczatkowego();
        while (true) {
            System.out.println("kolejna tura:");
            wyswietl_stan();

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
                    int x = (int) Math.floor(Math.random() * plansza.length);
                    int y = (int) Math.floor(Math.random() * plansza.length);
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
        for (int i = 0; i < plansza.length; i++) {
            if (sprawdz_wygrana_w_kolumnie(znak, i)) {
                return true;
            }

        }
        for (int i = 0; i < plansza.length; i++) {
            if (sprawdz_wygrana_w_wierszu(znak, i)) {
                return true;
            }
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
        boolean result = true;
        for (int i = 0; i < plansza[kolumna].length; i++) {
            if (plansza[kolumna][i] != znak) {
                result = false;
            }
        }
        return result;
    }

    /**
     * sprawdzanie wygranej w wierszu
     *
     * @param znak wybor czy jest kolko czy krzyzyk
     * @param wiersz numer wiersza w ktorym nastapila wygrana
     * @return jesli wygrywa kolko/krzyzyk zwraca true jesli nie wygrywa false
     */
    public static boolean sprawdz_wygrana_w_wierszu(char znak, int wiersz) {
        boolean result = true;
        for (int i = 0; i < plansza[wiersz].length; i++) {
            if (plansza[wiersz][i] != znak) {
                result = false;
            }
        }
        return result;
    }

    public static boolean sprawdz_wygrana_na_skos(char znak) {
        return sprawdz_wygrana_na_skos_1(znak) || sprawdz_wygrana_na_skos_2(znak);
    }

    public static boolean sprawdz_wygrana_na_skos_1(char znak) {
        boolean result = true;
        for (int i = 0; i < plansza.length; i++) {
            if (plansza[i][i] != znak) {
                result = false;
            }

        }
        return result;
    }

    public static boolean sprawdz_wygrana_na_skos_2(char znak) {
        boolean result = true;
        for (int i = 0, j = plansza.length - 1; i < plansza.length; i++, j--) {
            if (plansza[i][j] != znak) {
                result = false;
            }

        }
        return result;
    }

    public static boolean sprawdz_remis() {

        if (plansza.length == 3) {
            return numerRuchu == 10 && !sprawdz_wygrana(kolko) && !sprawdz_wygrana(krzyzyk);
        } else if (plansza.length == 5) {
            return numerRuchu == 26 && !sprawdz_wygrana(kolko) && !sprawdz_wygrana(krzyzyk);
        } else {
            return numerRuchu == 50 && !sprawdz_wygrana(kolko) && !sprawdz_wygrana(krzyzyk);
        }

// return numerRuchu == 50  numerRuchu == 10 && numerRuchu == 26 && !sprawdz_wygrana(kolko) && !sprawdz_wygrana(krzyzyk);
    }
}
