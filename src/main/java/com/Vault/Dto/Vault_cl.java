package com.Vault.Dto;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Vault_cl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int valut_id;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    // Assuming 'otherFields' is a JSONB or TEXT field in database
    @Transient // Indicates that 'otherFields' is not mapped to a database column
    private Map<String, Object> otherFields;

    // Constructors, getters, setters


    public int getId() {
        return valut_id;
    }

    public void setId(int id) {
        this.valut_id = id;
    }


    public void setValut_id(int id) {
        this.valut_id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
