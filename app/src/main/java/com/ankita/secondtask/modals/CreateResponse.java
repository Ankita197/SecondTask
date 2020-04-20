package com.ankita.secondtask.modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CreateResponse {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public Object message;
    @SerializedName("data")
    @Expose
    public UserData data;

    public class UserData {
        @SerializedName("users")
        public List<User> userArrayList;

        @SerializedName("has_more")
        public boolean hasMore;

    }

    public class User {
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("items")
        @Expose
        public List<String> items = null;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }
    }
}
