package com.lemoncash.wallet.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class UserDTO {

    @NotNull
    @NotEmpty
    @JsonProperty("firstName")
    private String firstName;

    @NotNull
    @NotEmpty
    @JsonProperty("lastName")
    private String lastName;

    @NotNull
    @NotEmpty
    @JsonProperty("username")
    private String username;

    @NotNull
    @NotEmpty
    @JsonProperty("password")
    private String password;

    @Email
    @NotNull
    @NotEmpty
    @JsonProperty("email")
    private String email;

}
