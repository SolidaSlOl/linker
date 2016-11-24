package org.linker.service;

import org.linker.model.domain.User;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);

    User findLoggedInUser();
}
