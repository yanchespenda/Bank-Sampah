/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.controller;

import id.web.arproject.uas.model.UserModel;

/**
 *
 * @author Mea
 */
public class LoginController {
    private final UserModel userModel = new UserModel();
    
    public boolean getUsername(javax.swing.JTextField inputUsername) {
        return userModel.baca(inputUsername.getText());
    }
    
    public boolean matchPassword(javax.swing.JTextField inputPassword) {
        return userModel.verifyPassword(inputPassword.getText());
    }
    
    public String getPesan() {
        return userModel.getPesan();
    }
    
}
