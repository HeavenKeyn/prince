package com.example.model.user;

import java.io.Serializable;

/**
 * Created by Heavenkenyn on 2017/3/23.
 */

public class User implements Serializable {
    protected String id;
    protected String name;
    protected String portrait;
    protected long createTime;

    public User(String id) {
        this.id = id;
    }

    protected User(){}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setName(String name) {
        if (name != null)
            this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
