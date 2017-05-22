package com.developer.c_alicio.sessaodatarde;

import java.io.Serializable;

/**
 * Created by c_ali on 09/05/2017.
 */


public class Usuario implements Serializable{

    String login;
    String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
