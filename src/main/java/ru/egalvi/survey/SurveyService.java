package ru.egalvi.survey;

import ru.egalvi.survey.model.Survey;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class SurveyService {

    private final JAXBContext jc;
    private final Unmarshaller unmarshaller;
    private final Marshaller marshaller;

    public SurveyService() throws JAXBException {
        jc = JAXBContext.newInstance(Survey.class);
        unmarshaller = jc.createUnmarshaller();
        marshaller = jc.createMarshaller();
    }

    public Survey getSurvey() throws JAXBException {
        return (Survey) unmarshaller.unmarshal(Thread.currentThread().getContextClassLoader().getResourceAsStream("questions.xml"));
    }
}
