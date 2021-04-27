package mihaijulien.eu.msscsm.repository;

import mihaijulien.eu.msscsm.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
