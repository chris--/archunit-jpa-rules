package de.christianvogt.archunit;

import jakarta.persistence.*;

@Entity
public class MyEntity {
    @OneToOne(fetch = FetchType.LAZY)
    private Object field1_good;
    @OneToOne(fetch = FetchType.EAGER)
    private Object field2_bad;
    @OneToOne
    private Object field3_bad;

    @OneToMany(fetch = FetchType.LAZY)
    private Object field4_good;
    @OneToMany(fetch = FetchType.EAGER)
    private Object field5_bad;
    @OneToMany
    private Object field6_good;

    @ManyToOne(fetch = FetchType.LAZY)
    private Object field7_good;
    @ManyToOne(fetch = FetchType.EAGER)
    private Object field8_bad;
    @ManyToOne
    private Object field9_bad;

    @ManyToMany(fetch = FetchType.LAZY)
    private Object field10_good;
    @ManyToMany(fetch = FetchType.EAGER)
    private Object field11_bad;
    @ManyToMany
    private Object field12_good;

}
