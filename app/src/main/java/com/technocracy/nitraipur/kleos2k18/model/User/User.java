package com.technocracy.nitraipur.kleos2k18.model.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.technocracy.nitraipur.kleos2k18.model.Message;

public class User extends Message {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("passwod")
    @Expose
    private String password;

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("college")
    @Expose
    private String college;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("profile")
    @Expose
    private String profile;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("otp")
    @Expose
    private int otp;



    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public User(String username, int otp){
        this.username = username;
        this.otp = otp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getToken(){ return token; }

    public void setToken(String token){ this.token = token; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }


}