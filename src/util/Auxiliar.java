/**
 * @author Adão Ferreira
 */
package util;

import java.awt.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class Auxiliar {

    /**
     * Efetua criptografia de uma string
     *
     * @param texto
     * @return
     */
    public static String MD5(String texto) {
        String cripto;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(texto.getBytes(), 0, texto.length());
            cripto = new BigInteger(1, md.digest()).toString();

            return cripto;
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return null;
    }

    /**
     * Recebe um lista de Componentes e limpa seus valores
     *
     * @param components
     */
    public static void clearFields(Component[] components) {
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JComboBox) {
                ((JComboBox) c).setSelectedIndex(0);
            }
            if (c instanceof JCheckBox) {
                ((JCheckBox) c).setSelected(false);
            }
        }
    }

    /**
     * Recebe um lista de Componentes e os desabilita
     *
     * @param components
     */
    public static void disabledFields(Component[] components) {
        for (Component c : components) {
            c.setEnabled(false);
        }
    }

    /**
     * Recebe um lista de Componentes e os habilita
     *
     * @param components
     */
    public static void enabledFields(Component[] components) {
        for (Component c : components) {
            c.setEnabled(true);
        }
    }

    /**
     * Converte Data para formato Americano
     *
     * @param data String
     * @return Date
     */
    public static Date convertToSQl(String data) {
        String[] split = data.split("/");
        String date = split[2] + "-" + split[1] + "-" + split[0];

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date strDate = formato.parse(date);
            java.sql.Date dataSQL = new Date(strDate.getTime());

            return dataSQL;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Converte Data para formato Americano
     *
     * @param data String
     * @return Date
     */
    public static String dateBr(java.sql.Date data) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formato.format(data);

            return strDate;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return null;
        }
    }

    /**
     * Verifica se a data é válida
     *
     * @param data String
     * @return
     */
    public static boolean isValideData(String data) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(data, dateTimeFormatter);

            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Verifica se CPF e válido
     *
     * @param cpf String
     * @return
     */
    public static boolean isValideCPF(String cpf) {
        String value = cpf.replaceAll("[^0-9]", "");

        if (value.length() != 11) {
            return false;
        }

        if (value.equals("000000000000") || value.equals("11111111111")
                || value.equals("22222222222") || value.equals("33333333333")
                || value.equals("44444444444") || value.equals("55555555555")
                || value.equals("66666666666") || value.equals("77777777777")
                || value.equals("88888888888") || value.equals("99999999999")) {

            return false;
        }

        char digit10, digit11;
        int sm = 0, i, r, num, peso = 10;

        try {
            // Calculo do 1o. Digito Verificador
            // converte o i-esimo caractere do CPF em um numero:
            // por exemplo, transforma o caractere '0' no inteiro 0         
            // (48 eh a posicao de '0' na tabela ASCII)              
            for (i = 0; i < 9; i++) {
                num = (int) (value.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                digit10 = '0';
            } else {
                digit10 = (char) (r + 48); // converte no respectivo caractere numerico
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (value.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                digit11 = '0';
            } else {
                digit11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((digit10 == value.charAt(9)) && (digit11 == value.charAt(10))) {
                return (true);
            } else {
                return (false);
            }

        } catch (InputMismatchException e) {
            return false;
        }
    }

    public static boolean isValideCNPJ(String cnpj) {
        try {
            cnpj = cnpj.replaceAll("[^0-9]", "");

            int soma = 0, dig;
            String cnpj_calc = cnpj.substring(0, 12);

            if (cnpj.length() != 14) {
                return false;
            }

            char[] chr_cnpj = cnpj.toCharArray();

            /* Primeira parte */
            for (int i = 0; i < 4; i++) {
                if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                    soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
                }
            }

            for (int i = 0; i < 8; i++) {
                if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                    soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
                }
            }

            dig = 11 - (soma % 11);
            cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

            /* Segunda parte */
            soma = 0;
            for (int i = 0; i < 5; i++) {
                if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                    soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
                }
            }

            for (int i = 0; i < 8; i++) {
                if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                    soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
                }
            }

            dig = 11 - (soma % 11);
            cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

            return cnpj.equals(cnpj_calc);
        } catch (Exception e) {
            return false;
        }
    }

}
