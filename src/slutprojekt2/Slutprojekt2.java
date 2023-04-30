/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package slutprojekt2;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author pouyan.kardounikhoz
 */

public class Slutprojekt2 {
    // Fält som håller reda på passagerarna och deras födelsedatum
    private static String[] passagerare = new String[21];
    private static int[] fodelsedagar = new int[21];
       
    // Antal sålda biljetter
    private static int antalBiljetter;

    // Pris per plats
    private static final double PRISPERBILJETT = 299.90;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int val;

        do {
            // Skriv ut menyn
            System.out.println("Välj ett alternativ:");
            System.out.println("1. Lägg till en passagerare");
            System.out.println("2. Printa ut antal lediga platser");
            System.out.println("3. Beräkna vinsten av antalet sålda biljetter");
            System.out.println("4. Avsluta programmet");
try {
    System.out.print("Ange 1-4: ");
} catch (InputMismatchException e) {
    System.out.println("Felaktig inmatning. Du måste ange 1-4.");
    input.nextLine(); // konsumera felaktig inmatning
}
            // Läs in användarens val
            val = input.nextInt();

            switch (val) {
                case 1 -> laggaTillPassagerare();
                case 2 -> skrivUtLedigaPlatser();
                case 3 -> beraknaVinst();
                case 4 -> System.out.println("Programmet avslutas...");
                default -> System.out.println("Felaktigt val, försök igen.");
            }
            
        } while (val != 4);

    }

    // Lägger till en passagerare till bussen
   // Lägger till en passagerare till bussen
private static void laggaTillPassagerare() {
    Scanner input = new Scanner(System.in);
    int ledigaPlatser = antalLedigaPlatser();

    if (ledigaPlatser == 0) {
        System.out.println("Tyvärr finns inga lediga platser kvar.");
    } else {
        // Hitta första lediga plats
        int plats = -1;
        for (int i = 0; i < passagerare.length; i++) {
            if (passagerare[i] == null) {
                plats = i;
                break;
            }
        }

        // Läs in passagerarens namn och födelsedatum
        System.out.print("Ange passagerarens namn: ");
        String namn = input.nextLine();

        int fodelsedatum = 0;
        boolean invalidInput = true;
        while (invalidInput) {
            try {
                System.out.print("Ange passagerarens födelsedatum (ååååmmdd): ");
                fodelsedatum = input.nextInt();
                invalidInput = false;
//InputMismatchException är ett fel som uppstår när användarens inmatning inte matchar det som förväntas enligt programkoden
            } catch (InputMismatchException e) {
                System.out.println("Felaktig inmatning. Försök igen.");
                input.nextLine(); // konsumera felaktig inmatning
            }
        }

        // Lägg till passageraren på platsen
        passagerare[plats] = namn;
        fodelsedagar[plats] = fodelsedatum;
        antalBiljetter++;

        System.out.println(namn + " har bokat plats " + (plats + 1) + ".");
    }
}


    // Skriver ut antalet lediga platser
    private static void skrivUtLedigaPlatser() {
        int ledigaPlatser = antalLedigaPlatser();
        System.out.println("Det finns " + ledigaPlatser + " lediga platser kvar.");
    }

    // Räknar ut och skriver ut vinsten av sålda biljetter
private static void beraknaVinst() {
double vinst = antalBiljetter * PRISPERBILJETT;
System.out.println("Vinsten av sålda biljetter är " + vinst + " kr.");
}


        public Slutprojekt2() {
            Slutprojekt2.antalBiljetter = 0;
        }
// Räknar ut antalet lediga platser
private static int antalLedigaPlatser() {
    int ledigaPlatser = 0;
        for (String passagerare1 : passagerare) {
            if (passagerare1 == null) {
                ledigaPlatser++;
            }
        }
                
    return ledigaPlatser;
}

    
}
