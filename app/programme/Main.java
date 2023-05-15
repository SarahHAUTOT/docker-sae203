/*---------------------------------------------------*/
/*                     IMPORT                        */
/*---------------------------------------------------*/



//Import des classes pour la lecture des fichiers
import java.util.Scanner;
import java.io.FileInputStream;

//Import des classes pour l'ecriture des fichiers
import java.io.PrintWriter;
import java.io.FileOutputStream;

//Import des classes pour decomposer des phrases
import iut.algo.Decomposeur;
import java.util.StringTokenizer; //Utilise pour determiner le nombre de prof

//Import des classes pour trier les tableaux
import java.util.Arrays;

//Import des classes pour manipuler les dates
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; 



/*---------------------------------------------------*/
/*                       CODE                        */
/*---------------------------------------------------*/



public class Main
{
    public static void main(String[] args) 
    {



        /*---------------------------------------------------*/
        /*                      DONNES                       */
        /*---------------------------------------------------*/



        /*---Donnees constante---*/

        //Parametre d'entree
        final boolean TRI_CAT       = true;

        //Fichiers traites
        final String      FICH_INFO   = "../data/ressources.data"; //Fichier info (salle + nbMembre)
        final String      FICH_ELEVES = "../data/promotion.data";  //Fichier des eleves
        final String      FICHE_JURY  = "../data/jury.data";       //Fichier des jury

        /*---Donnees variable---*/

        //Lecture doc
        Scanner     scanner ;

        //Liste des etudiants
        Etudiant[]  liste, tabEleve;
        int         nbPers,numPers;

        //Salle
        String[]    salle;
        int[]       capaciteSalle;
        int         nbSalle;

        //Equipe
        Equipe[]    tabEquipe;
        int 		numPersEqu, numEqu;
        Etudiant[]  equipeEtudiant;
        int         nbEleveReste;
        int         tailleEquipe = 4;

        //Jury 
        Jury[] jury;
        int    nbJury;

        //Emploie du temps
        int    totalMinute, nbEquipePassable;
        int    durePassage, tempsPause;
        
        //Autre
        int         cpt;
        


        /*---------------------------------------------------*/
        /*         PARTIE 1 : CREATION DES GROUPES           */
        /*---------------------------------------------------*/



        try
        {
            //Lecture du nombre de salle
            scanner = new Scanner (new FileInputStream(FICH_INFO)); 
            nbSalle = compteLigneFich(scanner);
            scanner.close();


            //Lecture du nombre de personne par equipe
            scanner       = new Scanner (new FileInputStream(FICH_INFO));
            Decomposeur dec = new Decomposeur ( scanner.nextLine() );
            tailleEquipe   = dec.getInt(0);
            

            //Lecture des salles et ses capacites
            salle         = new String[nbSalle];
            capaciteSalle = new int   [nbSalle];

            for (cpt = 0; scanner.hasNextLine(); cpt++ )
            {
                dec    = new Decomposeur ( scanner.nextLine() );
                salle[cpt]         = dec.getString(0);
                capaciteSalle[cpt] = dec.getInt   (1);
            }
            scanner.close();
            
            //Compter le nombre d'eleves total
            scanner = new Scanner (new FileInputStream(FICH_ELEVES)); 
            scanner.nextLine();
            nbPers = compteLigneFich(scanner)-1;

            tabEleve = new Etudiant [nbPers];


            //On remplie la liste
            scanner = new Scanner (new FileInputStream(FICH_ELEVES)); 
            scanner.nextLine();
            for (cpt = 0; cpt < nbPers; cpt++)
            {
                dec = new Decomposeur ( scanner.nextLine() );
                tabEleve[cpt] = new Etudiant ( dec.getString(0),dec.getString(1),dec.getChar(2),dec.getChar(3) );
            }

            liste = tabEleve.clone();

            if (TRI_CAT)
                Arrays.sort(liste);

            //On remplie les equipes par categorie
            int nbEqu    = 0;
            int deb      = 0;
            int fin      = nbPers-1;

            if (TRI_CAT)
            {
                while ( deb < liste.length)
                {
                    fin    = numFinCat(deb, liste, liste[deb].getCat());
                    nbEqu += (fin-deb+1) / tailleEquipe;
                    deb    = fin+1;
                }
                // if (tailleEquipe %2 == 1 )
                    tabEquipe = new Equipe[nbEqu  ];
                // else
                //     tabEquipe = new Equipe[nbEqu+1];

                deb       = 0;
                while ( deb < liste.length)
                {
                    fin    = numFinCat(deb, liste, liste[deb].getCat());
                    tabEquipe = remplirEquipes(liste, tabEquipe, deb, fin, tailleEquipe, salle,capaciteSalle);
                    deb    = fin+1;
                }
            }
            else
            {
                tabEquipe = new Equipe[deb/fin];
                tabEquipe = remplirEquipes(liste, tabEquipe, deb, fin, tailleEquipe, salle,capaciteSalle);
            }
           

            
            /*---------------------------------------------------*/
            /*      PARTIE 2 : CREATION DES EMPLOIS DU TEMPS     */
            /*---------------------------------------------------*/



            //Decompose et compte le nombre de jurry
            scanner = new Scanner (new FileInputStream(FICHE_JURY));
            dec = new Decomposeur ( scanner.nextLine() );

            durePassage = dec.getInt(0);
            tempsPause  = dec.getInt(1);

            scanner.nextLine();

            nbJury = compteLigneFich(scanner);
            scanner.close();
            
            jury = new Jury[nbJury];

            //Decompose les profs pour les donner dans la classe Jury
            scanner = new Scanner (new FileInputStream(FICHE_JURY));
            scanner.nextLine();

            for (int cpt1 = 0; cpt1 < jury.length; cpt1++)
            {
                //Calcul du nombre de mots
                String          sPhrase   = scanner.nextLine();
                StringTokenizer sT = new StringTokenizer(sPhrase,"\t");
                int nbCol          = sT.countTokens();

                //Decomposition 
                dec = new Decomposeur(sPhrase);
                String[] tabJuge = new String[nbCol-5];

                for (cpt = 0; cpt < nbCol - 5; cpt++)
                   tabJuge[cpt] = dec.getString (5 + cpt);
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(dec.getString(2), formatter);

                //Transformation des heure en decimal
                
                double heureDeb = stringToDouble(dec.getString(3));
                double heureFin = stringToDouble(dec.getString(4));
                
                jury[cpt1] = new Jury(dec.getString(0),dec.getInt(1),date,durePassage/60.0,tempsPause/60.0,heureDeb,
                                      heureFin, tabJuge);
                
                
            }

            numEqu = cpt = 0; 
            while (numEqu < tabEquipe.length && cpt < jury.length)
            {
                if (jury[cpt].getNbEquipe() < jury[cpt].getNbPassageMax())
                {

                    jury[cpt].ajouterEquipe(tabEquipe[numEqu]);                 
                    numEqu ++;
                }
                else
                {
                    cpt++;

                }
            }



			/*---------------------------------------------------*/
            /*         PARTIE 3 : CREATION DES PAGES WEB         */
            /*---------------------------------------------------*/



            /*                            */
            /*  Preparation des tableaux  */
            /*                            */


            //Ajout des groupes des etudiants
            for (numPers = 0; numPers < tabEleve.length; numPers ++)
                for (numEqu = 0; numEqu < tabEquipe.length; numEqu ++)
                    for (int ind = 0; ind < tabEquipe[numEqu].getNbEleve(); ind++)
                        if (tabEleve[numPers].equalsTo(tabEquipe[numEqu].getEtudiant(ind)))
                            tabEleve[numPers].ajouterEquipe(numEqu+1);
            

            //Envoi des tableaux aux programme des generations des pages web
            ListeEquipes.main(tabEquipe);
            ListeEtudiant.main(tabEleve);
            ListePlanning.main(jury);



        }catch (Exception e){ e.printStackTrace(); } 
        //La ligne dessus permet de ne rien faire si un des
        //fichier n'existe pas, pour eviter de nombreuses
        //erreur

    }


    
    /*---------------------------------------------------*/
    /*                   SOUS-PROGRAMME                  */
    /*---------------------------------------------------*/



