package fr.delcey.pokedex;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import fr.delcey.pokedex.model.Pokemon;
import fr.delcey.pokedex.model.PokemonType;
import fr.delcey.pokedex.repository.PokemonRepository;

public class MainViewModel extends ViewModel {

    @NonNull
    private final PokemonRepository mPokemonRepository;

    private final MutableLiveData<List<Pokemon>> mPokemonsMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> mButtonNameMutableLiveData = new MutableLiveData<>();

    private int pokemonTypeOrdinalIndex = 0;

    public MainViewModel(
        @NonNull PokemonRepository pokemonRepository
    ) {
        mPokemonRepository = pokemonRepository;

        mPokemonsMutableLiveData.setValue(pokemonRepository.getPokemons());
        mButtonNameMutableLiveData.setValue(transformOrdinalToName());
    }

    public LiveData<List<Pokemon>> getPokemonsLiveData() {
        return mPokemonsMutableLiveData;
    }

    public LiveData<String> getButtonNameLiveData() {
        return mButtonNameMutableLiveData;
    }

    public void onTypeButtonClicked() {
        pokemonTypeOrdinalIndex++;
        mButtonNameMutableLiveData.setValue(transformOrdinalToName());

        PokemonType pokemonType = PokemonType.values()[limitPokemonTypeOrdinal()];

        List<Pokemon> pokemons = new ArrayList<>();

        for (Pokemon pokemon : mPokemonRepository.getPokemons()) {
            if (pokemon.getPokemonType() == pokemonType) {
                pokemons.add(pokemon);
            }
        }

        mPokemonsMutableLiveData.setValue(pokemons);
    }

    private String transformOrdinalToName() {
        PokemonType pokemonType = PokemonType.values()[limitPokemonTypeOrdinal()];

        return pokemonType.name();
    }

    private int limitPokemonTypeOrdinal() {
        return pokemonTypeOrdinalIndex % PokemonType.values().length;
    }
}
