package de.christianvogt.archunit;

import jakarta.persistence.*;

@Entity
public class MyEntityGetterBased {
    @OneToOne(fetch = FetchType.LAZY)
    public Object getField1_good() {
        return null;
    }
    @OneToOne(fetch = FetchType.EAGER)
    public Object getField2_bad() {
        return null;
    }
    @OneToOne
    public Object getField3_bad() {
        return null;
    }

    @OneToMany(fetch = FetchType.LAZY)
    public Object getField4_good() {
        return null;
    }
    @OneToMany(fetch = FetchType.EAGER)
    public Object getField5_bad() {
        return null;
    }
    @OneToMany
    public Object getField6_good() {
        return null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Object getField7_good() {
        return null;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    public Object getField8_bad() {
        return null;
    }
    @ManyToOne
    public Object getField9_bad() {
        return null;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    public Object getField10_good() {
        return null;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    public Object getField11_bad() {
        return null;
    }
    @ManyToMany
    public Object getField12_good() {
        return null;
    }

}
