package org.poo.cb.Commands;

import org.poo.cb.UserService;

public class CommandListUser extends Command {
    public void execute(String[] params) {
        UserService userService = UserService.InstantaUserService();
        userService.splitElementsListUser(params);
    }
}
