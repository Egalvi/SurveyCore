package ru.egalvi.survey.service;

import ru.egalvi.survey.model.Answer;
import ru.egalvi.survey.model.Question;

/**
 * Iterates through survey questions and collects the selected answers.
 */
public interface SurveyIterationHandler {
    Question getNextQuestion();

    boolean hasNestQuestion();

    void setAnswer(Answer answer);

    String getResult();
}
