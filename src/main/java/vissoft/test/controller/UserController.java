package vissoft.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import vissoft.test.model.User;
import vissoft.test.service.UserService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @RequestMapping(value = "/user/{userID}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userID") int userID) {
        return userService.getUser(userID);
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public User addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/removeUser/{userID}", method = RequestMethod.DELETE)
    public String removeUser(@PathVariable("userID") int userID){
        return userService.removeUser(userID);
    }
    @RequestMapping(value = "/removeAll", method = RequestMethod.DELETE)
    public String removeAll(){
        return userService.removeAll();
    }
}
