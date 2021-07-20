package com.example.patient_feedback.services;

import com.example.patient_feedback.domain.PatientQuestionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PatientQuestionnaireSentimentService
{

    @Autowired
    private RestTemplate restTemplate;

    public Integer getSentimentAnalysisScore(PatientQuestionnaire patientQuestionnaire)
    {
        String API_URL = "localhost:1739/sentiment/%s";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(String.format(API_URL, patientQuestionnaire.getResponse()));

        try {
            String sentimentScore = restTemplate.getForObject(uriBuilder.toUriString(), String.class);
            System.out.printf("Sentiment score for %s : %s%n", patientQuestionnaire.getResponse(), sentimentScore);
            return sentimentScore != null ? Integer.valueOf(sentimentScore) : null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}