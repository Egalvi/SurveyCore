
import ru.egalvi.survey.model.Answer;
import ru.egalvi.survey.model.Question;
import ru.egalvi.survey.service.SurveyIterationHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import ru.egalvi.survey.service.SurveyService;
import ru.egalvi.survey.service.impl.SurveyServiceImpl;

public class ConsoleSurveyApp {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SurveyService service = new SurveyServiceImpl("test1.xml");
        SurveyIterationHandler survey = service.getSurvey();

//        Iterator<Integer> aI = answers.iterator();

        while (survey.hasNestQuestion()) {
            Question question = survey.getNextQuestion();
            System.out.println(question.getText());
            List<Answer> answers = question.getAnswer();
            int i = 0;
            for (Answer a : answers) {
                System.out.println(++i + ". " + a.getText());
            }

            Integer answerNumber = null;
            while (answerNumber == null) {
                System.out.print("Enter answer number:");
                try {
                    answerNumber = Integer.parseInt(br.readLine());
                    if (answerNumber > answers.size() || answerNumber <= 0) {
                        answerNumber = null;
                        System.err.println("Invalid input");
                    }
                } catch (NumberFormatException nfe) {
                    System.err.println("Invalid Format!");
                }
            }
//            answerNumber = aI.next();

            survey.setAnswer(answers.get(answerNumber - 1));
        }
        System.out.println(survey.getResult());
    }

    private static List<Integer> answers = new ArrayList<Integer>();
    static {
        answers = Arrays.asList(new Integer[]{2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 3, 2, 1, 2, 2, 1, 2, 2, 2, 1});
    }
}
