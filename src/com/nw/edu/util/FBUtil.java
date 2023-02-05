package com.nw.edu.util;

import com.nw.edu.constants.Constants;
import com.nw.edu.constants.DBConstants;
import com.nw.edu.model.CategoryModel;
import com.nw.edu.model.ChefUserModel;
import com.nw.edu.model.RecipeModel;
import com.nw.edu.pojo.Category;
import com.nw.edu.pojo.ChefUser;
import com.nw.edu.pojo.Recipe;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Home
 */
public class FBUtil {

    ProcessLog fbLog = null;
    ProcessLog dbLog = null;

    private ChefUser user = null;
    private Category category = null;
    private Recipe recipe = null;

    public FBUtil() {
        fbLog = new ProcessLog(Constants.FOOD_BLOGGER_LOG, Constants.LOG_FILE_EXTENSION);
        dbLog = new ProcessLog(Constants.DB_CONNECTION_LOG, Constants.LOG_FILE_EXTENSION);

    }

    public static void loadCategories(JComboBox<String> categoryCombo) {
        List<Category> categoryList = CategoryModel.getAllCategories();
        categoryList.forEach((e) -> {
            categoryCombo.addItem(e.getName());
        });
    }

    public void populateTable(JTable tbl) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
            tblModel.setRowCount(0);
            con = DBConnection.getDBConnection(
                    dbLog, DBConstants.MYSQL_DRIVER,
                    DBConstants.DATABASE_URL,
                    DBConstants.DATABASE_USER,
                    DBConstants.DATABASE_PASSWORD);
            ps = con.prepareStatement(DBConstants.CHEF_RECIPE_CATEGORY_SELECT_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] objArray = {rs.getString("chef_id"), rs.getString("username"), rs.getString("first_name"),
                     rs.getString("last_name"), rs.getString("email"), rs.getString("phone_number"),
                     rs.getString("recipe_id"), rs.getString("title"), rs.getInt("no_of_servings"), rs.getString("is_gluten_free"),
                     rs.getFloat("difficulty_level"), rs.getInt("no_of_ingredients"), rs.getString("category_name"),
                     rs.getString("description"), rs.getString("recipe_image_path")};
                tblModel.addRow(objArray);
            }
        } catch (SQLException e) {
            dbLog.log(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                dbLog.log(ex);
            }
            dbLog.close();
        }
    }

    public void setUser(int chefid, JTextField userName, JTextField firstName, JTextField lastName,
            JTextField email, JTextField phoneNo) {
        user = new ChefUser();
        user.setId(chefid);
        user.setUsername(userName.getText());
        user.setFirst_name(firstName.getText());
        user.setLast_name(lastName.getText());
        user.setEmail(email.getText());
        user.setPhone_number(phoneNo.getText());

    }

    public void setCategory(JComboBox categoryName) {
        category = new Category();
        category.setName(categoryName.getSelectedItem().toString());
    }

    public void setRecipe(int recipeid, JTextField title, JTextField servings, JComboBox glutenFree,
            JComboBox level, JTextField ingredients, JTextArea description, String imgFileName) {
        recipe = new Recipe();
        recipe.setId(recipeid);
        recipe.setTitle(title.getText());
        recipe.setNo_of_servings(Integer.parseInt(servings.getText()));
        recipe.setIs_gluten_free(glutenFree.getSelectedItem().toString());
        recipe.setLevel(Float.parseFloat(level.getSelectedItem().toString()));
        recipe.setNo_of_ingredients(Integer.parseInt(ingredients.getText()));
        recipe.setDescription(description.getText());
        recipe.setRecipe_image_path(imgFileName);
        recipe.setRecipe_picture(getImageStream(imgFileName));
    }

    public byte[] getImageStream(final String imgFileName) {
        try {
            File imgFile = new File(imgFileName);
            FileInputStream fis = new FileInputStream(imgFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int bytesRead; (bytesRead = fis.read(buffer)) != -1;) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException ex) {
            fbLog.log(ex);
            return null;
        } catch (IOException ex) {
            fbLog.log(ex);
            return null;
        }
    }

    public boolean saveAll() {
        Connection con = DBConnection.getDBConnection(
                dbLog, DBConstants.MYSQL_DRIVER,
                DBConstants.DATABASE_URL,
                DBConstants.DATABASE_USER,
                DBConstants.DATABASE_PASSWORD);
        try {
//            System.out.println("1User name:" + user.getUsername());
            ChefUserModel.createChefUser(user, con);

//            user1 = ChefUserModel.getChefUserByUsername(user.getUsername());
//            System.out.println("2User id:" + user1.getId() + "-" + "User name:" + user1.getUsername());
//            category1 = CategoryModel.getCategoryByName(category.getName());
//            System.out.println("category id:" + category1.getId());
            if (ChefUserModel.getChefUserByUsername(user.getUsername()).getId() > 0) {
                return RecipeModel.createRecipe(recipe, con, ChefUserModel.getChefUserByUsername(user.getUsername()).getId(), CategoryModel.getCategoryByName(category.getName()).getId());
            } else {
                return false;
            }

        } catch (Exception ex) {
            dbLog.log(ex);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex1) {
                dbLog.log(ex1);
            }
            dbLog.close();
            fbLog = null;
            dbLog = null;
            user = null;
            category = null;
            recipe = null;

        }
    }

    public boolean update() {
        Connection con = DBConnection.getDBConnection(
                dbLog, DBConstants.MYSQL_DRIVER,
                DBConstants.DATABASE_URL,
                DBConstants.DATABASE_USER,
                DBConstants.DATABASE_PASSWORD);
        try {
            ChefUserModel.updateChefUser(user, con);

            System.out.println("category id:" + CategoryModel.getCategoryByName(category.getName()).getId());

            return RecipeModel.updateRecipe(recipe, con, CategoryModel.getCategoryByName(category.getName()).getId());

        } catch (Exception ex) {
            dbLog.log(ex);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex1) {
                dbLog.log(ex1);
            }
            dbLog.close();
            fbLog = null;
            dbLog = null;
            user = null;
            category = null;
            recipe = null;

        }
    }

    public boolean delete() {
        Connection con = DBConnection.getDBConnection(
                dbLog, DBConstants.MYSQL_DRIVER,
                DBConstants.DATABASE_URL,
                DBConstants.DATABASE_USER,
                DBConstants.DATABASE_PASSWORD);
        try {
            con.setAutoCommit(false);
            boolean recipeDeleted = RecipeModel.deleteRecipe(recipe, con);            
            //ChefUserModel.deleteChefUser(user, con);
            dbLog.log("Recipe Deleted:"+recipeDeleted);
            con.setAutoCommit(true);
            return recipeDeleted;
        } catch (SQLException ex) {
            dbLog.log(ex);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex1) {
                dbLog.log(ex1);
            }
            dbLog.close();
            fbLog = null;
            dbLog = null;
            user = null;
            category = null;
            recipe = null;

        }
    }
}
