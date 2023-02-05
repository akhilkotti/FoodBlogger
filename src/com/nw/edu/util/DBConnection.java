package com.nw.edu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.nw.edu.constants.Constants;
import com.nw.edu.constants.DBConstants;

/**
 *
 * @author Home
 */
public class DBConnection {

    public static Connection getDBConnection(final ProcessLog dbLog, final String driver, final String url,
            final String user, final String password) {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            dbLog.log("Connection Established");
        } catch (Exception e) {
            dbLog.log(e);
            if (con != null) {
                try {
                    con.close();
                    dbLog.log("Ex Connection closed");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        }
        return con;

    }

    public static void main(String[] args) {
        ProcessLog dbLog = null;
        Connection con_ = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            dbLog = new ProcessLog(Constants.DB_CONNECTION_LOG, Constants.LOG_FILE_EXTENSION);
            con_ = getDBConnection(dbLog, DBConstants.MYSQL_DRIVER, DBConstants.DATABASE_URL, DBConstants.DATABASE_USER,
                    DBConstants.DATABASE_PASSWORD);

            st = con_.createStatement();
            rs = st.executeQuery(DBConstants.CHEF_RECIPE_CATEGORY_SELECT_QUERY);
            while (rs.next()) {
                dbLog.logWOD(rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3) + "-" + rs.getString(4)
                        + "-" + rs.getString(5) + "-" + rs.getString(6) + "-" + rs.getInt(7) + "-" + rs.getString(8)
                        + "-" + rs.getInt(9) + "-" + rs.getInt(10) + "-" + rs.getString(11) + "-" + rs.getString(12));
            }

            rs.close();
            dbLog.log("resultset closed");
            st.close();
            dbLog.log("Statement closed");
            con_.close();
            dbLog.log("Connection closed");

        } catch (SQLException e) {
            dbLog.log(e);
            if (con_ != null) {
                try {
                    con_.close();
                    dbLog.log("Connection closed");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                    dbLog.log("Statement closed");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                    dbLog.log("resultset closed");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            dbLog.close();
        }

    }

}
