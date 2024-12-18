package entite;

public class matiereEL {
	
	
	private int cr_id;
	private String cr_titre;
	private int cr_coef;
	public int getCr_id() {
		return cr_id;
	}
	public void setCr_id(int cr_id) {
		this.cr_id = cr_id;
	}
	public String getCr_titre() {
		return cr_titre;
	}
	public void setCr_titre(String cr_titre) {
		this.cr_titre = cr_titre;
	}
	public int getCr_coef() {
		return cr_coef;
	}
	public void setCr_coef(int cr_coef) {
		this.cr_coef = cr_coef;
	}
	public matiereEL(int cr_id, String cr_titre, int cr_coef) {
		super();
		this.cr_id = cr_id;
		this.cr_titre = cr_titre;
		this.cr_coef = cr_coef;
	}
	public matiereEL() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
