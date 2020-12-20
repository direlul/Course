package com.leo.course.controller;

import com.leo.course.dao.JDBCTemplate;
import com.leo.course.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final JDBCTemplate jdbcTemplate;

    @Autowired
    public MainController(JDBCTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/user";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("users", jdbcTemplate.getUsers());
        return "admin";
    }

    @RequestMapping(value = "/admin/createUser", method = RequestMethod.GET)
    public String createUser() {
        return "addUser";
    }

    @RequestMapping(value = "/admin/createUser", method = RequestMethod.POST)
    public String createUser(@RequestParam String username,
                   @RequestParam String password,
                   @RequestParam String authority) {
        try {
            int res = jdbcTemplate.create(username, password, authority);
            return "success";
        } catch (DataAccessException exception) {
            return "error";
        }
    }

    @RequestMapping(value = "/admin/deleteUser", method = RequestMethod.GET)
    public String deleteUser() {
        return "deleteUser";
    }

    @RequestMapping(value = "/admin/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam String username) {
        jdbcTemplate.delete(username);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model) {
        List<AllPerformanceSeats> seats = jdbcTemplate.getAllPerformanceSeats();
        List<AllPerformanceSeats> freeSeats = jdbcTemplate.getFreeSeats();
        List<AllPerformanceSeats> bookedSeats = jdbcTemplate.getBookedSeats();
        List<Bookings> bookings = jdbcTemplate.getBookings();
        List<Customers> customers = jdbcTemplate.getCustomers();
        List<PerformanceNumbers> performanceNumbers = jdbcTemplate.getNumbers();
        List<Performances> performances = jdbcTemplate.getPerformances();
        List<RefProductionTypes> productionTypes = jdbcTemplate.getProductionTypes();
        List<Theaters> theaters = jdbcTemplate.getTheaters();
        List<TheatricalProductions> theatricalProductions = jdbcTemplate.getTheatricalProductions();

        model.addAttribute("seats", seats);
        model.addAttribute("freeSeats", freeSeats);
        model.addAttribute("bookedSeats", bookedSeats);
        model.addAttribute("bookings", bookings);
        model.addAttribute("customers", customers);
        model.addAttribute("performanceNumbers", performanceNumbers);
        model.addAttribute("performances", performances);
        model.addAttribute("productionTypes", productionTypes);
        model.addAttribute("theaters", theaters);
        model.addAttribute("theatricalProductions", theatricalProductions);

        return "user";
    }

    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public String booking(Model model) {
        model.addAttribute("performances", jdbcTemplate.getPerformancesView());
        return "booking";
    }

    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public String addBooking(BookingForm bookingForm) {
        int resCustomers = jdbcTemplate.insertCustomers(bookingForm);
        int resBookings = jdbcTemplate.insertBookings(bookingForm);

        try {
            jdbcTemplate.updateSeat(bookingForm);
        } catch (Exception exception) {
            return "booked";
        }


        if (resBookings * resCustomers != 0) {
            return "success";
        } else {
            return "error";
        }
    }

}
