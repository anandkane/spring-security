package com.ssai.handsonch11.authserver.user.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otps")
public class OtpEntity {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "otp")
    private String otp;

    public OtpEntity(String username, String otp) {
        this.username = username;
        this.otp = otp;
    }

    public OtpEntity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "OtpEntity{" +
                "username='" + username + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}
