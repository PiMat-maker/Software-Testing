package task3;

import java.util.List;

public class Race {

    private final String type;
    private final boolean looksLikeHuman;
    private final int size;
    private boolean isExhausted;
    private boolean isDistracted;
    private boolean isSitDown;
    private final String favouriteGame;

    public Race(String type, boolean looksLikeHuman, int size, String favouriteGame) {
        this.type = type;
        this.looksLikeHuman = looksLikeHuman;
        this.size = size;
        this.isExhausted = false;
        this.isDistracted = false;
        this.isSitDown = false;
        this.favouriteGame = favouriteGame;
    }

    public void setDistracted(boolean distracted) {
        isDistracted = distracted;
    }

    public String getType(){
        return type;
    }

    public boolean isExhausted() {
        return isExhausted;
    }

    public boolean isDistracted() {
        return isDistracted;
    }

    public boolean isSitDown() {
        return isSitDown;
    }

    public boolean isLooksLikeHuman() {
        return looksLikeHuman;
    }

    public int getSize() {
        return size;
    }

    public String getFavouriteGame() {
        return favouriteGame;
    }

    public void gotExhausted(Question question){
        if (question.getTopic().equals("Life sense")){
            isExhausted = true;
            System.out.println("We're exhausted");
        } else{
            isExhausted = false;
            System.out.println("We're full of energy");
        }
    }

    public void sitDown(){
        if (this.isExhausted){
            isSitDown = true;
            System.out.println("Race sit down at the table");
        } else{
            isSitDown = false;
            System.out.println("Race did not sit down at the table");
        }
    }

    public void solveProblems(List<Question> questionsList){
        if (this.isSitDown){
            for (Question question : questionsList){
                System.out.println("Race solved the problem: " + question.getTopic());
                question.setSolved(true);
            }
            isSitDown = false;
        } else {
            System.out.println("Life is good. No problems");
        }
    }
}
