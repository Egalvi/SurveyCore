package ru.egalvi.survey.model;

import org.junit.Test;
import ru.egalvi.survey.model.Survey;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MarshallUnmarshallTest {

    @Test
    public void testMarshallUnmarshall() throws Exception {
        //Create JAXB context
        JAXBContext jc = JAXBContext.newInstance(Survey.class);
        //Unmarshall an XML file
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Survey survey = (Survey) unmarshaller.unmarshal(Thread.currentThread().getContextClassLoader().getResourceAsStream("questions.xml"));
        //Marshall to XML
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(survey, System.out);
    }
}
