package idv.ericlee.cathaybktest.repository;

import idv.ericlee.cathaybktest.model.dao.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency,String> {
}
