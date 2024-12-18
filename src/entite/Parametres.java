package entite;


public class Parametres {
	private String driver;
	private String username;
	private String passwd;
	private String serverDB;
	
	public Parametres(){
		driver = "com.mysql.jdbc.Driver";
		username = "root";
		passwd = "";
		serverDB = "jdbc:mysql://localhost/ecole";
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getServerDB() {
		return serverDB;
	}

	public void setServerDB(String serverDB) {
		this.serverDB = serverDB;
	}
	
	

}
