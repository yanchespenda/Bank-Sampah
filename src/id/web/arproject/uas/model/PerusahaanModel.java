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
import java.util.ArrayList;

/**
 *
 * @author Mea
 */
public class PerusahaanModel {
    private String id_perusahaan, nama_perusahaan, alamat, no_telepon;
    private String pesan;
    private Object[][] list;
    private final Koneksi koneksi = new Koneksi();
    private final dialogMessage dialogMessage = new dialogMessage();

    public String getId_perusahaan() {
        return id_perusahaan;
    }

    public void setId_perusahaan(String id_perusahaan) {
        this.id_perusahaan = id_perusahaan;
    }

    public String getNama_perusahaan() {
        return nama_perusahaan;
    }

    public void setNama_perusahaan(String nama_perusahaan) {
        this.nama_perusahaan = nama_perusahaan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
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
    
    public boolean simpan() {
        boolean adaKesalahan = false;
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahSimpan=0;
            boolean simpan = false; 
            String SQLStatemen="";
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select * from data_perusahaan where nama_perusahaan=?";
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, nama_perusahaan);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    if (dialogMessage.tampilkanPilihan("Nama perusahaan sudah ada\nApakah data ingin diperbaharui?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                        simpan = true;
                        SQLStatemen = "update data_perusahaan set nama_perusahaan=?, alamat=?, no_telepon=? where nama_perusahaan=?";
                        
                        preparedStatement = connection.prepareStatement(SQLStatemen);   
                        preparedStatement.setString(1, nama_perusahaan);
                        preparedStatement.setString(2, alamat);
                        preparedStatement.setString(3, no_telepon);
                        preparedStatement.setString(4, nama_perusahaan);
                        
                        jumlahSimpan = preparedStatement.executeUpdate(); 
                    }
                } else {
                    simpan = true;
                    SQLStatemen = "insert into data_perusahaan(nama_perusahaan, alamat, no_telepon) values (?,?,?)"; 
                    
                    preparedStatement = connection.prepareStatement(SQLStatemen);   
                    preparedStatement.setString(1, nama_perusahaan);
                        preparedStatement.setString(2, alamat);
                        preparedStatement.setString(3, no_telepon);
                    
                    jumlahSimpan = preparedStatement.executeUpdate();
                }
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data perusahaan";
                    }
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel perusahaan\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    public boolean update() {
        boolean adaKesalahan = false;
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahSimpan=0;
            boolean simpan = false; 
            String SQLStatemen="";
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                simpan = true;
                SQLStatemen = "update data_perusahaan set nama_perusahaan=?, alamat=?, no_telepon=? where nama_perusahaan=?";

                preparedStatement = connection.prepareStatement(SQLStatemen);   
                preparedStatement.setString(1, nama_perusahaan);
                preparedStatement.setString(2, alamat);
                preparedStatement.setString(3, no_telepon);
                preparedStatement.setString(4, nama_perusahaan);

                jumlahSimpan = preparedStatement.executeUpdate(); 
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal mengupdate data perusahaan";
                    }
                }
                
                preparedStatement.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel perusahaan\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    public boolean baca(String namaPerusahaan){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        this.nama_perusahaan = "";
        this.alamat = "";
        this.no_telepon = "";
        
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "select nama_perusahaan, alamat, no_telepon from data_perusahaan where nama_perusahaan=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, namaPerusahaan);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    this.nama_perusahaan = rset.getString("nama_perusahaan");
                    this.alamat = rset.getString("alamat");
                    this.no_telepon = rset.getString("no_telepon");
                } else {
                    adaKesalahan = true;
                    pesan = "Perusahaan \""+namaPerusahaan+"\" tidak ditemukan";
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel perusahaan\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    public boolean bacaData(){
        boolean adaKesalahan = false;
        Connection connection;
        list = new Object[0][0];
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select nama_perusahaan, no_telepon from data_perusahaan order by id_perusahaan desc";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new Object[rset.getRow()][3];
                
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do {
                        list[i] = new Object[]{(i+1), rset.getString("nama_perusahaan"), rset.getString("no_telepon")};
                        i++;
                    } while (rset.next());
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membaca data\n"+ex.getMessage();
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    public boolean hapus(String namaPerusahaan){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahHapus;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try {
                SQLStatemen = "delete from data_perusahaan where nama_perusahaan=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, namaPerusahaan);
                jumlahHapus = preparedStatement.executeUpdate();
                
                if (jumlahHapus < 1){
                    pesan = "Data perusahaan dengan Nama "+namaPerusahaan+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel perusahaan\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    public boolean hapusBatch(ArrayList<String> namaPerusahaan){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahHapus;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try {
                String whereKondisi = "";
                for (int i = 0; i < namaPerusahaan.size(); i++) {
                    if (i == 0) {
                        whereKondisi += "nama_perusahaan=?";
                    } else {
                        whereKondisi += " or nama_perusahaan=?";
                    }
                }
                SQLStatemen = "delete from data_perusahaan where " + whereKondisi;
                preparedStatement = connection.prepareStatement(SQLStatemen);
                for (int i = 0; i < namaPerusahaan.size(); i++) {
                    preparedStatement.setString((i+1), namaPerusahaan.get(i));
                }
                jumlahHapus = preparedStatement.executeUpdate();
                
                if (jumlahHapus < 1){
                    pesan = "Data perusahaan tidak ditemukan";
                    adaKesalahan = true;
                }
                
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel perusahaan\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
}
