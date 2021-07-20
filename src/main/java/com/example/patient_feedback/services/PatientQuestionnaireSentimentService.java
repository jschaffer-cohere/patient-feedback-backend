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

    public Double getSentimentAnalysisScore(PatientQuestionnaire patientQuestionnaire)
    {
        String API_URL = "http://127.0.0.1:1739/sentiment/";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(API_URL);

        try {
            System.out.printf("Sending request to %s", uriBuilder.toUriString() + patientQuestionnaire.getResponse());
            String sentimentScore = restTemplate.getForObject(uriBuilder.toUriString() + patientQuestionnaire.getResponse(), String.class);
            System.out.printf("Sentiment score for %s : %s%n", patientQuestionnaire.getResponse(), sentimentScore);
            return sentimentScore != null ? Double.valueOf(sentimentScore) : null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}