import java.time.LocalDate;

public class Jury
{
    /*ATTRIBUT*/
    private String      id;
    private int         salle;
    private LocalDate   date;
    private double      heureDeb;
    private double      heureFin;
    private String[]    tabProf;
    private Equipe[]    tabEquipe;
    private double      tempsOral;
    private double      tempsPause;
    private int         nbEquipe;


    /*CONSTRUCTEUR*/
    public Jury(String id, int salle, LocalDate date, double tempsOral, double tempsPause,
                double heureDeb, double heureFin, String[] tabProf)
    {
        this.id             = id;
        this.salle          = salle;
        this.date           = date;
        this.tempsOral      = tempsOral;
        this.tempsPause     = tempsPause;
        this.heureDeb       = heureDeb;
        this.heureFin       = heureFin;
        this.tabProf        = tabProf;

        double taille = ((this.heureFin - this.heureDeb) / (this.tempsPause + this.tempsOral) - tempsPause);
        
        this.tabEquipe      = new Equipe[Outils.arrondir(taille)];
        this.nbEquipe       = 0;
    }


    /* ACCESSEUR */
    public int getNbPassageMax ()   { return this.tabEquipe.length;            }

    public String getDate ()        { return Outils.AfficherLaDate(this.date); }

    public String[] getListeProf()       
    { 
        return this.tabProf.clone();             
    }

    public String getHeureDebPassage(int indice)
    {
        double heure = this.heureDeb + (this.tempsOral + this.tempsPause) * indice;
        return Outils.Decimal2HeureMinute(heure);
    }

    public String getHeureFinPassage(int indice)
    {
        return Outils.Decimal2HeureMinute(this.heureDeb + (this.tempsOral + this.tempsPause) * indice + tempsOral);
    }

    public int getNumGroupe(int indice)
    {
        return this.tabEquipe[indice].getNumero();
    }

    public int getSalle()
    {
        return this.salle;
    }

    public String getID ()          { return this.id;                          }

    public int getNbEquipe()        { return this.nbEquipe;                    }
    

    /* MODIFICATEUR */
    public boolean ajouterEquipe( Equipe equipe )
    {

        if( (this.heureDeb + this.tempsOral + (this.tempsOral + this.tempsPause) * this.nbEquipe) <= this.heureFin )
        {
            this.tabEquipe[ this.nbEquipe ] = equipe;
            this.nbEquipe++;

            return true;
        }

        return false;
    }

    public String toString()
    {
        int cpt;
        String s;
        double heure;

        heure = this.heureDeb;
        
        s  = "---------------------------------" + '\n';
        s += Outils.AfficherLaDate(date) + '\n'; 
        s += "---------------------------------\n" + this.id + '\n';

        for(cpt=0; cpt < this.tabProf.length;cpt ++ )
        {
            s += tabProf[cpt] + '\n';
        }

        s += '\n';

        for(cpt=0; cpt < this.nbEquipe ;cpt ++ )
        {
            s += Outils.Decimal2HeureMinute(heure) + " a " + Outils.Decimal2HeureMinute(heure + tempsOral) +
                " Equipe " + String.format("%2d", this.tabEquipe[cpt].getNumero()) + "  Salle " + this.salle + '\n';
                
                heure += tempsOral + tempsPause;
        }
        
        return s;
    }

    public boolean dateEquals(Jury autre)
    {
        return Outils.AfficherLaDate(this.date).equals(Outils.AfficherLaDate(autre.date));
    }
}