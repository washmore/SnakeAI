package tech.washmore.game.snake.ai.api;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.washmore.game.snake.model.enums.Direction;

/**
 * Created by Washmore on 2017/4/14.
 */
@RestController
public class DirectionController {
    /**
     *
     * @param map
     * @return
     */
    @RequestMapping("/test")
    public Direction getDirectionForNextAction(@RequestBody(required = false) int[][] map) {
        if (map == null) {
            map = new int[20][10];
        }
        System.out.println(JSON.toJSONString(map));
        return Direction.getDirection(1);
    }
}
