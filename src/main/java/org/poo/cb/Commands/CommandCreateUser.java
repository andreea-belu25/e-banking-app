package org.poo.cb.Commands;

import org.poo.cb.UserService;

public class CommandCreateUser extends Command {
    public void execute(String[] params) {
        UserService userService = UserService.InstantaUserService();
        userService.splitElementsCreateUser(params);
    }
}
