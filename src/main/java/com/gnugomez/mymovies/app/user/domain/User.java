package com.gnugomez.mymovies.app.user.domain;

import com.gnugomez.mymovies.app.user.infrastructure.auth.UsernamePasswordPrincipal;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Id
    private String username;

    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "account_enabled")
    private boolean accountEnabled;

    @Column(name = "account_expired")
    private boolean accountExpired;

    @Column(name = "account_locked")
    private boolean accountLocked;

    @Column(name = "credentials_expired")
    private boolean credentialsExpired;


    public UsernamePasswordPrincipal toUsernamePasswordPrincipal() {
        return new UsernamePasswordPrincipal(id, username, password, firstName, lastName, userRole, accountEnabled, accountExpired, accountLocked, credentialsExpired);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountEnabled=" + accountEnabled +
                ", accountExpired=" + accountExpired +
                ", accountLocked=" + accountLocked +
                ", credentialsExpired=" + credentialsExpired +
                ", userRole='" + userRole + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }
}
