package com.outpatient.service;

import com.outpatient.entity.QuestionAnswer;
import com.outpatient.repository.ChatbotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    @Autowired
    private ChatbotRepository chatbotRepository;

    public String getAnswer(String question) {
        QuestionAnswer qa = chatbotRepository.findByQuestion(question);
        if (qa != null) {
            return qa.getAnswer();
        } else {
            return "I'm sorry, I couldn't find an answer to your question.";
        }
    }
}