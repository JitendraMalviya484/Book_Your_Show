package com.book.your.show.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookingIdGenerator implements IdentifierGenerator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
    	 String prefix = "BKID";
    	Connection connection = session.connection();
        try {
        	Statement statement=connection.createStatement();

        	 ResultSet rs=statement.executeQuery("select count(booking_id) as id from book_your_show.booking");

            if(rs.next())
            {
                String generatedValue;
                if(rs.getInt(1) == 0)   {
                    generatedValue=prefix +"00001";
                }
                else {
                    generatedValue = prefix +String.format("%05d",rs.getInt(1)+ 1);
                }
                return generatedValue;
            }
        } catch (SQLException e) {
            logger.error("Booking ticket - generation id failed");
            e.printStackTrace();
        }


        return null;
    }

}
