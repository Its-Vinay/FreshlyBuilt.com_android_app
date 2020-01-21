package com.example.website.model;

public class Groups {
    String coverpic,profilepic,group_name,group_type;

    public Groups(String coverpic, String profilepic, String group_name, String group_type) {
        this.coverpic = coverpic;
        this.profilepic = profilepic;
        this.group_name = group_name;
        this.group_type = group_type;
    }

    public String getCoverpic() {
        return coverpic;
    }

    public void setCoverpic(String coverpic) {
        this.coverpic = coverpic;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_type() {
        return group_type;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }
}
