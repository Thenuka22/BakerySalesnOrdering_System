
package bakerypossystem.Controller;
import bakerypossystem.Model.LoginValidation;
import javax.swing.JOptionPane;
public class CUserSignIn {
    LoginValidation  mSignIn;
    public int signIn(String username, String password) {
        mSignIn = new LoginValidation();
        if(mSignIn.signIn(username, password)==true) {
            return 1;
        }
        else
            return 0;
    }
}
