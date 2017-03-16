package ru.egalvi.survey.service.impl;


import ru.egalvi.survey.model.Survey;
import ru.egalvi.survey.service.SurveyIterationHandler;
import ru.egalvi.survey.service.SurveyService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;

public class SurveyServiceImpl implements SurveyService {
    private final JAXBContext jc;
    private final Unmarshaller unmarshaller;
    private final Marshaller marshaller;
    private final String fileName;

    public SurveyServiceImpl(String fileName) throws JAXBException {
        this.fileName = fileName;
        jc = JAXBContext.newInstance(Survey.class);
        unmarshaller = jc.createUnmarshaller();
        marshaller = jc.createMarshaller();
    }

    public List<String> getAllNames() throws JAXBException {
        List<String> names = new ArrayList<String>();
        names.add(getSurveyInner().getName());
        return names;
    }

    public List<Survey> getAll() throws JAXBException {
        List<Survey> surveys = new ArrayList<Survey>();
        surveys.add(getSurveyInner());
        return surveys;
    }

    public SurveyIterationHandler getSurvey() throws JAXBException {
        return new SurveyIterationHandlerImpl(getSurveyInner());
    }

    private Survey getSurveyInner() throws JAXBException {
        return (Survey) unmarshaller.unmarshal(Thread.
                currentThread().
                getContextClassLoader().
                getResourceAsStream(fileName));
    }
}
