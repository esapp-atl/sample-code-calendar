package org.tinroof.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CalendarUser {

    @Id
    private String userName;

    private String displayName;

    public CalendarUser() {}

    public CalendarUser(String userName, String displayName) {
        this.userName = userName;
        this.displayName = displayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "CalendarUser{" +
                "userName='" + userName + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
