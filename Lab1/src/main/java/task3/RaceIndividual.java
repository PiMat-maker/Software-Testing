package task3;

public class RaceIndividual extends Race{

    private final String name;
    private int numberOfSucceedHits;
    private int numberOfSucceedRuns;

    public RaceIndividual(String type, boolean looksLikeHuman, int size, String favouriteGame, String name) {
        super(type, looksLikeHuman, size, favouriteGame);
        this.name = name;
        this.numberOfSucceedHits = 0;
        this.numberOfSucceedRuns = 0;
    }

    public void hitIndividual(RaceIndividual individual){
        if (individual.isDistracted() && !this.isDistracted()){
            System.out.println(this.name + " hit " + individual.name);
            this.numberOfSucceedHits++;
            run();
            this.numberOfSucceedRuns++;
        } else {
            System.out.println(this.name + " did not hit " + individual.name);
        }
    }

    public void run(){
        System.out.println(this.name + " ran");
    }

    public Argue startArgue(RaceIndividual individual, Question question){
        System.out.println(this.name + " starts to argue with " + individual.name + " about " + question.getTopic());
        return new Argue(question, this, individual);
    }

    public int getNumberOfSucceedHits() {
        return numberOfSucceedHits;
    }

    public int getNumberOfSucceedRuns() {
        return numberOfSucceedRuns;
    }


}
