package io.lker.webstore.usermanagement.util.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id){
        super("Could not find user: " + id);
    }
}
