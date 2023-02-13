package vissoft.test.controller;

import vissoft.test.model.Contact;
import vissoft.test.model.User;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vissoft.test.service.ContactService;

@RestController
public class ContactController {
    
    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public List<Contact> getInfoContact() {
        return contactService.getInfoContact();
    }

    @RequestMapping(value = "/contactFr/{userID}", method = RequestMethod.GET)
    public List<User> getInfotoUser(@PathVariable("userID") int userID){
        return contactService.getContactFrUser(userID);
    }

    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public String createContact(@RequestBody Contact contact){
        return contactService.createContact(contact);
    } 
    @RequestMapping(value = "/removeContact", method = RequestMethod.DELETE)
    public String deleteContact(@RequestBody Contact contact){
        return contactService.deleteContact(contact);
    }

    @RequestMapping(value = "/removeAllContactOf/{userID}", method = RequestMethod.DELETE)
    public String deleteContactFrUser(@PathVariable("userID") int userID){
        return contactService.deleteContactFr(userID);
    }
}
