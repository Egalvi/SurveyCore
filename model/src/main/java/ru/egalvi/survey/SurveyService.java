package ru.egalvi.survey;


import ru.egalvi.survey.model.Answer;
import ru.egalvi.survey.model.Question;
import ru.egalvi.survey.model.Scale;
import ru.egalvi.survey.model.Survey;

import java.util.List;
import java.util.Map;

public interface SurveyService {

    Survey getSurvey() throws Exception;

    String getResult(Map<Question, Answer> answers) throws Exception;

    String getResult(List<Scale> scales) throws Exception;

}
