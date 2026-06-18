package fullstack.Repositories;

import fullstack.Entities.UrgentPatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrgentPatientRepository extends JpaRepository<UrgentPatient, Integer> {

}
