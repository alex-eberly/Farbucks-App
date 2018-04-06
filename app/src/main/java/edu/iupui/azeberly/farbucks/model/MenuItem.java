package edu.iupui.azeberly.farbucks.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by Alex on 4/4/2018.
 */

@Entity(nameInDb = "MENUITEM")
public class MenuItem {

    public static final long serialVersionUID = 42L;

    @Id
    private Long id;

    @NotNull
    private String category_id;

    private String name;

    private String menu_image;

    @Generated(hash = 870622004)
    public MenuItem(Long id, @NotNull String category_id, String name,
            String menu_image) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.menu_image = menu_image;
    }

    @Generated(hash = 1324140183)
    public MenuItem() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory_id() {
        return this.category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenu_image() {
        return this.menu_image;
    }

    public void setMenu_image(String menu_image) {
        this.menu_image = menu_image;
    }

}

