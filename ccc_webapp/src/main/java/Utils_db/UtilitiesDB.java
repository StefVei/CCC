/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils_db;


import hy360.ccc.db.CccDB;
import hy360.ccc.model.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panagiotisk
 */
public class UtilitiesDB {

    private static char digits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    public static void closeConnection(PreparedStatement preparedStatement, Connection con, String name) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException sql_ex) {
                Logger.getLogger(name).log(Level.SEVERE, null, sql_ex);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException sql_ex) {
                Logger.getLogger(UtilitiesDB.class.getName()).log(Level.SEVERE, null, sql_ex);
            }
        }
    }

    public static boolean containsId(final List<Transaction> list, final String id) {
        return list.stream().anyMatch(o -> o.getTransaction_id().equals(id));
    }

    private static List<String> getAllAccountNumbers() {

        PreparedStatement preparedStatement = null;
        Connection con = null;
        List<String> AccountNumbers = new ArrayList<String>();
        try {

            con = CccDB.getConnection();
            String sql1 = "SELECT ACCOUNT_NUMBER "
                    + "FROM users";
            preparedStatement = con.prepareStatement(sql1);
            preparedStatement.executeQuery();
            ResultSet res1 = preparedStatement.getResultSet();

            while (res1.next() == true) {
                AccountNumbers.add(res1.getString("ACCOUNT_NUMBER"));
            }

        } catch (Exception ex) {
            Logger.getLogger(UtilitiesDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return AccountNumbers;
    }

    private static char randomDecimalDigit() {
        return digits[(int) Math.floor(Math.random() * 10)];
    }

    private static String randomDecimalString(int ndigits) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < ndigits; i++) {
            res.append(randomDecimalDigit());
        }
        return res.toString();
    }

    public static String getNewAccountNumber() {
        List<String> accs = getAllAccountNumbers();
        while (true) {
            String acc = "GR" + randomDecimalString(25);
            if (!accs.contains(acc)) {
                return acc;
            } else {
            }
        }

    }

}
