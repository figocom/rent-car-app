package com.figo.services.car;

import com.figo.criteria.CarCriteria;
import com.figo.daos.CarDAO;

import com.figo.daos.OrderDAO;
import com.figo.daos.RegionDAO;
import com.figo.domain.Car;
import com.figo.domain.Order;
import com.figo.dtos.cars.CarCreateDTO;
import com.figo.dtos.cars.CarDTO;
import com.figo.enums.CarStatus;
import com.figo.mapper.CarMapper;

import com.figo.response.DataDTO;
import com.figo.response.ErrorDTO;
import com.figo.response.Response;
import com.figo.services.base.AbstractService;
import com.figo.utils.validators.CarValidator;

import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class CarServiceImpl extends AbstractService<CarDAO, CarMapper, CarValidator> implements CarService {
    public CarServiceImpl(CarDAO dao, CarMapper mapper, CarValidator validator) {
        super(dao, mapper, validator);
    }
static   LocalDateTime lastRunTime;
static {
   lastRunTime = LocalDateTime.now();
}
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public Response<DataDTO<String>> create(@NonNull CarCreateDTO dto) {
        try {
            validator.checkOnCreate(dto);
            Car car = mapper.fromCreateDTO(dto);
            dao.saveCar(car);
            return new Response<>(new DataDTO<>(car.getCarNumber()));
        } catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }

    @Override
    public Response<DataDTO<Boolean>> update(@NonNull CarDTO dto) {
        return null;
    }
    //todo bir kunda birmarta ishlaydigan qilish


        LocalDateTime  localDateTime = LocalDateTime.now();
        public static class CarThread extends Thread {


            @Override
            public void run() {
                {
                    if (Objects.isNull(lastRunTime)){
                        lastRunTime=LocalDateTime.now();
                    }
                    // get time span since last run
                    int sinceLastRunTime = LocalDateTime.now().getDayOfYear() - lastRunTime.getDayOfYear();

                    // if a day has passed
                    if (sinceLastRunTime >= 1)
                    {
                        // write anything to the file to update the lastWriteTime
//                        System.IO.File.WriteAllText(lastRunFile, DateTime.Now.ToString());
                        // Do the job
                        System.out.println("thread worked");

                    }
                    else
                    {
                        // Sleep for an hour
                        try {
                            Thread.sleep(1000 * 60 * 60);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }


                }
            }

        }



    @Override
    public Response<DataDTO<Boolean>> update(@NonNull List<CarDTO> dto, CarCriteria criteria) {


        try {
            for (CarDTO carDTO : dto) {
                int carId = CarDAO.getCarId(carDTO.getCarNumber());
                List<Order> orders = OrderDAO.getOrders(carId);
                if (!orders.isEmpty()) {
                    for (Order order : orders) {
                        int dayOfYear = LocalDateTime.now().getDayOfYear();
                        if (order.getStartTime().getDayOfYear() >= dayOfYear && order.getEndTime().getDayOfYear() <= dayOfYear) {
                            dao.updateCarStatus(carId, criteria.getStatus());
                        }
                    }

                }
            }

        } catch (RuntimeException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
        return new Response<>(new DataDTO<>(Boolean.TRUE));
    }

    @Override
    public void check() {

    }

    @Override
    public Response<DataDTO<Boolean>> delete(@NonNull String s) {
        return null;
    }

    @Override
    public Response<DataDTO<List<CarDTO>>> getAll() {
        try {
            List<Car> cars = dao.getAllCars();
            List<CarDTO> carDTOs = mapper.toDTOs(cars);
            return new Response<>(new DataDTO<>(carDTOs));
        } catch (RuntimeException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }

    @Override
    public Response<DataDTO<CarDTO>> get(@NonNull String carNumber) {
        try {
            Car car = dao.getAllCars().stream().filter(carFilter -> carFilter.getCarNumber().equals(carNumber)).findFirst().orElse(null);
            if (Objects.nonNull(car)) {
                CarDTO carDTO = mapper.toDTO(car);
                return new Response<>(new DataDTO<>(carDTO));
            }
            return new Response<>(new DataDTO<>(new ErrorDTO("Car not found")));
        } catch (RuntimeException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }


    }

    @Override
    public Response<DataDTO<List<CarDTO>>> getAll(@NonNull CarCriteria criteria) {
        try {
            List<Car> cars = dao.getAllCars();
            cars.removeIf(car -> !car.getCarStatus().equals(criteria.getStatus()));
            List<CarDTO> carDTOs = mapper.toDTOs(cars);
            return new Response<>(new DataDTO<>(carDTOs));
        } catch (RuntimeException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }
}
