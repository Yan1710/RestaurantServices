package com.example.demo.entity;

import org.springframework.lang.NonNull;

public class Cocina {

    private Boolean Preparacion(@NonNull Pedido pedido){
        if(!pedido.isStatus()){
            return true;
        }

        return false;
    }


}
