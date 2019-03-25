package com.simpleproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class ControllerUsers {


    @Autowired
    private DataSource datasource;


    @RequestMapping(value = {"/users", ""} , produces = MediaType.TEXT_PLAIN_VALUE)

    public String connectDb() throws SQLException {
        Connection connection = datasource.getConnection();
        PreparedStatement consultaBanco = connection.prepareStatement("SELECT * FROM usuarios");
        ResultSet resultBanco = consultaBanco.executeQuery();

        String resultado = "";
        while(resultBanco.next()) {
            resultado += resultBanco.getInt("id_grupo") + " "
                    + resultBanco.getString("ds_nome") + " "
                    + resultBanco.getString("ds_descricao") + "\n";
        }
        return resultado;
    }
}
