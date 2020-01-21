package com.example.website.model;

public class About_us {
    private String aboutus, image, name, skills, college, linkedin;

    public About_us(String aboutus, String image, String name, String skills, String college, String linkedin) {
        this.aboutus = aboutus;
        this.image = image;
        this.name = name;
        this.skills = skills;
        this.college = college;
        this.linkedin = linkedin;
    }

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}


