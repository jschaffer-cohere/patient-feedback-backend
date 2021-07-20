package com.example.patient_feedback.listeners;


import com.example.patient_feedback.domain.PatientQuestionnaire;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoPatientQuestionnaireListener extends AbstractMongoEventListener<PatientQuestionnaire>
{

    @Override
    public void onAfterSave(AfterSaveEvent<PatientQuestionnaire> event) {
        System.out.println("onAfterSave(" + event.getSource() + ", " + event.getDocument());
        // Send request to flask app
    }
}