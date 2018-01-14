package chat.server.info;

import java.util.Arrays;

public class DatabaseManager {
	private DatabaseConnector db;

	public DatabaseManager() {
		db = new LocalDatabaseConnector("./res/chatdb.db");
	}
	public void addUser(String pUsername, String pPassword) {
		db.executeStatement("SELECT * from users");
		int newID= db.getCurrentQueryResult().getRowCount();
		db.executeStatement("INSTERT INTO Users VALUES("+newID+","+pUsername+","+pPassword+")");
	}
	public String getPassword(String Username) {
		db.executeStatement("Select password from user where Username='"+Username+"';");
		if(db.getCurrentQueryResult().getRowCount()!=0){
			//System.out.println(db.getCurrentQueryResult().getData()[0][0]);
			return db.getCurrentQueryResult().getData()[0][0];
		}
		else return "invalidUser\n";
	}

}
