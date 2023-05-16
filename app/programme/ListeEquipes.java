/* IMPORTATIONS */
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import iut.algo.*;
import java.util.Scanner;
import iut.algo.Decomposeur;
import java.awt.Desktop;
import java.io.*;

public class ListeEquipes
{
    public static void main(Equipe[] tabEqu)
    {
		Scanner scanner;
		int nbPers;
		Decomposeur dec;
        String coul = "";
        String cote = "";


		//Compter le nombre d'eleves total
        try
        {

            PrintWriter pw = new PrintWriter( new FileOutputStream("../web/listeEquipes.html") );

            pw.println ( "<!DOCTYPE html>" );
            pw.println ( "<html lang=\"fr\">" );
            pw.println ( "  <head>" );            
            pw.println ( "      <meta charset=\"UTF-8\">");
            pw.println ( "      <link href=\"./style/styleGroupe.css\" rel=\"stylesheet\" media=\"all\" type=\"text/css\">");
            pw.println ( "      <title>" );
            pw.println ( "          Liste des Equipes" );
            pw.println ( "      </title>" );
            pw.println ( "  </head>" );
            pw.println ( "  <body>" );
            pw.println ( "      <header>");
            pw.println ( "          <h1>");
            pw.println ( "              Liste des Equipes");
            pw.println ( "          </h1>");
            pw.println ( "      </header>");

			
            for (int cpt = 0; cpt < tabEqu.length; cpt++)
            {
                if ( cpt<(tabEqu.length/3))
                {
                    coul = "bord1";
                }
                else
                {
                    if ( cpt >=(tabEqu.length/3) && cpt <(2*tabEqu.length/3))
                    {
                        coul = "bord2";
                    }
                    else
                    {
                        coul = "bord3";
                    }
                }
                
                if ( tabEqu[cpt].getEtudiant(0).getCat() == 'A')
                {
                    cote = "un";
                }
                else
                {
                    if ( tabEqu[cpt].getEtudiant(0).getCat() == 'B')
                    {
                        cote = "de";
                    }
                    else
                    {
                        if ( tabEqu[cpt].getEtudiant(0).getCat() == 'C')
                        {
                            cote = "tr";
                        }
                        else
                        {
                            cote = "qu";
                            coul = "bord";
                        }
                    }
                }

                if (cpt==0 || cpt==(tabEqu.length/3) || cpt==(2*tabEqu.length/3))
                {
                    pw.println ( "          <div class=\"" + cote + "\">"); 
                }

                pw.println ( "              <table>");
                pw.println ( "                  <tbody>");
                pw.println ( "                      <tr>");
                pw.println ( "                          <td rowspan=\"" + tabEqu[cpt].getNbEleve() + "\" class=\"" + coul + "\"> " + tabEqu[cpt].getNumero() + "</td>");
                
                for (int cpt1=0; cpt1<tabEqu[cpt].getNbEleve(); cpt1++)
                {
                    pw.println ( "                          <td>" + tabEqu[cpt].getEtudiant(cpt1).getNom() + "</td>");
                    pw.println ( "                          <td>" + tabEqu[cpt].getEtudiant(cpt1).getPrenom() + "</td>");
                    pw.println ( "                          <td>" + tabEqu[cpt].getEtudiant(cpt1).getClasse() + "</td>");
                    
                    if (cpt1==0)
                    {
                        pw.println ( "                          <td rowspan=\"" + tabEqu[cpt].getNbEleve() + "\" class=\"" + coul + "\">" + tabEqu[cpt].getSalle() + "</td>");
                    }
                    pw.println ( "                      </tr>");
                    if (cpt1!=(tabEqu[cpt].getNbEleve()-1))
                    {
                        pw.println ( "                      <tr>" );
                    }
                }
                pw.println ( "                  </tbody>");
                pw.println ( "              </table>");
                
                if ( cpt==(tabEqu.length/3-1) || cpt==(2*tabEqu.length/3-1)|| cpt==(tabEqu.length-1))
                {
                    pw.println ( "          </div>");
                }

            }

            pw.println ( "  </body>" );
            pw.println ( "      </html>" );

            pw.close();
        }
        catch (Exception e){ e.printStackTrace(); }

        ListeEquipes.ouvrirAccueil();
    }

    public static void ouvrirAccueil() 
    {
        File file = new File("../Web/main.html");
        
        //Verifier si le systeme prend en charge la classe Desktop ou non
        if(!Desktop.isDesktopSupported())
        {
            System.out.println("Desktop n'est pas prise en charge");
            return;
        }
        
        Desktop d = Desktop.getDesktop();
        if(file.exists()) 
            try 
            {
                d.open(file);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
    } 

}