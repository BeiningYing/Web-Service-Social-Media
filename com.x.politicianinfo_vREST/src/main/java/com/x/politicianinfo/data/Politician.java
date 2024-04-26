package com.x.politicianinfo.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Politician {
    private String id;
    private String name;
    private String socialMediaUrl;
    private String post; // Added this new field
    private String PartyId;

    // Default constructor
    public Politician() {}

    // Constructor with name
    public Politician(String name) {
        this.name = name;
    }

    // Full constructor with all attributes including the new 'post' field
    public Politician(String id, String name, String socialMediaUrl, String post, String PartyId){
        this.id = id;
        this.name = name;
        this.socialMediaUrl = socialMediaUrl;
        this.post = post;
        this.PartyId = PartyId; 
    }

    // Getters and setters for all fields including the new 'post' field
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialMediaUrl() {
        return socialMediaUrl;
    }

    public void setSocialMediaUrl(String socialMediaUrl) {
        this.socialMediaUrl = socialMediaUrl;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPartyId() {
        return PartyId;
    }

    public void setPartyId(String PartyId) {
        this.PartyId = PartyId;
    }

    // Overridden toString method
    @Override
    public String toString() {
        return "Politician{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", socialMediaUrl='" + socialMediaUrl + '\'' +
                ", post='" + post + '\'' +
                ", PartyId='" + PartyId + '\'' +
                '}';
    }
}
