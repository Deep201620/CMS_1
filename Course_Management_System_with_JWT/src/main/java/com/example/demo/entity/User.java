package com.example.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "User")
public class User {

    @Id
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "userName")
    @NotNull
    private String userName;
    
    @NotNull
    @Size(min=3)
    @Column(name = "userFirstName")
    private String userFirstName;
    
    @NotNull
    @Column(name = "userLastName")
    private String userLastName;
    
    @NotNull
    @Column(name = "userPassword")
    private String userPassword;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    
    private Set<Role> role;
    
 

}
