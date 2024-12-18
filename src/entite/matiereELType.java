package entite;

public class matiereELType {
	
	
	private int id_ty;
	private String titre;
	
	public int getId_ty() {
		return id_ty;
	}
	public void setId_ty(int id_ty) {
		this.id_ty = id_ty;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public matiereELType(int id_ty, String titre) {
		super();
		this.id_ty = id_ty;
		this.titre = titre;
	}
	public matiereELType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
