package com.nw.edu.model;

import com.nw.edu.constants.Constants;
import com.nw.edu.constants.DBConstants;
import com.nw.edu.pojo.Category;
import com.nw.edu.util.DBConnection;
import com.nw.edu.util.ProcessLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home
 */
public class CategoryModel {
    public static List<Category> getAllCategories() {
        ProcessLog dbLog = new ProcessLog(Constants.DB_CONNECTION_LOG, Constants.LOG_FILE_EXTENSION);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List<Category> categoryList = new ArrayList<>();
            ps = DBConnection.getDBConnection(
                    dbLog, DBConstants.MYSQL_DRIVER,
                    DBConstants.DATABASE_URL,
                    DBConstants.DATABASE_USER,
                    DBConstants.DATABASE_PASSWORD).prepareStatement(DBConstants.CATEGORY_SELECT_QUERY);

            rs = ps.executeQuery();
            while (rs.next()) {
                Category cat = new Category();
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("name"));
                categoryList.add(cat);
            }
            return categoryList;
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
    
   public static Category getCategoryByName(String name) {
        ProcessLog dbLog = new ProcessLog(Constants.DB_CONNECTION_LOG, Constants.LOG_FILE_EXTENSION);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Category category = new Category();
            ps = DBConnection.getDBConnection(
                    dbLog, DBConstants.MYSQL_DRIVER,
                    DBConstants.DATABASE_URL,
                    DBConstants.DATABASE_USER,
                    DBConstants.DATABASE_PASSWORD).prepareStatement(DBConstants.CATEGORY_SELECT_QUERY_BY_NAME);
            ps.setString(1, name.trim().toLowerCase());
            rs = ps.executeQuery();
            while (rs.next()) {
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
            return category;
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
}
