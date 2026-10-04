package com.example.pokemonapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;

import java.text.Normalizer;

/**
 * Pinta o "selo" de tipo (Fogo, Água...) com a cor do tipo.
 * Usa só o primeiro tipo quando o Pokémon tem dois ("Planta / Veneno" -> Planta).
 */
public class TipoCores {

    public static void aplicar(TextView badge, String tipoCompleto) {
        int cor = ContextCompat.getColor(badge.getContext(), getCorRes(tipoCompleto));

        badge.setText(tipoCompleto);
        ViewCompat.setBackgroundTintList(badge, ColorStateList.valueOf(cor));

        // Fundo claro (ex.: Elétrico) -> texto preto; fundo escuro -> texto branco
        badge.setTextColor(ColorUtils.calculateLuminance(cor) > 0.5 ? Color.BLACK : Color.WHITE);
    }

    private static int getCorRes(String tipoCompleto) {
        String primeiro = tipoCompleto.split("/")[0].trim();

        // Remove acentos e deixa minúsculo: "Água" -> "agua", "Elétrico" -> "eletrico"
        String chave = Normalizer.normalize(primeiro, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();

        switch (chave) {
            case "fogo":     return R.color.tipo_fogo;
            case "agua":     return R.color.tipo_agua;
            case "eletrico": return R.color.tipo_eletrico;
            case "planta":   return R.color.tipo_planta;
            case "gelo":     return R.color.tipo_gelo;
            case "lutador":  return R.color.tipo_lutador;
            case "veneno":   return R.color.tipo_veneno;
            case "terra":    return R.color.tipo_terra;
            case "voador":   return R.color.tipo_voador;
            case "psiquico": return R.color.tipo_psiquico;
            case "inseto":   return R.color.tipo_inseto;
            case "pedra":    return R.color.tipo_pedra;
            case "fantasma": return R.color.tipo_fantasma;
            case "dragao":   return R.color.tipo_dragao;
            case "sombrio":  return R.color.tipo_sombrio;
            case "aco":      return R.color.tipo_aco;
            case "fada":     return R.color.tipo_fada;
            default:         return R.color.tipo_normal;
        }
    }
}
