package entite;

public class matParClas {
	private int idMClsEns;
	private String crTitre;
	private String clsNom;
	private String  anne_scol;
	
	public int getIdMClsEns() {
		return idMClsEns;
	}
	public void setIdMClsEns(int idMClsEns) {
		this.idMClsEns = idMClsEns;
	}
	public String getCrTitre() {
		return crTitre;
	}
	public void setCrTitre(String crTitre) {
		this.crTitre = crTitre;
	}
	public String getClsNom() {
		return clsNom;
	}
	public void setClsNom(String clsNom) {
		this.clsNom = clsNom;
	}
	public String getAnne_scol() {
		return anne_scol;
	}
	public void setAnne_scol(String anne_scol) {
		this.anne_scol = anne_scol;
	}
	public matParClas(int idMClsEns, String crTitre, String clsNom,
			String anne_scol) {
		super();
		this.idMClsEns = idMClsEns;
		this.crTitre = crTitre;
		this.clsNom = clsNom;
		this.anne_scol = anne_scol;
	}
	public matParClas() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
