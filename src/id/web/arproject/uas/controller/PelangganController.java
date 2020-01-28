/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.controller;
import id.web.arproject.uas.model.PelangganModel;
import id.web.arproject.uas.view.MDPelangganView;
import id.web.arproject.uas.view.MDPelangganViewAction;
import java.awt.Dialog;
import java.awt.Frame;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mea
 */
public class PelangganController {
    
    private final PelangganModel pelangganModel = new PelangganModel();
    
    private MDPelangganView mDPelangganView;
    private MDPelangganViewAction mDPelangganViewAction;
    
    private javax.swing.JDesktopPane dataMdiDesktopPane;
    
    public void openMasterData(javax.swing.JDesktopPane mdiDesktopPane) {
        if ((mDPelangganView != null) && mDPelangganView.isVisible()){
            try {
                mDPelangganView.setSelected(true);
            } catch (PropertyVetoException ex){}
        } else {
            dataMdiDesktopPane = mdiDesktopPane;
            mDPelangganView = new MDPelangganView();
            mdiDesktopPane.add(mDPelangganView);
            refreshList(true);
            mDPelangganView.setVisible(true);
        }
        
    }
    
    public Dialog openTambahData() {
        mDPelangganViewAction = new MDPelangganViewAction(null, true);
        mDPelangganViewAction.setTitle("Tambah Data");
        mDPelangganViewAction.setDefaultAction(1);
        showDialog(mDPelangganViewAction);
        
        return mDPelangganViewAction;
    }
    
    public Dialog openEditData(String namaCustumer) {
        mDPelangganViewAction = new MDPelangganViewAction(null, true);
        mDPelangganViewAction.setTitle("Edit Data");
        mDPelangganViewAction.setDefaultAction(2);
        if (pelangganModel.baca(namaCustumer)) {
            System.out.println("Trigger: PelangganController | openEditData | baca:Success");
            mDPelangganViewAction.setNama(pelangganModel.getNama_custumer());
            mDPelangganViewAction.setNoTelepon(pelangganModel.getNo_telepon());
            mDPelangganViewAction.setAlamat(pelangganModel.getAlamat());
            showDialog(mDPelangganViewAction);
        } else {
            System.out.println("Trigger: PelangganController | openEditData | baca:Failed");
            JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return mDPelangganViewAction;
    }
    
    public void simpan(javax.swing.JDialog dialog, javax.swing.JTextField inputNama, javax.swing.JTextField inputNoTelepon, javax.swing.JTextArea inputAlamat) {
        if (!inputNama.getText().equals("")) {
            pelangganModel.setNama_custumer(inputNama.getText());
            pelangganModel.setNo_telepon(inputNoTelepon.getText());
            pelangganModel.setAlamat(inputAlamat.getText());
            
            if (pelangganModel.simpan()) {
                JOptionPane.showMessageDialog(null, "Palanggan berhasil di simpan", "Success", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Trigger: PelangganController | simpan | simpan:Success");
                inputNama.setText("");
                inputNoTelepon.setText("");
                inputAlamat.setText("");
                dialog.dispose();
            } else {
                System.out.println("Trigger: PelangganController | simpan | simpan:Failed");
                JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void update(javax.swing.JDialog dialog, javax.swing.JTextField inputNama, javax.swing.JTextField inputNoTelepon, javax.swing.JTextArea inputAlamat) {
        if (!inputNama.getText().equals("")) {
            pelangganModel.setNama_custumer(inputNama.getText());
            pelangganModel.setNo_telepon(inputNoTelepon.getText());
            pelangganModel.setAlamat(inputAlamat.getText());
            
            if (pelangganModel.update()) {
                JOptionPane.showMessageDialog(null, "Palanggan berhasil di update", "Success", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Trigger: PelangganController | update | update:Success");
                inputNama.setText("");
                inputNoTelepon.setText("");
                inputAlamat.setText("");
                dialog.dispose();
            } else {
                System.out.println("Trigger: PelangganController | update | update:Failed");
                JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void delete(ArrayList<String> id) {
        if (pelangganModel.hapusBatch(id)) {
            JOptionPane.showMessageDialog(null, "Palanggan berhasil di hapus", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Trigger: PelangganController | update | update:Success");
        } else {
            System.out.println("Trigger: PelangganController | update | update:Failed");
            JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void refreshList(boolean hardReset) {
        if (pelangganModel.bacaData()) {
            if (hardReset){
                mDPelangganView.tampilkanData(pelangganModel.getList());
            } else {
                if ((mDPelangganView != null) && mDPelangganView.isVisible())
                    mDPelangganView.tampilkanData(pelangganModel.getList());
            }
        } else {
            System.out.println("Trigger: PelangganController | refreshList | bacaData:Failed");
            JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void refreshListDirect(MDPelangganView mDPelangganViewDirect) {
        if (pelangganModel.bacaData()) {
            mDPelangganViewDirect.tampilkanData(pelangganModel.getList());
        } else {
            JOptionPane.showMessageDialog(null, getPesan(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String getPesan() {
        return pelangganModel.getPesan();
    }
    
    private void showDialog(Dialog dialog) {
        Frame frame = JOptionPane.getFrameForComponent(dataMdiDesktopPane);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
