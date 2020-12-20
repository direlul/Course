package com.leo.course.dao;

import com.leo.course.mapper.*;
import com.leo.course.model.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

@Repository
@Data
public class JDBCTemplate extends JdbcDaoSupport implements Dao, UserRepository {

    @Autowired
    public JDBCTemplate(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    @Autowired
    private PasswordEncoder encoder;


    @Override
    public List<AllPerformanceSeats> getAllPerformanceSeats() {
        String SQL = "select * from all_performance_seats";
        List<AllPerformanceSeats> list = this.getJdbcTemplate().query(SQL, new AllPerformanceSeatsMapper());
        return list;
    }

    @Override
    public List<AllPerformanceSeats> getFreeSeats() {
        String SQL = "select * from free_seats";
        List<AllPerformanceSeats> list = this.getJdbcTemplate().query(SQL, new AllPerformanceSeatsMapper());
        return list;
    }

    @Override
    public List<AllPerformanceSeats> getBookedSeats() {
        String SQL = "select * from booked_seats";
        List<AllPerformanceSeats> list = this.getJdbcTemplate().query(SQL, new AllPerformanceSeatsMapper());
        return list;
    }

    @Override
    public List<Performances> getPerformances() {
        String SQL = "select * from performances";
        List<Performances> list = this.getJdbcTemplate().query(SQL, new PerformancesMapper());
        return list;
    }

    @Override
    public List<TheatricalProductions> getTheatricalProductions() {
        String SQL = "select * from theatrical_productions";
        List<TheatricalProductions> list = this.getJdbcTemplate().query(SQL, new TheatricalProductionsMapper());
        return list;
    }

    @Override
    public List<Bookings> getBookings() {
        String SQL = "select * from bookings";
        List<Bookings> list = this.getJdbcTemplate().query(SQL, new BookingsMapper());
        return list;
    }

    @Override
    public List<Customers> getCustomers() {
        String SQL = "select * from customers";
        List<Customers> performances = this.getJdbcTemplate().query(SQL, new CustomersMapper());
        return performances;
    }

    @Override
    public List<Theaters> getTheaters() {
        String SQL = "select * from theater";
        List<Theaters> list = this.getJdbcTemplate().query(SQL, new TheatersMapper());
        return list;
    }

    @Override
    public List<RefProductionTypes> getProductionTypes() {
        String SQL = "select * from ref_production_types";
        List<RefProductionTypes> list = this.getJdbcTemplate().query(SQL, new RefProductionTypesMapper());
        return list;
    }

    @Override
    public List<PerformanceNumbers> getNumbers() {
        String SQL = "select * from performance_numbers";
        List<PerformanceNumbers> list = this.getJdbcTemplate().query(SQL, new PerformanceNumbersMapper());
        return list;
    }

    @Override
    public List<PerformanceView> getPerformancesView() {
        String SQL = "select performance_id, show_date, performance_number, production_name, production_description\n" +
                "from performances join theatrical_productions tp on performances.production_id = tp.production_id";
        List<PerformanceView> list = this.getJdbcTemplate().query(SQL, new PerformanceViewMapper());
        return list;
    }

    @Override
    public int insertCustomers(BookingForm form) {
        String insert = "insert into customers (fio, mobile_phone) " +
                "values (?,?);";
        return this.getJdbcTemplate().update(insert, form.getFio(), form.getNumberPhone());
    }

    @Override
    public int insertBookings(BookingForm form) {

        String insert = "WITH ins (booking_made_date, mobile, peformance_id) AS " +
                "( VALUES" +
                "    (?,?,?)" +
                ")" +
                "INSERT INTO bookings " +
                "    (customer_id, booking_made_date, peformance_id) " +
                "SELECT" +
                "    customers.customer_id, ins.booking_made_date, ins.peformance_id " +
                "FROM" +
                "    customers join ins" +
                "    ON ins.mobile = customers.mobile_phone;";

        return this.getJdbcTemplate().update(insert, LocalDate.now(),
                form.getNumberPhone(), form.getPerformanceId());
    }

    @Override
    public int updateSeat(BookingForm form) {
        String update = "UPDATE all_performance_seats " +
                "SET seat_status_code = 101, booking_id = currval(pg_get_serial_sequence('bookings', 'booking_id')) " +
                "WHERE theater_id = 1 and block_code = ? and row_number = ? and seat_number = ? and performance_id = ?";

        return this.getJdbcTemplate().update(update, form.getSeatBlock(), form.getRowSeats(),
                form.getSeat(), form.getPerformanceId());
    }

    @Override
    public List<User> getUsers() {
        String SQL = "select username, password, enabled, authority " +
                "from users inner join authorities using (username);";

        List<User> list = this.getJdbcTemplate().query(SQL, new UserMapper());
        return list;
    }

    @Override
    public int create(String username, String password, String authority) throws DataAccessException {
        TransactionDefinition definition = new DefaultTransactionDefinition();
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(getDataSource());
        TransactionStatus status = transactionManager.getTransaction(definition);

        String insertUser = "insert into users(username, password, enabled) values(?,?,?);";
        String insertAuthority = "insert into authorities(username, authority) values (?,?);";

        try {
            return this.getJdbcTemplate().update(insertUser, username, encoder.encode(password), true) *
                    this.getJdbcTemplate().update(insertAuthority, username, authority);
        } catch (DataAccessException exception) {
            transactionManager.rollback(status);
            throw exception;
        }
    }

    @Override
    public int delete(String username) {
        String deleteAuthority = "delete from authorities where username = ?";
        String deleteUser = "delete from users where username = ?";

        return this.getJdbcTemplate().update(deleteAuthority, username) *
                this.getJdbcTemplate().update(deleteUser, username);
    }
}
