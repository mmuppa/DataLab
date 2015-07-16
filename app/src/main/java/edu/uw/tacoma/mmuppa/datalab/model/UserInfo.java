package edu.uw.tacoma.mmuppa.datalab.model;

/**
 * Created by mmuppa on 7/16/15.
 */
public class UserInfo {
    private String mEmail;
    private String mPassword;

    public UserInfo(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "mEmail='" + mEmail + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}';
    }
}
