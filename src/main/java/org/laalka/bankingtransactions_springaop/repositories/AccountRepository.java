package org.laalka.bankingtransactions_springaop.repositories;

import org.laalka.bankingtransactions_springaop.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//    @Modifying
//    @Query("UPDATE Account SET balance= :balance WHERE id= :id")
//    void updateBalance(@Param("balance")double balance, @Param("id")Long id);

}
