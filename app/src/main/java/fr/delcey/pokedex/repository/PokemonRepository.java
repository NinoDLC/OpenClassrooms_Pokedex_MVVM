package fr.delcey.pokedex.repository;

import java.util.ArrayList;
import java.util.List;

import fr.delcey.pokedex.R;
import fr.delcey.pokedex.model.Pokemon;
import fr.delcey.pokedex.model.PokemonType;

public class PokemonRepository {

    private final List<Pokemon> mPokemons;

    public PokemonRepository() {
        mPokemons = new ArrayList<>();

        mPokemons.add(new Pokemon(1, R.drawable.ic_baseline_airplanemode_active_24, "Bulbizarre", PokemonType.GRASS));
        mPokemons.add(new Pokemon(2, R.drawable.ic_baseline_local_fire_department_24, "Salameche", PokemonType.FIRE));
        mPokemons.add(new Pokemon(3, R.drawable.ic_baseline_bubble_chart_24, "Carapuce", PokemonType.WATER));
    }

    public List<Pokemon> getPokemons() {
        return new ArrayList<>(mPokemons);
    }
}
