package ru.egalvi.survey.service.impl;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.egalvi.survey.model.Survey;
import ru.egalvi.survey.service.SurveyIterationHandler;
import ru.egalvi.survey.service.SurveyService;

import java.util.ArrayList;
import java.util.List;

public class SurveyServiceImpl implements SurveyService {
    private Serializer serializer;
    private final String fileName;

    public SurveyServiceImpl(String fileName) throws Exception {
        this.fileName = fileName;
        serializer = new Persister();
    }

    public List<String> getAllNames() throws Exception {
        List<String> names = new ArrayList<String>();
        names.add(getSurveyInner().getName());
        return names;
    }

    public List<Survey> getAll() throws Exception {
        List<Survey> surveys = new ArrayList<Survey>();
        surveys.add(getSurveyInner());
        return surveys;
    }

    public SurveyIterationHandler getSurvey() throws Exception {
        return new SurveyIterationHandlerImpl(getSurveyInner());
    }

    private Survey getSurveyInner() throws Exception {
        return (Survey) serializer.read(Survey.class, Thread.
                currentThread().
                getContextClassLoader().
                getResourceAsStream(fileName));
    }
}
