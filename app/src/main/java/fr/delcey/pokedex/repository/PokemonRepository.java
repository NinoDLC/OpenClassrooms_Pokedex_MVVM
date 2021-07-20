package fr.delcey.pokedex.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.delcey.pokedex.R;
import fr.delcey.pokedex.model.Pokemon;
import fr.delcey.pokedex.model.PokemonType;

public class PokemonRepository {

    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public LiveData<List<Pokemon>> getPokemonsLiveData() {
        MutableLiveData<List<Pokemon>> pokemonsMutableLiveData = new MutableLiveData<>();

        executor.execute(() -> {
            publishPokemonForUser1(pokemonsMutableLiveData);
        });

        return pokemonsMutableLiveData;
    }

    private void publishPokemonForUser1(MutableLiveData<List<Pokemon>> pokemonsMutableLiveData) {

        List<Pokemon> pokemons = new ArrayList<>();
        pokemonsMutableLiveData.postValue(new ArrayList<>(pokemons));

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pokemons.add(new Pokemon(1, R.drawable.ic_baseline_airplanemode_active_24, "Bulbizarre", PokemonType.GRASS));
        pokemonsMutableLiveData.postValue(new ArrayList<>(pokemons));

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pokemons.add(new Pokemon(2, R.drawable.ic_baseline_local_fire_department_24, "Salameche", PokemonType.FIRE));
        pokemonsMutableLiveData.postValue(new ArrayList<>(pokemons));

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pokemons.add(new Pokemon(3, R.drawable.ic_baseline_bubble_chart_24, "Carapuce", PokemonType.WATER));
        pokemonsMutableLiveData.postValue(new ArrayList<>(pokemons));

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pokemons.add(new Pokemon(4, R.drawable.ic_baseline_airplanemode_active_24, "Herbizarre", PokemonType.GRASS));
        pokemonsMutableLiveData.postValue(new ArrayList<>(pokemons));

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pokemons.add(new Pokemon(5, R.drawable.ic_baseline_local_fire_department_24, "Reptincel", PokemonType.FIRE));
        pokemonsMutableLiveData.postValue(new ArrayList<>(pokemons));

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pokemons.add(new Pokemon(6, R.drawable.ic_baseline_bubble_chart_24, "Carabaffe", PokemonType.WATER));
        pokemonsMutableLiveData.postValue(new ArrayList<>(pokemons));

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pokemons.add(new Pokemon(7, R.drawable.ic_baseline_airplanemode_active_24, "Florizarre", PokemonType.GRASS));
        pokemonsMutableLiveData.postValue(new ArrayList<>(pokemons));

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pokemons.add(new Pokemon(8, R.drawable.ic_baseline_local_fire_department_24, "Dracofeu", PokemonType.FIRE));
        pokemonsMutableLiveData.postValue(new ArrayList<>(pokemons));

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pokemons.add(new Pokemon(9, R.drawable.ic_baseline_bubble_chart_24, "Tortank", PokemonType.WATER));
        pokemonsMutableLiveData.postValue(new ArrayList<>(pokemons));
    }
}
