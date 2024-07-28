package org.tpo10.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import org.tpo10.Constraint.PasswordCheckDigits;
import org.tpo10.Constraint.PasswordCheckLowercase;
import org.tpo10.Constraint.PasswordCheckSpecial;
import org.tpo10.Constraint.PasswordCheckUppercase;

@Entity
public class Link {

    @Id
    private String id;
    @NotNull
    @Size(min = 5, max = 20)
    private String name;
    @PasswordCheckDigits()
    @PasswordCheckSpecial()
    @PasswordCheckUppercase()
    @PasswordCheckLowercase()
    private String password;
    @NotNull
    @URL(protocol = "https", message = "the only protocol accepted is https")
    private String targetUrl;

    private String redirectUrl;

    private int visits;

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

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }
}
