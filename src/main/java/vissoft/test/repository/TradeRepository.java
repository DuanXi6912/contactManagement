package vissoft.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vissoft.test.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer>{
    
}
