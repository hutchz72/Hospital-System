package fullstack.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "urgentpatient")
public class UrgentPatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "urgentPatientId")
    private Long urgentPatientId;

    @Column(name = "urgentPatientType", nullable = false)
    private String urgentPatientType; // เก็บค่า 'E' หรือ 'N'

    @Column(name = "severityScore", nullable = false)
    private Integer severityScore; // ค่า 1-10

    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }



}
