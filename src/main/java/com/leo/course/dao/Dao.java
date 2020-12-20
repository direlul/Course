package com.leo.course.dao;

import com.leo.course.model.*;

import javax.sql.DataSource;
import java.util.List;

public interface Dao {

    public void setDataSource(DataSource ds);

    public List<AllPerformanceSeats> getAllPerformanceSeats();
    public List<AllPerformanceSeats> getFreeSeats();
    public List<AllPerformanceSeats> getBookedSeats();
    public List<Performances> getPerformances();
    public List<TheatricalProductions> getTheatricalProductions();
    public List<Bookings> getBookings();
    public List<Customers> getCustomers();
    public List<Theaters> getTheaters();
    public List<RefProductionTypes> getProductionTypes();
    public List<PerformanceNumbers> getNumbers();
    public List<PerformanceView> getPerformancesView();
    public int insertCustomers(BookingForm form);
    public int insertBookings(BookingForm form);
    public int updateSeat(BookingForm form);

}
