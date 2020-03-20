package com.adam.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static final String URL = "jdbc:postgresql://localhost:5432/exercises";
    public static final String USER = "postgres";
    public static final String PASSWORD = "Matylda123";

    public static final String TABLE_BOOKING = "cd.bookings";
    public static final String COLUMN_BOOKING_ID = "bookid";
    public static final String COLUMN_BOOKING_FACID = "facid";
    public static final String COLUMN_BOOKING_MEMID = "memid";
    public static final String COLUMN_START_TIME = "starttime";
    public static final String COLUMN_SLOTS = "slots";
    public static final int INDEX_BOOKING_ID = 1;
    public static final int INDEX_BOOKING_FACID = 2;
    public static final int INDEX_BOOKING_MEMID = 3;
    public static final int INDEX_START_TIME = 4;
    public static final int INDEX_SLOTS = 5;

    public static final String TABLE_FACILITIES = "cd.facilities";
    public static final String COLUMN_FACILITIES_FACID = "facid";
    public static final String COLUMN_FACILITIES_NAME = "name";
    public static final String COLUMN_FACILITIES_MEMBER_COST = "membercost";
    public static final String COLUMN_FACILITIES_GUEST_COST = "guestcost";
    public static final String COLUMN_FACILITIES_INITIAL_OUTLAY = "initialoutlay";
    public static final String COLUMN_FACILITIES_MONTHLY_MAINTENACE = "monthlymaintenace";
    public static final int INDEX_FACILITIES_FACID = 1;
    public static final int INDEX_FACILITIES_NAME = 2;
    public static final int INDEX_FACILITIES_MEMBER_COST = 3;
    public static final int INDEX_FACILITIES_GUEST_COST = 4;
    public static final int INDEX_FACILITIES_INITIAL_OUTLAY = 5;
    public static final int INDEX_FACILITIES_MONTHLY_MAINTENACE = 6;

    public static final String TABLE_MEMBERS = "cd.members";
    public static final String COLUMN_MEMBERS_MEMID = "memid";
    public static final String COLUMN_MEMBERS_SURNAME = "surname";
    public static final String COLUMN_MEMBERS_FIRST_NAME = "firstname";
    public static final String COLUMN_MEMBERS_ADDRESS = "address";
    public static final String COLUMN_MEMBERS_ZIP_CODE = "zipcode";
    public static final String COLUMN_MEMBERS_TELEPHONE = "telephone";
    public static final String COLUMN_MEMBERS_RECOMMENDED_BY = "recommendedby";
    public static final String COLUMN_MEMBERS_JOIN_DATE = "joindate";
    public static final int INDEX_MEMBERS_MEMID = 1;
    public static final int INDEX_MEMBERS_SURNAME = 2;
    public static final int INDEX_MEMBERS_FIRST_NAME = 3;
    public static final int INDEX_MEMBERS_ADDRESS = 4;
    public static final int INDEX_MEMBERS_ZIP_CODE = 5;
    public static final int INDEX_MEMBERS_TELEPHONE = 6;
    public static final int INDEX_MEMBERS_RECOMMENDED_BY = 7;
    public static final int INDEX_MEMBERS_JOIN_DATE = 8;

    public enum Orders {
        NONE(1, " "), ASC(1, " ASC "), DSC(2, " DESC ");

        int value;
        String sortOrder;

        Orders(int value, String sortOrder) {
            this.value = value;
            this.sortOrder = sortOrder;
        }
    }

    private Connection con;

    public static Connection makeConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean open() {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Member> queryMembers(int sortOrder) {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");
        stringBuilder.append(TABLE_MEMBERS);
        if (sortOrder != Orders.NONE.value) {
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append(COLUMN_MEMBERS_FIRST_NAME);
            if (sortOrder == Orders.DSC.value) {
                stringBuilder.append(Orders.DSC.sortOrder);
            } else {
                stringBuilder.append(Orders.ASC.sortOrder);
            }
        }

        try (
                Connection connection = makeConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<Member> members = new ArrayList<>();
            while (resultSet.next()) {
                Member member = new Member();
                member.setMemID(resultSet.getInt(INDEX_MEMBERS_MEMID));
                member.setSurname(resultSet.getString(INDEX_MEMBERS_SURNAME));
                member.setFirstName(resultSet.getString(INDEX_MEMBERS_FIRST_NAME));
                member.setAddress(resultSet.getString(INDEX_MEMBERS_ADDRESS));
                member.setZipCode(resultSet.getString(INDEX_MEMBERS_ZIP_CODE));
                member.setTelephone(resultSet.getString(INDEX_MEMBERS_TELEPHONE));
                member.setRecommendedBy(resultSet.getInt(INDEX_MEMBERS_RECOMMENDED_BY));
                member.setJoinDate(resultSet.getString(INDEX_MEMBERS_JOIN_DATE));
                members.add(member);
            }
            return members;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public int countMembers() {
        StringBuilder stringBuilder = new StringBuilder("SELECT COUNT(");
        stringBuilder.append(COLUMN_MEMBERS_MEMID);
        stringBuilder.append(") FROM ");
        stringBuilder.append(TABLE_MEMBERS);
        int i = 0;
        try (
                Connection con = makeConnection();
                PreparedStatement preparedStatement = con.prepareStatement(stringBuilder.toString());
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                i = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return i;
    }
}
