package io.incondensable.auth.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author abbas
 */
@Entity(name = "t_user")
@Table
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;

    //for simplicity, instead of a Collection of Role type, I'd prefer using String
    private String role;

    //the better solution, for sure, is to separate Token into a concrete Table and Entity.
    private String token;

}
