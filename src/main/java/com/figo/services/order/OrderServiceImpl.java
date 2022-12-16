package com.figo.services.order;

import com.figo.criteria.OrderCriteria;
import com.figo.daos.OrderDAO;
import com.figo.domain.Order;
import com.figo.dtos.orders.OrderCreateDTO;
import com.figo.dtos.orders.OrderDTO;
import com.figo.dtos.orders.OrderUpdateDTO;
import com.figo.mapper.OrderMapper;
import com.figo.response.DataDTO;
import com.figo.response.ErrorDTO;
import com.figo.response.Response;
import com.figo.services.base.AbstractService;
import com.figo.utils.serviceutil.OrderUtil;
import com.figo.utils.validators.OrderValidator;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class OrderServiceImpl extends AbstractService<OrderDAO, OrderMapper, OrderValidator> implements OrderService{
    public OrderServiceImpl(OrderDAO dao, OrderMapper mapper , OrderValidator validator){
        super(dao , mapper ,validator);
    }
    private final Logger logger = Logger.getLogger(getClass().getName());
    @Override
    public Response<DataDTO<String>> create(@NonNull OrderCreateDTO dto) {
        try {
            validator.checkOnCreate(dto);
            LocalDateTime startTime = OrderUtil.stringToLocalDateTime(dto.startTime());
            LocalDateTime endTime = OrderUtil.stringToLocalDateTime(dto.endTime());
            Order order = mapper.fromCreateDTO(dto);
            order.setStartTime(startTime);
            order.setEndTime(endTime);
            validator.checkTime(order);
            Double totalPrice=OrderUtil.getTotalOrderPrice(order);
            order.setTotalPrice(totalPrice);
            dao.registerOrder(order);
            dao.writeHistory(order);
            return new Response<>(new DataDTO<>("\"Request successfully sent to admins\\nPlease wait confirmation\""));
        } catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }

    @Override
    public Response<DataDTO<Boolean>> update(@NonNull OrderUpdateDTO dto) {
        try {
          boolean update=  dao.update(dto);
          return new Response<>(new DataDTO<>(update));
        } catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }

    @Override
    public Response<DataDTO<Boolean>> delete(@NonNull String s) {
        return null;
    }

    @Override
    public Response<DataDTO<List<OrderDTO>>> getAll() {
        return null;
    }

    @Override
    public Response<DataDTO<OrderDTO>> get(@NonNull String s) {
        return null;
    }

    @Override
    public Response<DataDTO<List<OrderDTO>>> getAll(@NonNull OrderCriteria criteria) {
        try {
            List<Order>orders=dao.getUserOrders(criteria.getUserId());
            List<OrderDTO> orderDTOS = mapper.toDTOs(orders);
            return new Response<>(new DataDTO<>(orderDTOS));

        }catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }
}
