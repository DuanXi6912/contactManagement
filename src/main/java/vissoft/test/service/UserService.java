package vissoft.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vissoft.test.model.Contact;
import vissoft.test.model.Trade;
import vissoft.test.model.User;
import vissoft.test.repository.ContactRepository;
import vissoft.test.repository.TradeRepository;
import vissoft.test.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    TradeRepository tradeRepository;

    // 1. Lấy thông tin của tất cả user hiện tại
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    // 2. Lấy ra thông tin của user được chỉ định
    public User getUser(int userID) {
        int index = checkExist(userID);
        if (index != -1) {
            return userRepository.findAll().get(index);
        }
        return null;
    }

    // 3. Thêm mới một user với userID được tạo tự động
    public User addNewUser(User user) {
        if (checkExistbyNE(user)) {
            return null;
        }
        return userRepository.save(user);
    }

    // 4. Cập nhật thông tin mới của User (điều kiện là user đã tồn tại)
    public User updateUser(User user) {
        int userID = user.getUserID();
        if (checkExist(userID) != -1) {
            return userRepository.save(user);
        }
        return null;
    }

    // 5. Xoá một user
    public String removeUser(int userID) {
        if (checkExist(userID) != -1) {
            changeIDTrade(userID);
            deleteContactOfUser(userID);
            userRepository.deleteById(userID);
            return "Removed";
        }
        return "Disinformation";
    }

    // 6. Xoá toàn bộ user
    public String removeAll() {
        userRepository.deleteAll();
        tradeRepository.deleteAll();
        contactRepository.deleteAll();
        return "DELETE ALL";
    }

    // Function
    // Hàm kiểm tra tồn tại bằng userID và trả về vị trí (nếu có)
    public int checkExist(int userID) {
        List<User> users = userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID() == userID) {
                return i;
            }
        }
        return -1;
    }

    // Hàm kiểm tra tồn tại bằng Email trong trường hợp "muốn thêm mới" : Ta quy ước
    // một email cho một user
    public boolean checkExistbyNE(User user) {
        List<User> users = userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserEmail().equals(user.getUserEmail())) {
                return true;
            }
        }
        return false;
    }

    // Chuyển userID của user (bị xoá) trong các trade thành 0 : unknow
    private void changeIDTrade(int userID) {
        List<Trade> trades = tradeRepository.findAll();
        for (int i = 0; i < trades.size(); i++) {
            Trade trade = trades.get(i);
            int fr_ID = trade.getFrom_UserID();
            if (fr_ID == userID) {
                trade.setFrom_UserID(0);
                tradeRepository.save(trade);
            }
            int to_ID = trade.getTo_UserID();
            if (to_ID == userID) {
                trade.setTo_UserID(0);
                tradeRepository.save(trade);
            }
        }
    }

    // Khi xoá một user thì đồng thời danh sách liên hệ của user cũng như các liên
    // hệ của những user khác tới user này sẽ bị xoá theo
    private void deleteContactOfUser(int userID) {
        List<Contact> contacts = contactRepository.findAll();
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getTo_UserID() == userID || contact.getFrom_UserID() == userID) {
                contactRepository.deleteById(contact.getContactID());
            }
        }
    }
}
