/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import id.web.arproject.uas.controller.TrxJualController;
import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mea
 */
public class TrxJualView extends javax.swing.JInternalFrame {

    private final TrxJualController trxJualController = new TrxJualController();
    private final dialogMessage dialogMessage = new dialogMessage();
    
    private final DefaultTableModel mainTableModel;
    private int currentData = 0;
    private float currentHarga = 0;
    private int currentBerat = 0;
    

    /**
     * Creates new form TrxJualView
     */
    public TrxJualView() {
        initComponents();
        
        mainTableModel = (DefaultTableModel) mainTable.getModel();
        
        inputBerat.setText("0");
        inputHarga.setText("Rp0,00");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inputNama = new javax.swing.JTextField();
        btnAksiLihat = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        inputBerat = new javax.swing.JTextField();
        inputHarga = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnAksiTambah = new javax.swing.JButton();
        btnAksiHapus = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnAksiSimpan = new javax.swing.JButton();
        btnAksiBatal = new javax.swing.JButton();

        setTitle("Transaksi Jual Sampah");
        setToolTipText("");

        jLabel1.setText("Tujuan Perusahaan");

        inputNama.setEditable(false);

        btnAksiLihat.setText("Lihat");
        btnAksiLihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAksiLihatActionPerformed(evt);
            }
        });

        jLabel2.setText("Berat Sampah");

        jLabel3.setText("Total Harga Jual");

        inputBerat.setEditable(false);

        inputHarga.setEditable(false);

        jLabel4.setText("Kg");

        btnAksiTambah.setText("Tambah Sampah");
        btnAksiTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAksiTambahActionPerformed(evt);
            }
        });

        btnAksiHapus.setText("Hapus Sampah");
        btnAksiHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAksiHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(inputNama, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAksiLihat))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(inputHarga)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(inputBerat, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAksiTambah, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(btnAksiHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAksiLihat))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputBerat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnAksiTambah)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(inputHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAksiHapus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nama Sampah", "Harga Jual / Kg", "Berat Sampah (Kg)", "Harga Jual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mainTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(mainTable);
        if (mainTable.getColumnModel().getColumnCount() > 0) {
            mainTable.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAksiSimpan.setText("Simpan Transaksi");
        btnAksiSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAksiSimpanActionPerformed(evt);
            }
        });

        btnAksiBatal.setText("Batal");
        btnAksiBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAksiBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAksiSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAksiBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAksiSimpan)
                    .addComponent(btnAksiBatal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void resetData() {
        inputNama.setText("");
        inputBerat.setText("");
        inputHarga.setText("");
        
        mainTableModel.setRowCount(0);
    }

    private void btnAksiBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAksiBatalActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
    }//GEN-LAST:event_btnAksiBatalActionPerformed

    private void btnAksiSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAksiSimpanActionPerformed
        // TODO add your handling code here:
        
        if (mainTable.getRowCount() > 0) {
            if (dialogMessage.tampilkanPilihan("Apa Anda yakin ingin menyimpan data ini?","Aksi", new Object[]{"Ya","Tidak"}) == 0) {
                ArrayList<ArrayList> dataSampah = new ArrayList<>();
                int selectedRows = mainTable.getRowCount();
                for (int i = 0; i < selectedRows; i++) {
                    float getDataSatuan = Float.parseFloat(mainTable.getValueAt(mainTable.convertRowIndexToModel(i), 2).toString().replace("Rp","").replace(".","").replace(",","."));
                    int getDataBerat = Integer.parseInt(mainTable.getValueAt(mainTable.convertRowIndexToModel(i), 3).toString());
                    String getNamaSampah = mainTable.getValueAt(mainTable.convertRowIndexToModel(i), 1).toString();
                    float getDataEstimasi = getDataSatuan * getDataBerat;
                    ArrayList dataArray = new ArrayList<>();
                    dataArray.add(getNamaSampah);
                    dataArray.add(getDataSatuan);
                    dataArray.add(getDataBerat);
                    dataArray.add(getDataEstimasi);
                    dataSampah.add(dataArray);
                }
                
                Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().create();
                System.out.println(gson.toJson(dataSampah));
                
                trxJualController.simpan(this, inputNama, inputBerat, inputHarga, dataSampah);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang di pilih", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAksiSimpanActionPerformed

    private void btnAksiHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAksiHapusActionPerformed
        // TODO add your handling code here:
        
        if (mainTable.getSelectedRowCount() > 0) {
            if (mainTable.getSelectedRowCount() == 1) {
                float getDataSatuan = Float.parseFloat(mainTable.getValueAt(mainTable.getSelectedRow(), 2).toString().replace("Rp","").replace(".","").replace(",","."));
                int getDataBerat = Integer.parseInt(mainTable.getValueAt(mainTable.getSelectedRow(), 3).toString());

                float getDataEstimasi = getDataSatuan * getDataBerat;
                
                currentHarga -= getDataEstimasi;
                currentBerat -= getDataBerat;

                inputBerat.setText(Integer.toString(currentBerat));
                inputHarga.setText(NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(currentHarga));
                mainTableModel.removeRow(mainTable.getSelectedRow());
                
                currentData--;
            } else {
                JOptionPane.showMessageDialog(null, "Data yang di pilih hanya 1", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang di pilih", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAksiHapusActionPerformed

    private void btnAksiTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAksiTambahActionPerformed
        // TODO add your handling code here:
        
        trxJualController.openSampahDialog().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Aksi: TrxBeliView | btnAksiTambahActionPerformed | windowClosed | afterDialogSampah");
                afterDialogSampah();
            }
        });
    }//GEN-LAST:event_btnAksiTambahActionPerformed

    private void afterDialogSampah() {
        if (trxJualController.getSampahModel().baca(trxJualController.getSelectedData())) {
            currentData++;
            float getDataSatuan = trxJualController.getSampahModel().getHarga_jual();
            int getDataBerat = trxJualController.getDataBerat();
            
            float getDataEstimasi = getDataSatuan * getDataBerat;
            
            currentHarga += getDataEstimasi;
            currentBerat += getDataBerat;
            
            inputBerat.setText(Integer.toString(currentBerat));
            inputHarga.setText(NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(currentHarga));

            mainTableModel.addRow(new Object[]{
                currentData, 
                trxJualController.getSampahModel().getNama_sampah(), 
                NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(getDataSatuan), 
                getDataBerat, 
                NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(getDataEstimasi)
            });
        }
    }

    private void btnAksiLihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAksiLihatActionPerformed
        // TODO add your handling code here:
        
        Dialog selectDataPerusahaanView = trxJualController.openPerusahaanDialog();
        selectDataPerusahaanView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Aksi: TrxBeliView | btnAksiLihatActionPerformed | windowClosed | afterDialogPelanggan");
                afterDialogPerusahaan();
            }
        });
    }//GEN-LAST:event_btnAksiLihatActionPerformed

    private void afterDialogPerusahaan() {
        inputNama.setText(trxJualController.getSelectedData());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAksiBatal;
    private javax.swing.JButton btnAksiHapus;
    private javax.swing.JButton btnAksiLihat;
    private javax.swing.JButton btnAksiSimpan;
    private javax.swing.JButton btnAksiTambah;
    private javax.swing.JTextField inputBerat;
    private javax.swing.JTextField inputHarga;
    private javax.swing.JTextField inputNama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable mainTable;
    // End of variables declaration//GEN-END:variables
}
