package com.pages.ufazerp.util.dto.users;

import com.pages.ufazerp.domain.BaseModel;
import com.pages.ufazerp.domain.User;

public class GetUserDto extends BaseModel {
    protected long id;
    protected String email;
    protected String firstName;
    protected String lastName;

    public GetUserDto(User user) {
        this.id = user.getUserId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
