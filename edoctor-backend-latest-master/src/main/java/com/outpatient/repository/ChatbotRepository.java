package com.outpatient.repository;

import com.outpatient.entity.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatbotRepository extends JpaRepository<QuestionAnswer, Long> {
    QuestionAnswer findByQuestion(String question);
}