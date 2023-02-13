package vissoft.test.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import vissoft.test.model.Trade;
import vissoft.test.service.TradeService;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class TradeController {
    @Autowired
    TradeService tradeService;

    @RequestMapping(value = "/trades", method = RequestMethod.GET)
    public List<Trade> getAllTrade() {
        return tradeService.getAllTrade();
    }

    @RequestMapping(value = "/tradefr/{from_UserID}", method = RequestMethod.GET)
    public List<Trade> getTradeFromUserID(@PathVariable("from_UserID") int from_UserID) {
        return tradeService.getTradeFromUserID(from_UserID);
    }

    @RequestMapping(value = "tradeto/{to_UserID}", method = RequestMethod.GET)
    public List<Trade> getTradetoUserID(@PathVariable("to_UserID") int to_UserID) {
        return tradeService.getTradeToUserID(to_UserID);
    }

    @RequestMapping(value = "/newTrade", method = RequestMethod.POST)
    public Trade createTrade(@RequestBody Trade trade) {
        return tradeService.createTrade(trade);
    }

}
