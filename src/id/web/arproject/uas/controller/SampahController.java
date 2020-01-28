/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.controller;

import id.web.arproject.uas.model.SampahModel;
import id.web.arproject.uas.view.MDSampahView;
import id.web.arproject.uas.view.MDSampahViewAction;
import java.awt.Dialog;
import java.awt.Frame;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mea
 */
public class SampahController {
    
    private final SampahModel sampahModel = new SampahModel();
    
    private MDSampahView mDSampahView;
    private MDSampahViewAction mDSampahViewAction;
    private javax.swing.JDesktopPane dataMdiDesktopPane;
    
    public void openMasterData(javax.swing.JDesktopPane mdiDesktopPane) {
        if ((mDSampahView != null) && mDSampahView.isVisible()){
            try {
                mDSampahView.setSelected(true);
            } catch (PropertyVetoException ex){}
        } else {
            dataMdiDesktopPane = mdiDesktopPane;
            mDSampahView = new MDSampahView();
            mdiDesktopPane.add(mDSampahView);
            refreshList(true);
            mDSampahView.setVisible(true);
        }
        
    }
    
    public Dialog openTambahData() {
        mDSampahViewAction = new MDSampahViewAction(null, true);
        mDSampahViewAction.setTitle("Tambah Data");
        mDSampahViewAction.setDefaultAction(1);
        showDialog(mDSampahViewAction);
        
        return mDSampahViewAction;
    }
    
    public Dialog openEditData(String namaSampah) {
        mDSampahViewAction = new MDSampahViewAction(null, true);
        mDSampahViewAction.setTitle("Edit Data");
        mDSampahViewAction.setDefaultAction(2);
        if (sampahModel.baca(namaSampah)) {
            System.out.println("Trigger: SampahController | openEditData | baca:Success");
            mDSampahViewAction.setNama(sampahModel.getNama_sampah());
            mDSampahViewAction.setHarga(sampahModel.getHarga_satuan());
            mDSampahViewAction.setHargaJual(sampahModel.getHarga_jual());
            showDialog(mDSampahViewAction);
        } else {
            System.out.println("Trigger: SampahController | openEditData | baca:Failed");
            JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return mDSampahViewAction;
    }
    
    public void simpan(javax.swing.JDialog dialog, javax.swing.JTextField inputNama, javax.swing.JTextField inputHarga, javax.swing.JTextField inputHargaJual) {
        if (!inputNama.getText().equals("")) {
            sampahModel.setNama_sampah(inputNama.getText());
            boolean isError = false;
            float tempGetHarga = 0,
                    tempGetHargaJual = 0;
            try {
                tempGetHarga = Float.parseFloat(inputHarga.getText());
                if (tempGetHarga < 0) {
                    tempGetHarga = 0;
                }
            } catch(java.lang.NumberFormatException ex) {
                isError = true;
            }
            try {
                tempGetHargaJual = Float.parseFloat(inputHargaJual.getText());
                if (tempGetHargaJual < 0) {
                    tempGetHargaJual = 0;
                }
            } catch(java.lang.NumberFormatException ex) {
                isError = true;
            }
            sampahModel.setHarga_satuan(tempGetHarga);
            sampahModel.setHarga_jual(tempGetHargaJual);
            
            if (!isError) {
                if (sampahModel.simpan()) {
                    JOptionPane.showMessageDialog(null, "Sampah berhasil di simpan", "Success", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Trigger: SampahController | simpan | simpan:Success");
                    inputNama.setText("");
                    inputHarga.setText("");
                    dialog.dispose();
                } else {
                    System.out.println("Trigger: SampahController | simpan | simpan:Failed");
                    JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Trigger: SampahController | simpan | isError:Failed");
                JOptionPane.showMessageDialog(null, "Nilai harga tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void update(javax.swing.JDialog dialog, javax.swing.JTextField inputNama, javax.swing.JTextField inputHarga, javax.swing.JTextField inputHargaJual) {
        if (!inputNama.getText().equals("")) {
            sampahModel.setNama_sampah(inputNama.getText());
            boolean isError = false;
            float tempGetHarga = 0,
                    tempGetHargaJual = 0;
            try {
                tempGetHarga = Float.parseFloat(inputHarga.getText());
                if (tempGetHarga < 0) {
                    tempGetHarga = 0;
                }
            } catch(java.lang.NumberFormatException ex) {
                isError = true;
            }
            try {
                tempGetHargaJual = Float.parseFloat(inputHargaJual.getText());
                if (tempGetHargaJual < 0) {
                    tempGetHargaJual = 0;
                }
            } catch(java.lang.NumberFormatException ex) {
                isError = true;
            }
            sampahModel.setHarga_satuan(tempGetHarga);
            sampahModel.setHarga_jual(tempGetHargaJual);
            
            if (!isError) {
                if (sampahModel.update()) {
                    JOptionPane.showMessageDialog(null, "Sampah berhasil di update", "Success", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Trigger: SampahController | update | update:Success");
                    inputNama.setText("");
                    inputHarga.setText("");
                    dialog.dispose();
                } else {
                    System.out.println("Trigger: SampahController | update | update:Failed");
                    JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Trigger: SampahController | update | isError:Failed");
                JOptionPane.showMessageDialog(null, "Nilai harga tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void delete(ArrayList<String> id) {
        if (sampahModel.hapusBatch(id)) {
            JOptionPane.showMessageDialog(null, "Sampah berhasil di hapus", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Trigger: SampahController | update | update:Success");
        } else {
            System.out.println("Trigger: SampahController | update | update:Failed");
            JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void refreshList(boolean hardReset) {
        if (sampahModel.bacaData()) {
            if (hardReset){
                mDSampahView.tampilkanData(sampahModel.getList());
            } else {
                if ((mDSampahView != null) && mDSampahView.isVisible())
                    mDSampahView.tampilkanData(sampahModel.getList());
            }
        } else {
            System.out.println("Trigger: SampahController | refreshList | bacaData:Failed");
            JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void refreshListDirect(MDSampahView mDPelangganViewDirect) {
        if (sampahModel.bacaData()) {
            mDPelangganViewDirect.tampilkanData(sampahModel.getList());
        } else {
            JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void getListSelect() {
        
    }
    
    public String getPesan() {
        return sampahModel.getPesan();
    }
    
    private void showDialog(Dialog dialog) {
        Frame frame = JOptionPane.getFrameForComponent(dataMdiDesktopPane);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
