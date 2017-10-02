package com.brubix.entity.identity;

import com.brubix.entity.communication.Email;
import com.brubix.entity.communication.Phone;
import com.brubix.entity.identity.Role;
import com.brubix.entity.inventory.Address;
import com.brubix.entity.inventory.KYC;
import com.brubix.entity.inventory.MileStone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by sanjeev.singh1 on 11/09/17.
 */

@Entity
@Table(name = "user", catalog = "brubix")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true, fetch = FetchType.LAZY)
    private KYC kyc;

    @Embedded
    private MileStone mileStone;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Phone> phones;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Email> emails;


    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

}