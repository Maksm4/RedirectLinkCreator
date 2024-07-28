package org.tpo10.Models;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import org.tpo10.Constraint.PasswordCheckDigits;
import org.tpo10.Constraint.PasswordCheckLowercase;
import org.tpo10.Constraint.PasswordCheckSpecial;
import org.tpo10.Constraint.PasswordCheckUppercase;

import java.util.Random;

public class LinkDTO {
    private String id;

    @NotNull
    @Size(min = 5, max = 20)
    private String name;
    @PasswordCheckDigits()
    @PasswordCheckSpecial()
    @PasswordCheckUppercase()
    @PasswordCheckLowercase()
    private String password;

    @URL(protocol = "https", message = "the only protocol accepted is https")
    private String targetUrl;
    private String redirectUrl;
    private int visits;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public LinkDTO() {
        this.password = null;
        this.visits = 0;
    }


    public static String generateID(){
        Random random = new Random();
        StringBuilder randomID = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                int index = random.nextInt(CHARACTERS.length());
                randomID.append(CHARACTERS.charAt(index));
            }

        return String.valueOf(randomID);
    }


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public void setRedirectUrl(String targetUrl) {
        this.redirectUrl = "http://localhost:8080/red/" + getId();
    }

}
