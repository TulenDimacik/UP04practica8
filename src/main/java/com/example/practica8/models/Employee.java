package com.example.practica8.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 4,max =4, message = "Серия должна состоять из 4 цифр")
    private String series;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 6,max =6, message = "Номер должен состоять из 6 цифр")
    private String number;
    @OneToOne(optional = true, cascade = CascadeType.ALL)// обязательное или нет каскад - взаимодействие с данными в одной из сторон
    //указываем у главной стороны при удалении пользователя удаляется и сам паспорт
    //name - id паспорта при связи
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "employee")
    private Collection<Invoice> invoices;

    @OneToMany(mappedBy = "employee")
    private Collection<Contract> contracts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Collection<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Collection<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<Contract> contracts) {
        this.contracts = contracts;
    }
}
