package com.nw.edu.model;

import com.nw.edu.constants.Constants;
import com.nw.edu.constants.DBConstants;
import static com.nw.edu.model.RecipeModel.dbLog;
import com.nw.edu.pojo.ChefUser;
import com.nw.edu.util.DBConnection;
import com.nw.edu.util.ProcessLog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Home
 */
public class ChefUserModel {
    
    private boolean userExist = false;
    
    public boolean isUserExist() {
        return userExist;
    }

    public void setUserExist(boolean userExist) {
        this.userExist = userExist;
    }
    
    public static boolean createChefUser(ChefUser user, Connection con) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(DBConstants.CHEF_INSERT_QUERY);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirst_name());
            ps.setString(3, user.getLast_name());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone_number());
            int recordInserted = ps.executeUpdate();
            ps.close();
            return recordInserted > 0;
        } catch (SQLException e) {
            dbLog.log(e);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                dbLog.log(ex);
            }
            dbLog.close();
        }
    }

    public static ChefUser getChefUserByUsername(String userName) {
        ProcessLog dbLog = new ProcessLog(Constants.DB_CONNECTION_LOG, Constants.LOG_FILE_EXTENSION);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ChefUser user = new ChefUser();
            ChefUserModel model = new ChefUserModel();
            ps = DBConnection.getDBConnection(
                    dbLog, DBConstants.MYSQL_DRIVER,
                    DBConstants.DATABASE_URL,
                    DBConstants.DATABASE_USER,
                    DBConstants.DATABASE_PASSWORD).prepareStatement(DBConstants.CHEF_SELECT_QUERY_BY_USERNAME);
            ps.setString(1, userName.trim().toLowerCase());
            rs = ps.executeQuery();
            while (rs.next()) {
                model.setUserExist(true);
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone_number(rs.getString("phone_number"));
            }
            return user;
        } catch (SQLException e) {
            dbLog.log(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                dbLog.log(ex);
            }
            dbLog.close();
        }
    }

        public static boolean updateChefUser(ChefUser user, Connection con) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(DBConstants.CHEF_UPDATE_QUERY);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPhone_number());
            ps.setInt(3, user.getId());
            int recordInserted = ps.executeUpdate();
            return recordInserted > 0;
        } catch (SQLException e) {
            dbLog.log(e);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                dbLog.log(ex);
            }
            dbLog.close();
        }
    }
    
    public static boolean deleteChefUser(ChefUser user, Connection con) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(DBConstants.CHEF_DELETE_QUERY);
            ps.setInt(1, user.getId());
            int userDeleted = ps.executeUpdate();
            return userDeleted > 0;
        } catch (SQLException e) {
            dbLog.log(e);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                dbLog.log(ex);
            }
            dbLog.close();
        }
    }  
}
