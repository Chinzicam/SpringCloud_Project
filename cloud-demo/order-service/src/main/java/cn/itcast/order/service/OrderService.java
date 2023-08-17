package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2. 远程查询User
        // 2.1 url地址，用user-service替换了localhost:8081
        String url = "http://user-service/user/" + order.getUserId();
        // 2.2 发起调用
        User user = restTemplate.getForObject(url, User.class);
        // 3. 存入order
        order.setUser(user);
        // 4.返回
        return order;
    }
}