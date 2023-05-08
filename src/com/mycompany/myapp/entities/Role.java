package com.mycompany.myapp.entities;

/**
 *
 * @author ezine
 */
public class Role {
    private int idRole;
    private String description;

    
    //Constructors 
    public Role() {
    }

    public Role(int idRole, String description) {
        this.idRole = idRole;
        this.description = description;
    }

    public Role(String description) {
        this.description = description;
    }

    
    //getters & setters
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //show
    @Override
    public String toString() {
        return "Role{" + "idRole=" + idRole + ", description=" + description + '}';
    }
    
}
