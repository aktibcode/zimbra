package entite;

public class enseignantEL {
	private int ensId;
	private String ensLogin;
	private String nomCls;
	private String ensCours;
	private int idEns;
	public int getIdEns() {
		return idEns;
	}
	public void setIdEns(int idEns) {
		this.idEns = idEns;
	}
	public int getEnsId() {
		return ensId;
	}
	public void setEnsId(int ensId) {
		this.ensId = ensId;
	}
	public String getEnsLogin() {
		return ensLogin;
	}
	public void setEnsLogin(String ensLogin) {
		this.ensLogin = ensLogin;
	}
	public String getNomCls() {
		return nomCls;
	}
	public void setNomCls(String nomCls) {
		this.nomCls = nomCls;
	}
	public String getEnsCours() {
		return ensCours;
	}
	public void setEnsCours(String ensCours) {
		this.ensCours = ensCours;
	}
	public enseignantEL(int ensId, String ensCours,String ensLogin, String nomCls, int idEns
			) {
		super();
		this.ensId = ensId;
		this.ensCours = ensCours;
		this.ensLogin = ensLogin;
		this.nomCls = nomCls;
		this.idEns = idEns;
		
	}
	public enseignantEL() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
