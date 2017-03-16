package ru.egalvi.survey.service;

import ru.egalvi.survey.model.Survey;

import java.util.List;

/**
 * Provides access to survey items.
 */
public interface SurveyService {
    List<String> getAllNames() throws Exception;

    List<Survey> getAll() throws Exception;

    SurveyIterationHandler getSurvey() throws Exception;
}
