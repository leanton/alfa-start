package me.antonle.alfastart.accounts.controller;

import me.antonle.alfastart.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;


}