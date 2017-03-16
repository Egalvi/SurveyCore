package ru.egalvi.survey;


import ru.egalvi.survey.model.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.List;
import java.util.Map;

public class SurveyServiceImpl implements SurveyService {

    private final JAXBContext jc;
    private final Unmarshaller unmarshaller;
    private final Marshaller marshaller;

    public SurveyServiceImpl() throws JAXBException {
        jc = JAXBContext.newInstance(Survey.class);
        unmarshaller = jc.createUnmarshaller();
        marshaller = jc.createMarshaller();
    }

    public Survey getSurvey() throws JAXBException {
        return (Survey) unmarshaller.unmarshal(Thread.currentThread().getContextClassLoader().getResourceAsStream("questions.xml"));
    }

    public String getResult(Map<Question, Answer> answers) throws JAXBException {
        return null;
    }

    public String getResult(List<Scale> scales) throws JAXBException {
        Survey survey = (Survey) unmarshaller.unmarshal(Thread.currentThread().getContextClassLoader().getResourceAsStream("questions.xml"));
        for (Key k : survey.getKeys().getKey()) {
            if (k.getScale().equals(scales)) {
                return k.getText();
            }
        }
        return null;
    }
}
