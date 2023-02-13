package vissoft.test.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "trade")
@Data
public class Trade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Trade_ID")
    private int tradeID;

    public int getTradeID() {
        return this.tradeID;
    }

    public void setTradeID(int tradeID) {
        this.tradeID = tradeID;
    }

    @Column(name = "from_UserID")
    private int from_UserID;

    public int getFrom_UserID() {
        return this.from_UserID;
    }

    public void setFrom_UserID(int from_UserID) {
        this.from_UserID = from_UserID;
    }
  
    @Column(name = "to_UserID")
    private int to_UserID;

    public int getTo_UserID() {
        return this.to_UserID;
    }

    public void setTo_UserID(int to_UserID) {
        this.to_UserID = to_UserID;
    }

    @Column(name = "trade_Amount")
    private int trade_Amount;

    public int getTrade_Amount() {
        return this.trade_Amount;
    }

    public void setTrade_Amount(int Trade_Amount) {
        this.trade_Amount = Trade_Amount;
    }
   
    public Trade(){

    }
    public Trade(int tradeID, int from_UserID, int to_UserID, int trade_Amount) {
        this.tradeID = tradeID;
        this.from_UserID = from_UserID;
        this.to_UserID = to_UserID;
        this.trade_Amount = trade_Amount;
    }

}
