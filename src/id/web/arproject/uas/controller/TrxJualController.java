/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.controller;

import id.web.arproject.uas.model.PerusahaanModel;
import id.web.arproject.uas.model.SampahModel;
import id.web.arproject.uas.model.TransaksiKeluarModel;
import id.web.arproject.uas.view.SelectDataPerusahaanView;
import id.web.arproject.uas.view.SelectDataSampahView;
import id.web.arproject.uas.view.TrxJualView;
import java.awt.Dialog;
import java.awt.Frame;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mea
 */
public class TrxJualController {
    private TrxJualView trxJualView;
    private SelectDataPerusahaanView selectDataPerusahaanView;
    private SelectDataSampahView selectDataSampahView;
    private javax.swing.JDesktopPane dataMdiDesktopPane;
    
    private final SampahModel sampahModel = new SampahModel();
    private final TransaksiKeluarModel transaksiKeluarModel = new TransaksiKeluarModel();
    private final PerusahaanModel perusahaanModel = new PerusahaanModel();
    
    private String dataSelected;
    private int dataBerat;
    
    public void openTransaksi(javax.swing.JDesktopPane mdiDesktopPane) {
        if ((trxJualView != null) && trxJualView.isVisible()){
            try {
                trxJualView.setSelected(true);
            } catch (PropertyVetoException ex){}
        } else {
            dataMdiDesktopPane = mdiDesktopPane;
            trxJualView = new TrxJualView();
            mdiDesktopPane.add(trxJualView);
            trxJualView.setVisible(true);
        }
    }
    
    public Dialog openPerusahaanDialog() {
        selectDataPerusahaanView = new SelectDataPerusahaanView(null, true);
        selectDataPerusahaanView.setTitle("Lihat Data Pelanggan");
        
        if (perusahaanModel.bacaData()) {
            System.out.println("Aksi: TrxJualController | openPerusahaanDialog | bacaData:Success");
            selectDataPerusahaanView.tampilkanData(perusahaanModel.getList());
            showDialog(selectDataPerusahaanView);
            if (!selectDataPerusahaanView.getSelectedData().equals("")) {
                System.out.println("Aksi: TrxJualController | openPerusahaanDialog | bacaData | getSelectedData:Success");
                this.dataSelected = selectDataPerusahaanView.getSelectedData();
            } else {
                System.out.println("Aksi: TrxJualController | openPerusahaanDialog | bacaData | getSelectedData:Failed");
            }
        } else {
            System.out.println("Aksi: TrxJualController | openPerusahaanDialog | bacaData:Failed");
        }
        
        return selectDataPerusahaanView;
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
        selectDataSampahView.settingMainModel(2);
        sampahModel.setTipeBacaData(2);
        
        if (sampahModel.bacaData()) {
            System.out.println("Aksi: TrxJualController | openSampahDialog | bacaData:Success");
            selectDataSampahView.tampilkanData(sampahModel.getList());
            showDialog(selectDataSampahView);
            if (!selectDataSampahView.getSelectedData().equals("")) {
                if (selectDataSampahView.getDataBtnAksi() == 1) {
                    System.out.println("Aksi: TrxJualController | openSampahDialog | bacaData | getSelectedData:Success");
                    this.dataSelected = selectDataSampahView.getSelectedData();
                    this.dataBerat = selectDataSampahView.getDataBerat();
                } else {
                    System.out.println("Aksi: TrxJualController | openSampahDialog | bacaData | getSelectedData:getDataBtnAksi:Empty");
                    this.dataSelected = "";
                    this.dataBerat = 0;
                }
            } else {
                this.dataSelected = "";
                this.dataBerat = 0;
                System.out.println("Aksi: TrxJualController | openSampahDialog | bacaData | getSelectedData:Failed");
            }
        } else {
            this.dataSelected = "";
            this.dataBerat = 0;
            System.out.println("Aksi: TrxJualController | openSampahDialog | bacaData:Failed");
        }
        
        return selectDataSampahView;
    }
    
    public void simpan(TrxJualView trxBeliView, javax.swing.JTextField inputNama, javax.swing.JTextField inputBerat, javax.swing.JTextField inputHarga, ArrayList<ArrayList> dataSampah) {
        if (!inputNama.getText().equals("")) {
            if (dataSampah.size() > 0) {
                if (transaksiKeluarModel.cariPerusahaanId(inputNama.getText())) {
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
            
                    transaksiKeluarModel.setBerat_sampah(getBeratSampah);
                    transaksiKeluarModel.setHarga_sampah(getHargaSampah);
                    transaksiKeluarModel.setTanggal(sdf.format(waktuServer));
                    if (!isError) {
                        if (transaksiKeluarModel.simpanTransaksi()) {
                            for (ArrayList list : dataSampah) {
                                if (transaksiKeluarModel.cariSampahId(list.get(0).toString())) {
                                    transaksiKeluarModel.setBerat_sampah_relasi(Integer.parseInt(list.get(2).toString()));
                                    transaksiKeluarModel.setJumlah_pemasukan_relasi(Float.parseFloat(list.get(3).toString()));

                                    if (!transaksiKeluarModel.simpanTransaksiRelasi() || !transaksiKeluarModel.updateDataSampah(getBeratSampah)) {
                                        System.out.println("Error: TrxJualController | simpan | cariPerusahaanId | simpanTransaksiRelasi:" + transaksiKeluarModel.getPesan());
                                    }
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Transaksi berhasil di simpan", "Success", JOptionPane.INFORMATION_MESSAGE);
                            trxBeliView.resetData();
                        } else {
                            JOptionPane.showMessageDialog(null, transaksiKeluarModel.getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private void showDialog(Dialog dialog) {
        Frame frame = JOptionPane.getFrameForComponent(dataMdiDesktopPane);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
