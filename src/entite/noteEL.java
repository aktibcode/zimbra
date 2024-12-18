package entite;

public class noteEL {
	
	private int note_id;
	private String Semestre;
	private String nom_et_prenom;
	private String matiere;
	private Double note1;
	private Double note2;
	private Double note_Examen;
	private Double moy_cls;
	private int id_elv;
	private String classe;
	private int coef;
	
	public int getCoef() {
		return coef;
	}


	public void setCoef(int coef) {
		this.coef = coef;
	}


	public noteEL(){ }
	
	
	public noteEL( int note_id, String semestre,String classe,String nom_et_prenom , String matiere, int coef, Double note1, Double note2, Double note_Examen,Double moy_cls, int id_elv) {
		
		this.note_id = note_id;
		this.Semestre = semestre;
		this.classe = classe;
		this.nom_et_prenom = nom_et_prenom;
		this.matiere = matiere;
		this.coef = coef;
		this.note1 = note1;
		this.note2 = note2;
		this.note_Examen = note_Examen;
		this.moy_cls = moy_cls;
		this.id_elv = id_elv;
	}


	public String getClasse() {
		return classe;
	}


	public void setClasse(String classe) {
		this.classe = classe;
	}


	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
	public String getSemestre() {
		return Semestre;
	}
	public void setSemestre(String semestre) {
		Semestre = semestre;
	}
	public String getNom_et_prenom() {
		return nom_et_prenom;
	}
	public void setNom_et_prenom(String nom_et_prenom) {
		this.nom_et_prenom = nom_et_prenom;
	}
	public String getMatiere() {
		return matiere;
	}
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	public Double getNote1() {
		return note1;
	}
	public void setNote1(Double note1) {
		this.note1 = note1;
	}
	public Double getNote2() {
		return note2;
	}
	public void setNote2(Double note2) {
		this.note2 = note2;
	}
	public Double getNote_Examen() {
		return note_Examen;
	}
	public void setNote_Examen(Double note_Examen) {
		this.note_Examen = note_Examen;
	}
	public Double getMoy_cls() {
		return moy_cls;
	}
	public void setMoy_cls(Double moy_cls) {
		this.moy_cls = moy_cls;
	}
	public int getId_elv() {
		return id_elv;
	}
	public void setId_elv(int id_elv) {
		this.id_elv = id_elv;
	}
	
	

}
