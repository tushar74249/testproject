import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.sql.*;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class passwordcontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailid;

    @FXML
    private TextField password;

    @FXML
    private ComboBox<String> platformname;

    @FXML
    private Button Save;

    @FXML
    private Button Update;

    @FXML
    private Button Search;

   
    
  

    @FXML
    void Datasave(ActionEvent event) {
    	try {
			prsmtc=con.prepareStatement("insert into  pasword values(?,?,?)");
			prsmtc.setString(1, platformname.getEditor().getText());
			prsmtc.setString(2, emailid.getText());
			prsmtc.setString(3, password.getText());
			
			
			if(Platforms.contains(platformname.getEditor().getText())) {
				error("platform already present");
				emailid.clear();
				password.clear();
				platformname.getEditor().clear();
			}
			else{
				prsmtc.executeUpdate();
				
				platformname.getItems().add(platformname.getEditor().getText());
				info("data saved sucessfully");
				
				emailid.clear();
				password.clear();
				platformname.getEditor().clear();
			}
			/*try {
				prsmtc=con.prepareStatement("select * from pasword");
				ResultSet res=prsmtc.executeQuery();
				while(res.next()){
					String pla=res.getString("Platform");
					if(pla==platformname.getEditor().getText()) {
						prsmtc.executeUpdate();
						
						addplatform(platformname.getEditor().getText());
						info("data saved sucessfully");
						
						emailid.clear();
						password.clear();
						platformname.getEditor().clear();
					}
					else {
						error("platform already added");
						platformname.getEditor().clear();
						emailid.clear();
						password.clear();
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void Dataupdate(ActionEvent event) {
    	try {
			prsmtc=con.prepareStatement("update pasword set passwordhint=?,emialid=? where Platform=?");
			prsmtc.setString(1, password.getText());
			prsmtc.setString(2, emailid.getText());
			prsmtc.setString(3, platformname.getEditor().getText());
			
			prsmtc.executeUpdate();
			
			info("data updated sucessfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void datasearch(ActionEvent event) {
    	try {
			prsmtc=con.prepareStatement("select * from pasword where Platform=?");
			prsmtc.setString(1, platformname.getEditor().getText());
			ResultSet res=prsmtc.executeQuery();
			while(res.next()) {
				String email=res.getString("emialid");
				String passsword=res.getString("passwordhint");
				
				emailid.setText(email);
				password.setText(passsword);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    void info(String s) {
    	Alert info=new Alert(AlertType.INFORMATION);
    	info.setTitle("title");
    	info.setContentText(s);
    	info.show();
    }
    	
    	 void error(String e) {
    	    	Alert error=new Alert(AlertType.ERROR);
    	    	error.setTitle("title");
    	    	error.setContentText(e);
    	    	error.show();
    }

   Connection con;
   PreparedStatement prsmtc;
   
   ArrayList<String>Platforms=new ArrayList<String>();
    @FXML
    void initialize() {
    	con=passwordconnect.getConnection();
    	try {
			prsmtc=con.prepareStatement("select * from pasword");
			ResultSet res=prsmtc.executeQuery();
			while(res.next()){
				String pla=res.getString("Platform");
				platformname.getItems().add(pla);
				Platforms.add(pla);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
}
