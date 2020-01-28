/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectuas;

import id.web.arproject.uas.view.LoginView;

/**
 *
 * @author Mea
 */
public class ProjectUAS {
    
    private final static LoginView loginView = new LoginView(); 

    private static void showLogin() {
        loginView.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here  
        
        showLogin();
    }
    
}
