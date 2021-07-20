package fr.delcey.pokedex;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import fr.delcey.pokedex.repository.PokemonRepository;


public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;

    @NonNull
    private final PokemonRepository mPokemonRepository;

    public ViewModelFactory(
        @NonNull PokemonRepository pokemonRepository
    ) {
        mPokemonRepository = pokemonRepository;
    }

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(
                        new PokemonRepository()
                    );
                }
            }
        }

        return factory;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(MainApplication.getInstance(), mPokemonRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}