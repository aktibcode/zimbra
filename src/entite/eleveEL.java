package entite;

public class eleveEL {
	private int elv_id;
	private String elv_nom;
	private String elv_prenom;
	private String Date_de_naissance;
	private String Lieu_de_naissance;
	private String Nom_du_pere;
	private String Nom_de_la_mere;
	private String Quartier;

	private String nomEtprenom;
	private String elv_cls;
	private String anne_scolaire;
	
	public String getAnnee_scolaire() {
		return anne_scolaire;
	}
	public void setAnnee_scolaire(String annee_scolaire) {
		this.anne_scolaire = annee_scolaire;
	}
	public String getNomEtprenom() {
		return nomEtprenom;
	}
	public void setNomEtprenom(String nomEtprenom) {
		this.nomEtprenom = nomEtprenom;
	}
	
	public int getElv_id() {
		return elv_id;
	}
	public void setElv_id(int elv_id) {
		this.elv_id = elv_id;
	}
	public String getElv_nom() {
		return elv_nom;
	}
	public void setElv_nom(String elv_nom) {
		this.elv_nom = elv_nom;
	}
	public String getElv_prenom() {
		return elv_prenom;
	}
	public void setElv_prenom(String elv_prenom) {
		this.elv_prenom = elv_prenom;
	}
	public String getDate_de_naissance() {
		return Date_de_naissance;
	}
	public void setDate_de_naissance(String date_de_naissance) {
		Date_de_naissance = date_de_naissance;
	}
	public String getLieu_de_naissance() {
		return Lieu_de_naissance;
	}
	public void setLieu_de_naissance(String lieu_de_naissance) {
		Lieu_de_naissance = lieu_de_naissance;
	}
	public String getNom_du_pere() {
		return Nom_du_pere;
	}
	public void setNom_du_pere(String nom_du_pere) {
		Nom_du_pere = nom_du_pere;
	}
	public String getNom_de_la_mere() {
		return Nom_de_la_mere;
	}
	public void setNom_de_la_mere(String nom_de_la_mere) {
		Nom_de_la_mere = nom_de_la_mere;
	}
	public String getQuartier() {
		return Quartier;
	}
	public void setQuartier(String quartier) {
		Quartier = quartier;
	}
	
	public String getElv_cls() {
		return elv_cls;
	}
	public void setElv_cls(String elv_cls) {
		this.elv_cls = elv_cls;
	}
	public eleveEL(int elv_id, String elv_nom, String elv_prenom,String date_de_naissance, String lieu_de_naissance,String nom_du_pere, 
			String nom_de_la_mere, String quartier,	 String nomEtprenom, String elv_cls, String anne_scolaire) {
		
		this.elv_id = elv_id;
		this.elv_nom = elv_nom;
		this.elv_prenom = elv_prenom;
		this.Date_de_naissance = date_de_naissance;
		this.Lieu_de_naissance = lieu_de_naissance;
		this.Nom_du_pere = nom_du_pere;
		this.Nom_de_la_mere = nom_de_la_mere;
		this.Quartier = quartier;
		
		this.nomEtprenom = nomEtprenom;
		this.elv_cls = elv_cls;
		this.anne_scolaire = anne_scolaire;
	}
	public eleveEL() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
