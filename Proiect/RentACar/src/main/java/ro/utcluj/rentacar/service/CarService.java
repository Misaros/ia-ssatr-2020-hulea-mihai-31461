/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.rentacar.service;

import ro.utcluj.rentacar.connection.DbAccess;
import ro.utcluj.rentacar.model.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Martin
 */
public class CarService {
    DbAccess dbAccess;

    public CarService() throws ClassNotFoundException, SQLException {
        this.dbAccess = DbAccess.getInstance();
    }

    public String addANewCar(Car car) throws SQLException {
        Random random = new Random();
        int id = random.nextInt(999999999);
        try (Statement s = dbAccess.getConnection().createStatement()) {
            s.executeUpdate("INSERT INTO CARS (id,name, model, capacity, engine,isAvailable) VALUES ("+id+",'"+ car.getName() + "','" + car.getModel() + "'," + Integer.parseInt(String.valueOf(car.getCapacity())) + "," + Double.parseDouble(String.valueOf(car.getEngine()))+ "," + car.isIsAvailable() + ")");
        }
        return "Successfully inserted new car: " + car.toString();
    }

    public List<Car> readCars() throws SQLException {
        try (Statement s = dbAccess.getConnection().createStatement()) {
            ResultSet resultSet = s.executeQuery("SELECT * FROM CARS");
            if (resultSet.next()) {
                Car car = createCarFromResultSet(resultSet);

                List<Car> cars = new ArrayList<>();
                cars.add(car);
                while (resultSet.next()) {
                    car = new Car();
                    car.setId(resultSet.getInt("id"));
                    car.setCapacity(resultSet.getInt("capacity"));
                    car.setEngine(resultSet.getDouble("engine"));
                    car.setModel(resultSet.getString("model"));
                    car.setName(resultSet.getString("name"));
                    cars.add(car);
                }
                return cars;
            } else {
                return new ArrayList<>(0);
            }
        }
    }

    public String deleteCarById(Integer carId) throws SQLException {
        try (Statement s = dbAccess.getConnection().createStatement()) {
            Car car = findCarById(carId);
            if (car != null) {
                s.executeUpdate("DELETE FROM CARS WHERE ID = " + carId);
                return "Car with id = " + carId + " deleted successfully";
            } else {
                return "Delete failed, car with id = " + carId + " not found";
            }
        }
    }

    public Car findCarById(Integer carId) throws SQLException {
        try (Statement s = dbAccess.getConnection().createStatement()) {
            ResultSet resultSet = s.executeQuery("SELECT * FROM CARS WHERE ID = " + carId);
            if (resultSet.next()) {
                return createCarFromResultSet(resultSet);
            } else {
                return null;
            }
        }
    }

    private Car createCarFromResultSet(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("id"));
        car.setCapacity(resultSet.getInt("capacity"));
        car.setEngine(resultSet.getDouble("engine"));
        car.setModel(resultSet.getString("model"));
        car.setName(resultSet.getString("name"));
        return car;
    }
}
