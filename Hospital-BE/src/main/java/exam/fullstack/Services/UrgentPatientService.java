package fullstack.Services;

import fullstack.Entities.UrgentPatient;
import fullstack.Repositories.UrgentPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UrgentPatientService {
    @Autowired
    private UrgentPatientRepository urgentPatientRepository;

    public static UrgentPatient getUrgentPatient(List<UrgentPatient> queue, LocalDateTime currentTime) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }

        UrgentPatient targetPatient = queue.get(0);
        int targetIndex = 0;

        for (int i = 1; i < queue.size(); i++) {
            UrgentPatient currentPatient = queue.get(i);

            String targetType = getEffectiveType(targetPatient, currentTime);
            String currentType = getEffectiveType(currentPatient, currentTime);

            boolean changeTarget = false;

            if (!currentType.equals(targetType)) {
                if ("E".equals(currentType)) changeTarget = true;
            }
            else if (!currentPatient.getSeverityScore().equals(targetPatient.getSeverityScore())) {
                if (currentPatient.getSeverityScore() > targetPatient.getSeverityScore()) changeTarget = true;
            }
            else if (currentPatient.getCreatedAt().isBefore(targetPatient.getCreatedAt())) {
                changeTarget = true;
            }

            if (changeTarget) {
                targetPatient = currentPatient;
                targetIndex = i;
            }
        }

        queue.remove(targetIndex);
        return targetPatient;
    }

    private static String getEffectiveType(UrgentPatient patient, LocalDateTime currentTime) {
        if ("N".equals(patient.getUrgentPatientType()) &&
                Duration.between(patient.getCreatedAt(), currentTime).toMinutes() >= 60) {
            return "E";
        }
        return patient.getUrgentPatientType();
    }

    public List<UrgentPatient> getAllWaitingPatients() {
        // วิ่งไปเรียก Repository เพื่อดึงคนไข้ที่ status = false ทั้งหมดขึ้นมาจาก DB
        return urgentPatientRepository.findByStatus(false);
    }
}
