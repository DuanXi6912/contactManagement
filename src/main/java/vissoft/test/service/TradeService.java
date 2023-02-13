package vissoft.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vissoft.test.model.Trade;
import vissoft.test.model.Contact;
import vissoft.test.model.User;
import vissoft.test.repository.TradeRepository;
import vissoft.test.repository.UserRepository;
import vissoft.test.repository.ContactRepository;

@Service
public class TradeService {
    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactRepository contactRepository;

    // 1. Lấy ra tất cả các giao dịch
    public List<Trade> getAllTrade() {
        List<Trade> trades = tradeRepository.findAll();
        return trades;
    }

    // 2. Tiền ra của một User | danh sách các giao dịch mà người gửi là user đó
    public List<Trade> getTradeFromUserID(int from_UserID) {
        List<Trade> trades = new ArrayList<Trade>();
        for (int i = 0; i < getAllTrade().size(); i++) {
            Trade tradeFr = getAllTrade().get(i);
            if (from_UserID == tradeFr.getFrom_UserID()) {
                trades.add(tradeFr);
            }
        }
        return trades;
    }

    // 3. Tiền vào của một User | danh sách các giao dịch mà người nhận là user đó
    public List<Trade> getTradeToUserID(int to_UserID) {
        List<Trade> trades = new ArrayList<Trade>();
        for (int i = 0; i < getAllTrade().size(); i++) {
            Trade tradeTo = getAllTrade().get(i);
            if (to_UserID == tradeTo.getTo_UserID()) {
                trades.add(tradeTo);
            }
        }
        return trades;
    }

    // 4. Tạo mới một giao dịch
    public Trade createTrade(Trade trade) {
        int fr_ID = trade.getFrom_UserID();
        int to_ID = trade.getTo_UserID();
        int amount = trade.getTrade_Amount();
        if (checkExistUser(to_ID) != -1 && checkBalanceUser(fr_ID, amount)) {
            updateContact(fr_ID, to_ID);
            updateBalance(fr_ID, to_ID, amount);
            return tradeRepository.save(trade);
        }
        return null;
    }

    // function
    // tồn tại người gửi và số dư đủ để thực hiện giao dịch
    private boolean checkBalanceUser(int userID, int amount) {
        int index = checkExistUser(userID);
        if (index != -1) {
            User user = userRepository.findAll().get(index);
            if (user.getUserBalance() >= amount) {
                return true;
            }
        }
        return false;
    }

    // Kiểm tra tồn tại người dùng
    private int checkExistUser(int userID) {
        List<User> users = userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUserID() == userID) {
                return i;
            }
        }
        return -1;
    }

    // Cập nhật số dư khi giao dịch thành công
    private void updateBalance(int fr_ID, int to_ID, int amount) {
        int indexFr = checkExistUser(fr_ID);
        User userFr = userRepository.findAll().get(indexFr);
        userFr.setUserBalance(userFr.getUserBalance() - amount);
        userRepository.save(userFr);
        int indexTo = checkExistUser(to_ID);
        User userTo = userRepository.findAll().get(indexTo);
        userTo.setUserBalance(userFr.getUserBalance() + amount);
        userRepository.save(userTo);
    }

    // cập nhật thêm một liên hệ khi giao dịch thành công
    private void updateContact(int fr_ID, int to_ID) {
        if (!checkExistContact(fr_ID, to_ID)) {
            Contact contact = new Contact(fr_ID, to_ID);
            contactRepository.save(contact);
        }
    }

    // Kiểm tra xem liên hệ đã tồn tại chưa
    private boolean checkExistContact(int fr_ID, int to_ID) {
        List<Contact> contacts = contactRepository.findAll();
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (fr_ID == contact.getFrom_UserID() && to_ID == contact.getTo_UserID()) {
                return true;
            }
        }
        return false;
    }
}
