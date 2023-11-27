package com.skilldistillery.film.entities;

// Category class representing a film category entity
public class Category {

    // Fields
    private int id;        // Unique identifier for the category
    private String category; // Name of the category

    // Constructors
    public Category() {
        // Default constructor
    }

    public Category(int id, String category) {
        this.id = id;
        this.category = category;
    }

    // Getter and Setter methods for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // HashCode and Equals methods for comparing Category objects
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (id != other.id)
            return false;
        return true;
    }

    // ToString method for creating a string representation of the Category object
    @Override
    public String toString() {
        return "Category [id=" + id + ", category=" + category + "]";
    }
}
