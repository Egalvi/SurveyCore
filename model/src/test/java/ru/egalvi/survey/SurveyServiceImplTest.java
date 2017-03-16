package ru.egalvi.survey;

import org.junit.Test;
import ru.egalvi.survey.model.Survey;

import static org.junit.Assert.*;

public class SurveyServiceImplTest {
    @Test
    public void testCreate() throws Exception {
        SurveyServiceImpl service = new SurveyServiceImpl();
    }

    @Test
    public void name() throws Exception {
        SurveyService service = new SurveyServiceImpl();
        Survey survey = service.getSurvey();
        assertNotNull(survey);
    }

}