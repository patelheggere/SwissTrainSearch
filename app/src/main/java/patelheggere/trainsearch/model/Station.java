package patelheggere.trainsearch.model;

/**
 * Created by Patel Heggere on 2/4/2018.
 */

public class Station {
    private String id;
    private String name;
    private String score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
