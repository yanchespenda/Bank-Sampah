/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.model;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 *
 * @author Mea
 */
public class Hashing {
    public String hash(String value) throws Exception{
        String password = value;
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        
        return bcryptHashString;
    }
    
    public Boolean verify(String value, String dbHash) throws Exception{
        String password = value;
        String bcryptHashString = dbHash;
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        
        return result.verified;
    }
}
