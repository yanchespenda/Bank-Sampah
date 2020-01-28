/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.view;

import javax.swing.JOptionPane;

/**
 *
 * @author Mea
 */
public class dialogMessage {
    public int tampilkanPilihan(String pesan, String judul, Object[] opsiPilihan){        
        return JOptionPane.showOptionDialog(null, pesan, judul, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, opsiPilihan, opsiPilihan[0]);
    }
}
