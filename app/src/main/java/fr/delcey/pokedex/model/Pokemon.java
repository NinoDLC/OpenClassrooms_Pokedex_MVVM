package fr.delcey.pokedex.model;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import java.util.Objects;

public class Pokemon {

    private final int id;

    @DrawableRes
    private final int iconDrawableRes;

    private final String name;

    private final PokemonType pokemonType;

    public Pokemon(int id, int iconDrawableRes, String name, PokemonType pokemonType) {
        this.id = id;
        this.iconDrawableRes = iconDrawableRes;
        this.name = name;
        this.pokemonType = pokemonType;
    }

    public int getId() {
        return id;
    }

    public int getIconDrawableRes() {
        return iconDrawableRes;
    }

    public String getName() {
        return name;
    }

    public PokemonType getPokemonType() {
        return pokemonType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return id == pokemon.id &&
            iconDrawableRes == pokemon.iconDrawableRes &&
            Objects.equals(name, pokemon.name) &&
            pokemonType == pokemon.pokemonType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iconDrawableRes, name, pokemonType);
    }

    @NonNull
    @Override
    public String toString() {
        return "Pokemon{" +
            "id=" + id +
            ", iconDrawableRes=" + iconDrawableRes +
            ", name='" + name + '\'' +
            ", pokemonType=" + pokemonType +
            '}';
    }
}
