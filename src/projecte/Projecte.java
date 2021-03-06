/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author alumne
 */
public class Projecte {
    
    //tectem espai maxim de espai que tenim en el array
    private static final int MAX_DJS = 4000;
    private static Dj[] array = new Dj[MAX_DJS];
    private static int valor;
    //generem un fitxer per tal de volcar a aquest els elements creats en el array    
    private static File fitxer = new File("dj.db");

    /**
     *
     * @return
     */
    public static Dj[] getArray() {
        return array;
    }

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {

        // TODO code application logic here
        inicialitzarVariables();
        do {
            demanarDj();
            tractarDj();
        } while (!opcioFinal());

    }

    /**
     * Inicialitzem dades i altres parametres
     */
    public static int inicialitzarVariables() {
        Dj p = null; //apuntar dj segons la casella
        int i = 0;
        
        //visulitzem si el fitxer existeix i tractem cada un dels errors que ens poden sorguir
        if (fitxer.exists()) {

            boolean finalitza = false;

            ObjectInputStream lleguir = null;
            
            //tractem en cas de no trenir mes llocs per omplir dintre el array
            //i sino es aixi els volquem en un fitxer 
            try {

                lleguir = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fitxer)));

                while (true) {
                    array[i] = (Dj) lleguir.readObject();
                    i++;
                }
                //en cas de no tenir espai mostrem el seguent 
            } catch (ArrayIndexOutOfBoundsException ex) {

                System.err.println("Atenció, no caben mes dj dintre la base de dades. Si continues pots perdre dades. Vols continuar?(S/N):");
                Scanner ent = new Scanner(System.in);
                char sn;
                do {
                    sn = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
                } while (sn != 'S' && sn != 'N');
                if (sn == 'N') {
                    finalitza = true;
                }
            //en cas de areixe cualsevol altre error 
            } catch (IOException ex) {
                
            } catch (ClassNotFoundException ex) {
            //finatlitzem aquest     
            } finally {
                try {
                    lleguir.close();
                } catch (IOException ex) {
                }

                if (finalitza) {
                    System.exit(0);
                }
            }
        }
        //mpstrem cada posicio 
        int resultat=i;
        for (; i < array.length; i++) {
            array[i] = new Dj();
            array[i].setOmplit(false);
        }
        return resultat;
    }

    /**
     * Mostrem menu per saber la opcio que volem escollir
     */
    public static void demanarDj() {

        Scanner entText = new Scanner(System.in);
        Scanner entNum = new Scanner(System.in);

        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        System.out.println("---------DJ DE MUSICA ELECRTRONICA--------");
        System.out.println("1 ---> SORTIR ");
        System.out.println("2 ---> INTRODUEIX DJ");
        System.out.println("3 ---> BORRAR DJ");
        System.out.println("4 ---> MODIFICAR DJ");
        System.out.println("5 ---> LLISTA DJ");
        System.out.println("6 ---> RECUPERA DJ");
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        do {
            System.out.println("\n");
            System.out.print("Introdueix una opció : ");
            try {
                valor = entNum.nextInt();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Opció incorrecta!!");
                entNum.next();
                continue;
            }
        } while (true);
    }

    /**
     * Metode principal el cual ens motra cada una de les opcions que tenim i segons la seleccionada
     * fem una cosa o una altra
     */
    public static void tractarDj() {
        
        //atraves del el switch i el valor donat mostrem la opcio eleguida
        switch (valor) {
            case 1:
                sortir();
                break;
            case 2:
                introduirDj();
                break;
            case 3:
                borrarDj();
                break;
            case 4:
                modificarDj();
                break;
            case 5:
                llistarDj();
                break;
            case 6:
                recuperarDJ();
            default:
                System.out.println("Opcio incorrecta");
        }
    }

    /**
     * Sortim si dintre ens escolleixen la primera opcio
     *
     * @return
     */
    //opcio per sortir de el menu principal
    public static boolean opcioFinal() {
        return valor == 1;
    }

    /**
     * Introduir valors per tal de carreguar un nou dj 
     */
    public static void introduirDj() {
        Scanner entText = new Scanner(System.in);
        Scanner entNum = new Scanner(System.in);
        int i;
        boolean veritat;
        char eshome = ' ';
        Dj p = null;
        
        //recorrem el array miran si esta omplit o no i ens situem en la primera casella buida
        for (i = 0; i < array.length && array[i].isOmplit(); i++);

        System.out.println("\n");
        
        //mirem si tenim array ple o no
        if (i != array.length) {

            p = array[i]; // aixo es igual que array[i]
            
            //mortrem cada una de les caselles i el que es vol introduir
            System.out.print("Introdueix el nom: ");
            p.setNom(entText.skip("[\r\n]*").nextLine());

            System.out.print("Introdueix el lloc de on es: ");
            p.setLloc(entText.skip("[\r\n]*").nextLine());

            do {
                System.out.print("Introdueix l'any de naixement: ");
                try {
                    veritat = false;
                    p.setNaixement(entNum.nextInt());

                } catch (InputMismatchException e) {
                    System.out.println("Torna-ho provar.,.");
                    veritat = true;
                    entNum.next();
                }
            } while (veritat);

            do {
                System.out.print("Introdueix el sexe: (Masculí o Femení): ");
                eshome = entText.nextLine().toUpperCase().charAt(0);
            } while (eshome != 'M' && eshome != 'F');
            p.setHome((eshome == 'M'));
            do {
                System.out.print("Introdueix quant a facturat durant l'any:");
                try {
                    veritat = false;
                    p.setDiners(entNum.nextDouble());
                } catch (InputMismatchException e) {
                    System.out.println("Torna-ho a provar");
                    veritat = true;
                    entNum.next();
                }
            } while (veritat);
            //una vegada intruida mostrem la dada introduida i el fem visible
            System.out.println("\n");
            System.out.println("Dades introduides ...");
            System.out.println("\n");
            p.setOmplit(true);
        } else {
            System.out.println("Dades introduides... ");
            System.out.println("\n");
        }
    }

    /**
     * Metode el qual borrarem un dj pasat anteriorment
     */
    public static void borrarDj() {

        Dj p = null;
        Scanner entText = new Scanner(System.in);
        Scanner entNum = new Scanner(System.in);
        char sn = 'n';
        int i;
        
        //passem array de dj
        for (i = 0; i < array.length; i++) {
            p = array[i];
            
            //si el tenim omplit mostrem cada un del dj i si volem que aquest sigui borrat o no
            if (p.isOmplit()) {
                System.out.println(p);
                do {
                    System.out.println("Vols borrar el dj? (s/n)");
                    sn = entText.skip("[\r\n]*").nextLine().toLowerCase().charAt(0);
                } while (sn != 's' && sn != 'n');
            }
            if (sn == 's') {
                break;
            }
        }
        System.out.println("\n");
        //en cas de ser un si motrem el seguent missatge i canviem la variable omplit daquesta a false,per apareixe borrat
        if (sn == 's') {
            p.setOmplit(false);
            System.out.println("Dj borrat correctament");
        } else {
            System.out.println("No hem borrat res");
        }
    }

    /**
     * Modifiquem si volem algun dels dj introduits previament per l'usuari
     */
    public static void modificarDj() {
        Scanner entText = new Scanner(System.in);
        Scanner entNum = new Scanner(System.in);
        char sn = 'n';
        int cont = 1, i;
        char eshome = ' ';
        boolean veritat;
        
        //Recorrem el arrau establint si volem modificar les dades intoduides o no
        for (i = 0; i < array.length && sn != 'S' && sn != 'F'; i++) {
            if (array[i].isOmplit()) {

                System.out.format("\nDj %d:\n", cont++);
                System.out.println(array[i].toString());
                
                //mostrem el dj si volem modificarlo o no
                do {
                    System.out.print("Vols modificar el dj? (S/N) ");
                    sn = entText.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
                    } while (sn != 'S' && sn != 'N');
                
                //en cas que sigui un si motrem les seves dades una per una i otorgem 
                //la opcio de ser modificat o no per cada una de les seves opcions
                switch (sn) {
                    case 'S':
                        System.out.println("\nNom: " + array[i].getNom());
                        do {
                            System.out.println("\nVols modificar el nom?(S/N):");
                            sn = entText.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
                        } while (sn != 'S' && sn != 'N');
                        switch (sn) {
                            //en cas que ens vulguin canviar modiiquem aquest aquest
                            case 'S':
                                System.out.println("Quin nom posem? ");
                                array[i].setNom(entText.skip("[\r\n]*").nextLine());
                                break;
                            //en cas que no sigui aixi no fem res    
                            case 'N':
                                System.out.println("No canviem res... ");
                                break;
                        }
                        System.out.println("\nLloc: " + array[i].getLloc());
                        do {
                            System.out.println("\nVols modificar el lloc?(S/N):");
                            sn = entText.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
                        } while (sn != 'S' && sn != 'N');
                       
                        switch (sn) {
                            case 'S':
                                System.out.println("Quin lloc posem?");
                                array[i].setLloc(entText.nextLine());
                                break;
                            case 'N':
                                System.out.print("No canviem res...");
                                break;
                        }
                        System.out.println("\nNaixement: " + array[i].getNaixement());
                        System.out.println("\nVols canviar el any de naixement?(S/N) ");
                        sn = entNum.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
                        switch (sn) {
                            case 'S':
                                do {
                                System.out.println("Quin  any de naixement  posem? ");
                                    try {
                                        veritat = false;
                                        array[i].setNaixement(entNum.nextInt());
                                    } catch (InputMismatchException e) {
                                        System.out.println("Torna-ho provar...");
                                        veritat = true;
                                        entNum.next();
                                    }
                                } while (veritat);
                                break;
                            case 'N':
                                System.out.print("no canviem res...");
                                break;
                        }
                        if (array[i].isHome()) {
                            System.out.println("\nÉs masculi");
                        } else {
                            System.out.println("\nÉs femeni");
                        }
                        do {
                            System.out.print("\nVols canviar el sexe?(S/N)");
                            sn = entText.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
                        } while (sn != 'S' && sn != 'N');
                        
                        switch (sn) {
                            case 'S':
                                char esHome;
                                do {
                                    System.out.println("Quin  sexe posem : Masculí o Femení");
                                    esHome = entText.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
                                } while (esHome != 'M' && esHome != 'F');
                                array[i].setHome(esHome == 'H');
                                System.out.print("Nou gènere: ");
                                if (array[i].isHome()) {
                                    System.out.println("masculi");
                                } else {
                                    System.out.println("femeni");
                                }
                                break;
                            case 'N':
                                System.out.println("No canviem res...");
                                
                                break;
                        }
                        System.out.println("\n Diners: " + array[i].getDiners());
                        
                        System.out.print("\nVols canviar el diners facturats?(S/N) ");
                        sn = entText.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);
                        
                        switch (sn) {
                            case 'S':
                                do {
                                System.out.print("Quants diners posem? ");
                                    try {
                                        veritat = false;
                                        array[i].setDiners(entNum.nextDouble());
                                    } catch (InputMismatchException e) {
                                        System.out.println("Torna-ho provar.,.");
                                        veritat = true;
                                        entNum.next();
                                    }
                                } while (veritat);
                                
                                break;
                            case 'N':
                                System.out.println("No canviem res...");
                                break;
                        }
                        break;
                    //en cas d eno voler modificar res no fem res
                    case 'N':
                        System.out.println("No canviem res...");
                        break;
                    //en cas de no eleguir una opcio establerta mostrem el seguent missatge    
                    default:
                        System.out.println("Opcio incorrecta....");
                }
            //en cas de seleccionar intro sense eleguir establir cap dada motrem el seguent    
            } else {
                System.out.println("No hi ha s'han introduit dades.....");
                break;
            }

        }
    }

    /**
     * Metode el qual ens mostra cada un de els dj introduits un per un
     */
    public static void llistarDj() {
        Scanner entText = new Scanner(System.in);
        Scanner entNum = new Scanner(System.in);
        char sn = 'S';
        int i;
        boolean algun = false;
        
        //recorrem el array mostran per cada un dels dj si volem seguir mostran llista de dj establerta
        for (i = 0; i < array.length; i++) {
            Dj p = array[i]; //carreguem array
            
            //mirem si tenim elements dintre el array
            if (p.isOmplit()) {
                algun = true;
                System.out.println(p);
                do {
                    System.out.println("\nVols vore més Dj(S/N)?:");
                    sn = entNum.skip("[\r\n]*").nextLine().toUpperCase().charAt(0); //usem toUpperCase() que traduix el text introduït per l'usuari a majúscules
                    //per tant només haurem de tractar les lletres majúscules
                } while (sn != 'S' && sn != 'N');
            }
            if (sn == 'N') {
                break;
            }
        }
        //En cas de no tenir elements en l'array mostrem el seguent missatge
        if (!algun) {
            System.out.println("\nNo hi ha dj per mostrar, si vols, primer crea'n.");
        }
    }

    /**
     *Metode el qual ens permet recuperar un dj una vegada borrat anteriorment
     */
    public static void recuperarDJ() {
        
        Scanner ent = new Scanner(System.in);
        //Primer recorrem l'array buscant caselles buides i preguntant quina volem recuperar
        char sn = 'N';
        int cont = 0, i;
        //Recorrem tot el array motran cada pilot borrat i indicant si aquest es vol recuperar o no
        for (i = 0; i < array.length && sn != 'S' && sn != 'F'; i++) {
            if (!array[i].isOmplit()) {
                System.out.format("\nDj %d:\n", ++cont);
                System.out.println(array[i].toString());
                //Mostrem el si es vol recuperar el dj o no
                do {
                    System.out.println("\nVols recuperar el dj(S/N) o finalitzar la cerca (F)?:");
                    sn = ent.skip("[\r\n]*").nextLine().toUpperCase().charAt(0);

                } while (sn != 'S' && sn != 'N' && sn != 'F');
            }
            if (sn == 'S') {
                break;
            }
        }
        
        //si ens selecciona la opcio si, mostrem el seguent missatge i tornem a mostrar
        if (sn == 'S') {
            array[i].setOmplit(true);
            System.out.println("Dj recuperat correctament.");
        } else if (cont == 0) {
            System.out.println("No hi ha Dj per recuperat.");
        } else {
            System.out.println("Dj no recuperat.");
        }

    }

    /**
     * Opcio final en la qual mostre a l'usuari que sortim del programa
     */
    public static void sortir() {

        ObjectOutputStream escriptura = null;
        //comprotrolem que en cas de escollir opcio sortir ens sorgueix cualsevol error
        try {
            escriptura = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fitxer)));

            for (int i = 0; i < array.length; i++) {

                if (array[i].isOmplit()) {
                    escriptura.writeObject(array[i]);
                }
            }
        } catch (IOException ex) {
            System.err.println("Dades introduides incorrectament!!");
        } finally {
            try {
                escriptura.close();
            } catch (IOException ex) {
                System.err.println("ERROR TANCANT EL FITXER!!");
            }

        }
        System.out.println("Sortint...");
        System.exit(0);
    }
}
