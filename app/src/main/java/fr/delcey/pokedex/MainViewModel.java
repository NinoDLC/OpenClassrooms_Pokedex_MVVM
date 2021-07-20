package fr.delcey.pokedex;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
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

    private final MutableLiveData<String> mToastMessageMutableLiveData = new MutableLiveData<>();

    private final MutableLiveData<PokemonType> pokemonTypeMutableLiveData = new MutableLiveData<>();

    private int pokemonTypeOrdinalIndex = 0;

    public MainViewModel(
        @NonNull Application application,
        @NonNull PokemonRepository pokemonRepository
    ) {
        mApplication = application;
        mPokemonRepository = pokemonRepository;
    }

    public LiveData<List<Pokemon>> getPokemonsLiveData() {

        MediatorLiveData<List<Pokemon>> pokemonMediatorLiveData = new MediatorLiveData<>();

        LiveData<List<Pokemon>> pokemonLiveData = mPokemonRepository.getPokemonsLiveData();

        pokemonMediatorLiveData.addSource(pokemonLiveData, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                pokemonMediatorLiveData.setValue(combine(pokemons, pokemonTypeMutableLiveData.getValue()));
            }
        });

        pokemonMediatorLiveData.addSource(pokemonTypeMutableLiveData, new Observer<PokemonType>() {
            @Override
            public void onChanged(PokemonType pokemonType) {
                pokemonMediatorLiveData.setValue(combine(pokemonLiveData.getValue(), pokemonType));
            }
        });

        return pokemonMediatorLiveData;
    }

    private List<Pokemon> combine(@Nullable List<Pokemon> pokemons, @Nullable PokemonType pokemonType) {
        if (pokemons == null) {
            return new ArrayList<>(); // Don't work when there's no pokemon in the list !
        }
        if (pokemonType == null) {
            return pokemons;
        } else {
            List<Pokemon> newPokemonList = new ArrayList<>();

            for (Pokemon pokemon : pokemons) {
                if (pokemon.getPokemonType() == pokemonType) {
                    newPokemonList.add(pokemon);
                }
            }

            return newPokemonList;
        }
    }

    public LiveData<String> getToastMessageLiveData() {
        return mToastMessageMutableLiveData;
    }

    public void onTypeButtonClicked() {
        pokemonTypeOrdinalIndex++;

        PokemonType pokemonType = PokemonType.values()[limitPokemonTypeOrdinal()];

        pokemonTypeMutableLiveData.setValue(pokemonType);
    }

    public void onPokemonClicked(Pokemon pokemon) {
        String message = mApplication.getString(R.string.i_am, pokemon.getName());

        mToastMessageMutableLiveData.setValue(message);
    }

    private int limitPokemonTypeOrdinal() {
        return pokemonTypeOrdinalIndex % PokemonType.values().length;
    }
}
