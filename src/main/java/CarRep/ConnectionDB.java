package CarRep;

import java.sql.*;

public abstract class ConnectionDB {
    static Connection connection = null;
    static Statement statement = null;

    public static void setConnection() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("НЕ удалось загрузить драйвер ДБ.");
            e.printStackTrace();
            System.exit(1);
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:hsqldb:file:dbpath/carrepdb", "SA", "");
        } catch (SQLException e) {
            System.err.println("НЕ удалось создать соединение.");
            e.printStackTrace();
            System.exit(1);
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTables() {
        String query = "CREATE TABLE clients (id IDENTITY , lastname VARCHAR(32), firstname varchar(32), patronymic varchar (32), phonenumber varchar(32))";
        try {
            statement.executeUpdate(query);
        } catch (SQLException ignored) {
        }

        query = "CREATE TABLE mehs (id IDENTITY , lastname VARCHAR(32), firstname varchar(32), patronymic varchar (32), payhour varchat(32))";
        try {
            statement.executeUpdate(query);
        } catch (SQLException ignored) {
        }

        query = "CREATE TABLE orders (id IDENTITY , Description varchar(255), Client varchar(32), Meh varchar(32), CreateDate datetime, endDate datetime, cost bigint, status varchar(255))";
        try {
            statement.executeUpdate(query);
        } catch (SQLException ignored) {
        }
    }

    public static void getclientlist() {
        try {
            String query = "SELECT * FROM clients";
            ResultSet res = statement.executeQuery(query);

            while (res.next()) {
                Client.clientList.add(new Client(res.getLong(1), res.getString(2), res.getString(3), res.getString(4),
                        res.getString(5)));
            }
        } catch (SQLException ignored) {
        }
    }

    public static void getMehList() {
        ResultSet res;
        String query = "SELECT * FROM mehs";
        try {
            res = statement.executeQuery(query);
            while (res.next()) {
                Meh.mehList.add(new Meh(res.getLong(1), res.getString(2), res.getString(3), res.getString(4),
                        res.getString(5)));
            }
        } catch (SQLException ignored) {
        }
    }

    public static void getOrderList() {
        String query = "SELECT * FROM orders";
        ResultSet res;
        try {
            res = statement.executeQuery(query);

            while (res.next()) {
                Order.orderList.add(new Order(res.getLong(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5),
                        res.getDate(6), res.getString(7), res.getString(8)));
            }
        } catch (SQLException ignored) {
        }
    }

    public static void addOrder(String description, String client, String meh, Date createDate, Date endDate, String cost, String status) {
        String query = "INSERT INTO orders (Description, Client, Meh, CreateDate, endDate, cost, status) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, client);
            preparedStatement.setString(3, meh);
            preparedStatement.setDate(4, createDate);
            preparedStatement.setDate(5, endDate);
            preparedStatement.setString(6, cost);
            preparedStatement.setString(7, status);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public static void addClient(String lastName, String firstName, String patronymic, String phoneNumber) {
        String query = "INSERT INTO clients (lastname, firstname, patronymic, phonenumber) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, patronymic);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }

    }

    public static void updateOrder(String description, String client, String meh, Date createDate, Date endDate, String cost, String status, long id) {
        String query = "UPDATE orders SET Description = ?, Client = ?, Meh = ?, CreateDate = ?, endDate = ?, cost = ?, status = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, client);
            preparedStatement.setString(3, meh);
            preparedStatement.setDate(4, createDate);
            preparedStatement.setDate(5, endDate);
            preparedStatement.setString(6, cost);
            preparedStatement.setString(7, status);
            preparedStatement.setLong(8, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }

    }


    public static void updateClient(String lastName, String firstName, String patronymic, String phoneNumber, long id) {
        String query = "UPDATE clients SET lastname = ?, firstname = ?, patronymic = ?, phonenumber = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, patronymic);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public static void updateMeh(String lastName, String firstName, String patronymic, String payHour, long id) {
        String query = "UPDATE mehs SET lastname = ?, firstname = ?, patronymic = ?, payhour = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, patronymic);
            preparedStatement.setString(4, payHour);
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public static void deleteOrder(long id) {
        String query = "DELETE from orders WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public static void deleteClient(long id) {
        String query = "DELETE from clients WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public static void deleteMeh(long id) {
        String query = "DELETE from mehs WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public static void addMeh(String lastName, String firstName, String patronymic, String payHour) {
        String query = "INSERT INTO mehs (lastname, firstname, patronymic, payhour) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, patronymic);
            preparedStatement.setString(4, payHour);
            preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public static Integer ifClient(String client) {
        Integer rows = null;
        ResultSet res;
        String query = "SELECT COUNT(Client) FROM orders WHERE Client ='" + client + "'";
        try {
            res = statement.executeQuery(query);
            res.next();
            rows = res.getInt(1);
        } catch (SQLException ignored) {
        }
        return rows;
    }

    public static Integer ifMeh(String meh) {
        Integer rows = null;
        ResultSet res;
        String query = "SELECT COUNT(Meh) FROM orders WHERE Meh ='" + meh + "'";
        try {
            res = statement.executeQuery(query);
            res.next();
            rows = res.getInt(1);
        } catch (SQLException ignored) {
        }
        return rows;
    }

    public static void getDescription(String description) {
        String query = "SELECT * FROM orders WHERE description LIKE '%" + description + "%'";
        ResultSet res;
        try {
            res = statement.executeQuery(query);
            while (res.next()) {
                Order.orderList.add(new Order(res.getLong(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5),
                        res.getDate(6), res.getString(7), res.getString(8)));

            }
        } catch (SQLException ignored) {
        }
    }

    public static void getClient(String client) {
        String query = "SELECT * FROM orders WHERE Client LIKE '%" + client + "%'";
        ResultSet res;
        try {
            res = statement.executeQuery(query);
            while (res.next()) {
                Order.orderList.add(new Order(res.getLong(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5),
                        res.getDate(6), res.getString(7), res.getString(8)));

            }
        } catch (SQLException ignored) {
        }
    }

    public static void getStatus(String status) {
        String query = "SELECT * FROM orders WHERE status LIKE '%" + status + "%'";
        ResultSet res;
        try {
            res = statement.executeQuery(query);
            while (res.next()) {
                Order.orderList.add(new Order(res.getLong(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5),
                        res.getDate(6), res.getString(7), res.getString(8)));

            }
        } catch (SQLException ignored) {
        }
    }
}


