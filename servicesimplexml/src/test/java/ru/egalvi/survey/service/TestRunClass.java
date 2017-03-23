package ru.egalvi.survey.service;

import org.junit.Assert;
import ru.egalvi.survey.model.Answer;
import ru.egalvi.survey.model.Question;
import ru.egalvi.survey.service.impl.SurveyIterationHandlerImpl;
import ru.egalvi.survey.service.impl.SurveyServiceImpl;

import java.util.List;

public class TestRunClass {
    @org.junit.Test
    public void iterativeSurvey() throws Exception {
        SurveyServiceImpl service = new SurveyServiceImpl("questions.xml");
        SurveyIterationHandlerImpl survey = service.getSurvey();
        while (survey.hasNestQuestion()) {
            Question question = survey.getNextQuestion();
            System.out.println(question.getText());
            List<Answer> answers = question.getAnswer();
            int i = 0;
            for (Answer a : answers) {
                System.out.println(++i + ". " + a.getText());
            }
            survey.setAnswer(answers.get(0));
        }
        System.out.println(survey.getResult());
        Assert.assertEquals("Answer text2", survey.getResult());
    }
}