    /*                                               */
    /* TROUVE L'INDICE DE FIN D'UNE CATEGORIE (TRIE) */
    /*                                               */
    public static int numFinCat(int deb, Etudiant[] tab, char bonneCat)
    {
        int cpt;

        for (cpt = deb; cpt < tab.length-1 && tab[cpt].getCat() == bonneCat; cpt++ ){}
        
        if (cpt == tab.length-1)
            return cpt; //Si on est a la limite, on renvoie cpt
        return cpt-1; //Sinon on renvoie cpt - 1, soit la position d'avant
    }


    
    /*                                               */
    /*   COMPTE LE NOMBRE DE LIGNE DANS UN FICHIER   */
    /*                                               */
    public static int compteLigneFich(Scanner scanner)
    {
        int cpt;
        for(cpt = 0; scanner.hasNextLine(); cpt++) 
        {
            scanner.nextLine();
        }
        scanner.close();
        return cpt+1;
    }


    /*                                               */
    /* Remplies les equipes sur un intervalle donnee */
    /*                                               */
    public static Equipe[] remplirEquipes ( Etudiant[] liste, Equipe[] tabEqu,int deb, int fin, int tailleEqu, String[] salle, int[] cap) 
    {
        int        numEqu;
        int        numPers    = deb; 
        int        nbPers     = fin-deb+1; 
        int        nbPersRest = nbPers % tailleEqu;
        int        indSalle   = (int) (Math.random()*salle.length);
        int        nbGroupe   = nbPers/tailleEqu;

        int indiceTab=0;
        for (int cpt = 0; cpt < tabEqu.length && deb !=0; cpt++)
            if ( tabEqu[cpt] != null )
                indiceTab = cpt+1;

        Etudiant[] etudEqu= new Etudiant[tailleEqu];

        for (numEqu = 0;numEqu < nbGroupe - nbPersRest; numEqu++)
        {
            for(int numPersEqu=0; numPersEqu < tailleEqu; numPersEqu++ )
            {
                etudEqu[numPersEqu] = liste[numPers];
                numPers ++;
            }
  
            while (cap[indSalle] <= 0)
                indSalle = (int) (Math.random()*salle.length);
            
            tabEqu[numEqu + indiceTab ] = new Equipe(numEqu + deb/tailleEqu + 1, etudEqu, salle[indSalle]);

            cap[indSalle]--;
        }

        //Creer le reste des equipes qui comportent les eleves supplementaires
        
        if (nbPersRest < nbGroupe)
        {
            while (numEqu<nbGroupe)
            {
                etudEqu = new Etudiant[tailleEqu+1];

                for(int numPersEqu=0; numPersEqu < etudEqu.length; numPersEqu++ )
                {
                    etudEqu[numPersEqu] = liste[numPers];
                    numPers ++;
                }
    
                while (cap[indSalle] <= 0)
                    indSalle = (int) (Math.random()*salle.length);
                
                tabEqu[numEqu + indiceTab] = new Equipe(numEqu + deb/tailleEqu + 1, etudEqu, salle[indSalle]);

                cap[indSalle]--;
                numEqu++;
            }

        }
        else
        {
            int moyenneEnPlus = nbPersRest/nbGroupe;
            int groupeUnPlus  = nbPersRest-moyenneEnPlus*nbGroupe;

            while (numEqu<nbGroupe-groupeUnPlus)
            {
                etudEqu = new Etudiant[tailleEqu+moyenneEnPlus];

                for(int numPersEqu=0; numPersEqu < etudEqu.length; numPersEqu++ )
                {
                    etudEqu[numPersEqu] = liste[numPers];
                    numPers ++;
                }
    
                while (cap[indSalle] <= 0)
                    indSalle = (int) (Math.random()*salle.length);
                
                tabEqu[numEqu + indiceTab] = new Equipe(numEqu + deb/tailleEqu + 1, etudEqu, salle[indSalle]);

                cap[indSalle]--;
                numEqu++;
            }

            while (numEqu<nbGroupe)
            {
                etudEqu = new Etudiant[tailleEqu+moyenneEnPlus+1];

                for(int numPersEqu=0; numPersEqu < etudEqu.length; numPersEqu++ )
                {
                    etudEqu[numPersEqu] = liste[numPers];
                    numPers ++;
                }
    
                while (cap[indSalle] <= 0)
                    indSalle = (int) (Math.random()*salle.length);
                
                tabEqu[numEqu + indiceTab] = new Equipe(numEqu + deb/tailleEqu + 1, etudEqu, salle[indSalle]);

                cap[indSalle]--;
                numEqu++;
            }

        }

        return tabEqu;
    }

    /*                                   */
    /* Transformer les horaire en double */
    /*                                   */

    public static double stringToDouble(String sPhrase)
    {
        Decomposeur dec    = new Decomposeur (sPhrase);
        String      heure  = sPhrase.charAt(0)+ "" + sPhrase.charAt(1);
        String      minute = sPhrase.charAt(3)+ "" + sPhrase.charAt(4);

        return Outils.HeureMinute2Decimal(Integer.parseInt(heure),
                                          Integer.parseInt(minute));
    }


}