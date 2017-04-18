package tech.washmore.game.snake.model.enums;

/**
 * Created by Washmore on 2017/4/14.
 */
public enum Grade {
    障碍物(-10),
    蛇身体(-1),
    蛇头(-2),
    可行走区域(10);

    private int value;

    public int getValue() {
        return value;
    }

    Grade(int value) {
        this.value = value;
    }

    public static Direction getGrade(int value) {
        for (Direction d : Direction.values()) {
            if (value == d.getValue()) {
                return d;
            }
        }
        throw new IllegalArgumentException(String.format("未找到value=%d的Grade!", value));
    }
}
