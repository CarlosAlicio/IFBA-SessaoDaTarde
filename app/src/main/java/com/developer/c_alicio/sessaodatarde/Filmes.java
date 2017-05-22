package com.developer.c_alicio.sessaodatarde;

import java.io.Serializable;

/**
 * Created by c_ali on 09/05/2017.
 */

public class Filmes implements Serializable{

    String[] titulo = {"X-man", "Os Vingadores", "Wolverine", "Hulk", "Thor", "Homem-Aranha", "Captão América", "DeadPool"};

    String[] descricao = {"Xman_descricao", "OsVingadores_descricao", "Wolverine_descricao", "Hulk_descricao", "Thor_descricao", "Homem-Aranha_descricao", "CaptaoAmerica_descricao", "DeadPool_descricao"};

    int[] capas = {R.drawable.xman, R.drawable.osvingadores, R.drawable.wolverine, R.drawable.hulk, R.drawable.thor, R.drawable.homemaranha,  R.drawable.captaoamerica,  R.drawable.deadpool };

    public String[] getTitulo() {
        return titulo;
    }

    public String[] getDescricao() {
        return descricao;
    }

    public int[] getCapas() {
        return capas;
    }
}
