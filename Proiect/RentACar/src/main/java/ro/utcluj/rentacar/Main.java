/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.rentacar;

import ro.utcluj.rentacar.model.Car;
import ro.utcluj.rentacar.service.CarService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import ro.utcluj.rentacar.model.Review;
import ro.utcluj.rentacar.model.User;
import ro.utcluj.rentacar.service.ReviewService;
import ro.utcluj.rentacar.service.UserService;

/**
 * @author Martin
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        CarService carService = new CarService();
        UserService userService = new UserService();
        ReviewService reviewService = new ReviewService();

        try {

            ServerSocket ss = new ServerSocket(4050);

            while (true) {
                System.out.println("Astept conexiune de la client...");
                Socket s = ss.accept(); 
                System.out.println("Clientul s-a conectat!");
                //......
                BufferedReader fluxIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter fluxOut = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
                //......
                String line = "";
                String[] data = fluxIn.readLine().split(" ");
                switch (data[0]) {
                    case "readCars": {
                        List<Car> result = carService.readCars();
                        fluxOut.println(result);
                        break;
                    }
                     case "readUsers": {
                        List<User> result = userService.readUsers();
                        fluxOut.println(result);
                        break;
                    }
                    
                    case "addNewCar":{
                        Car car = new Car();
                        car.setName(data[1]);
                        car.setModel(data[2]);
                        car.setCapacity(Integer.parseInt(data[3]));
                        car.setEngine(Double.parseDouble(data[4]));

                        String result = carService.addANewCar(car);
                        fluxOut.println(result);
                        break;
                    }
                    case "addNewClient":{
                        User user = new User();
                        user.setFirstName(data[1]);
                        user.setLastName(data[2]);
                        user.setAge(Integer.parseInt(data[3]));
                        user.setDriveingExperience(Double.parseDouble(data[4]));
                        user.setCnp((data[5]));
                        
                        String result = userService.addANewUser(user);
                        fluxOut.println(result);
                        break;
                    }
                    case "addReview":{
                        Review review = new Review();
                        review.setCarId(Integer.parseInt(data[1]));
                        review.setClientId(Integer.parseInt(data[2]));
                        review.setStars(Integer.parseInt(data[3]));

                        String result = reviewService.addANewReview(review);
                        fluxOut.println(result);
                        break;
                    }
                    default: {
                        
                    }
                }

                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
