package com.example.model.msg;

import com.example.RandomUtil;

/**
 * Created by Heavenkenyn on 2017/3/23.
 */

public class Msg {
    protected String id;
    protected String content;
    protected String resourceId;

    public Msg(String id) {
        if (id != null)
            this.id = id;
        else
            this.id = RandomUtil.getRandomString(16);
    }

    public Msg(){
        this.id = RandomUtil.getRandomString(16);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id != null)
            this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
