public class Etudiant implements Comparable<Etudiant>
{
    /*ATTRIBUT*/
    private String nom;
    private String prenom;
    private char   classe;
    private char   cat;
    private int    equipe;

    /* CONSTRUCTEUR */
    public Etudiant(String nom, String prenom, char classe, char cat)
    {
        this.nom    = nom;
        this.prenom = prenom;
        this.classe = classe;
		this.cat    = cat;
        this.equipe = -1;
    }
    
    /* ACCESSEUR */
    public String getNom()       { return this.nom;    }
    
    public String getPrenom()    { return this.prenom; }

    public char   getClasse()    { return this.classe; }
    
    public char   getCat()       { return this.cat;    }
    
    public int    getNumEquipe() { return this.equipe; }
    
    /* MODIFICATEUR */
    public void ajouterEquipe( int equipe)
    {
        this.equipe = equipe;
    }

    /* METHODE */
    public String toString()  
    { 
        return String.format("%-14s",this.nom) + String.format("%-14s",this.prenom) + 
               String.format("%-3s",this.classe) ;
    }
 
    public int compareTo (Etudiant autre) 
    {
        int res = 0;
        try{
            res = ( ""+this.cat).compareTo( ""+autre.cat);
        }
        catch(Exception e) {
            System.out.println("test");
        }

        return res;
    }

    public boolean equalsTo (Etudiant autre) 
    {
        return autre.nom == this.nom && autre.prenom == this.prenom && autre.classe == this.classe && autre.cat == this.cat;
    }
}