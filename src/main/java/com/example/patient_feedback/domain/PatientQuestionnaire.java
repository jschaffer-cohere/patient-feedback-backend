package com.example.patient_feedback.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Builder
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

    private Instant createdDateTime;

    private Instant updatedDateTime;

}
