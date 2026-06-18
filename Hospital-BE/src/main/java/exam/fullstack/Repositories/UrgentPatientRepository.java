package fullstack.Repositories;

import fullstack.Entities.UrgentPatient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrgentPatientRepository extends JpaRepository<UrgentPatient, Integer> {

    List<UrgentPatient> findByStatus(boolean status);
}
