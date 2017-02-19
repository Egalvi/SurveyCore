package ru.egalvi.survey;

import org.junit.Test;
import ru.egalvi.survey.model.Survey;

import static org.junit.Assert.*;

public class SurveyServiceTest {
    @Test
    public void testCreate() throws Exception {
        SurveyService service = new SurveyService();
    }

    @Test
    public void name() throws Exception {
        SurveyService service = new SurveyService();
        Survey survey = service.getSurvey();
        assertNotNull(survey);
    }
}