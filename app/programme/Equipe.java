import java.util.Arrays;

public class Equipe
{
    /*ATTRIBUT*/
    private int         numero;
    private Etudiant[]  tabEtudiant;
    private String      salle;
    
    /*CONSTRUCTEUR*/
    public Equipe(int numero, Etudiant[] tabEtudiant, String salle)
    {
        this.numero      = numero;

        if (tabEtudiant.length != 0)
            Arrays.sort(tabEtudiant);
        this.tabEtudiant = tabEtudiant.clone();

        this.salle       = salle;
    }

    /* ACCESSEUR */
    public int getNumero()
    {
        return this.numero;
    }

    public Etudiant getEtudiant( int indice )
    {
        return this.tabEtudiant[ indice ];
    }

    public String getSalle()
    {
        return this.salle;
    }

    public int getNbEleve()
    {
        return this.tabEtudiant.length;
    }

    /* MODIFICATEUR */
    public void setNumero( int numero )
    {
        this.numero = numero;
    }

    public void setTabEtudiant( Etudiant[] tabEtudiant )
    {
        this.tabEtudiant = tabEtudiant;
    }

    public void setSalle( String salle)
    {
        this.salle = salle;
    }
     
    /* METHODE */
    public String toString()
    {
        int    cpt;
        String s;
        
        s = "Equipe " + this.numero + " " + this.salle + "\n";

        for(cpt=0; cpt < this.tabEtudiant.length;cpt ++ )
        {
            s = s + tabEtudiant[cpt].toString() + '\n';
        }
        
        return s;
    }

}