/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.controller;

import cvcenter.service.StudentService;
import cvcenter.service.UsersService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Giang Tran Hoang
 * 
 * The controller that controls the login functions and the front pages,
 * Most of the functions are just redirecting, nothing much to see.
 */

@Controller
public class LoginController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    @Transactional
    public String showWelcome() {

        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "login";

    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {

        model.addAttribute("error", "true");
        return "login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {

        return "redirect:/welcome.htm";

    }
}
