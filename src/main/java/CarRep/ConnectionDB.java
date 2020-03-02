package CarRep;

import java.sql.*;

public class ConnectionDB {
    Connection connection = null;
    Statement statement = null;
    String query = null;
    ResultSet res = null;


    public void setConnection() {
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

    public void createTables() {
        query = "CREATE TABLE clients (id IDENTITY , lastname VARCHAR(32), firstname varchar(32), patronymic varchar (32), phonenumber varchar(32))";
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

    public void dropConnection() {
        try {
            statement.close();
            statement = connection.createStatement();
            query = "SHUTDOWN";
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getclientlist() {
        try {
            query = "SELECT * FROM clients";
            res = statement.executeQuery(query);

            while (res.next()) {
                ClientList.clientArrayList.add(new Client(res.getLong(1), res.getString(2), res.getString(3), res.getString(4),
                        res.getString(5)));
            }
        } catch (SQLException e) {
            System.err.println("Не вывелось(((");

        }
    }

    public void getMehList() {
        try {
            query = "SELECT * FROM mehs";
            res = statement.executeQuery(query);

            while (res.next()) {
                MehList.mehArrayList.add(new Meh(res.getLong(1), res.getString(2), res.getString(3), res.getString(4),
                        res.getString(5)));
            }
        } catch (SQLException e) {
            System.err.println("Не вывелось(((");

        }
    }

    public void getOrderList() {
        query = "SELECT * FROM orders";
        try {

            res = statement.executeQuery(query);

            while (res.next()) {
                OrderList.orderArrayList.add(new Order(res.getLong(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5),
                        res.getDate(6), res.getString(7), res.getString(8)));
            }
        } catch (SQLException e) {
            System.err.println("Не вывелось.");
        }
    }

    public void addOrder(String description, String client, String meh, Date createDate, Date endDate, String cost, String status) {
        query = "INSERT INTO orders (Description, Client, Meh, CreateDate, endDate, cost, status) VALUES (?,?,?,?,?,?,?)";
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
        } catch (SQLException e) {
            System.err.println("Не добавилось");
        }
    }

    public void addClient(String lastName, String firstName, String patronymic, String phoneNumber) {
        query = "INSERT INTO clients (lastname, firstname, patronymic, phonenumber) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, patronymic);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Не добавилось(((");

        }

    }

    public void updateOrder(String description, String client, String meh, Date createDate, Date endDate, String cost, String status, Long id) {
        query = "UPDATE orders SET Description = ?, Client = ?, Meh = ?, CreateDate = ?, endDate = ?, cost = ?, status = ? WHERE id = ?";
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
        } catch (SQLException e) {
            System.err.println("Не добавилось(((");

        }

    }


    public void updateClient(String lastName, String firstName, String patronymic, String phoneNumber, Long id) {
        query = "UPDATE clients SET lastname = ?, firstname = ?, patronymic = ?, phonenumber = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, patronymic);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Клиент не был обновлён");
        }
    }

    public void updateMeh(String lastName, String firstName, String patronymic, String payHour, Long id) {
        query = "UPDATE mehs SET lastname = ?, firstname = ?, patronymic = ?, payhour = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, patronymic);
            preparedStatement.setString(4, payHour);
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Механик не был обновлён");
        }
    }

    public void deleteOrder(Long id) {
        query = "DELETE from orders WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Заказ не был удалён");
        }
    }

    public void deleteClient(Long id) {
        query = "DELETE from clients WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Клиент не был удалён");
        }
    }

    public void deleteMeh(Long id) {
        query = "DELETE from mehs WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Механик не был удалён");
        }
    }

    public void addMeh(String lastName, String firstName, String patronymic, String payHour) {
        query = "INSERT INTO mehs (lastname, firstname, patronymic, payhour) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, patronymic);
            preparedStatement.setString(4, payHour);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Механик не был добавлен");
        }
    }

    public Long ifClient(String client) {
        Long rows = null;
        query = "SELECT COUNT(Client) FROM orders WHERE Client ='" + client + "'";
        try {
            res = statement.executeQuery(query);
            res.next();
            rows = res.getLong(1);
        } catch (SQLException e) {
            System.err.println("Не удалось проверить наличие заказа для указанного клиента");
        }
        return rows;
    }

    public Long ifMeh(String meh) {
        Long rows = null;
        query = "SELECT COUNT(Meh) FROM orders WHERE Meh ='" + meh + "'";
        try {
            res = statement.executeQuery(query);
            res.next();
            rows = res.getLong(1);
        } catch (SQLException e) {
            System.err.println("Не удалось проверить наличие заказа для указанного механика");
        }
        return rows;
    }

    public void getDescription(String description) {
        query = "SELECT * FROM orders WHERE description LIKE '%" + description + "%'";
        try {
            res = statement.executeQuery(query);
            while (res.next()) {
                OrderList.orderArrayList.add(new Order(res.getLong(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5),
                        res.getDate(6), res.getString(7), res.getString(8)));

            }
        } catch (SQLException e) {
            System.out.println("Не вывелось");
        }
    }

    public void getClient(String client) {
        query = "SELECT * FROM orders WHERE Client LIKE '%" + client + "%'";
        try {
            res = statement.executeQuery(query);
            while (res.next()) {
                OrderList.orderArrayList.add(new Order(res.getLong(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5),
                        res.getDate(6), res.getString(7), res.getString(8)));

            }
        } catch (SQLException e) {
            System.out.println("Не вывелось");
        }
    }
    public void getStatus(String status) {
        query = "SELECT * FROM orders WHERE status LIKE '%" + status + "%'";
        try {
            res = statement.executeQuery(query);
            while (res.next()) {
                OrderList.orderArrayList.add(new Order(res.getLong(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5),
                        res.getDate(6), res.getString(7), res.getString(8)));

            }
        } catch (SQLException e) {
            System.out.println("Не вывелось");
        }
    }
}


