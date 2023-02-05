package com.nw.edu.pojo;

public class Recipe {

    private int id;
    private String title;
    private int no_of_servings;
    private String is_gluten_free;
    private float level;
    private int no_of_ingredients;
    private int category_id;
    private String description;
    private byte[] recipe_picture;
    private String recipe_image_path;
    private int chef_id;

    /**
     * @return the id
     */
    public final int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public final String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @return the no_of_servings
     */
    public final int getNo_of_servings() {
        return no_of_servings;
    }

    /**
     * @param no_of_servings the no_of_servings to set
     */
    public void setNo_of_servings(final int no_of_servings) {
        this.no_of_servings = no_of_servings;
    }

    /**
     * @return the is_gluten_free
     */
    public final String getIs_gluten_free() {
        return is_gluten_free;
    }

    /**
     * @param is_gluten_free the is_gluten_free to set
     */
    public void setIs_gluten_free(final String is_gluten_free) {
        this.is_gluten_free = is_gluten_free;
    }

    /**
     * @return the level
     */
    public final float getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(final float level) {
        this.level = level;
    }

    /**
     * @return the no_of_ingredients
     */
    public final int getNo_of_ingredients() {
        return no_of_ingredients;
    }

    /**
     * @param no_of_ingredients the no_of_ingredients to set
     */
    public void setNo_of_ingredients(final int no_of_ingredients) {
        this.no_of_ingredients = no_of_ingredients;
    }

    /**
     * @return the category_id
     */
    public final int getCategory_id() {
        return category_id;
    }

    /**
     * @param category_id the category_id to set
     */
    public void setCategory_id(final int category_id) {
        this.category_id = category_id;
    }

    /**
     * @return the description
     */
    public final String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the recipe_picture
     */
    public final byte[] getRecipe_picture() {
        return recipe_picture;
    }

    /**
     * @param recipe_picture the recipe_picture to set
     */
    public void setRecipe_picture(final byte[] recipe_picture) {
        this.recipe_picture = recipe_picture;
    } 

    /**
     * @return the recipe_image_path
     */    
    public String getRecipe_image_path() {
        return recipe_image_path;
    }

    /**
     * @param recipe_image_path the recipe_image_path to set
     */    
    public void setRecipe_image_path(String recipe_image_path) {
        this.recipe_image_path = recipe_image_path;
    }

    
    /**
     * @return the chef_id
     */
    public final int getChef_id() {
        return chef_id;
    }

    /**
     * @param chef_id the chef_id to set
     */
    public void setChef_id(final int chef_id) {
        this.chef_id = chef_id;
    }

}
