package com.auth.beans.request;

public class UserRegistrationServiceRequest {



    private String browserName;
    private String ipAddress;
    private String deviceType;
    private String location;
    private String username;
    private String email;
    private String password;

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRegistrationResourceRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + maskPassword(password) + '\'' +
                ", browserName='" + browserName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    private String maskPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "***";
        }
        return "******";   // Always fully masked


    }

}
