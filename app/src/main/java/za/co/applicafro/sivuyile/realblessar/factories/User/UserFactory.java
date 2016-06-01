package za.co.applicafro.sivuyile.realblessar.factories.User;

import za.co.applicafro.sivuyile.realblessar.domain.Users.User;

/**
 * Created by sivuyile on 6/1/16.
 */
public class UserFactory {
    public static User getUser(String email, String firtname,String lastname,String gender,String birthdate, int phone){
        return new User.Builder()
                .gender(gender)
                .phone(phone)
                .birthDate(birthdate)
                .email(email)
                .firstName(firtname)
                .lastName(lastname)
                .build();
    }
}
