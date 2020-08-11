package CarRep;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;

import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;


import java.sql.Date;
import java.time.LocalDate;


/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        ConnectionDB.setConnection();
        ConnectionDB.createTables();
        Label mainLabel = new Label("Автомастерская CarRep");
        HorizontalLayout workspace = new HorizontalLayout();
        VerticalLayout mainLayout = new VerticalLayout();
        HorizontalLayout menuLayout = new HorizontalLayout();
        HorizontalLayout workButtons = new HorizontalLayout();
        HorizontalLayout labelLayout = new HorizontalLayout();

        Panel orderGridPanel = new Panel("Список заказов");
        Button deleteOrder = new Button("Удалить");
        Button addOrder = new Button("Добавить");
        Button editOrder = new Button("Изменить");
        Grid<Order> orderGrid = new Grid<>("Заказы");

        Panel orderAddPanel = new Panel("Добавление нового заказа");
        Button addOrderAdd = new Button("Добавить");
        Button cancelAddOrder = new Button("Отменить");
        TextArea descriptionOrder = new TextArea("Описание заказа");

        Panel orderUpdPanel = new Panel("Редактирование заказа");
        Button updOrder = new Button("Сохранить");
        Button cancelUpdOrder = new Button("Отменить");
        TextArea descriptionOrderUpd = new TextArea("Описание заказа");

        mainLabel.setStyleName(ValoTheme.LABEL_H1);
        mainLayout.setSizeFull();
        workButtons.setSizeUndefined();
        workButtons.setSpacing(true);
        labelLayout.setSizeFull();
        labelLayout.addComponent(mainLabel);
        workspace.setSizeFull();
        labelLayout.setComponentAlignment(mainLabel, Alignment.TOP_CENTER);
        mainLayout.addComponent(labelLayout);
        // ПАНЕЛЬ ДОБАВЛЕНИЯ ЗАКАЗА

        descriptionOrder.setPlaceholder("Описание заказа...");

        ComboBox<Client> clientComboBox = new ComboBox<>();
        clientComboBox.setCaption("Клиент");
        clientComboBox.setItems(Client.clientList);
        clientComboBox.setItemCaptionGenerator(Client::getFullName);

        ComboBox<Meh> mehComboBox = new ComboBox<>();
        mehComboBox.setCaption("Механик");
        mehComboBox.setItems(Meh.mehList);
        mehComboBox.setItemCaptionGenerator(Meh::getFullName);

        DateField dateStart = new DateField();
        dateStart.setValue(LocalDate.now());
        dateStart.setCaption("Дата создания заказа");
        dateStart.setPlaceholder("Выберите дату...");

        DateField dateEnd = new DateField();
        dateEnd.setCaption("Дата окончания заказа");
        dateEnd.setPlaceholder("Выберите дату...");


        TextField orderCost = new TextField("Стоимость заказа");
        ComboBox<String> status = new ComboBox<>();
        status.setItems("Запланирован", "Выполнен", "Принят клиентом");
        FormLayout orderContent = new FormLayout();
        orderContent.addComponents(descriptionOrder, clientComboBox, mehComboBox, dateStart, dateEnd, orderCost, status, addOrderAdd, cancelAddOrder);
        orderAddPanel.setSizeUndefined();
        orderAddPanel.setHeight("100%");
        orderContent.setMargin(true);
        orderAddPanel.setContent(orderContent);
        // ПАНЕЛЬ РЕДАКТИРОВАНИЯ ЗАКАЗА

        descriptionOrderUpd.setPlaceholder("Описание заказа...");

        ComboBox<Client> clientComboBoxUpd = new ComboBox<>();
        clientComboBoxUpd.setCaption("Клиент");
        clientComboBoxUpd.setItems(Client.clientList);
        clientComboBoxUpd.setItemCaptionGenerator(Client::getFullName);

        ComboBox<Meh> mehComboBoxUpd = new ComboBox<>();
        mehComboBoxUpd.setCaption("Механик");
        mehComboBoxUpd.setItems(Meh.mehList);
        mehComboBoxUpd.setItemCaptionGenerator(Meh::getFullName);

        DateField dateStartUpd = new DateField();
        dateStartUpd.setCaption("Дата создания заказа");
        dateStartUpd.setPlaceholder("Выберите дату...");

        DateField dateEndUpd = new DateField();
        dateEndUpd.setCaption("Дата окончания заказа");
        dateEndUpd.setPlaceholder("Выберите дату...");

        TextField orderCostUpd = new TextField("Стоимость заказа");
        ComboBox<String> statusUpd = new ComboBox<>();
        statusUpd.setItems("Запланирован", "Выполнен", "Принят клиентом");
        FormLayout orderContentUpd = new FormLayout();
        orderContentUpd.addComponents(descriptionOrderUpd, clientComboBoxUpd, mehComboBoxUpd, dateStartUpd, dateEndUpd, orderCostUpd, statusUpd, updOrder, cancelUpdOrder);
        orderUpdPanel.setSizeUndefined();
        orderUpdPanel.setHeight("100%");
        orderContentUpd.setMargin(true);
        orderUpdPanel.setContent(orderContentUpd);
        // ПАНЕЛЬ ДОБАВЛЕНИЯ НОВОГО МЕХАНИКА

        Panel mehAddPanel = new Panel("Добавление нового механика");
        Button addMehAdd = new Button("Добавить");
        Button cancelAddMeh = new Button("Отменить");
        TextField lastNameMeh = new TextField("Фамилия");
        TextField firstNameMeh = new TextField("Имя");
        TextField patronymicMeh = new TextField("Отчество");
        TextField payHourMeh = new TextField("Часовая ставка");
        FormLayout mehContent = new FormLayout();
        mehContent.addComponents(lastNameMeh, firstNameMeh, patronymicMeh, payHourMeh, addMehAdd, cancelAddMeh);
        mehAddPanel.setSizeUndefined();
        mehAddPanel.setHeight("100%");
        mehContent.setMargin(true);
        mehAddPanel.setContent(mehContent);
        // ПАНЕЛЬ ДОБАВЛЕНИЯ НОВОГО КЛИЕНТА

        Panel clientAddPanel = new Panel("Добавление нового клиента");

        Button addClientAdd = new Button("Добавить");
        Button cancelAddClient = new Button("Отменить");

        TextField lastNameClient = new TextField("Фамилия");
        TextField firstNameClient = new TextField("Имя");
        TextField patronymicClient = new TextField("Отчество");
        TextField phoneNumberClient = new TextField("Номер телефона");
        FormLayout clientContent = new FormLayout();
        clientContent.addComponents(lastNameClient, firstNameClient, patronymicClient, phoneNumberClient, addClientAdd, cancelAddClient);
        clientAddPanel.setSizeUndefined();
        clientAddPanel.setHeight("100%");
        clientContent.setMargin(true);
        clientAddPanel.setContent(clientContent);
        // ПАНЕЛЬ РЕДАКТИРОВАНИЯ МЕХАНИКА

        Panel mehUpdPanel = new Panel("Редактирование механика");
        Button cancelUpdMeh = new Button("Отменить");
        TextField lastNameMehUpd = new TextField("Фамилия");
        TextField firstNameMehUpd = new TextField("Имя");
        TextField patronymicMehUpd = new TextField("Отчество");
        TextField payHourMehUpd = new TextField("Часовая ставка");
        Button updMeh = new Button("Сохранить");
        FormLayout mehUpdate = new FormLayout();
        mehUpdate.addComponents(lastNameMehUpd, firstNameMehUpd, patronymicMehUpd, payHourMehUpd, updMeh, cancelUpdMeh);
        mehUpdate.setSizeUndefined();
        mehUpdate.setHeight("100%");
        mehUpdate.setMargin(true);
        mehUpdPanel.setContent(mehUpdate);
        // ПАНЕЛЬ РЕДАКТИРОВАНИЯ КЛИЕНТА

        Panel clientUpdPanel = new Panel("Редактирование клиента");
        Button cancelUpdClient = new Button("Отменить");
        TextField lastNameClientUpd = new TextField("Фамилия");
        TextField firstNameClientUpd = new TextField("Имя");
        TextField patronymicClientUpd = new TextField("Отчество");
        TextField phoneNumberClientUpd = new TextField("Номер телефона");
        Button updClient = new Button("Сохранить");
        FormLayout clientUpdate = new FormLayout();
        clientUpdate.addComponents(lastNameClientUpd, firstNameClientUpd, patronymicClientUpd, phoneNumberClientUpd, updClient, cancelUpdClient);
        clientUpdate.setSizeUndefined();
        clientUpdate.setHeight("100%");
        clientUpdate.setMargin(true);
        clientUpdPanel.setContent(clientUpdate);
        // ТАБЛИЦА ЗАКАЗОВ

        orderGrid.addColumn(Order::getDescription).setCaption("Описание");
        orderGrid.addColumn(Order::getClient).setCaption("Клиент");
        orderGrid.addColumn(Order::getMeh).setCaption("Механик");
        orderGrid.addColumn(Order::getCreateDate).setCaption("Дата создания");
        orderGrid.addColumn(Order::getEndDate).setCaption("Дата окончания работ");
        orderGrid.addColumn(Order::getCost).setCaption("Стоимость");
        orderGrid.addColumn(Order::getStatus).setCaption("Статус");
        TextField description = new TextField();
        description.setPlaceholder("Фильтр по описанию");
        description.setValueChangeMode(ValueChangeMode.EAGER);
        description.addValueChangeListener(e -> {
            if (!description.isEmpty()) {
                Order.orderList.clear();
                ConnectionDB.getDescription(description.getValue());
                orderGrid.setItems(Order.orderList);
            } else {
                Order.orderList.clear();
                ConnectionDB.getOrderList();
                orderGrid.setItems(Order.orderList);
            }
        });
        TextField client = new TextField();
        client.setPlaceholder("Фильтр по клиенту");
        client.setValueChangeMode(ValueChangeMode.EAGER);
        client.addValueChangeListener(e -> {
            if (!client.isEmpty()) {
                Order.orderList.clear();
                ConnectionDB.getClient(client.getValue());
                orderGrid.setItems(Order.orderList);
            } else {
                Order.orderList.clear();
                ConnectionDB.getOrderList();
                orderGrid.setItems(Order.orderList);
            }
        });
        TextField statustf = new TextField();
        statustf.setPlaceholder("Фильтр по статусу");
        statustf.setValueChangeMode(ValueChangeMode.EAGER);
        statustf.addValueChangeListener(e -> {
            if (!statustf.isEmpty()) {
                Order.orderList.clear();
                ConnectionDB.getStatus(statustf.getValue());
                orderGrid.setItems(Order.orderList);
            } else {
                Order.orderList.clear();
                ConnectionDB.getOrderList();
                orderGrid.setItems(Order.orderList);
            }
        });

        orderGrid.setSizeFull();
        orderGridPanel.setSizeFull();
        orderGridPanel.setContent(orderGrid);
        orderGrid.asSingleSelect().addValueChangeListener(event -> workButtons.addComponents(editOrder, deleteOrder));
        // ТАБЛИЦА МЕХАНИКОВ

        Panel mehGridPanel = new Panel("Список механиков");
        Button deleteMeh = new Button("Удалить");
        Button addMeh = new Button("Добавить");
        Button editMeh = new Button("Изменить");
        Button showMehOrders = new Button("Показать статистику");
        Grid<Meh> mehGrid = new Grid<>("Механики");
        mehGrid.addColumn(Meh::getLastName).setCaption("Фамилия");
        mehGrid.addColumn(Meh::getFirstName).setCaption("Имя");
        mehGrid.addColumn(Meh::getPatronymic).setCaption("Отчество");
        mehGrid.addColumn(Meh::getPayHour).setCaption("Часовая ставка");
        mehGrid.setSizeFull();
        mehGridPanel.setSizeFull();
        mehGridPanel.setContent(mehGrid);
        mehGrid.asSingleSelect().addValueChangeListener(event -> workButtons.addComponents(editMeh, deleteMeh, showMehOrders));
        // ТАБЛИЦА КЛИЕНТОВ

        Panel clientGridPanel = new Panel("Список клиентов");
        Button deleteClient = new Button("Удалить");
        Button addClient = new Button("Добавить");
        Button editClient = new Button("Изменить");
        Grid<Client> clientGrid = new Grid<>("Клиенты");
        clientGrid.addColumn(Client::getLastName).setCaption("Фамилия");
        clientGrid.addColumn(Client::getFirstName).setCaption("Имя");
        clientGrid.addColumn(Client::getPatronymic).setCaption("Отчество");
        clientGrid.addColumn(Client::getPhoneNumber).setCaption("Номер телефона");

        clientGrid.setSizeFull();

        clientGridPanel.setSizeFull();
        clientGridPanel.setContent(clientGrid);
        clientGrid.asSingleSelect().addValueChangeListener(event -> workButtons.addComponents(editClient, deleteClient));
        // ОСНОВНОЕ МЕНЮ
        MenuBar menu = new MenuBar();

        menu.addItem("Клиенты", e -> {
            workspace.removeAllComponents();
            workButtons.removeAllComponents();
            Client.clientList.clear();
            ConnectionDB.getclientlist();
            clientGrid.setItems(Client.clientList);
            workspace.addComponent(clientGridPanel);
            workButtons.addComponent(addClient);
        });
        menu.addItem("Механики", e -> {
            workspace.removeAllComponents();
            workButtons.removeAllComponents();
            Meh.mehList.clear();
            ConnectionDB.getMehList();
            mehGrid.setItems(Meh.mehList);
            workspace.addComponent(mehGridPanel);
            workButtons.addComponent(addMeh);

        });
        menu.addItem("Заказы", e -> {
            Client.clientList.clear();
            ConnectionDB.getclientlist();
            clientGrid.setItems(Client.clientList);
            Meh.mehList.clear();
            ConnectionDB.getMehList();
            mehGrid.setItems(Meh.mehList);
            workspace.removeAllComponents();
            workButtons.removeAllComponents();
            Order.orderList.clear();
            ConnectionDB.getOrderList();
            orderGrid.setItems(Order.orderList);
            workspace.addComponent(orderGridPanel);
            workButtons.addComponent(addOrder);
            workButtons.addComponents(description, client, statustf);
            workButtons.setComponentAlignment(description, Alignment.TOP_LEFT);
            workButtons.setComponentAlignment(client, Alignment.TOP_LEFT);
            workButtons.setComponentAlignment(statustf, Alignment.TOP_LEFT);

        });
        menuLayout.addComponent(menu);
        mainLayout.addComponent(menuLayout);
        mainLayout.addComponent(workspace);
        mainLayout.addComponent(workButtons);
        mainLayout.setExpandRatio(labelLayout, 1);
        mainLayout.setExpandRatio(menuLayout, 1);
        mainLayout.setExpandRatio(workspace, 5);
        mainLayout.setExpandRatio(workButtons, 1);
        menuLayout.setSizeFull();
        menuLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);
        // КНОПКА СОХРАНЕНИЯ ДОБАВЛЕННОГО ЗАКАЗА
        addOrderAdd.addClickListener(e -> {
            orderGrid.asSingleSelect().deselectAll();
            workButtons.removeComponent(editOrder);
            workButtons.removeComponent(deleteOrder);
            Date dateS = Date.valueOf(dateStart.getValue());
            Date dateE = Date.valueOf(dateEnd.getValue());
            try {
                ConnectionDB.addOrder(descriptionOrder.getValue(), clientComboBox.getValue().getFullName(), mehComboBox.getValue().getFullName(), dateS, dateE, orderCost.getValue(), status.getValue());
                Order.orderList.clear();
                ConnectionDB.getOrderList();
                orderGrid.setItems(Order.orderList);
            } catch (Exception e1) {
                Notification.show("Заказ не был добавлен!");
            }
            workspace.removeComponent(orderAddPanel);
            descriptionOrder.clear();
            clientComboBox.clear();
            mehComboBox.clear();
            dateStart.clear();
            dateEnd.clear();
            orderCost.clear();
            status.clear();
            orderCost.setComponentError(null);
            Notification.show("Заказ успешно добавлен!");

        });
        // КНОПКА СОХРАНЕНИЯ ДОБАВЛЕННОГО КЛИЕНТА
        addClientAdd.addClickListener(e -> {
            clientGrid.asSingleSelect().deselectAll();
            workButtons.removeComponent(editClient);
            workButtons.removeComponent(deleteClient);
            try {
                ConnectionDB.addClient(lastNameClient.getValue(), firstNameClient.getValue(), patronymicClient.getValue(), phoneNumberClient.getValue());
                Client.clientList.clear();
                ConnectionDB.getclientlist();
                clientGrid.setItems(Client.clientList);
            } catch (Exception exception) {
                Notification.show("Клиент не был добавлен!");
            }
            workspace.removeComponent(clientAddPanel);
            lastNameClient.clear();
            firstNameClient.clear();
            patronymicClient.clear();
            phoneNumberClient.clear();
            lastNameClient.setComponentError(null);
            firstNameClient.setComponentError(null);
            patronymicClient.setComponentError(null);
            phoneNumberClient.setComponentError(null);
            Notification.show("Клиент успешно добавлен!");
        });
        // КНОПКА СОХРАНЕНИЯ ДОБАВЛЕННОГО МЕХАНИКА
        addMehAdd.addClickListener(e -> {
            mehGrid.asSingleSelect().deselectAll();
            workButtons.removeComponent(editMeh);
            workButtons.removeComponent(deleteMeh);
            try {
                ConnectionDB.addMeh(lastNameMeh.getValue(), firstNameMeh.getValue(), patronymicMeh.getValue(), payHourMeh.getValue());
                Meh.mehList.clear();
                ConnectionDB.getMehList();
                mehGrid.setItems(Meh.mehList);
            } catch (Exception exception) {
                Notification.show("Механик не был добавлен!");
            }
            workspace.removeComponent(mehAddPanel);
            lastNameMeh.clear();
            firstNameMeh.clear();
            patronymicMeh.clear();
            payHourMeh.clear();
            lastNameMeh.setComponentError(null);
            firstNameMeh.setComponentError(null);
            patronymicMeh.setComponentError(null);
            payHourMeh.setComponentError(null);
            Notification.show("Механик успешно добавлен!");
        });
        MyUI.addButton(workspace, orderGrid, workButtons, orderUpdPanel, orderAddPanel, orderGridPanel, editOrder, deleteOrder, addOrder);
        MyUI.addButton(workspace, clientGrid, workButtons, clientUpdPanel, clientAddPanel, clientGridPanel, editClient, editClient, addClient);
        MyUI.addButton(workspace, mehGrid, workButtons, mehUpdPanel, mehAddPanel, mehGridPanel, editMeh, editMeh, addMeh);
        // КНОПКА СОХРАНЕНИЯ ОТРЕДАКТИРОВАННОГО ЗАКАЗА
        updOrder.addClickListener(e -> {
            Date dateSUpd = Date.valueOf(dateStartUpd.getValue());
            Date dateEUpd = Date.valueOf(dateEndUpd.getValue());
            try {
                ConnectionDB.updateOrder(descriptionOrderUpd.getValue(), clientComboBoxUpd.getValue().getFullName(), mehComboBoxUpd.getValue().getFullName(),
                        dateSUpd, dateEUpd, orderCostUpd.getValue(), statusUpd.getValue(), orderGrid.asSingleSelect().getValue().getId());
                Order.orderList.clear();
                ConnectionDB.getOrderList();
                orderGrid.setItems(Order.orderList);

            } catch (Exception ignored) {
            }
            workspace.removeComponent(orderUpdPanel);
            orderGrid.asSingleSelect().deselectAll();
            workButtons.removeComponent(editOrder);
            workButtons.removeComponent(deleteOrder);
            Notification.show("Заказ успешно изменён!");
        });
        // КНОПКА СОХРАНЕНИЯ ОТРЕДАКТИРОВАННОГО КЛИЕНТА
        updClient.addClickListener(e -> {
            try {
                ConnectionDB.updateClient(lastNameClientUpd.getValue(), firstNameClientUpd.getValue(), patronymicClientUpd.getValue(),
                        phoneNumberClientUpd.getValue(), clientGrid.asSingleSelect().getValue().getId());
                Client.clientList.clear();
                ConnectionDB.getclientlist();
                clientGrid.setItems(Client.clientList);

            } catch (Exception ignored) {
            }
            workspace.removeComponent(clientUpdPanel);
            clientGrid.asSingleSelect().deselectAll();
            workButtons.removeComponent(editClient);
            workButtons.removeComponent(deleteClient);
            Notification.show("Клиент успешно изменён!");
        });
        // КНОПКА СОХРАНЕНИЯ ОТРЕДАКТИРОВАННОГО МЕХАНИКА
        updMeh.addClickListener(e -> {
            try {
                ConnectionDB.updateMeh(lastNameMehUpd.getValue(), firstNameMehUpd.getValue(), patronymicMehUpd.getValue(), payHourMehUpd.getValue(),
                        mehGrid.asSingleSelect().getValue().getId());
                Meh.mehList.clear();
                ConnectionDB.getMehList();
                mehGrid.setItems(Meh.mehList);
            } catch (Exception ignored) {
            }
            workspace.removeComponent(mehUpdPanel);
            mehGrid.asSingleSelect().deselectAll();
            workButtons.removeComponent(editMeh);
            workButtons.removeComponent(deleteMeh);
            Notification.show("Механик успешно изменён!");
        });
        // КНОПКА УДАЛЕНИЯ ЗАКАЗА
        deleteOrder.addClickListener(e -> {
            try {
                ConnectionDB.deleteOrder(orderGrid.asSingleSelect().getValue().getId());
                Order.orderList.clear();
                ConnectionDB.getOrderList();
                orderGrid.setItems(Order.orderList);
                workspace.removeComponent(orderAddPanel);
                workspace.removeComponent(orderUpdPanel);
            } catch (Exception ignored) {
            }
            orderGrid.asSingleSelect().deselectAll();
            workButtons.removeComponent(editOrder);
            workButtons.removeComponent(deleteOrder);
            Notification.show("Заказ успешно удалён!");
        });
        // КНОПКА УДАЛЕНИЯ КЛИЕНТА
        deleteClient.addClickListener(e -> {
            if (ConnectionDB.ifClient(clientGrid.asSingleSelect().getValue().getFullName()) != 0)
                Notification.show("Клиент не может быть удалён, так как у клиента назначен заказ!");
            else {
                try {
                    ConnectionDB.deleteClient(clientGrid.asSingleSelect().getValue().getId());
                    Client.clientList.clear();
                    ConnectionDB.getclientlist();
                    clientGrid.setItems(Client.clientList);
                    workspace.removeComponent(clientAddPanel);
                    workspace.removeComponent(clientUpdPanel);
                } catch (Exception ignored) {
                }
                clientGrid.asSingleSelect().deselectAll();
                workButtons.removeComponent(editClient);
                workButtons.removeComponent(deleteClient);
                Notification.show("Клиент успешно удалён!");
            }
        });
        // КНОПКА УДАЛЕНИЯ МЕХАНИКА
        deleteMeh.addClickListener(e -> {
            if (ConnectionDB.ifMeh(mehGrid.asSingleSelect().getValue().getFullName()) != 0)
                Notification.show("Клиент не может быть удалён, так как у клиента назначен заказ!");
            else {
                try {
                    ConnectionDB.deleteMeh(mehGrid.asSingleSelect().getValue().getId());
                    Meh.mehList.clear();
                    ConnectionDB.getMehList();
                    mehGrid.setItems(Meh.mehList);
                    workspace.removeComponent(mehAddPanel);
                    workspace.removeComponent(mehUpdPanel);
                } catch (Exception ignored) {
                }
                clientGrid.asSingleSelect().deselectAll();
                workButtons.removeComponent(editMeh);
                workButtons.removeComponent(deleteMeh);
                workButtons.removeComponent(showMehOrders);
                Notification.show("Механик успешно удалён!");
            }
        });
        // КНОПКА ОТМЕНТЫ ДОБАВЛЕНИЯ
        MyUI.cancelAddButton(cancelAddOrder, workspace, orderGrid, workButtons, orderAddPanel, editOrder, deleteOrder);
        MyUI.cancelAddButton(cancelAddClient, workspace, clientGrid, workButtons, clientAddPanel, editClient, deleteClient);
        MyUI.cancelAddButton(cancelAddMeh, workspace, mehGrid, workButtons, mehAddPanel, editMeh, deleteMeh);
        // КНОПКА ОТМЕНТЫ РЕДАКТИРОВАНИЯ
        MyUI.cancelUpdButton(cancelUpdOrder, workspace, orderGrid, workButtons, orderUpdPanel, editOrder, deleteOrder);
        MyUI.cancelUpdButton(cancelUpdClient, workspace, clientGrid, workButtons, clientUpdPanel, editClient, deleteClient);
        MyUI.cancelUpdButton(cancelUpdMeh, workspace, mehGrid, workButtons, mehUpdPanel, editMeh, deleteMeh);
        // КНОПКА ОТКРЫТИЯ ПАНЕЛИ РЕДАКТИРОВАНИЯ ЗАКАЗА
        editOrder.addClickListener(e -> {
            try {
                descriptionOrderUpd.setValue(orderGrid.asSingleSelect().getValue().getDescription());
                workspace.removeComponent(orderAddPanel);
            } catch (Exception ignored) {
            }
            workspace.addComponent(orderUpdPanel);
            workspace.setExpandRatio(orderGridPanel, 4);
            workspace.setExpandRatio(orderUpdPanel, 1);

        });
        // КНОПКА ОТКРЫТИЯ ПАНЕЛИ РЕДАКТИРОВАНИЯ КЛИЕНТА
        editClient.addClickListener(e -> {
            try {
                lastNameClientUpd.setValue(clientGrid.asSingleSelect().getValue().getLastName());
                firstNameClientUpd.setValue(clientGrid.asSingleSelect().getValue().getFirstName());
                patronymicClientUpd.setValue(clientGrid.asSingleSelect().getValue().getPatronymic());
                phoneNumberClientUpd.setValue(clientGrid.asSingleSelect().getValue().getPhoneNumber());
                workspace.removeComponent(clientAddPanel);
            } catch (Exception ignored) {
            }
            workspace.addComponent(clientUpdPanel);
            workspace.setExpandRatio(clientGridPanel, 4);
            workspace.setExpandRatio(clientUpdPanel, 1);

        });
        // КНОПКА ОТКРЫТИЯ ПАНЕЛИ РЕДАКТИРОВАНИЯ МЕХАНИКА
        editMeh.addClickListener(e -> {
            try {
                lastNameMehUpd.setValue(mehGrid.asSingleSelect().getValue().getLastName());
                firstNameMehUpd.setValue(mehGrid.asSingleSelect().getValue().getFirstName());
                patronymicMehUpd.setValue(mehGrid.asSingleSelect().getValue().getPatronymic());
                payHourMehUpd.setValue(mehGrid.asSingleSelect().getValue().getPayHour());
                workspace.removeComponent(clientAddPanel);
            } catch (Exception ignored) {
            }
            workspace.addComponent(mehUpdPanel);
            workspace.setExpandRatio(mehGridPanel, 4);
            workspace.setExpandRatio(mehUpdPanel, 1);
        });

        showMehOrders.addClickListener(e -> {
            if (ConnectionDB.ifMeh(mehGrid.asSingleSelect().getValue().getFullName()) == 0)
                Notification.show("У данного механика нет заказов!");
            else {
                Notification.show("Заказов у данного механика - " + ConnectionDB.ifMeh(mehGrid.asSingleSelect().getValue().getFullName()));
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////
        // ВАЛИДАЦИЯ ВВОДА
        ////////////////////////////////////////////////////////////////////////////////////////////
        MyUI.setError(lastNameClient);
        MyUI.setError(firstNameClient);
        MyUI.setError(patronymicClient);
        MyUI.setError(lastNameClientUpd);
        MyUI.setError(firstNameClientUpd);
        MyUI.setError(patronymicClientUpd);

        MyUI.setErrorEmpty(descriptionOrder);
        MyUI.setErrorEmpty(clientComboBox);
        MyUI.setErrorEmpty(mehComboBox);
        MyUI.setErrorEmpty(dateStart);
        MyUI.setErrorEmpty(dateEnd);
        MyUI.setErrorEmpty(orderCost);
        MyUI.setErrorEmpty(status);
        MyUI.setErrorEmpty(descriptionOrderUpd);
        MyUI.setErrorEmpty(clientComboBoxUpd);
        MyUI.setErrorEmpty(mehComboBoxUpd);
        MyUI.setErrorEmpty(dateStartUpd);
        MyUI.setErrorEmpty(dateEndUpd);
        MyUI.setErrorEmpty(orderCostUpd);
        MyUI.setErrorEmpty(statusUpd);

        setContent(mainLayout);


    }

    private static boolean isStr(String s) {
        try {
            Long.parseLong(s);
            return false;
        } catch (Exception ignored) {
            return true;
        }
    }

    private static void cancelUpdButton(Button cancelUpdOrder, HorizontalLayout workspace, Grid<?> orderGrid, HorizontalLayout workButtons, Panel orderUpdPanel, Button editOrder, Button deleteOrder) {
        cancelUpdOrder.addClickListener(e -> {
            workspace.removeComponent(orderUpdPanel);
            orderGrid.asSingleSelect().deselectAll();
            workButtons.removeComponent(editOrder);
            workButtons.removeComponent(deleteOrder);
        });
    }

    private static void cancelAddButton(Button cancelAdd, HorizontalLayout workspace, Grid<?> grid, HorizontalLayout workButtons, Panel addPanel, Button edit, Button delete) {
        cancelAdd.addClickListener(e -> {
            workspace.removeComponent(addPanel);
            grid.asSingleSelect().deselectAll();
            workButtons.removeComponent(edit);
            workButtons.removeComponent(delete);
        });
    }

    private static void addButton(HorizontalLayout workspace, Grid<?> grid, HorizontalLayout workButtons, Panel UpdPanel, Panel AddPanel, Panel GridPanel, Button edit, Button delete, Button add) {
        add.addClickListener(e -> {
            workspace.removeComponent(UpdPanel);
            workspace.addComponent(AddPanel);
            workspace.setExpandRatio(GridPanel, 4);
            workspace.setExpandRatio(AddPanel, 1);
            grid.asSingleSelect().deselectAll();
            workButtons.removeComponent(edit);
            workButtons.removeComponent(delete);

        });
    }

    private static void setError(TextField field) {
        field.addValueChangeListener(event -> {
            if (!isStr(event.getValue())) {
                field.setComponentError(new UserError("Проверьте правильность ввода"));
            } else if (event.getValue().isEmpty()) {
                field.setComponentError(new UserError("Поле не должно быть пустым."));
            } else field.setComponentError(null);
        });
    }

    private static void setErrorEmpty(TextField field) {
        if (field.isEmpty()) field.setComponentError(new UserError("Проверьте правильность ввода"));
        else field.setComponentError(null);
    }

    private static void setErrorEmpty(DateField field) {
        if (field.isEmpty()) field.setComponentError(new UserError("Проверьте правильность ввода"));
        else field.setComponentError(null);
    }

    private static void setErrorEmpty(ComboBox<?> field) {
        if (field.isEmpty()) field.setComponentError(new UserError("Проверьте правильность ввода"));
        else field.setComponentError(null);
    }

    private static void setErrorEmpty(TextArea field) {
        if (field.isEmpty()) field.setComponentError(new UserError("Проверьте правильность ввода"));
        else field.setComponentError(null);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
