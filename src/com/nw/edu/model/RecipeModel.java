package com.nw.edu.model;

import com.nw.edu.constants.Constants;
import com.nw.edu.constants.DBConstants;
import com.nw.edu.pojo.Recipe;
import com.nw.edu.util.ProcessLog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Home
 */
public class RecipeModel {
    
    static ProcessLog dbLog = new ProcessLog(Constants.DB_CONNECTION_LOG, Constants.LOG_FILE_EXTENSION);
    
    public static boolean createRecipe(Recipe recipe, Connection con, int userId, int categoryId) {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement(DBConstants.RECIPE_INSERT_QUERY);
            ps.setString(1, recipe.getTitle());
            ps.setInt(2, recipe.getNo_of_servings());
            ps.setString(3, recipe.getIs_gluten_free());
            ps.setFloat(4, recipe.getLevel());
            ps.setInt(5, recipe.getNo_of_ingredients());
            ps.setInt(6, categoryId);
            ps.setString(7, recipe.getDescription());
            ps.setBytes(8, recipe.getRecipe_picture());
            ps.setString(9, recipe.getRecipe_image_path());
            ps.setInt(10, userId);

            int recordInserted = ps.executeUpdate();
            return recordInserted > 0;
        } catch (SQLException e) {
            dbLog.log(e);
            return false;
        }finally{
            try {
                if( ps != null)
                    ps.close();
            } catch (SQLException ex) {
                dbLog.log(ex);
            }
            dbLog.close();
        }
    }  
    
    public static boolean updateRecipe(Recipe recipe, Connection con, int categoryId) {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement(DBConstants.RECIPE_UPDATE_QUERY);
            ps.setString(1, recipe.getTitle());
            ps.setInt(2, recipe.getNo_of_servings());
            ps.setString(3, recipe.getIs_gluten_free());
            ps.setFloat(4, recipe.getLevel());
            ps.setInt(5, recipe.getNo_of_ingredients());
            ps.setInt(6, categoryId);
            ps.setString(7, recipe.getDescription());
            ps.setBytes(8, recipe.getRecipe_picture());
            ps.setString(9, recipe.getRecipe_image_path());
            ps.setInt(10, recipe.getId());

            int recordInserted = ps.executeUpdate();
            return recordInserted > 0;
        } catch (SQLException e) {
            dbLog.log(e);
            return false;
        }finally{
            try {
                if( ps != null)
                    ps.close();
            } catch (SQLException ex) {
                dbLog.log(ex);
            }
            dbLog.close();
        }
    }    
    
    public static boolean deleteRecipe(Recipe recipe, Connection con) {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement(DBConstants.RECIPE_DELETE_QUERY);
            ps.setInt(1, recipe.getId());

            int recordInserted = ps.executeUpdate();
            return recordInserted > 0;
        } catch (SQLException e) {
            dbLog.log(e);
            return false;
        }finally{
            try {
                if( ps != null)
                    ps.close();
            } catch (SQLException ex) {
                dbLog.log(ex);
            }
            dbLog.close();
        }
    }        
}
