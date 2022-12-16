package com.figo.services.order;

import com.figo.criteria.OrderCriteria;
import com.figo.dtos.orders.OrderCreateDTO;
import com.figo.dtos.orders.OrderDTO;
import com.figo.dtos.orders.OrderUpdateDTO;
import com.figo.services.base.GenericCrudService;

public interface OrderService extends GenericCrudService<OrderDTO , OrderCreateDTO , OrderUpdateDTO , String , OrderCriteria> {

}
