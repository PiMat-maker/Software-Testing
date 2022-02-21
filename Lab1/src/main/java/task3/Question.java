package task3;

public class Question {

    private final String topic;
    private boolean isSolved;

    public Question(String topic) {
        this.topic = topic;
        this.isSolved = false;
    }

    public String getTopic() {
        return topic;
    }

    public void setSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }

    public boolean isSolved(){
        return isSolved;
    }
}
