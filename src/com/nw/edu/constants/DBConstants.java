package com.nw.edu.constants;

/**
 *
 * @author Home
 */
public class DBConstants {
    
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE_NAME = "mrudhuladb";
    public static final String DATABASE_PORT = "3306";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:" + DATABASE_PORT + "/" + DATABASE_NAME;
    public static final String DATABASE_USER = "root";
    public static final String DATABASE_PASSWORD = "kottiakhil1969$";

    public static final String CATEGORY_SELECT_QUERY = "select * from category";
    public static final String CHEF_RECIPE_CATEGORY_SELECT_QUERY = "SELECT chef_.id as chef_id, chef_.username,chef_.first_name,chef_.last_name,chef_.email,chef_.phone_number,\r\n"
                    + "       recipe_.id as recipe_id,recipe_.title,recipe_.no_of_servings,recipe_.is_gluten_free,recipe_.level difficulty_level,\r\n"
                    + "       recipe_.no_of_ingredients,cat_.name category_name,recipe_.description,recipe_.recipe_picture,recipe_.recipe_image_path\r\n"
                    + "       FROM mrudhuladb.chef chef_,mrudhuladb.recipe recipe_,mrudhuladb.category cat_\r\n"
                    + "where chef_.id = recipe_.chef_id\r\n" + "and  cat_.id = recipe_.category_id\r\n"
                    + "order by chef_.username";
    public static final String CATEGORY_SELECT_QUERY_BY_NAME = "select * from category where name=?";
    
    public static final String CHEF_INSERT_QUERY = "insert into chef(username,first_name,last_name,email,"
            + "phone_number) values(?,?,?,?,?)";
    public static final String CHEF_SELECT_QUERY_BY_USERNAME = "select * from chef where username=?";    
    public static final String CHEF_UPDATE_QUERY = "update chef set email=?,phone_number=? where id=?"; 
    public static final String CHEF_DELETE_QUERY = "delete from chef where id=?"; 
    
    public static final String RECIPE_INSERT_QUERY = "insert into recipe(title,no_of_servings,is_gluten_free,"
            + "level,no_of_ingredients,category_id,description,recipe_picture,recipe_image_path,chef_id) values(?,?,?,?,?,?,?,?,?,?)";
    public static final String RECIPE_UPDATE_QUERY = "update recipe set title=?,no_of_servings=?,is_gluten_free=?,"
            + "level=?,no_of_ingredients=?,category_id=?,description=?,recipe_picture=?,recipe_image_path=? where id = ?";
    public static final String RECIPE_DELETE_QUERY = "delete from recipe where id=?";    
    
}
