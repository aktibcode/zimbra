package entite;

public class classeEL {
	
	private int id_recu;
	private String nom_cls;
	private String nom_prenom;
	private int elv_id;
	private Double Montant_Vers�;
	private Double Reste_�_Vers�;
	private String Date_Versement;
	private String Ann�e_Scolaire;
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
	public Double getMontant_Vers�() {
		return Montant_Vers�;
	}
	public void setMontant_Vers�(Double montant_Vers�) {
		Montant_Vers� = montant_Vers�;
	}
	public Double getReste_�_Vers�() {
		return Reste_�_Vers�;
	}
	public void setReste_�_Vers�(Double reste_�_Vers�) {
		Reste_�_Vers� = reste_�_Vers�;
	}
	public String getDate_Versement() {
		return Date_Versement;
	}
	public void setDate_Versement(String date_Versement) {
		Date_Versement = date_Versement;
	}
	public String getAnn�e_Scolaire() {
		return Ann�e_Scolaire;
	}
	public void setAnn�e_Scolaire(String ann�e_Scolaire) {
		Ann�e_Scolaire = ann�e_Scolaire;
	}
	public classeEL(int id_recu, String nom_cls, String nom_prenom, int elv_id,
			Double montant_Vers�, Double reste_�_Vers�, String date_Versement,
			String ann�e_Scolaire) {
		super();
		this.id_recu = id_recu;
		this.nom_cls = nom_cls;
		this.nom_prenom = nom_prenom;
		this.elv_id = elv_id;
		Montant_Vers� = montant_Vers�;
		Reste_�_Vers� = reste_�_Vers�;
		Date_Versement = date_Versement;
		Ann�e_Scolaire = ann�e_Scolaire;
	}
	public classeEL() {
		super();
		// TODO Auto-generated constructor stub
	}
	public classeEL(int int1) {
		this.id_recu = id_recu;
	}
	
	
	public classeEL (String anneScolaire){
		Ann�e_Scolaire = anneScolaire;
		
	}
	
	
	
	
}
