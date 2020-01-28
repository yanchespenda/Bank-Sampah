/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.model;

import id.web.arproject.uas.view.dialogMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mea
 */
public class UserModel {
    private String id_user, username, password, nama;
    private String pesan;
    private Object[][] list;
    private final Koneksi koneksi = new Koneksi();
    private final dialogMessage dialogMessage = new dialogMessage();
    
    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
    
    public Object[][] getList() {
        return list;
    }

    public void setList(Object[][] list) {
        this.list = list;
    }
    
    public boolean simpan(){
        boolean isError = false;
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahSimpan=0;
            boolean simpan = false; 
            String SQLStatemen="";
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select * from user where username=? limit 1 ";
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, username);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    if (dialogMessage.tampilkanPilihan("Username sudah ada\nApakah data diperbaharui?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                        simpan = true;
                        
                        String passwordHash = "";
                        boolean isUpdatePassword = false;
                        
                        if (password != null) {
                            try {
                                Hashing hashing = new Hashing();
                                passwordHash = hashing.hash(password);
                                isUpdatePassword = true;
                            } catch(Exception ex) {

                            }
                        }
                        
                        if (isUpdatePassword) {
                            SQLStatemen = "update user set username=?, password=?, nama=? where username=?";
                            preparedStatement = connection.prepareStatement(SQLStatemen);   
                            preparedStatement.setString(1, username);
                            preparedStatement.setString(2, password);
                            preparedStatement.setString(3, nama);
                            preparedStatement.setString(4, username);
                        } else {
                            SQLStatemen = "update user set username=?, nama=? where username=?";
                            preparedStatement = connection.prepareStatement(SQLStatemen);   
                            preparedStatement.setString(1, username);
                            preparedStatement.setString(2, nama);
                            preparedStatement.setString(3, username);
                        }
                        jumlahSimpan = preparedStatement.executeUpdate(); 
                    }
                } else {
                    simpan = true;
                    SQLStatemen = "insert into user(username, password, nama) values (?,?,?)"; 
                    String passwordHash = "";
                    try {
                        Hashing hashing = new Hashing();
                        passwordHash = hashing.hash(password);
                    } catch(Exception ex) {
                        
                    }
                    preparedStatement = connection.prepareStatement(SQLStatemen);   
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, passwordHash);
                    preparedStatement.setString(3, nama);
                    
                    jumlahSimpan = preparedStatement.executeUpdate();
                }
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        isError = true; 
                        pesan = "Gagal menyimpan data user";
                    }
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();                
            } catch (SQLException ex){
                isError = true;
                pesan = "Tidak dapat membuka tabel user\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            isError = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !isError;
    }
    
    public boolean baca(String username){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        this.username = "";
        this.password = "";
        this.nama = "";
        
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "select * from user where username=? limit 1 ";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, username);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    this.username = rset.getString("username");
                    this.password = rset.getString("password");
                    this.nama = rset.getString("nama");
                } else {
                    adaKesalahan = true;
                    pesan = "Username \""+username+"\" tidak ditemukan";
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel user\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    public boolean verifyPassword(String password) {
        boolean result = false;
        
        try {
            Hashing hashing = new Hashing();
            result = hashing.verify(password, this.password);
            if (!result) {
                pesan = "Password salah";
            }
        } catch(Exception ex) {
            result = false;
            pesan = "Something went wrong /n" + ex.getMessage();
        }
        
        return result;
    }
}
