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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author Mea
 */
public class SampahModel {
    private String id_sampah, nama_sampah;
    private float harga_satuan, harga_jual;
    private String pesan;
    private Object[][] list;
    private final Koneksi koneksi = new Koneksi();
    private final dialogMessage dialogMessage = new dialogMessage();
    
    private int tipeBacaData = 1;
    
    public int getTipeBacaData() {
        return tipeBacaData;
    }

    public void setTipeBacaData(int tipeBacaData) {
        this.tipeBacaData = tipeBacaData;
    }

    public String getId_sampah() {
        return id_sampah;
    }

    public void setId_sampah(String id_sampah) {
        this.id_sampah = id_sampah;
    }

    public String getNama_sampah() {
        return nama_sampah;
    }

    public void setNama_sampah(String nama_sampah) {
        this.nama_sampah = nama_sampah;
    }

    public float getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(float harga_satuan) {
        this.harga_satuan = harga_satuan;
    }
    
    public float getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(float harga_satuan) {
        this.harga_jual = harga_satuan;
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
                SQLStatemen = "select * from data_sampah where nama_sampah=?";
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, nama_sampah);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    if (dialogMessage.tampilkanPilihan("Nama custumer sudah ada\nApakah data ingin diperbaharui?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                        simpan = true;
                        SQLStatemen = "update data_sampah set nama_sampah=?, harga_satuan=?, harga_jual=? where nama_sampah=?";
                        
                        preparedStatement = connection.prepareStatement(SQLStatemen);   
                        preparedStatement.setString(1, nama_sampah);
                        preparedStatement.setFloat(2, harga_satuan);
                        preparedStatement.setFloat(3, harga_jual);
                        preparedStatement.setString(4, nama_sampah);
                        
                        jumlahSimpan = preparedStatement.executeUpdate(); 
                    }
                } else {
                    simpan = true;
                    SQLStatemen = "insert into data_sampah(nama_sampah, harga_satuan, harga_jual) values (?,?,?)"; 
                    
                    preparedStatement = connection.prepareStatement(SQLStatemen);   
                    preparedStatement.setString(1, nama_sampah);
                    preparedStatement.setFloat(2, harga_satuan);
                    preparedStatement.setFloat(3, harga_jual);
                    
                    jumlahSimpan = preparedStatement.executeUpdate();
                }
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data sampah";
                    }
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel sampah\n"+ex+"\n"+SQLStatemen;
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
                SQLStatemen = "update data_sampah set nama_sampah=?, harga_satuan=?, harga_jual=? where nama_sampah=?";
                        
                preparedStatement = connection.prepareStatement(SQLStatemen);   
                preparedStatement.setString(1, nama_sampah);
                preparedStatement.setFloat(2, harga_satuan);
                preparedStatement.setFloat(3, harga_jual);
                preparedStatement.setString(4, nama_sampah);

                jumlahSimpan = preparedStatement.executeUpdate(); 
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal mengupdate data sampah";
                    }
                }
                
                preparedStatement.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel sampah\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    public boolean baca(String namaSampah){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        this.nama_sampah = "";
        this.harga_satuan = 0;
        this.harga_jual = 0;
        
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "select nama_sampah, harga_satuan, harga_jual from data_sampah where nama_sampah=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, namaSampah);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    this.nama_sampah = rset.getString("nama_sampah");
                    this.harga_satuan = rset.getFloat("harga_satuan");
                    this.harga_jual = rset.getFloat("harga_jual");
                } else {
                    adaKesalahan = true;
                    pesan = "Sampah \""+namaSampah+"\" tidak ditemukan";
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel data_sampah\n"+ex;
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
                SQLStatemen = "select nama_sampah, harga_satuan, harga_jual, sampah_terkumpul from data_sampah order by id_sampah desc";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new Object[rset.getRow()][3];
                
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do {
                        if (tipeBacaData == 1) {
                            list[i] = new Object[]{
                                (i+1),
                                rset.getString("nama_sampah"),
                                NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(rset.getFloat("harga_satuan"))
                            };
                        } else {
                            list[i] = new Object[]{
                                (i+1),
                                rset.getString("nama_sampah"),
                                NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(rset.getFloat("harga_jual")),
                                rset.getInt("sampah_terkumpul")
                            };
                        }
                        
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
    
    public boolean hapus(String namaSampah){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahHapus;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try {
                SQLStatemen = "delete from data_sampah where nama_sampah=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, namaSampah);
                jumlahHapus = preparedStatement.executeUpdate();
                
                if (jumlahHapus < 1){
                    pesan = "Data sampah dengan Nama "+namaSampah+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel sampah\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    public boolean hapusBatch(ArrayList<String> namaSampah){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahHapus;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try {
                String whereKondisi = "";
                for (int i = 0; i < namaSampah.size(); i++) {
                    if (i == 0) {
                        whereKondisi += "nama_sampah=?";
                    } else {
                        whereKondisi += " or nama_sampah=?";
                    }
                }
                SQLStatemen = "delete from data_sampah where " + whereKondisi;
                preparedStatement = connection.prepareStatement(SQLStatemen);
                for (int i = 0; i < namaSampah.size(); i++) {
                    preparedStatement.setString((i+1), namaSampah.get(i));
                }
                jumlahHapus = preparedStatement.executeUpdate();
                
                if (jumlahHapus < 1){
                    pesan = "Data sampah tidak ditemukan";
                    adaKesalahan = true;
                }
                
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel sampah\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
}
