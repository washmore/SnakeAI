package tech.washmore.game.snake.ai;

import com.alibaba.fastjson.JSON;
import tech.washmore.game.snake.model.Header;
import tech.washmore.game.snake.model.Point;
import tech.washmore.game.snake.model.enums.Grade;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Washmore on 2017/4/14.
 */
public class MapElementParser {
    static Point[][] map = new Point[20][10];

    static List<Point> openList = new ArrayList<>();
    static List<Point> closeList = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {

                if (j == 6 && i >= 3 && i <= 7) {
                    System.out.print("+  ");
                    map[j][i] = new Point(j, i, Grade.障碍物.getValue());
                } else if (j == 2 && i == 6) {
                    System.out.print("+  ");
                    map[j][i] = new Point(j, i, Grade.可行走区域.getValue());
                } else if (j == 15 && i == 2) {
                    System.out.print("+  ");
                    map[j][i] = new Point(j, i, Grade.可行走区域.getValue());
                } else if (i == 3 && j >= 10 && j <= 16) {
                    System.out.print("+  ");
                    map[j][i] = new Point(j, i, Grade.障碍物.getValue());
                } else if (j == 10 && i >= 1 && i <= 2) {
                    System.out.print("+  ");
                    map[j][i] = new Point(j, i, Grade.障碍物.getValue());
                } else {
                    System.out.print("*  ");
                    map[j][i] = new Point(j, i, Grade.可行走区域.getValue());
                }
            }
            System.out.println();
        }


        Point start = new Point(2, 6, Grade.可行走区域.getValue());
        Point end = new Point(15, 2, Grade.可行走区域.getValue());
        if (checkRoad(start, end)) {
            openList = new ArrayList<>();
            closeList = new ArrayList<>();
            checkRoad(end, start);
            System.out.println(JSON.toJSONString(closeList, true));

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 20; j++) {
                    boolean isRoad = false;
                    for (Point p : closeList) {
                        if (p.getX() == j && p.getY() == i) {
                            isRoad = true;
                            break;
                        }
                    }
                    if (isRoad) {
                        if (start.getX() == j && start.getY() == i) {
                            System.out.print("★  ");
                        } else if (end.getX() == j && end.getY() == i) {
                            System.out.print("☆  ");
                        }else{
                            System.out.print("◇  ");
                        }
                    } else {
                        if (map[j][i].getGrade() == Grade.可行走区域.getValue()) {
                            System.out.print("□  ");
                        } else if (map[j][i].getGrade() == Grade.障碍物.getValue()) {
                            System.out.print("■  ");
                        }
                    }
                }
                System.out.println();
            }
        }


    }

    static boolean checkRoad(Point start, Point end) {
        System.out.println(JSON.toJSONString(start));
        closeList.add(start);
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            return true;
        }
        int x = start.getX();
        int y = start.getY();
        if (y > 0) {
            Point up = map[x][y - 1];
            if ((!containClosePoint(up)) && up.getGrade() == Grade.可行走区域.getValue()) {
                openList.add(up);
            }
        }
        if (y < map[0].length - 1) {
            Point down = map[x][y + 1];
            if ((!containClosePoint(down)) && down.getGrade() == Grade.可行走区域.getValue()) {
                openList.add(down);
            }
        }
        if (x > 0) {
            Point left = map[x - 1][y];
            if ((!containClosePoint(left)) && left.getGrade() == Grade.可行走区域.getValue()) {
                openList.add(left);
            }
        }
        if (x < map.length - 1) {
            Point right = map[x + 1][y];
            if ((!containClosePoint(right)) && right.getGrade() == Grade.可行走区域.getValue()) {
                openList.add(right);
            }
        }
        if (openList.size() == 0) {
            return false;
        }
        openList.sort(
                new Comparator<Point>() {
                    @Override
                    public int compare(Point o1, Point o2) {
                        int cha = (((end.getX() - o1.getX()) * (end.getX() - o1.getX()) + (end.getY() - o1.getY()) * (end.getY() - o1.getY())) - ((end.getX() - o2.getX()) * (end.getX() - o2.getX()) + (end.getY() - o2.getY()) * (end.getY() - o2.getY())));
                        return cha > 0 ? 1 : -1;
                    }
                });


        return checkRoad(openList.remove(0), end);
    }


    static boolean containClosePoint(Point c) {
        for (Point p : closeList) {
            if (p.getX() == c.getX() && p.getY() == c.getY()) {
                return true;
            }
        }
        for (Point p : openList) {
            if (p.getX() == c.getX() && p.getY() == c.getY()) {
                return true;
            }
        }
        return false;
    }

}
