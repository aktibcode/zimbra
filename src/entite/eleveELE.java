package entite;

public class eleveELE {
	/*
	 * 
	 * private int ID_ETUDIANT;
	private String NOM;
	private String PRENOM;
	private String DATE_NAIS;
	private String LIEU_NAIS;
	private String Nom_du_PERE;
	private String Nom_de_la_MERE;
	private String QUARTIER;
	private String NOMETPREMON;
	private String cls_nom;
	private String anne_courant;
	public int getID_ETUDIANT() {
		return ID_ETUDIANT;
	}
	public void setID_ETUDIANT(int iD_ETUDIANT) {
		ID_ETUDIANT = iD_ETUDIANT;
	}
	public String getNOM() {
		return NOM;
	}
	public void setNOM(String nOM) {
		NOM = nOM;
	}
	public String getPRENOM() {
		return PRENOM;
	}
	public void setPRENOM(String pRENOM) {
		PRENOM = pRENOM;
	}
	public String getDATE_NAIS() {
		return DATE_NAIS;
	}
	public void setDATE_NAIS(String dATE_NAIS) {
		DATE_NAIS = dATE_NAIS;
	}
	public String getLIEU_NAIS() {
		return LIEU_NAIS;
	}
	public void setLIEU_NAIS(String lIEU_NAIS) {
		LIEU_NAIS = lIEU_NAIS;
	}
	public String getNom_du_PERE() {
		return Nom_du_PERE;
	}
	public void setNom_du_PERE(String nom_du_PERE) {
		Nom_du_PERE = nom_du_PERE;
	}
	public String getNom_de_la_MERE() {
		return Nom_de_la_MERE;
	}
	public void setNom_de_la_MERE(String nom_de_la_MERE) {
		Nom_de_la_MERE = nom_de_la_MERE;
	}
	public String getQUARTIER() {
		return QUARTIER;
	}
	public void setQUARTIER(String qUARTIER) {
		QUARTIER = qUARTIER;
	}
	public String getNOMETPREMON() {
		return NOMETPREMON;
	}
	public void setNOMETPREMON(String nOMETPREMON) {
		NOMETPREMON = nOMETPREMON;
	}
	public String getCls_nom() {
		return cls_nom;
	}
	public void setCls_nom(String cls_nom) {
		this.cls_nom = cls_nom;
	}
	public String getAnne_courant() {
		return anne_courant;
	}
	public void setAnne_courant(String anne_courant) {
		this.anne_courant = anne_courant;
	}
	public eleveELE(int iD_ETUDIANT, String nOM, String pRENOM,
			String dATE_NAIS, String lIEU_NAIS, String nom_du_PERE,
			String nom_de_la_MERE, String qUARTIER, String nOMETPREMON,
			String cls_nom, String anne_courant) {
		super();
		ID_ETUDIANT = iD_ETUDIANT;
		NOM = nOM;
		PRENOM = pRENOM;
		DATE_NAIS = dATE_NAIS;
		LIEU_NAIS = lIEU_NAIS;
		Nom_du_PERE = nom_du_PERE;
		Nom_de_la_MERE = nom_de_la_MERE;
		QUARTIER = qUARTIER;
		NOMETPREMON = nOMETPREMON;
		this.cls_nom = cls_nom;
		this.anne_courant = anne_courant;
	}
	
	public eleveELE() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	 */
	
	
	
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
	public String getNomEtprenom() {
		return nomEtprenom;
	}
	public void setNomEtprenom(String nomEtprenom) {
		this.nomEtprenom = nomEtprenom;
	}
	public String getElv_cls() {
		return elv_cls;
	}
	public void setElv_cls(String elv_cls) {
		this.elv_cls = elv_cls;
	}
	public String getAnne_scolaire() {
		return anne_scolaire;
	}
	public void setAnne_scolaire(String anne_scolaire) {
		this.anne_scolaire = anne_scolaire;
	}
	
	public eleveELE(int elv_id, String elv_nom, String elv_prenom,
			String date_de_naissance, String lieu_de_naissance,
			String nom_du_pere, String nom_de_la_mere, String quartier,
			String nomEtprenom, String elv_cls, String anne_scolaire) {
		super();
		this.elv_id = elv_id;
		this.elv_nom = elv_nom;
		this.elv_prenom = elv_prenom;
		Date_de_naissance = date_de_naissance;
		Lieu_de_naissance = lieu_de_naissance;
		Nom_du_pere = nom_du_pere;
		Nom_de_la_mere = nom_de_la_mere;
		Quartier = quartier;
		this.nomEtprenom = nomEtprenom;
		this.elv_cls = elv_cls;
		this.anne_scolaire = anne_scolaire;
	}
	public eleveELE() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
