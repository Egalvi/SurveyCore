package ru.egalvi.survey.service.impl;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.egalvi.survey.model.Survey;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SurveyServiceImpl {
    private Serializer serializer;
    private String fileName;
    private InputStream surveyInputStream;

    public SurveyServiceImpl(String fileName) throws Exception {
        this.fileName = fileName;
        serializer = new Persister();
    }

    public SurveyServiceImpl(InputStream surveyInputStream) throws Exception {
        this.surveyInputStream = surveyInputStream;
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

    public SurveyIterationHandlerImpl getSurvey() throws Exception {
        return new SurveyIterationHandlerImpl(getSurveyInner());
    }

    private Survey getSurveyInner() throws Exception {
        if (surveyInputStream != null) {
            return serializer.read(Survey.class, surveyInputStream);
        }
        return serializer.read(Survey.class, Thread.
                currentThread().
                getContextClassLoader().
                getResourceAsStream(fileName));
    }
}
