package com.example.patient_feedback.listeners;


import com.example.patient_feedback.domain.PatientQuestionnaire;
import com.example.patient_feedback.repositories.PatientQuestionnaireRepository;
import com.example.patient_feedback.services.PatientQuestionnaireSentimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoPatientQuestionnaireListener extends AbstractMongoEventListener<PatientQuestionnaire>
{
    @Autowired
    PatientQuestionnaireSentimentService patientQuestionnaireSentimentService;

    @Autowired
    private PatientQuestionnaireRepository patientQuestionnaireRepository;

    @Override
    public void onAfterSave(AfterSaveEvent<PatientQuestionnaire> event) {
        System.out.println("onAfterSave(" + event.getSource() + ", " + event.getDocument());
        // Send request to flask app
        PatientQuestionnaire patientQuestionnaire = event.getSource();
        // Integer sentimentScore = patientQuestionnaireSentimentService.getSentimentAnalysisScore(patientQuestionnaire);
        patientQuestionnaire.setSentimentScore(0.5);
        // patientQuestionnaireRepository.save(patientQuestionnaire);
        System.out.println(patientQuestionnaire);
    }
}