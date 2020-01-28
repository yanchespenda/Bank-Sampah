/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.controller;

import id.web.arproject.uas.model.LaporanMasukModel;
import id.web.arproject.uas.view.LaporanMasukView;
import java.beans.PropertyVetoException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Mea
 */
public class LaporanMasukController {
    private final LaporanMasukModel laporanMasukModel = new LaporanMasukModel();
    private LaporanMasukView laporanMasukView;
    
    private javax.swing.JDesktopPane dataMdiDesktopPane;
    
    public void openLaporan(javax.swing.JDesktopPane mdiDesktopPane) {
        if ((laporanMasukView != null) && laporanMasukView.isVisible()){
            try {
                laporanMasukView.setSelected(true);
            } catch (PropertyVetoException ex){}
        } else {
            dataMdiDesktopPane = mdiDesktopPane;
            laporanMasukView = new LaporanMasukView();
            mdiDesktopPane.add(laporanMasukView);
            laporanMasukView.setVisible(true);
        }
    }
    
    public void getJumlahLaporan(javax.swing.JComboBox paramTanggal, javax.swing.JTextField inputJumlah) {
        int getParamTanggal = paramTanggal.getSelectedIndex();
        LocalDate date = LocalDate.now();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (getParamTanggal) {
            case 0:
                date = LocalDate.now();
                break;
            case 1:
                date = LocalDate.now().minusDays(1);
                break;
            case 2:
                date = LocalDate.now().minusDays(7);
                break;
            case 3:
                date = LocalDate.now().minusDays(30);
                break;
            case 4:
                date = LocalDate.now().minusDays(365);
                break;
            default:
                break;
        }
        java.util.Date dateNew = java.sql.Date.valueOf(date);
        String getTanggal = sdf.format(dateNew);

        if (laporanMasukModel.getJumlahTrx(getTanggal)) {
            inputJumlah.setText(String.valueOf(laporanMasukModel.getJumlahTrx()));
        } else {
            System.out.println("Error: LaporanMasukController | getJumlahLaporan | getJumlahTrx:" + laporanMasukModel.getPesan());
        }
    }

    public void cetak(javax.swing.JComboBox paramTanggal) {
        int getParamTanggal = paramTanggal.getSelectedIndex();
        LocalDate date = LocalDate.now();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (getParamTanggal) {
            case 0:
                date = LocalDate.now();
                break;
            case 1:
                date = LocalDate.now().minusDays(1);
                break;
            case 2:
                date = LocalDate.now().minusDays(7);
                break;
            case 3:
                date = LocalDate.now().minusDays(30);
                break;
            case 4:
                date = LocalDate.now().minusDays(365);
                break;
            default:
                break;
        }
        java.util.Date dateNew = java.sql.Date.valueOf(date);
        String getTanggal = sdf.format(dateNew);

        if (laporanMasukModel.getJumlahTrx(getTanggal)) {
            if (laporanMasukModel.getJumlahTrx() > 0) {
                if (!laporanMasukModel.cetakLaporan(getTanggal)) {
                    System.out.println("Error: LaporanMasukController | cetak | cetakLaporan:" + laporanMasukModel.getPesan());
                    JOptionPane.showMessageDialog(null, laporanMasukModel.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data Transaksi Kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Error: LaporanMasukController | getJumlahLaporan | getJumlahTrx:" + laporanMasukModel.getPesan());
        }
    }
}
