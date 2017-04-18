package tech.washmore.game.snake.model;

import java.io.Serializable;

/**
 * Created by Washmore on 2017/4/14.
 */
public class Point implements Serializable {

    private static final long serialVersionUID = 8213565621005289287L;

    private int x;
    private int y;
    private int grade;

    public Point() {
    }

    public Point(int j, int i, int g) {
        this.x = j;
        this.y = i;
        this.grade = g;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

}
