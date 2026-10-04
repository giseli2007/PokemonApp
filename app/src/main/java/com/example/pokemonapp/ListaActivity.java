package com.example.pokemonapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity implements PokemonAdapter.OnItemClickListener {

    private ArrayList<Pokemon> lista;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista = PokemonData.getPokemons();

        RecyclerView recyclerView = findViewById(R.id.recyclerPokemons);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PokemonAdapter(lista, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Pokemon p = lista.get(position);

        Intent intent = new Intent(ListaActivity.this, DetalhesActivity.class);
        intent.putExtra("nome", p.getNome());
        intent.putExtra("tipo", p.getTipo());
        intent.putExtra("descricao", p.getDescricao());
        intent.putExtra("imagem", p.getImagem());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        String nome = lista.get(position).getNome();

        new MaterialAlertDialogBuilder(this)
                .setTitle("Remover Pokémon")
                .setMessage("Deseja remover " + nome + " da lista?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Remover", (dialog, which) -> {
                    lista.remove(position);
                    adapter.notifyItemRemoved(position);

                    Toast.makeText(ListaActivity.this,
                            nome + " foi removido da lista.",
                            Toast.LENGTH_SHORT).show();
                })
                .show();

    }
}
