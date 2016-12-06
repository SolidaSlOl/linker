package org.linker.service;

import org.linker.model.domain.User;

public interface SecurityService {
    /**
     * Find currently logged in user by username
     * @return Username
     */
    String findLoggedInUsername();

    /**
     * Autologin user
     * @param username Username
     * @param password Password
     */
    void autologin(String username, String password);

    /**
     * Find currently logged in user
     * @return User
     */
    User findLoggedInUser();
}
