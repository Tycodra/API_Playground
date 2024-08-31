package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String id;
    private String first_name;
    private String last_name;
    private String email;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public final String toString() {
        return String.format("User [id=%s, first_name=%s, last_name=%s, email=%s]",
                id, first_name, last_name, email);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        return id.equals(user.id) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Objects.hashCode(first_name);
        result = 31 * result + Objects.hashCode(last_name);
        result = 31 * result + Objects.hashCode(email);
        return result;
    }


    public static final class UserBuilder {
        private String id;
        private String firstName;
        private String lastName;
        private String email;

        public UserBuilder() {
        }

        public UserBuilder(User other) {
            this.id = other.id;
            this.firstName = other.first_name;
            this.lastName = other.last_name;
            this.email = other.email;
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setFirst_name(firstName);
            user.setLast_name(lastName);
            user.setEmail(email);
            return user;
        }
    }
}
