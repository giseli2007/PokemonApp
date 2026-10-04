package com.example.pokemonapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetalhesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        String nome = getIntent().getStringExtra("nome");
        String tipo = getIntent().getStringExtra("tipo");
        String descricao = getIntent().getStringExtra("descricao");
        String imagem = getIntent().getStringExtra("imagem");

        ImageView imgPokemon = findViewById(R.id.imgPokemon);
        TextView txtNome = findViewById(R.id.txtNome);
        TextView txtTipo = findViewById(R.id.txtTipo);
        TextView txtDescricao = findViewById(R.id.txtDescricao);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        txtNome.setText(nome);
        TipoCores.aplicar(txtTipo, tipo);
        txtDescricao.setText(descricao);

        // O sprite da lista é pequeno (96x96) e fica pixelado em 240dp.
        // A mesma pasta do PokeAPI tem a "official-artwork" em alta resolução: só trocamos o caminho.
        // (Se preferir o sprite original, use "imagem" direto no load.)
        String imagemGrande = imagem.replace("/sprites/pokemon/", "/sprites/pokemon/other/official-artwork/");

        Glide.with(this)
                .load(imagemGrande)
                .placeholder(R.drawable.ic_pokeball)
                .error(R.drawable.ic_pokeball)
                .into(imgPokemon);

        btnVoltar.setOnClickListener(v -> finish());
    }
}