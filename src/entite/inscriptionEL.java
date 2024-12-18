package entite;

public class inscriptionEL {
	private String nomCls;
	private Double montant;
	private Double montant_Tenue;
	private Double montant_TeeShirt;
	private int coef_classe;
	
	
	
	public Double getMontant_TeeShirt() {
		return montant_TeeShirt;
	}
	public void setMontant_TeeShirt(Double montant_TeeShirt) {
		this.montant_TeeShirt = montant_TeeShirt;
	}
	public Double getMontant_Tenue() {
		return montant_Tenue;
	}
	public void setMontant_Tenue(Double montant_Tenue) {
		this.montant_Tenue = montant_Tenue;
	}
	
	public String getNomCls() {
		return nomCls;
	}
	public void setNomCls(String nomCls) {
		this.nomCls = nomCls;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public inscriptionEL() {
		super();
		// TODO Auto-generated constructor stub
	}
	public inscriptionEL(String nomCls, Double montant, Double montant_Tenue , Double montant_TeeShirt , int coef_classe) {
		super();
		this.nomCls = nomCls;
		this.montant = montant;
		this.montant_Tenue = montant_Tenue;
		this.coef_classe = coef_classe;
		this.montant_TeeShirt = montant_TeeShirt;
	}
	public int getCoef_classe() {
		return coef_classe;
	}
	public void setCoef_classe(int coef_classe) {
		this.coef_classe = coef_classe;
	}
	
	
	
}
