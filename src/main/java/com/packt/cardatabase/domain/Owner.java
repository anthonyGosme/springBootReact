package com.packt.cardatabase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // disable for constructor generator to work

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long ownerid;
    @Getter
    @Setter
    private String firstname, lastname;
    //  cascade delete = all,   mappedBy=owner -> the car clas has a foeign field owner
    //  pas la peinne de recuperer la liste de car de a chaque fois
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Car> cars;

    public Owner(String firstname, String lastname) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Owner() {
    }


    public List<Car> getCars() {
        return new ArrayList<>(this.cars);

    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

}
