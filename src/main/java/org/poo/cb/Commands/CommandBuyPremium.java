package org.poo.cb.Commands;

import org.poo.cb.UserService;

public class CommandBuyPremium extends Command {
    public void execute(String[] params) {
        UserService userService = UserService.InstantaUserService();
        userService.splitElementsBuyPremium(params);
    }
}
