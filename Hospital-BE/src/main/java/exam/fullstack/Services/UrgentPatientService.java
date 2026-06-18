package fullstack.Services;

import fullstack.Repositories.UrgentPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrgentPatientService {
    @Autowired
    private UrgentPatientRepository urgentPatientRepository;


}
