//importations pour ecrire dans un fichier
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class ListeEtudiant
{
    public static void main(Etudiant[] tabEtu)
    {
		/*Nom des fichiers*/
		final String      PAGE_LISTE_ETUDIANTS  = "../Web/listeEtudiant.html";       //Fichier html
		final String      FICHIER_CSS           = "./style/style.css";       //Fichier css

		/*variables*/
		int cpt;
		int nbPers;
		String couleur;


        try
        {
			//Compter le nombre d'eleves total
			nbPers = tabEtu.length;

            PrintWriter pw = new PrintWriter( new FileOutputStream( PAGE_LISTE_ETUDIANTS ) );

            
			pw.println ( "<!DOCTYPE html>" );
            pw.println ( "<html>" );
            pw.println ( "  <head>" );
			pw.println ( "		<meta charset=\"utf-8\">" );
			pw.println ( "		<meta name=\"Author\" lang=\"fr\" content=\"Groupe11\"> " );
    		pw.println ( "		<meta name=\"description\" content=\"Liste des etudiants\"> " );
			pw.println ( "		<link rel=\"stylesheet\" href=\"" + FICHIER_CSS + "\" media=\"all\" type=\"text/css\"> ");
            pw.println ( "      <title>" );
            pw.println ( "          Liste des Etudiants" );
            pw.println ( "      </title>" );
            pw.println ( "  </head>" );
            pw.println ( "  <body>" );
            pw.println ( "      <header>" );
            pw.println ( "          <h1>Liste des etudiants</h1>");
            pw.println ( "      </header>" );
			pw.println ( "      <article id=\"etudiants\">" );
			pw.println ( "			<div class=\"scroller\">" );
			pw.println ( "      		<table>" );
			pw.println ( "      			<thead>" );
			pw.println ( "      				<tr>" );
			pw.println ( "      					<th>Nom</th>" );
			pw.println ( "      					<th>Prenom</th>" );
			pw.println ( "      					<th>Groupe</th>" );
			pw.println ( "      					<th>Num equipe</th>" );
			pw.println ( "      				</tr>" );
			pw.println ( "      			</thead>" );
			pw.println ( "      			<tbody>" );

            for (cpt = 0; cpt < nbPers; cpt++)
			{
				/*Coloration alternee des cases du tableau*/
				if( cpt%2 == 1 )
				{
					couleur = "gris";
				}
				else
				{
					couleur = "transparent";
				}

				pw.println ( "      			<tr class=" + couleur + ">" );
                pw.println ( "          			<td>" + tabEtu[cpt].getNom() + "</td>");
                pw.println ( "          			<td>" + tabEtu[cpt].getPrenom() + "</td>");
                pw.println ( "          			<td>" + tabEtu[cpt].getClasse() + "</td>");
				pw.println ( "          			<td class=\"droite\">" + tabEtu[cpt].getNumEquipe() + "</td>");
				pw.println ( "      			</tr>" );
            }
			
			pw.println ( "      			</tbody>" );
			pw.println ( "      			<tfoot>" );
			pw.println ( "      				<tr>" );
			pw.println ( "      					<th>Nom</th>" );
			pw.println ( "      					<th>Prenom</th>" );
			pw.println ( "      					<th>Groupe</th>" );
			pw.println ( "      					<th>Num equipe</th>" );
			pw.println ( "      				</tr>" );
			pw.println ( "      			</tfoot>" );
			pw.println ( "				</table>" );
			pw.println ( "			</div>" );
			pw.println ( "		</article>" );
            pw.println ( "	</body>" );
            pw.println ( "</html>" );

            pw.close();
        }
        catch (Exception e){ e.printStackTrace(); }
    }

}
