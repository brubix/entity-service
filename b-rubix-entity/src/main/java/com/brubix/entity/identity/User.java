package com.brubix.entity.identity;

import com.brubix.entity.communication.Email;
import com.brubix.entity.communication.Phone;
import com.brubix.entity.communication.Social;
import com.brubix.entity.inventory.Address;
import com.brubix.entity.inventory.DocumentInfo;
import com.brubix.entity.inventory.Institution;
import com.brubix.entity.inventory.MileStone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = true, length = 100)
    private String lastName;

    @Column(name = "birth_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DocumentInfo> documents;

    @Embedded
    private MileStone mileStone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phones;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Email> emails;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "verified", nullable = false)
    private boolean verified;

    @ManyToOne(fetch = FetchType.LAZY)
    private Institution institution;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "social_id")
    private Social social;
}
