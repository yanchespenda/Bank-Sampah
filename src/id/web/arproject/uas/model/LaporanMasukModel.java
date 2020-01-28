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
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Mea
 */
public class LaporanMasukModel {
    private String id_transaksi, tanggal;
    private int no_custumer, berat_sampah;
    private float harga_sampah;
    
    private String id_transaksi_;
    private int id_transaksi_relasi, id_sampah_relasi, berat_sampah_relasi;
    private float jumlah_pengeluaran_relasi;
    
    private String paramTanggal;
    private int jumlahTrx;
    
    private String pesan;
    private Object[][] list;
    private final Koneksi koneksi = new Koneksi();
    private final dialogMessage dialogMessage = new dialogMessage();
    
    public String getParamTanggal() {
        return paramTanggal;
    }

    public void setParamTanggal(String paramTanggal) {
        this.paramTanggal = paramTanggal;
    }

    public int getJumlahTrx() {
        return jumlahTrx;
    }

    public void setJumlahTrx(int jumlahTrx) {
        this.jumlahTrx = jumlahTrx;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getNo_custumer() {
        return no_custumer;
    }

    public void setNo_custumer(int no_custumer) {
        this.no_custumer = no_custumer;
    }

    public int getBerat_sampah() {
        return berat_sampah;
    }

    public void setBerat_sampah(int berat_sampah) {
        this.berat_sampah = berat_sampah;
    }

    public float getHarga_sampah() {
        return harga_sampah;
    }

    public void setHarga_sampah(float harga_sampah) {
        this.harga_sampah = harga_sampah;
    }

    public String getId_transaksi_() {
        return id_transaksi_;
    }

    public void setId_transaksi_(String id_transaksi_) {
        this.id_transaksi_ = id_transaksi_;
    }

    public int getId_transaksi_relasi() {
        return id_transaksi_relasi;
    }

    public void setId_transaksi_relasi(int id_transaksi_relasi) {
        this.id_transaksi_relasi = id_transaksi_relasi;
    }

    public int getId_sampah_relasi() {
        return id_sampah_relasi;
    }

    public void setId_sampah_relasi(int id_sampah_relasi) {
        this.id_sampah_relasi = id_sampah_relasi;
    }

    public int getBerat_sampah_relasi() {
        return berat_sampah_relasi;
    }

    public void setBerat_sampah_relasi(int berat_sampah_relasi) {
        this.berat_sampah_relasi = berat_sampah_relasi;
    }

    public float getJumlah_pengeluaran_relasi() {
        return jumlah_pengeluaran_relasi;
    }

    public void setJumlah_pengeluaran_relasi(float jumlah_pengeluaran_relasi) {
        this.jumlah_pengeluaran_relasi = jumlah_pengeluaran_relasi;
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
    
    public boolean getJumlahTrx(String paramTanggal) {
        boolean adaKesalahan = false;	
        Connection connection;
        
        this.paramTanggal = paramTanggal;
        this.jumlahTrx = 0;
        
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "SELECT COUNT(*) AS jumlah FROM data_transaksi_masuk WHERE tanggal > ?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, paramTanggal);
                rset = preparedStatement.executeQuery();
                
//                System.out.println("Debug: LaporanMasukModel | getJumlahTrx | SQLStatemen:" + preparedStatement.toString());

                rset.next();
                if (rset.getRow()>0){
                    this.jumlahTrx = rset.getInt("jumlah");
                } else {
                    adaKesalahan = true;
                    pesan = "Data tanggal \""+paramTanggal+"\" tidak ditemukan";
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel data_transaksi_masuk\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    public boolean cetakLaporan(String paramTanggal){
        boolean adaKesalahan = false;
        Connection connection;
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatement;
            Statement statement;
            ResultSet resultSet = null;
                    
            try {
                SQLStatement = "SELECT " +
                    "     data_sampah.`nama_sampah` AS data_sampah_nama_sampah, " +
                    "     DATE_FORMAT(data_transaksi_masuk.`tanggal`, '%d-%m-%Y %r') AS data_transaksi_masuk_tanggal, " +
                    "     data_transaksi_masuk_relasi.`id_transaksi` AS data_transaksi_masuk_relasi_id_transaksi, " +
                    "     data_custumer.`nama_custumer` AS data_custumer_nama_custumer, " +
                    "     data_custumer.`no_telepon` AS data_custumer_no_telepon, " +
                    "     CONCAT('Rp ',FORMAT(data_transaksi_masuk_relasi.`jumlah_pengeluaran`,2,'id_ID')) AS data_transaksi_masuk_relasi_jumlah_pengeluaran, " +
                    "     CONCAT(data_transaksi_masuk_relasi.`berat_sampah`,' Kg') AS data_transaksi_masuk_relasi_berat_sampah, " +
                    "     data_transaksi_masuk.`id_transaksi` AS data_transaksi_masuk_id_transaksi, " +
                    "     CONCAT('Rp ',FORMAT(data_transaksi_masuk.`harga_sampah`,2,'id_ID')) AS data_transaksi_masuk_harga_sampah, " +
                    "     CONCAT(data_transaksi_masuk.`berat_sampah`,' Kg') AS data_transaksi_masuk_berat_sampah " +
                    "FROM " +
                    "     `data_transaksi_masuk` data_transaksi_masuk INNER JOIN `data_custumer` data_custumer ON data_transaksi_masuk.`no_custumer` = data_custumer.`id_custumer` " +
                    "     INNER JOIN `data_transaksi_masuk_relasi` data_transaksi_masuk_relasi ON data_transaksi_masuk.`id_transaksi` = data_transaksi_masuk_relasi.`id_transaksi` " +
                    "     INNER JOIN `data_sampah` data_sampah ON data_transaksi_masuk_relasi.`id_sampah` = data_sampah.`id_sampah` ";

                if (!paramTanggal.equals("")){
                    SQLStatement += " WHERE data_transaksi_masuk.`tanggal` > '" + paramTanggal + "' ";
                }
                
                SQLStatement += " ORDER BY data_transaksi_masuk.`tanggal` DESC ";
                
                statement = connection.createStatement();
                resultSet = statement.executeQuery(SQLStatement);
                
            } catch (SQLException ex) {
                adaKesalahan = true;
                pesan = "Tidak dapat membaca data\n"+ex;
            }
            
            if (resultSet != null){
                try {
                    JasperDesign reportDesain = JRXmlLoader.load("src/report/laporan_masuk.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(reportDesain);
                    JRResultSetDataSource reportDataSource = new JRResultSetDataSource(resultSet);
                    JasperPrint cetak = JasperFillManager.fillReport(report, new HashMap(), reportDataSource);
                    JasperViewer.viewReport(cetak, false);
                } catch (JRException ex) {
                    adaKesalahan = true;
                    pesan = "Tidak dapat mencetak laporan\n"+ex;
                }
            }
        }  else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getErorMessage();
        }
        
        return !adaKesalahan;
    }
    
    
}
