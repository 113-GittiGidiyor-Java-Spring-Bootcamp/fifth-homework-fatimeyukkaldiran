package patika.dev.schoolmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import patika.dev.schoolmanagementsystem.entity.enumeration.TransactionType;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InstructorServiceTransactionLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long instructorId;
    private double salaryBeforeUpdate;
    private double salaryAfterUpdate;
    private int transactionPercent;
    private LocalDate transactionDateTime;

    @Column(name = "client_URL")
    private String clientURL;

    @Column(name = "session_Activity_ID")
    private String sessionActivityID;

    @Column(name = "client_IP_Address")
    private String clientIPAddress;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

}
