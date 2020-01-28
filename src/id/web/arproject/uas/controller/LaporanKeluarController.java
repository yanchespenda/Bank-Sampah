/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.controller;

import id.web.arproject.uas.model.LaporanKeluarModel;
import id.web.arproject.uas.view.LaporanKeluarView;
import java.beans.PropertyVetoException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Mea
 */
public class LaporanKeluarController {
    private final LaporanKeluarModel laporanKeluarModel = new LaporanKeluarModel();
    private LaporanKeluarView laporanKeluarView;
    
    private javax.swing.JDesktopPane dataMdiDesktopPane;
    
    public void openLaporan(javax.swing.JDesktopPane mdiDesktopPane) {
        if ((laporanKeluarView != null) && laporanKeluarView.isVisible()){
            try {
                laporanKeluarView.setSelected(true);
            } catch (PropertyVetoException ex){}
        } else {
            dataMdiDesktopPane = mdiDesktopPane;
            laporanKeluarView = new LaporanKeluarView();
            mdiDesktopPane.add(laporanKeluarView);
            laporanKeluarView.setVisible(true);
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

        if (laporanKeluarModel.getJumlahTrx(getTanggal)) {
            inputJumlah.setText(String.valueOf(laporanKeluarModel.getJumlahTrx()));
        } else {
            System.out.println("Error: LaporanKeluarController | getJumlahLaporan | getJumlahTrx:" + laporanKeluarModel.getPesan());
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

        if (laporanKeluarModel.getJumlahTrx(getTanggal)) {
            if (laporanKeluarModel.getJumlahTrx() > 0) {
                if (!laporanKeluarModel.cetakLaporan(getTanggal)) {
                    System.out.println("Error: LaporanKeluarController | cetak | cetakLaporan:" + laporanKeluarModel.getPesan());
                    JOptionPane.showMessageDialog(null, laporanKeluarModel.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data Transaksi Kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Error: LaporanKeluarController | getJumlahLaporan | getJumlahTrx:" + laporanKeluarModel.getPesan());
        }
    }
}
