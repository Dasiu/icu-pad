package com.icupad.domain.user;

import com.icupad.domain.BaseEntity;
import com.icupad.domain.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class User extends BaseEntity {
    @Id
    @NotNull
    @Column(length = 50)
    private String login;

    @NotNull
    @Column(length = 60)
    private String password;

    @NotNull
    private Boolean enabled;

    @OneToMany(mappedBy = "user")
    private List<Role> roles = new ArrayList<>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
