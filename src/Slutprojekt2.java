import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Slutprojekt2 {
    
    static final int[] VANLIGAPLATSER = {2, 3, 6, 7, 10, 11, 14, 15, 18, 19, 20};
    static final int[] FÖNSTERPLATSER= {1, 4, 5, 8, 9, 12, 13, 16, 17, 21};
    static final double PENSIONÄR_KOSTNAD = 200.0;
    static final double VUXEN_KOSTNAD = 299.90;
    static final double KOSTNAD_BARN = 149.90;
    static final int ALLAPLATSER = 21;
    
   
    static String[] bokningar = new String[ALLAPLATSER];
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        int inmatning;
        
        boolean felaktigInmatning;
    
    do {
        System.out.println("Meny:");
        System.out.println("1. Boka en plats");
        System.out.println("2. Printa lista i ordning av bookings så du kan se platser som är upptagna och av vem");
        System.out.println("3. Ta reda på en bokning med personnummer eller namn för att se upptagen plats");
        System.out.println("4. Beräkna vinsten av alla sålda biljetter");
        System.out.println("5. Ta bort en bokning med personnummer eller namn");
        System.out.println("0. Avsluta programmet");
        System.out.print("Välj alternativ 0-5: ");
        try {
            inmatning = scan.nextInt();
            felaktigInmatning = false;
        } catch (InputMismatchException e) {
            System.out.println("Felaktig inmatning, välj 0-5");
            scan.nextLine(); // konsumera den felaktiga inmatningen
            felaktigInmatning = true;
            inmatning = -1;
        }
            boolean rättsvar = false;
                if(inmatning==1){ boka(); rättsvar=true;}
                
                if(inmatning==2){ visaBokningar();rättsvar=true;}
                
                if(inmatning==3){ sokBokning();rättsvar=true;}
                
                if(inmatning==4){ System.out.println("Total vinst: " + räknaVinst());rättsvar=true;}
                
                if(inmatning==5){ avboka();rättsvar=true;}

                if(inmatning==0){ System.out.println("Programmet avslutas");rättsvar=true;}
                
            }
        while (inmatning != 0);
    }

static void boka() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ange resenärens födelsedatum (yyyymmdd): ");
    String födelsedatum;
    
    try {
        födelsedatum = scanner.next("[0-9]{8}");
    } catch (InputMismatchException e) {
        System.out.println("Felaktig inmatning. Ange födelsedatum i formatet yyyymmdd.");
        return;
    }
    
    System.out.print("Ange resenärens förnamn: ");
    String fornamn;
    
    try {
        fornamn = scanner.next("[a-zA-Z]+");
    } catch (InputMismatchException e) {
        System.out.println("Felaktig inmatning. Ange namnet med bokstäver från A till Ö.");
        return;
    }
    
    System.out.print("Ange resenärens efternamn: ");
    String efternamn;
    
    try {
        efternamn = scanner.next("[a-zA-Z]+");
    } catch (InputMismatchException e) {
        System.out.println("Felaktig inmatning. Ange namnet med bokstäver från A till Ö.");
        return;
    }
    
    System.out.print("Ange resenärens kön (M/F): ");
    String kön;
    
    try {
        kön = scanner.next("[MFmf]");
    } catch (InputMismatchException e) {
        System.out.println("Felaktig inmatning. Ange 'M' för manligt eller 'F' för kvinnligt kön.");
        return;
    }
    
    System.out.println("Välj en plats:");
    System.out.println("Fönsterplatser: " + platserFörString(FÖNSTERPLATSER));
    System.out.println("Gångplatser: " + platserFörString(VANLIGAPLATSER));
    System.out.print("Ange platsnummer: ");
    
    try {
        int platsnummer = scanner.nextInt();
        
        if (platsTillgänglig(platsnummer)) {
            System.out.println("Platsen är redan bokad.");
        } else {
            String bokning = födelsedatum + ";" + fornamn +";" + efternamn + ";" + kön + ";" + platsnummer;
            bokningar[platsnummer - 1] = bokning;
            System.out.println("Bokningen är registrerad.");
        }
    } catch (InputMismatchException e) {
        System.out.println("Felaktig inmatning. Ange ett heltal som platsnummer.");
    }
}


static void sokBokning() {
Scanner scan = new Scanner(System.in);
System.out.print("Skriv in personnummer eller resenärens förnamn, efternamn eller hela namn: ");
String sokord = scan.nextLine();
boolean hittad = false;
for (int i = 0; i < bokningar.length; i++) {
if (bokningar[i] != null && bokningar[i].toLowerCase().contains(sokord.toLowerCase())) {
System.out.println("Bokning hittad på plats " + (i+1) + ": " + bokningar[i]);
hittad = true;
}
}
if (!hittad) {
System.out.println("Ingen bokning hittades med sökordet: " + sokord);
}
}

