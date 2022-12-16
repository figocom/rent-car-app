package com.figo.utils.serviceutil;


import com.figo.daos.CarDAO;
import com.figo.daos.OrderDAO;
import com.figo.domain.Order;
import com.figo.mapper.OrderMapper;
import com.figo.services.order.OrderService;
import com.figo.services.order.OrderServiceImpl;
import com.figo.utils.validators.OrderValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderUtil {
    public static OrderService getService() {
        OrderDAO dao = new OrderDAO();
        OrderMapper mapper = new OrderMapper();
        OrderValidator validator = new OrderValidator();
        return new OrderServiceImpl(dao, mapper, validator);
    }

    public static boolean isValidDate(String input) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (input.length() == 19) {
                format.setLenient(false);
                format.parse(input);
                return false;
            }
            return true;
        } catch (ParseException e) {
            return true;
        }
    }

    public static LocalDateTime stringToLocalDateTime(String time) {

        time = time.trim();
        time = time.concat(" 00:00:00");
        String[] s = time.split(" ");
        String returnedTimeStr = s[0].concat("T").concat(s[1]);
        if (OrderUtil.isValidDate(time) ) {
            throw  new IllegalArgumentException("Time don't match!");
        }
        return LocalDateTime.parse(returnedTimeStr);
    }

    public static Double getTotalOrderPrice(Order order) {
        timeCalculator(order);
        String carPrice = CarDAO.getCarPrice(order.getCarId());
        double price= Double.parseDouble(Objects.requireNonNull(carPrice).substring(0, carPrice.length() - 1));
        int i = order.getEndTime().getDayOfYear() - order.getStartTime().getDayOfYear();
        return i * price;

    }

    public static void timeCalculator(Order requestedOrder) {
        List<Order> orders = OrderDAO.getOrders(requestedOrder.getCarId());
        if (!orders.isEmpty()) {
            for (Order order : orders) {
                if (!requestedOrder.getStartTime().isAfter(order.getEndTime()) &&
                        !order.getStartTime().isAfter(requestedOrder.getEndTime())) {
                    throw new IllegalArgumentException("This car is busy during this interval");
                }
            }

        }
    }
}
