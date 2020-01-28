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
import java.sql.Statement;

/**
 *
 * @author Mea
 */
public class TransaksiKeluarModel {
    private String tanggal;
    private int id_transaksi, no_perusahaan, berat_sampah, sampah_terkumpul;
    private float harga_sampah;
    
    private String id_transaksi_relasi;
    private int berat_sampah_relasi, id_sampah_relasi;
    private float jumlah_pemasukan_relasi;
    
    private String pesan;
    private Object[][] list;
    private final Koneksi koneksi = new Koneksi();
    private final dialogMessage dialogMessage = new dialogMessage();

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getNo_perusahaan() {
        return no_perusahaan;
    }

    public void setNo_perusahaan(int no_perusahaan) {
        this.no_perusahaan = no_perusahaan;
    }

    public int getBerat_sampah() {
        return berat_sampah;
    }

    public void setBerat_sampah(int berat_sampah) {
        this.berat_sampah = berat_sampah;
    }

    public int getSampah_terkumpul() {
        return sampah_terkumpul;
    }

    public void setSampah_terkumpul(int sampah_terkumpul) {
        this.sampah_terkumpul = sampah_terkumpul;
    }

    public float getHarga_sampah() {
        return harga_sampah;
    }

    public void setHarga_sampah(float harga_sampah) {
        this.harga_sampah = harga_sampah;
    }

    public String getId_transaksi_relasi() {
        return id_transaksi_relasi;
    }

    public void setId_transaksi_relasi(String id_transaksi_relasi) {
        this.id_transaksi_relasi = id_transaksi_relasi;
    }

    public int getBerat_sampah_relasi() {
        return berat_sampah_relasi;
    }

    public void setBerat_sampah_relasi(int berat_sampah_relasi) {
        this.berat_sampah_relasi = berat_sampah_relasi;
    }

    public int getId_sampah_relasi() {
        return id_sampah_relasi;
    }

    public void setId_sampah_relasi(int id_sampah_relasi) {
        this.id_sampah_relasi = id_sampah_relasi;
    }

    public float getJumlah_pemasukan_relasi() {
        return jumlah_pemasukan_relasi;
    }

    public void setJumlah_pemasukan_relasi(float jumlah_pemasukan_relasi) {
        this.jumlah_pemasukan_relasi = jumlah_pemasukan_relasi;
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
    
    public boolean cariPerusahaanId(String namaPerusahaan) {
        boolean adaKesalahan = false;	
        Connection connection; 
        
        this.no_perusahaan = 0;
        
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "select id_perusahaan from data_perusahaan where nama_perusahaan=? limit 1";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, namaPerusahaan);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    this.no_perusahaan = rset.getInt("id_perusahaan");
                } else {
                    adaKesalahan = true;
                    pesan = "Sampah \""+namaPerusahaan+"\" tidak ditemukan";
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel data_custumer\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    public boolean simpanTransaksi() {
        boolean adaKesalahan = false;
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            boolean simpan = false; 
            String SQLStatemen="";
            PreparedStatement preparedStatement;
            
            try {
                SQLStatemen = "insert into data_transaksi_keluar(tanggal, no_perusahaan, berat_sampah, harga_sampah) values (?,?,?,?)";
                
                preparedStatement = connection.prepareStatement(SQLStatemen, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, tanggal);
                preparedStatement.setInt(2, no_perusahaan);
                preparedStatement.setInt(3, berat_sampah);
                preparedStatement.setFloat(4, harga_sampah);
                int jumlahInsert = preparedStatement.executeUpdate();
                
                if (jumlahInsert < 1) {
                    adaKesalahan = true; 
                    pesan = "Gagal menambah data transaksi jual";
                }
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()){
                    id_transaksi = rs.getInt(1);
                }
                
                preparedStatement.close();
                rs.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel data_transaksi_masuk\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    public boolean cariSampahId(String namaSampah) {
        boolean adaKesalahan = false;	
        Connection connection; 
        
        this.id_sampah_relasi = 0;
        
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "select id_sampah, sampah_terkumpul from data_sampah where nama_sampah=? limit 1";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, namaSampah);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    this.id_sampah_relasi = rset.getInt("id_sampah");
                    this.sampah_terkumpul = rset.getInt("sampah_terkumpul");
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
    
    public boolean simpanTransaksiRelasi() {
        boolean adaKesalahan = false;
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            boolean simpan = false; 
            String SQLStatemen="";
            PreparedStatement preparedStatement;
            
            try {
                SQLStatemen = "insert into data_transaksi_keluar_relasi(id_transaksi, id_sampah, berat_sampah, jumlah_pemasukan) values (?,?,?,?)";
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setInt(1, id_transaksi);
                preparedStatement.setInt(2, id_sampah_relasi);
                preparedStatement.setInt(3, berat_sampah_relasi);
                preparedStatement.setFloat(4, jumlah_pemasukan_relasi);
                int jumlahInsert = preparedStatement.executeUpdate();
                
                if (jumlahInsert < 1) {
                    adaKesalahan = true; 
                    pesan = "Gagal menambah data data_transaksi_keluar_relasi";
                }
                
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel data_transaksi_keluar_relasi\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    public boolean updateDataSampah(int beratKurang) {
        boolean adaKesalahan = false;
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            boolean simpan = false; 
            String SQLStatemen="";
            PreparedStatement preparedStatement;
            
            try {
                SQLStatemen = "update data_sampah set sampah_terkumpul=? where id_sampah=?";
                
                sampah_terkumpul = sampah_terkumpul - beratKurang;
                if (sampah_terkumpul < 0) {
                    sampah_terkumpul = 0;
                }
                
                System.out.println("Debug: TransaksiMasukModel | sampah_terkumpul:" + sampah_terkumpul + " - beratKurang:" + beratKurang + " - id_sampah_relasi:" + id_sampah_relasi);
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setInt(1, sampah_terkumpul);
                preparedStatement.setInt(2, id_sampah_relasi);
                int jumlahInsert = preparedStatement.executeUpdate();
                
                if (jumlahInsert < 1) {
                    adaKesalahan = true; 
                    pesan = "Gagal mengupdate data sampah";
                }
                
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel data_transaksi_masuk\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    
}
