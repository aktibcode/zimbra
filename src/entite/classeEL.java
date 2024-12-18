package entite;

public class classeEL {
	
	private int id_recu;
	private String nom_cls;
	private String nom_prenom;
	private int elv_id;
	private Double Montant_Versé;
	private Double Reste_à_Versé;
	private String Date_Versement;
	private String Année_Scolaire;
	public int getId_recu() {
		return id_recu;
	}
	public void setId_recu(int id_recu) {
		this.id_recu = id_recu;
	}
	public String getNom_cls() {
		return nom_cls;
	}
	public void setNom_cls(String nom_cls) {
		this.nom_cls = nom_cls;
	}
	public String getNom_prenom() {
		return nom_prenom;
	}
	public void setNom_prenom(String nom_prenom) {
		this.nom_prenom = nom_prenom;
	}
	public int getElv_id() {
		return elv_id;
	}
	public void setElv_id(int elv_id) {
		this.elv_id = elv_id;
	}
	public Double getMontant_Versé() {
		return Montant_Versé;
	}
	public void setMontant_Versé(Double montant_Versé) {
		Montant_Versé = montant_Versé;
	}
	public Double getReste_à_Versé() {
		return Reste_à_Versé;
	}
	public void setReste_à_Versé(Double reste_à_Versé) {
		Reste_à_Versé = reste_à_Versé;
	}
	public String getDate_Versement() {
		return Date_Versement;
	}
	public void setDate_Versement(String date_Versement) {
		Date_Versement = date_Versement;
	}
	public String getAnnée_Scolaire() {
		return Année_Scolaire;
	}
	public void setAnnée_Scolaire(String année_Scolaire) {
		Année_Scolaire = année_Scolaire;
	}
	public classeEL(int id_recu, String nom_cls, String nom_prenom, int elv_id,
			Double montant_Versé, Double reste_à_Versé, String date_Versement,
			String année_Scolaire) {
		super();
		this.id_recu = id_recu;
		this.nom_cls = nom_cls;
		this.nom_prenom = nom_prenom;
		this.elv_id = elv_id;
		Montant_Versé = montant_Versé;
		Reste_à_Versé = reste_à_Versé;
		Date_Versement = date_Versement;
		Année_Scolaire = année_Scolaire;
	}
	public classeEL() {
		super();
		// TODO Auto-generated constructor stub
	}
	public classeEL(int int1) {
		this.id_recu = id_recu;
	}
	
	
	public classeEL (String anneScolaire){
		Année_Scolaire = anneScolaire;
		
	}
	
	
	
	
}
