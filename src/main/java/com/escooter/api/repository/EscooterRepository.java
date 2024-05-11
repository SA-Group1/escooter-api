package com.escooter.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.escooter.api.model.*;
@Repository
public class EscooterRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addEscooter(Escooter escooter){
        System.out.println("EXCUTE INSERT MEMBER");

        jdbcTemplate.update(String.format("INSERT INTO escooter_rental.escooter (escooter_id,model_id,status) VALUES ('%d','%s','%s')",
        escooter.getEscooterId(),escooter.getModelName(),escooter.getStatus()));
    }
}
