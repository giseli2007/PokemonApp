package com.example.pokemonapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    // A Activity implementa esta interface para saber quando houve clique
    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemLongClick(int position);
    }

    private final ArrayList<Pokemon> lista;
    private final OnItemClickListener listener;

    public PokemonAdapter(ArrayList<Pokemon> lista, OnItemClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    // 1) Cria UMA linha (infla o layout_item.xml). Só roda algumas vezes: o suficiente para cobrir a tela.
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);
        return new PokemonViewHolder(view);
    }

    // 2) Preenche a linha com os dados. Roda toda vez que uma linha reaparece na tela,
    //    por isso TODOS os campos precisam ser preenchidos aqui.
    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon p = lista.get(position);

        holder.txtNome.setText(p.getNome());
        TipoCores.aplicar(holder.txtTipo, p.getTipo());

        Glide.with(holder.itemView.getContext())
                .load(p.getImagem())
                .placeholder(R.drawable.ic_pokeball)
                .error(R.drawable.ic_pokeball)
                .into(holder.imgPokemon);
    }

    // 3) Quantas linhas existem no total
    @Override
    public int getItemCount() {
        return lista.size();
    }

    // ViewHolder: guarda as referências das views da linha (evita findViewById a cada rolagem)
    class PokemonViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPokemon;
        TextView txtNome;
        TextView txtTipo;

        PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPokemon = itemView.findViewById(R.id.imgPokemon);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtTipo = itemView.findViewById(R.id.txtTipo);

            // Pergunta a posição NA HORA do clique (e não no bind): depois de remover um item,
            // as posições mudam, e uma posição guardada antes ficaria errada.
            itemView.setOnClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    listener.onItemClick(pos);
                }
            });

            itemView.setOnLongClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    listener.onItemLongClick(pos);
                }
                return true; // true = "consumi o clique longo" (não dispara o clique simples depois)
            });
        }
    }
}