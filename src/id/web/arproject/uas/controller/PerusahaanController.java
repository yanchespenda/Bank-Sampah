/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.controller;

import id.web.arproject.uas.model.PerusahaanModel;
import id.web.arproject.uas.view.MDPerusahaanView;
import id.web.arproject.uas.view.MDPerusahaanViewAction;
import java.awt.Dialog;
import java.awt.Frame;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mea
 */
public class PerusahaanController {
    
    private final PerusahaanModel perusahaanModel = new PerusahaanModel();
    
    private MDPerusahaanView mDPerusahaanView;
    private MDPerusahaanViewAction mDPerusahaanViewAction;
    
    private javax.swing.JDesktopPane dataMdiDesktopPane;
    
    public void openMasterData(javax.swing.JDesktopPane mdiDesktopPane) {
        if ((mDPerusahaanView != null) && mDPerusahaanView.isVisible()){
            try {
                mDPerusahaanView.setSelected(true);
            } catch (PropertyVetoException ex){}
        } else {
            dataMdiDesktopPane = mdiDesktopPane;
            mDPerusahaanView = new MDPerusahaanView();
            mdiDesktopPane.add(mDPerusahaanView);
            refreshList(true);
            mDPerusahaanView.setVisible(true);
        }
        
    }
    
    public Dialog openTambahData() {
        mDPerusahaanViewAction = new MDPerusahaanViewAction(null, true);
        mDPerusahaanViewAction.setTitle("Tambah Data");
        mDPerusahaanViewAction.setDefaultAction(1);
        showDialog(mDPerusahaanViewAction);
        
        return mDPerusahaanViewAction;
    }
    
    public Dialog openEditData(String namaCustumer) {
        mDPerusahaanViewAction = new MDPerusahaanViewAction(null, true);
        mDPerusahaanViewAction.setTitle("Edit Data");
        mDPerusahaanViewAction.setDefaultAction(2);
        if (perusahaanModel.baca(namaCustumer)) {
            System.out.println("Trigger: PerusahaanController | openEditData | baca:Success");
            mDPerusahaanViewAction.setNama(perusahaanModel.getNama_perusahaan());
            mDPerusahaanViewAction.setNoTelepon(perusahaanModel.getNo_telepon());
            mDPerusahaanViewAction.setAlamat(perusahaanModel.getAlamat());
            showDialog(mDPerusahaanViewAction);
        } else {
            System.out.println("Trigger: PerusahaanController | openEditData | baca:Failed");
            JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return mDPerusahaanViewAction;
    }
    
    public void simpan(javax.swing.JDialog dialog, javax.swing.JTextField inputNama, javax.swing.JTextField inputNoTelepon, javax.swing.JTextArea inputAlamat) {
        if (!inputNama.getText().equals("")) {
            perusahaanModel.setNama_perusahaan(inputNama.getText());
            perusahaanModel.setNo_telepon(inputNoTelepon.getText());
            perusahaanModel.setAlamat(inputAlamat.getText());
            
            if (perusahaanModel.simpan()) {
                JOptionPane.showMessageDialog(null, "Perusahaan berhasil di simpan", "Success", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Trigger: PerusahaanController | simpan | simpan:Success");
                inputNama.setText("");
                inputNoTelepon.setText("");
                inputAlamat.setText("");
                dialog.dispose();
            } else {
                System.out.println("Trigger: PerusahaanController | simpan | simpan:Failed");
                JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void update(javax.swing.JDialog dialog, javax.swing.JTextField inputNama, javax.swing.JTextField inputNoTelepon, javax.swing.JTextArea inputAlamat) {
        if (!inputNama.getText().equals("")) {
            perusahaanModel.setNama_perusahaan(inputNama.getText());
            perusahaanModel.setNo_telepon(inputNoTelepon.getText());
            perusahaanModel.setAlamat(inputAlamat.getText());
            
            if (perusahaanModel.update()) {
                JOptionPane.showMessageDialog(null, "Perusahaan berhasil di update", "Success", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Trigger: PerusahaanController | update | update:Success");
                inputNama.setText("");
                inputNoTelepon.setText("");
                inputAlamat.setText("");
                dialog.dispose();
            } else {
                System.out.println("Trigger: PerusahaanController | update | update:Failed");
                JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void delete(ArrayList<String> id) {
        if (perusahaanModel.hapusBatch(id)) {
            JOptionPane.showMessageDialog(null, "Perusahaan berhasil di hapus", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Trigger: PerusahaanController | update | update:Success");
        } else {
            System.out.println("Trigger: PerusahaanController | update | update:Failed");
            JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void refreshList(boolean hardReset) {
        if (perusahaanModel.bacaData()) {
            if (hardReset){
                mDPerusahaanView.tampilkanData(perusahaanModel.getList());
            } else {
                if ((mDPerusahaanView != null) && mDPerusahaanView.isVisible())
                    mDPerusahaanView.tampilkanData(perusahaanModel.getList());
            }
        } else {
            System.out.println("Trigger: PerusahaanController | refreshList | bacaData:Failed");
            JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void refreshListDirect(MDPerusahaanView mDPelangganViewDirect) {
        if (perusahaanModel.bacaData()) {
            mDPelangganViewDirect.tampilkanData(perusahaanModel.getList());
        } else {
            JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getPesan() {
        return perusahaanModel.getPesan();
    }
    
    private void showDialog(Dialog dialog) {
        Frame frame = JOptionPane.getFrameForComponent(dataMdiDesktopPane);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
