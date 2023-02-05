package com.nw.edu.util;

import com.nw.edu.model.ChefUserModel;
import com.nw.edu.pojo.ChefUser;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Home
 */
public class Validations {
    public static void allowOnlyLettersandSpace(KeyEvent evt, JTextField txt) {
        char c = evt.getKeyChar();
        boolean isSatisfied = Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c);
        if (isSatisfied) {
            txt.setEditable(true);
        } else {
            txt.setEditable(false);
        }
    }

    public static void allowOnlyNumbers(KeyEvent evt, JTextField txt) {
        char c = evt.getKeyChar();
        boolean isSatisfied = Character.isDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c);
        if (isSatisfied) {
            txt.setEditable(true);
        } else {
            txt.setEditable(false);
        }
    }

    public static boolean isEmpty(JTextField txtField, String message) {
        String txtValue = txtField.getText().trim();
        if (txtValue.length() == 0) {
            JOptionPane.showMessageDialog(null, message);
            txtField.setBackground(Color.ORANGE);
            txtField.requestFocus();
            return true;
        } else {
            txtField.setBackground(Color.white);
            return false;
        }
    }

    public static boolean isEmpty(JTextArea txtArea, String message) {
        String txtValue = txtArea.getText().trim();
        if (txtValue.length() == 0) {
            JOptionPane.showMessageDialog(null, message);
            txtArea.setBackground(Color.ORANGE);
            txtArea.requestFocus();
            return true;
        } else {
            txtArea.setBackground(Color.white);
            return false;
        }
    }

    public static boolean isEmpty(String strVal, JLabel picLabel, String message) {
        if (null == strVal) {
            JOptionPane.showMessageDialog(null, message);
            picLabel.setBackground(Color.ORANGE);
            picLabel.setOpaque(true);
            return true;
        } else {
            picLabel.setBackground(Color.white);
            picLabel.setOpaque(true);
            return false;
        }
    }

    public static boolean checkMinLength(JTextField txtField, int minLength, String message) {
        String txtValue = txtField.getText().trim();
        if (txtValue.length() < minLength) {
            JOptionPane.showMessageDialog(null, message);
            txtField.setBackground(Color.ORANGE);
            txtField.requestFocus();
            return true;
        } else {
            txtField.setBackground(Color.white);
            return false;
        }
    }

    public static boolean isEmailValid(JTextField txtField, String message) {
        String txtValue = txtField.getText().trim();
        String pattern = "^[a-zA-Z]\\w{2,}@\\w{2,}(\\.\\w{2,3}){1,2}$";
        if (!txtValue.matches(pattern)) {
            JOptionPane.showMessageDialog(null, message);
            txtField.setBackground(Color.ORANGE);
            txtField.requestFocus();
            return true;
        } else {
            txtField.setBackground(Color.white);
            return false;
        }
    } 
    
    public static boolean userExist(JTextField txtField, String message) {
        String txtValue = txtField.getText().trim();

        ChefUser user = ChefUserModel.getChefUserByUsername(txtValue);
        String username = user.getUsername();
        System.out.println("1Username:" + txtValue + "=" + username);
        return showMessage(txtValue, username, txtField, message);
    }

    private static boolean showMessage(String str1, String str2, JTextField txtField, String message) {
        if (str1.equalsIgnoreCase(str2)) {
            JOptionPane.showMessageDialog(null, message);
            txtField.setBackground(Color.ORANGE);
            txtField.requestFocus();
            return true;
        } else {
            txtField.setBackground(Color.white);
            return false;
        }
    }    
}
