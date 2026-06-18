package fullstack.Controllers;

import fullstack.Repositories.UrgentPatientRepository;
import fullstack.Services.UrgentPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urgentPatient")
@CrossOrigin(origins = {"http://localhost:5173"})
public class UrgentPatientController {
    @Autowired
    private UrgentPatientService urgentPatientService;


}
