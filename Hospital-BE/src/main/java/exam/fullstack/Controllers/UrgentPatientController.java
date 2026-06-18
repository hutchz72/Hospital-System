package fullstack.Controllers;

import fullstack.Entities.UrgentPatient;
import fullstack.Repositories.UrgentPatientRepository;
import fullstack.Services.UrgentPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/urgentPatient")
@CrossOrigin(origins = {"http://localhost:5173"})
public class UrgentPatientController {
    @Autowired
    private UrgentPatientService urgentPatientService;

    @GetMapping("/next")
    public ResponseEntity<UrgentPatient> getNextUrgentPatient() {
        // 1. ดึงรายชื่อผู้ป่วยทั้งหมดที่ยังไม่ได้รักษาจากฐานข้อมูลขึ้นมาเป็นคิว (List)
        List<UrgentPatient> queue = urgentPatientService.getAllWaitingPatients();

        // 2. ดึงเวลาปัจจุบันของระบบส่งเข้าไปคำนวณเงื่อนไข 60 นาที
        LocalDateTime currentTime = LocalDateTime.now();

        // 3. เรียกฟังก์ชันโดยส่งทั้งคิว (queue) และเวลาปัจจุบัน (currentTime) เข้าไปตามสเปกอัลกอริทึม
        UrgentPatient nextPatient = urgentPatientService.getUrgentPatient(queue, currentTime);

        // ถ้าไม่มีคิวผู้ป่วยเหลืออยู่เลย ให้คืนค่า 204 No Content
        if (nextPatient == null) {
            return ResponseEntity.noContent().build();
        }

        // ถ้าเจอผู้ป่วย ส่งข้อมูลกลับไปพร้อม HTTP Status 200 OK
        return ResponseEntity.ok(nextPatient);
    }
}