static void avboka() {
Scanner scan = new Scanner(System.in);
System.out.println("Vill du ta bort en bokning med (1) förnamn, (2) personnummer eller (3) platsnummer?");
int inmatning = scan.nextInt();
scan.nextLine(); // Tar bort newline character från förra scanen
switch (inmatning) {
    case 1 -> {
        System.out.print("Skriv in förnamnet på personen vars bokning du vill ta bort: ");
        String soknamn = scan.nextLine();
        for (int i = 0; i < bokningar.length; i++) {
            if (bokningar[i] != null) {
                String[] delar = bokningar[i].split(";");
                String fornamn = delar[1];
                if (fornamn.equals(soknamn)) {
                    System.out.println("Bokning på plats " + (i + 1) + " togs bort: " + bokningar[i]);
                    bokningar[i] = null;
                    return;
                }
            }
        }
        System.out.println("Ingen bokning hittades med det angivna förnamnet.");
    }
    case 2 -> {
        System.out.print("Skriv in personnumret på personen vars bokning du vill ta bort: ");
        String personnummer = scan.nextLine();
        for (int i = 0; i < bokningar.length; i++) {
            if (bokningar[i] != null) {
                String[] delar = bokningar[i].split(";");
                String fodelsedatum = delar[0];
                if (fodelsedatum.equals(personnummer)) {
                    System.out.println("Bokning på plats " + (i + 1) + " togs bort: " + bokningar[i]);
                    bokningar[i] = null;
                    return;
                }
            }
        }
        System.out.println("Ingen bokning hittades med det angivna personnumret.");
    }
    case 3 -> {
        System.out.print("Skriv in platsnumret på bokningen du vill ta bort: ");
        int platsnummer = scan.nextInt();
        if (platsTillgänglig(platsnummer)) {
            bokningar[platsnummer - 1] = null;
            System.out.println("Bokningen på plats " + platsnummer + " har tagits bort.");
        } else {
            System.out.println("Det finns ingen bokning på denna plats.");
        }
    }
    default -> System.out.println("Ogiltigt val.");
}
}


static void visaBokningar() {
sorteraBokningar();
System.out.println("Lista över bokningar:");
for (int i = 0; i < bokningar.length; i++) {
if (bokningar[i] != null) {
System.out.println("Plats " + (i+1) + ": " + bokningar[i]);
}
}
}
static void sorteraBokningar() {
    Arrays.sort(bokningar, new Comparator<String>() {
        public int compare(String b1, String b2) {
            if (b1 == null) {
                return 1;
            } else if (b2 == null) {
                return -1;
            } else {
                String[] delar1 = b1.split(";");
                String[] delar2 = b2.split(";");
                String namn1 = delar1[2] + " " + delar1[1];
                String namn2 = delar2[2] + " " + delar2[1];
                return namn1.compareTo(namn2);
            }
        }
    });
}




    /**
 * Räknar ut den totala vinsten för en resa baserat på antalet passagerare av olika typer.
 * @return den totala vinsten
 */
static double räknaVinst() {
int[] antalPassagerare = beräknaAntalPassagerare();
double vinst = antalPassagerare[0] * VUXEN_KOSTNAD + antalPassagerare[1] * KOSTNAD_BARN + antalPassagerare[2] * PENSIONÄR_KOSTNAD;
return vinst;
}

static int[] beräknaAntalPassagerare() {
int antalVuxna = 0;
int antalBarn = 0;
int antalPensionärer = 0;

for (int i = 0; i < bokningar.length; i++) {
    if (platsTillgänglig(i + 1)) {
        String[] delar = bokningar[i].split(";");
        String födelsedatum = delar[0];
        int födelseår = Integer.parseInt(födelsedatum.substring(0, 4));
        int aktuelltÅr = java.time.LocalDate.now().getYear();
        int ålder = aktuelltÅr - födelseår;

        if (ålder < 18) {
            antalBarn++;
        } else if (ålder >= 65) {
            antalPensionärer++;
        } else {
            antalVuxna++;
        }
    }
}

return new int[]{antalVuxna, antalBarn, antalPensionärer};

}

static boolean platsTillgänglig(int platsnummer) {
return bokningar[platsnummer - 1] != null;
}

static String platserFörString(int[] platser) {
String sträng = "";
for (int i = 0; i < platser.length; i++) {
sträng += platser[i];
if (i < platser.length - 1) {
sträng += ", ";
}
}
return sträng;
}
}