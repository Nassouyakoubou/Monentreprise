package MonEntrprise;
/* **********************************************************************
*  NASSOU YAKOUBOU 15A719FS La classe Employe
* **********************************************************************/
abstract class Employe {
    private final String Matricule;
    private final String Nom;
    private final String Prenom;
    private final int Age;
    private final String Date_prise_service;
    
    
    public Employe (String M,String N,String P,int A,String D )
    {
        this.Matricule=M;
        this.Nom = N;
        this.Prenom = P;
        this.Age = A;
        this.Date_prise_service = D;
    }
    
    public abstract double calculerSalaire();
    
    public String getTitre()
    {
        return "L'employe " ;
    }
    
    public String getNom() {
        return getTitre() + Matricule +" "+ Prenom +" " + Nom;
    }
}

/* **********************************************************************
*  La classe Commercial (regroupe Vendeur et Representant)
* **********************************************************************/
abstract class Commercial extends Employe {
    private final double chiffreAffaire;
    
    public Commercial(String Matricule,String Prenom, String Nom, int Age, String Date,
            double chiffreAffaire) {
        super(Matricule,Prenom, Nom, Age, Date);
        this.chiffreAffaire = chiffreAffaire;
    }
    
    public double getChiffreAffaire()
    {
        return chiffreAffaire;
    }
    
}

/* **********************************************************************
*  La classe Vendeur
* **********************************************************************/
class Vendeur extends Commercial {
    private final static double POURCENTAGE_VENDEUR = 0.2;
    private final static int BONUS_VENDEUR = 100;
    public Vendeur( String Matricule,String Prenom, String Nom, int Age, String Date,
            double chiffreAffaire) {
        super(Matricule,Prenom, Nom, Age, Date, chiffreAffaire);
    }
    
   @Override
    public double calculerSalaire() {
        return (POURCENTAGE_VENDEUR * getChiffreAffaire()) + BONUS_VENDEUR;
    }
    
    @Override
    public String getTitre()
    {
        return "Le vendeur ";
    }
    
}

/* **********************************************************************
*  La classe Representant
* **********************************************************************/
class Representant extends Commercial {
    private final static double POURCENT_REPRESENTANT = 0.2;
    private final static int BONUS_REPRESENTANT = 200;
    public Representant(String Matricule,String Prenom, String Nom, int Age, String Date, double chiffreAffaire) {
        super(Matricule,Prenom, Nom, Age, Date, chiffreAffaire);
    }
    
    @Override
    public double calculerSalaire() {
        return (POURCENT_REPRESENTANT * getChiffreAffaire()) + BONUS_REPRESENTANT;
    }
    
    @Override
    public String getTitre()
    {
        return "Le representant ";
    }
}

/* **********************************************************************
*  La classe Technicien (Production)
* **********************************************************************/
class Technicien extends Employe {
    private final static double FACTEUR_UNITE = 5.0;
    
    
    private final int unites;
    
    public Technicien(String Matricule, String Prenom , String Nom, int Age, String Date, int unites) {
        super(Matricule,Prenom, Nom, Age, Date);
        this.unites = unites;
    }
    
    @Override
    public double calculerSalaire() {
        return FACTEUR_UNITE * unites;
    }
    
    @Override
    public String getTitre()
    {
        return "Mon technicien";
    }
}

/* **********************************************************************
*  La classe Manutentionnaire
* **********************************************************************/
class Manutentionnaire extends Employe {
    private final static double SALAIRE_HORAIRE = 65.0;
    private final int heures;
    
    public Manutentionnaire(String Matricule,String Prenom, String Nom, int Age, String Date,
            int heures) {
        super(Matricule,Prenom, Nom, Age, Date);
        this.heures = heures;
    }
    
    @Override
    public double calculerSalaire() {
        return SALAIRE_HORAIRE * heures;
    }
    
    @Override
    public String getTitre()
    {
        return "Le Manutention. " ;
    }
}

/* **********************************************************************
*  L'interface d'employe risque
* **********************************************************************/
interface Risque {
    int PRIME = 25000;
}

/* **********************************************************************
*  Une premire sous-classe d'employe risque
* **********************************************************************/
class TechnARisque extends Technicien implements Risque {
    
    public TechnARisque( String Matricule,String Prenom, String Nom, int Age, String Date, int unites) {
        super(Matricule,Prenom, Nom, Age, Date, unites);
    }
  
    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}

/* **********************************************************************
*  Une autre sous-classe d'employe risque
* **********************************************************************/
class ManutARisque extends Manutentionnaire implements Risque {
    
    public ManutARisque(String Matricule,String Prenom, String Nom, int Age, String Date, int heures) {
        super( Matricule,Prenom, Nom, Age, Date, heures);
    }
    
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}

/* **********************************************************************
*  La classe Personnel
* **********************************************************************/
class Personnel {
    private final Employe[] staff;
    private int nombreEmploye;
    private final static int MAXEMPLOYE = 200;
    
    public Personnel() {
        staff = new Employe[MAXEMPLOYE];
        nombreEmploye = 0;
    }
    
    public void ajouterEmploye(Employe e) {
        ++nombreEmploye;
        if (nombreEmploye <= MAXEMPLOYE) {
            staff[nombreEmploye - 1] = e;
        } else {
            System.out.println("Pas plus de " + MAXEMPLOYE + " employe");
        }
    }
    
    public double salaireMoyen() {
        double somme = 0.00;
        for (int i = 0; i < nombreEmploye; i++) {
            somme += staff[i].calculerSalaire();
        }
        return somme / nombreEmploye;
    }
    
    public void afficherSalaires() {
        for (int i = 0; i < nombreEmploye; i++) {
            System.out.println(staff[i].getNom() +" gagnera "
                    + staff[i].calculerSalaire() + " francs.");
        }
    }
}

// ======================================================================

class Salaires {
    public static void main(String[] args) {
        Personnel p = new Personnel();
        p.ajouterEmploye(new Vendeur("16BOOOFS", "RIDAI", "VIER", 34, "2012", 30000));
        p.ajouterEmploye(new Representant("16BOO2FS","DADA", "BRUNO", 25, "2015", 20000));
        p.ajouterEmploye(new Technicien("16BO23FS","VAVA", "Boulrµ", 30, "1995", 1000));
        p.ajouterEmploye(new Manutentionnaire("16B45OFS","FILA", "MOIDA", 32, "1999", 45));
        p.ajouterEmploye(new TechnARisque("16B12OFS","JOLIE", "MONIQUE", 27, "2000", 1000));
        p.ajouterEmploye(new ManutARisque("16B123FS","FIA", "RUTH", 29, "2017", 45));
        
        p.afficherSalaires();
        System.out.println("Le salaire moyen dans l'entreprise est de "
                + p.salaireMoyen() + " francs.");
        
    }
    
}