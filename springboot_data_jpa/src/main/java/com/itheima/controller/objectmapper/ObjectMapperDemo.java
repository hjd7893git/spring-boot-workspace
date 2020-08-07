package com.itheima.controller.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itheima.domain.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2019/9/9.
 */
public class ObjectMapperDemo {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //对象
        User user = new User();
        user.setId(1);
        user.setName("hjd");
        user.setPassword("123");
        String userString = mapper.writeValueAsString(user);
        System.out.println("对象转化为字符串" + userString);
        User userRead = mapper.readValue(userString, User.class);
        System.out.println("字符串转化为对象" + userRead + "\n\n\n");

        //List
        List<UserMapper> userList = new ArrayList<>();
        userList.add(new UserMapper("a", "b", "c"));
        userList.add(new UserMapper("a1", "b1", "c1"));
        String jsonStr = mapper.writeValueAsString(userList);
        System.out.println("集合转为字符串：" + jsonStr);
        List<UserMapper> userListDes = mapper.readValue(jsonStr, List.class);
        System.out.println("字符串转集合：" + userListDes + "\n\n\n");

        //Map
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("name", "merry");
        testMap.put("age", 30);
        testMap.put("date", new Date());
        testMap.put("user", new UserMapper("a", "b", "c"));
        try {
            String jsonStr2 = mapper.writeValueAsString(testMap);
            System.out.println("Map转为字符串：" + jsonStr2);
            try {
                Map<String, Object> testMapDes = mapper.readValue(jsonStr2, Map.class);
                System.out.println("字符串转Map：" + testMapDes + "\n\n\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        // 修改时间格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        String jsonStr3 = mapper.writeValueAsString(new Date());
        System.out.println("对象转为字符串：" + jsonStr3);
    }
}
