package fr.delcey.pokedex;

import android.app.Application;

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
    private final Application mApplication;
    @NonNull
    private final PokemonRepository mPokemonRepository;

    private final MutableLiveData<List<Pokemon>> mPokemonsMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> mButtonNameMutableLiveData = new MutableLiveData<>();

    private final MutableLiveData<String> mToastMessageMutableLiveData = new MutableLiveData<>();

    private int pokemonTypeOrdinalIndex = 0;

    public MainViewModel(
        @NonNull Application application,
        @NonNull PokemonRepository pokemonRepository
    ) {
        mApplication = application;
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

    public LiveData<String> getToastMessageLiveData() {
        return mToastMessageMutableLiveData;
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

    public void onPokemonClicked(Pokemon pokemon) {
        String message = mApplication.getString(R.string.i_am, pokemon.getName());

        mToastMessageMutableLiveData.setValue(message);
    }

    private String transformOrdinalToName() {
        PokemonType pokemonType = PokemonType.values()[limitPokemonTypeOrdinal()];

        return pokemonType.name();
    }

    private int limitPokemonTypeOrdinal() {
        return pokemonTypeOrdinalIndex % PokemonType.values().length;
    }
}
