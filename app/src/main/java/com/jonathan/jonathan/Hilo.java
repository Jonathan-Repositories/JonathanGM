package com.jonathan.jonathan;

import Interface.Aviso;

public class Hilo implements Aviso {

    String msn = "Terminado";

    @Override
    public String Mensaje() {
        return msn;
    }
}
