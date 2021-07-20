package com.example.patient_feedback.repositories;

import java.util.List;

import com.example.patient_feedback.domain.PatientQuestionnaire;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "patientQuestionnaire", path = "patientQuestionnaire")
public interface PatientQuestionnaireRepository extends MongoRepository<PatientQuestionnaire, String> {

    List<PatientQuestionnaire> findPatientQuestionnaireByClinicalServiceId(@Param("clinicalServiceId") String clinicalServiceId);

    List<PatientQuestionnaire> findPatientQuestionnaireByPatientId(@Param("patientId") String patientId);

    List<PatientQuestionnaire> findPatientQuestionnaireByProviderId(@Param("providerId") String providerId);

    PatientQuestionnaire findPatientQuestionnaireById(@Param("id") String id);

}