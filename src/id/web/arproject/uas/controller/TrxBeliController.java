/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.controller;

import id.web.arproject.uas.model.PelangganModel;
import id.web.arproject.uas.model.SampahModel;
import id.web.arproject.uas.model.TransaksiMasukModel;
import id.web.arproject.uas.view.SelectDataPelangganView;
import id.web.arproject.uas.view.SelectDataSampahView;
import id.web.arproject.uas.view.TrxBeliView;
import java.awt.Dialog;
import java.awt.Frame;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mea
 */
public class TrxBeliController {
    private TrxBeliView trxBeliView;
    private SelectDataPelangganView selectDataPelangganView;
    private SelectDataSampahView selectDataSampahView;
    private javax.swing.JDesktopPane dataMdiDesktopPane;
    
    private final SampahModel sampahModel = new SampahModel();
    private final TransaksiMasukModel transaksiMasukModel = new TransaksiMasukModel();
    private final PelangganModel pelangganModel = new PelangganModel();
    
    private String dataSelected;
    private int dataBerat;
    
    public void openTransaksi(javax.swing.JDesktopPane mdiDesktopPane) {
        if ((trxBeliView != null) && trxBeliView.isVisible()){
            try {
                trxBeliView.setSelected(true);
            } catch (PropertyVetoException ex){}
        } else {
            dataMdiDesktopPane = mdiDesktopPane;
            trxBeliView = new TrxBeliView();
            mdiDesktopPane.add(trxBeliView);
            trxBeliView.setVisible(true);
        }
    }
    
    public Dialog openPelangganDialog() {
        selectDataPelangganView = new SelectDataPelangganView(null, true);
        selectDataPelangganView.setTitle("Lihat Data Pelanggan");

        if (pelangganModel.bacaData()) {
            System.out.println("Aksi: TrxBeliController | openPelangganDialog | bacaData:Success");
            selectDataPelangganView.tampilkanData(pelangganModel.getList());
            showDialog(selectDataPelangganView);
            if (!selectDataPelangganView.getSelectedData().equals("")) {
                System.out.println("Aksi: TrxBeliController | openPelangganDialog | bacaData | getSelectedData:Success");
                this.dataSelected = selectDataPelangganView.getSelectedData();
            } else {
                System.out.println("Aksi: TrxBeliController | openPelangganDialog | bacaData | getSelectedData:Failed");
            }
        } else {
            System.out.println("Aksi: TrxBeliController | openPelangganDialog | bacaData:Failed");
        }
        
        return selectDataPelangganView;
    }
    
    public String getSelectedData() {
        return dataSelected;
    }
    
    public int getDataBerat() {
        return dataBerat;
    }
    
    public SampahModel getSampahModel() {
        return sampahModel;
    }
    
    public Dialog openSampahDialog() {
        selectDataSampahView = new SelectDataSampahView(null, true);
        selectDataSampahView.setTitle("Lihat Data Sampah");
        
        if (sampahModel.bacaData()) {
            System.out.println("Aksi: TrxBeliController | openSampahDialog | bacaData:Success");
            selectDataSampahView.tampilkanData(sampahModel.getList());
            showDialog(selectDataSampahView);
            if (!selectDataSampahView.getSelectedData().equals("")) {
                if (selectDataSampahView.getDataBtnAksi() == 1) {
                    System.out.println("Aksi: TrxBeliController | openSampahDialog | bacaData | getSelectedData:Success");
                    this.dataSelected = selectDataSampahView.getSelectedData();
                    this.dataBerat = selectDataSampahView.getDataBerat();
                } else {
                    System.out.println("Aksi: TrxBeliController | openSampahDialog | bacaData | getSelectedData:getDataBtnAksi:Empty");
                    this.dataSelected = "";
                    this.dataBerat = 0;
                }
            } else {
                System.out.println("Aksi: TrxBeliController | openSampahDialog | bacaData | getSelectedData:Failed");
                this.dataSelected = "";
                this.dataBerat = 0;
            }
        } else {
            System.out.println("Aksi: TrxBeliController | openSampahDialog | bacaData:Failed");
            this.dataSelected = "";
            this.dataBerat = 0;
        }
        
        return selectDataSampahView;
    }
    
    public void simpan(TrxBeliView trxBeliView, javax.swing.JTextField inputNama, javax.swing.JTextField inputBerat, javax.swing.JTextField inputHarga, ArrayList<ArrayList> dataSampah) {
        if (!inputNama.getText().equals("")) {
            if (dataSampah.size() > 0) {
                if (transaksiMasukModel.cariCustumerId(inputNama.getText())) {
                    int getBeratSampah = 0;
                    float getHargaSampah = 0;
                    boolean isError = false;
                    try {
                        getBeratSampah = Integer.parseInt(inputBerat.getText());
                        if (getBeratSampah < 0) {
                            getBeratSampah = 0;
                        }
                    } catch(java.lang.NumberFormatException ex) {
                        isError = true;
                    }
                    try {
                        getHargaSampah = Float.parseFloat(inputHarga.getText().replace("Rp","").replace(".","").replace(",","."));
                        if (getHargaSampah < 0) {
                            getHargaSampah = 0;
                        }
                    } catch(java.lang.NumberFormatException ex) {
                        isError = true;
                    }

                    java.util.Date waktuServer = new java.util.Date();
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
                    transaksiMasukModel.setBerat_sampah(getBeratSampah);
                    transaksiMasukModel.setHarga_sampah(getHargaSampah);
                    transaksiMasukModel.setTanggal(sdf.format(waktuServer));
                    if (!isError) {
                        if (transaksiMasukModel.simpanTransaksi()) {
                            for (ArrayList list : dataSampah) {
                                if (transaksiMasukModel.cariSampahId(list.get(0).toString())) {
                                    transaksiMasukModel.setBerat_sampah_relasi(Integer.parseInt(list.get(2).toString()));
                                    transaksiMasukModel.setJumlah_pengeluaran_relasi(Float.parseFloat(list.get(3).toString()));

                                    if (!transaksiMasukModel.simpanTransaksiRelasi() || !transaksiMasukModel.updateDataSampah(getBeratSampah)) {
                                        System.out.println("Error: TrxBeliController | simpan | cariCustumerId | simpanTransaksiRelasi:" + transaksiMasukModel.getPesan());
                                    }
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Transaksi berhasil di simpan", "Success", JOptionPane.INFORMATION_MESSAGE);
                            trxBeliView.resetData();
                        } else {
                            JOptionPane.showMessageDialog(null, transaksiMasukModel.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Data berat atau harga tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Data pelanggan tidak di temukan", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data sampah kosong", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nama pelanggan harus di isi", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getPesan() {
//        return sampahModel.getPesan();
        return "";
    }
    
    private void showDialog(Dialog dialog) {
        Frame frame = JOptionPane.getFrameForComponent(dataMdiDesktopPane);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
