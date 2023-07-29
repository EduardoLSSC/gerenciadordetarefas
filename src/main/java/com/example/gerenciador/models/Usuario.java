package com.example.gerenciador.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    protected String password;
}
