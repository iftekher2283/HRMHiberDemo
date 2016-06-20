/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrmhiberdemo;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Employee;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author students
 */
public class HRMHiberDemoUIController implements Initializable {
    
    private Label label;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField designationField;
    @FXML
    private DatePicker joiningDatePicker;
    
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;
    private List<Employee> employees; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        employees = new ArrayList<>();
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String designation = designationField.getText();
      //  LocalDate date = joiningDatePicker.getValue();
        String joiningDate = joiningDatePicker.getValue() + "";
        
        Employee employee = new Employee(id, name, designation, joiningDate);
        try{
        session.save(employee);
        transaction.commit();
        }catch(Exception e){
            transaction.rollback();
        }
        idField.setText("");
        nameField.setText("");
        designationField.setText("");
        joiningDatePicker.getEditor().setText("");
    }

    @FXML
    private void handleLoadAction(ActionEvent event) {
        employees = session.createCriteria(Employee.class).list();
        Employee employee = employees.get(0);
        idField.setText(employee.getId() + "");
        nameField.setText(employee.getName());
        designationField.setText(employee.getDesignation());
        joiningDatePicker.getEditor().setText(employee.getJoiningDate());
    }

    @FXML
    private void handleNextAction(ActionEvent event) {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String designation = designationField.getText();
      //  LocalDate date = joiningDatePicker.getValue();
        String joiningDate = joiningDatePicker.getValue() + "";
        
        if(employees != null && !name.equals("") && !designation.equals("")){
            Employee loadedEmployee = new Employee(id, name, designation, joiningDate);
            if(employees.indexOf(loadedEmployee) != (employees.size() - 1)){
                Employee nextEmployee = employees.get(employees.indexOf(loadedEmployee) + 1);
                idField.setText(nextEmployee.getId() + "");
                nameField.setText(nextEmployee.getName());
                designationField.setText(nextEmployee.getDesignation());
                joiningDatePicker.getEditor().setText(nextEmployee.getJoiningDate());
            }
        }
    }

    @FXML
    private void handlePrevAction(ActionEvent event) {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String designation = designationField.getText();
      //  LocalDate date = joiningDatePicker.getValue();
        String joiningDate = joiningDatePicker.getValue() + "";
        
        if(employees != null && !name.equals("") && !designation.equals("")){
            Employee loadedEmployee = new Employee(id, name, designation, joiningDate);
            if (employees.indexOf(loadedEmployee) > 0){
                Employee prevEmployee = employees.get(employees.indexOf(loadedEmployee) - 1);
                idField.setText(prevEmployee.getId() + "");
                nameField.setText(prevEmployee.getName());
                designationField.setText(prevEmployee.getDesignation());
                joiningDatePicker.getEditor().setText(prevEmployee.getJoiningDate());
            }
        }
    }
    
}
