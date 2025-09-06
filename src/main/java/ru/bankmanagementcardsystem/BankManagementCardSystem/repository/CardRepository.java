package ru.bankmanagementcardsystem.BankManagementCardSystem.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bankmanagementcardsystem.BankManagementCardSystem.model.Card;


import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByName(String numberCard);
    List<Card> findAll();
    void deleteById(Long id);
    Page<Card> findByName(Pageable pageable);
}
