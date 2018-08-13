package com.application.chat.chatapplication.model;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.DataQueryBuilder;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Chat {
    private String ownerId;
    private String message;
    private String username;
    private String objectId;
    private Date created;
    private Date updated;

    public Chat(){

    }

    public Chat(Map map){
        this.ownerId = map.get("ownerId").toString();
        this.message = map.get("message").toString();
        this.username = map.get("username").toString();
        this.objectId = map.get("objectId").toString();
        this.created = new Date((long)map.get("created"));
        this.updated = (map.get("updated") != null) ? new Date((long)map.get("updated")) : null;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void saveAsync(AsyncCallback<Chat> callback)
    {
        Backendless.Data.of( Chat.class ).save( this, callback );
    }

    public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<Chat>> callback )
    {
        Backendless.Data.of( Chat.class ).find( queryBuilder, callback );
    }
}
