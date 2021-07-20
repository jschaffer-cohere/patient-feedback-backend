package com.example.patient_feedback.listeners;


import com.example.patient_feedback.domain.PatientQuestionnaire;
import com.example.patient_feedback.repositories.PatientQuestionnaireRepository;
import com.example.patient_feedback.services.PatientQuestionnaireSentimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class MongoPatientQuestionnaireListener extends AbstractMongoEventListener<PatientQuestionnaire>
{
    @Autowired
    PatientQuestionnaireSentimentService patientQuestionnaireSentimentService;

    @Autowired
    private PatientQuestionnaireRepository patientQuestionnaireRepository;

    @Override
    public void onAfterSave(AfterSaveEvent<PatientQuestionnaire> event) {

        PatientQuestionnaire patientQuestionnaire = event.getSource();
        boolean isInitialSave = patientQuestionnaire.getCreatedDateTime() == null;
        System.out.println("onAfterSave(" + patientQuestionnaire + ", " + event.getDocument());
        if (isInitialSave) {
            // Send request to flask app
            Double sentimentScore = patientQuestionnaireSentimentService.getSentimentAnalysisScore(patientQuestionnaire);
            patientQuestionnaire.setCreatedDateTime(Instant.now());
            patientQuestionnaire.setSentimentScore(sentimentScore);
            PatientQuestionnaire updatedPatientQuestionnaire = patientQuestionnaireRepository.save(patientQuestionnaire);
            System.out.println(updatedPatientQuestionnaire);
        }
    }
}