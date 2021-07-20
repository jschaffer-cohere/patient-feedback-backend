package com.example.patient_feedback.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
public class PatientQuestionnaire {

    @Id
    private String id;

    private String patientId;

    private String providerId;

    private String clinicalServiceId;

    private String question; //maybe question id,
    // similar to clinical assessments we can provider different patients
    // with different questions/forms to A/B test for better response rates

    private String response;

    private Double sentimentScore;

    private Instant createdDateTime;

}
