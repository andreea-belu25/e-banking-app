package org.poo.cb.Commands;

import org.poo.cb.UserService;

public class CommandBuyStocks extends Command {
    public void execute(String[] params) {
        UserService userService = UserService.InstantaUserService();
        userService.splitElementsBuyStocks(params);
    }
}
