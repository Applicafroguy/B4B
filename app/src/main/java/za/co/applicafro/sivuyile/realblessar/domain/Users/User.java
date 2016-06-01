package za.co.applicafro.sivuyile.realblessar.domain.Users;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    // variables
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private String gender;
    private int phone;

    private User(){}

    // begin getters

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    // end getters

    // init from builder
    public User(Builder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.birthDate = builder.birthDate;
        this.gender = builder.gender;
        this.phone = builder.phone;
    }

    // builder class
    public static class Builder{
        //variables
        private long id;
        private String firstName;
        private String lastName;
        private String email;
        private String birthDate;
        private String gender;
        private int phone;

        public Builder id(long value){
            this.id = value;
            return this;
        }

        public Builder firstName(String value){
            this.firstName = value;
            return this;
        }

        public Builder lastName(String value){
            this.lastName = value;
            return this;
        }

        public Builder birthDate(String value){
            this.birthDate = value;
            return this;
        }

        public Builder gender(String value){
            this.gender = value;
            return this;
        }

        public Builder email(String value){
            this.email = value;
            return this;
        }

        public Builder phone(int value){
            this.phone = value;
            return this;
        }

        public Builder copy(User value){
            this.id = value.id;
            this.firstName = value.firstName;
            this.lastName = value.lastName;
            this.email = value.email;
            this.birthDate = value.birthDate;
            this.gender = value.gender;
            this.phone = value.phone;

            return  this;
        }

        public User build(){
            return new User();
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Builder builder = (Builder) o;

            return id == builder.id;

        }

        @Override
        public int hashCode() {
            return (int) (id ^ (id >>> 32));
        }
    }
}

























