package vissoft.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vissoft.test.model.Contact;
import vissoft.test.model.User;
import vissoft.test.repository.ContactRepository;
import vissoft.test.repository.UserRepository;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    UserRepository userRepository;

    // 1. Thêm một liên hệ trong danh sách theo cách trực tiếp
    public String createContact(Contact contact) {
        // Xem liên hệ đã tồn tại chưa ?
        if (checkContact(contact) == -1) {
            // Trong trường hợp chưa tồn tại ta kiểm tra xem 2 userID trong contact có tồn
            // tại không
            int fr_userID = contact.getFrom_UserID();
            int to_userID = contact.getTo_UserID();
            if (checkExistUser(fr_userID) != -1 && checkExistUser(to_userID) != -1) {
                contactRepository.save(contact);
                return "Created ";
            }
            return "Disinformation";
        }
        return "Had Been Created ";
    }

    // 2. lấy ra danh sách các contact hiện có
    public List<Contact> getInfoContact() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts;
    }

    // 3. Lấy ra danh sách user mà User có liên hệ đến
    public List<User> getContactFrUser(int fr_userID) {
        List<User> users = userRepository.findAll();
        List<User> info = new ArrayList<>();
        List<Contact> contacts = contactRepository.findAll();
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (fr_userID == contact.getFrom_UserID()) {
                int to_UserID = contact.getTo_UserID();
                int index = checkExistUser(to_UserID);
                info.add(users.get(index));
            }
        }
        return info;
    }

    // 4. Xoá một liên hệ trong danh sách
    public String deleteContact(Contact contact) {
        // kiểm tra tồn tại liên hệ
        int index = checkContact(contact);
        if (index != -1) {
            int contactID = contactRepository.findAll().get(index).getContactID();
            contactRepository.deleteById(contactID);
            return "Removed";
        }
        return "Disinformation";
    }

    // 5. Xoá toàn bộ danh sách liên hệ của user
    public String deleteContactFr(int fr_userID) {
        if (checkExistUser(fr_userID) != -1) {
            List<Contact> contacts = contactRepository.findAll();
            for (int i = 0; i < contacts.size(); i++) {
                Contact contact = contacts.get(i);
                if (fr_userID == contact.getFrom_UserID()) {
                    int contactID = contact.getContactID();
                    contactRepository.deleteById(contactID);
                }
            }
            return "DELETE CONTACT";
        }
        return "Disinformation";
    }

    // Function

    // kiểm tra contact tồn tại
    public int checkContact(Contact myContact) {
        List<Contact> contacts = contactRepository.findAll();
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getFrom_UserID() == myContact.getFrom_UserID()
                    && contact.getTo_UserID() == myContact.getTo_UserID()) {
                return i;
            }
        }
        return -1;
    }
    // Kiểm tra người dùng tồn tại 
    private int checkExistUser(int userID) {
        List<User> users = userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            if (userID == users.get(i).getUserID()) {
                return i;
            }
        }
        return -1;
    }
}
