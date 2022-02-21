package task3;

public class Argue {

    private final Question question;
    private final RaceIndividual individual1;
    private final RaceIndividual individual2;

    public Argue(Question question, RaceIndividual individual1, RaceIndividual individual2) {
        this.question = question;
        this.individual1 = individual1;
        this.individual2 = individual2;
    }

    public void distractRaceIndividual(){
        if (question.getTopic().equals("Life sense") && !question.isSolved()){
            individual1.setDistracted(true);
            individual2.setDistracted(true);
            System.out.println("The topic " + question.getTopic() + " is distracted race");
        } else{
            individual1.setDistracted(false);
            individual2.setDistracted(false);
        }

    }
}
