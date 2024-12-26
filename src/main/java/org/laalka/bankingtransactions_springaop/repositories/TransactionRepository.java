package org.laalka.bankingtransactions_springaop.repositories;

import org.laalka.bankingtransactions_springaop.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
