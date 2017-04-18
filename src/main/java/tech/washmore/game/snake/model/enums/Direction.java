package tech.washmore.game.snake.model.enums;

/**
 * Created by Washmore on 2017/4/14.
 */
public enum Direction {
    上(0),
    下(1),
    左(2),
    右(3);

    private int value;

    public int getValue() {
        return value;
    }

    Direction(int value) {
        this.value = value;
    }

    public static Direction getDirection(int value) {
        for (Direction d : Direction.values()) {
            if (value == d.getValue()) {
                return d;
            }
        }
        throw new IllegalArgumentException(String.format("未找到value=%d的方向!", value));
    }

}
