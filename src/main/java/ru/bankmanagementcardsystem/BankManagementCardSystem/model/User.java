package ru.bankmanagementcardsystem.BankManagementCardSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NotBlank
    @Size(max = 30)
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "card",
            joinColumns = {@JoinColumn(name = "client", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "card", referencedColumnName = "id")})
    private Set<Card> cards;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "roles",
            joinColumns = {@JoinColumn(name = "client", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "roles", referencedColumnName = "id")})
    private Set<Role> roleName;

    public User(UserBuilder userBuilder) {
        this.name = userBuilder.name;
        this.password = userBuilder.password;
        this.cards = userBuilder.cards;
        this.roleName = userBuilder.roleName;
        this.id = (long) (Math.random() * Long.MAX_VALUE);
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoleName() {
        return roleName;
    }

    public Long getId() {
        return id;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static UserBuilder builder(User user){
        return new UserBuilder().from(user);
    }

    public static class UserBuilder {

        private String name;
        private String password;
        private Set<Card> cards;
        private Set<Role> roleName;

        public UserBuilder from(User user){
            this.name = user.getName();
            this.password = user.getPassword();
            this.cards = user.getCards();
            this.roleName = user.getRoleName();
            return this;
        }

        public UserBuilder name(String name){
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder cards(Set<Card> cards){
            this.cards = cards;
            return this;
        }

        public UserBuilder roleName(Set<Role> roleName){
            this.roleName = roleName;
            return this;
        }

        public UserDetails build(){
            return new User(this);
        }
    }
}
