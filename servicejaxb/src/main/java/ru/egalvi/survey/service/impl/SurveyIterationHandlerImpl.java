package ru.egalvi.survey.service.impl;

import ru.egalvi.survey.model.*;
import ru.egalvi.survey.service.SurveyIterationHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SurveyIterationHandlerImpl implements SurveyIterationHandler {

    private final Survey survey;
    private Iterator<Question> questionIterator;
    private Question current;
    private Map<Question, Answer> answers = new HashMap<Question, Answer>();

    public SurveyIterationHandlerImpl(Survey survey) {
        this.survey = survey;
        questionIterator = survey.getQuestions().getQuestion().iterator();
    }

    public Question getNextQuestion() {
        if (current == null || answers.containsKey(current)) {
            current = questionIterator.next();
        }
        return current;
    }

    public boolean hasNestQuestion() {
        return questionIterator.hasNext();
    }

    public void setAnswer(Answer answer) {
        answers.put(current, answer);
    }

    public String getResult() {
        Map<String, Scale> resultScales = new HashMap<String, Scale>();
        for (Answer a : answers.values()) {
            for (Scale s : a.getScale()) {
                Scale scale = new Scale();
                scale.setName(s.getName());
                scale.setValue(s.getValue());
                Scale existent = resultScales.get(s.getName());
                if (existent != null) {
                    scale.setValue(scale.getValue() + existent.getValue());
                }
                resultScales.put(s.getName(), scale);
            }
        }

        System.out.println("Result: ");
        for (Scale s :
                resultScales.values()) {
            System.out.print(s.getName() + ":::" + s.getValue().intValue() + " ");
        }

        for (Key k : survey.getKeys().getKey()) {
            boolean matched = true;
            for (Keyscale s : k.getScale()) {
                Scale scale = resultScales.get(s.getName());
                if (s.getValue() != null && !scale.getValue().equals(s.getValue())) {
                    matched = false;
                    break;
                } else if (s.getValue() == null && !((s.getMinValueExclusive() != null && scale.getValue().compareTo(s.getMinValueExclusive()) > 0
                        || s.getMinValueInclusive() != null && scale.getValue().compareTo(s.getMinValueInclusive()) >= 0)
                        && (s.getMaxValueExclusive() != null && scale.getValue().compareTo(s.getMaxValueExclusive()) < 0
                        || s.getMaxValueInclusive() != null && scale.getValue().compareTo(s.getMaxValueInclusive()) <= 0))) {
                    matched = false;
                    break;
                }
            }
            if (matched) {
                return k.getText();
            }
        }
        return null;
    }
}
