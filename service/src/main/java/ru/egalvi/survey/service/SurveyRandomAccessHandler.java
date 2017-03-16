package ru.egalvi.survey.service;

import ru.egalvi.survey.model.Answer;
import ru.egalvi.survey.model.Question;

/**
 * Provides random access to survey's questions.
 */
public interface SurveyRandomAccessHandler {
    Question getQuestion(int number);

    void setAnswer(Question q, Answer a);

    String getResult();
}
