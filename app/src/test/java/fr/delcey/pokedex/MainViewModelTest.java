package fr.delcey.pokedex;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import fr.delcey.pokedex.model.Pokemon;
import fr.delcey.pokedex.model.PokemonType;
import fr.delcey.pokedex.repository.PokemonRepository;

import static org.junit.Assert.assertEquals;

public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private final Application application = Mockito.mock(Application.class);

    private final PokemonRepository pokemonRepository = Mockito.mock(PokemonRepository.class);

    private final MutableLiveData<List<Pokemon>> pokemonsLiveData = new MutableLiveData<>();

    @Before
    public void setUp() {
        Mockito.doReturn(pokemonsLiveData).when(pokemonRepository).getPokemonsLiveData();
    }

    @Test
    public void default_state() {
        // Given
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, R.drawable.ic_baseline_airplanemode_active_24, "Bulbizarre", PokemonType.GRASS));
        pokemons.add(new Pokemon(2, R.drawable.ic_baseline_local_fire_department_24, "Salameche", PokemonType.FIRE));
        pokemons.add(new Pokemon(3, R.drawable.ic_baseline_bubble_chart_24, "Carapuce", PokemonType.WATER));

        pokemonsLiveData.setValue(pokemons);

        // When
        MainViewModel viewModel = new MainViewModel(application, pokemonRepository);
        viewModel.getPokemonsLiveData().observeForever(new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                // Then
                assertEquals(3, pokemons.size());
            }
        });
    }

    @Test
    public void with_2_pokemons_expose_2_pokemons() {
        // Given
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, R.drawable.ic_baseline_airplanemode_active_24, "Bulbizarre", PokemonType.GRASS));
        pokemons.add(new Pokemon(2, R.drawable.ic_baseline_local_fire_department_24, "Salameche", PokemonType.FIRE));

        pokemonsLiveData.setValue(pokemons);

        // When
        MainViewModel viewModel = new MainViewModel(application, pokemonRepository);
        viewModel.getPokemonsLiveData().observeForever(new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                // Then
                assertEquals(2, pokemons.size());
            }
        });
    }

    @Test
    public void with_type_button_clicked_should_expose_water_only() {
        // Given
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, R.drawable.ic_baseline_airplanemode_active_24, "Bulbizarre", PokemonType.GRASS));
        pokemons.add(new Pokemon(2, R.drawable.ic_baseline_local_fire_department_24, "Salameche", PokemonType.FIRE));
        pokemons.add(new Pokemon(3, R.drawable.ic_baseline_bubble_chart_24, "Carapuce", PokemonType.WATER));

        pokemonsLiveData.setValue(pokemons);

        // When
        MainViewModel viewModel = new MainViewModel(application, pokemonRepository);
        viewModel.onTypeButtonClicked();
        viewModel.getPokemonsLiveData().observeForever(new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                // Then
                assertEquals(1, pokemons.size());
                assertEquals(pokemons.get(0).getName(), "Carapuce");
            }
        });
    }

    @Test
    public void with_type_button_clicked_twice_should_expose_grass_only() {
        // Given
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, R.drawable.ic_baseline_airplanemode_active_24, "Bulbizarre", PokemonType.GRASS));
        pokemons.add(new Pokemon(2, R.drawable.ic_baseline_local_fire_department_24, "Salameche", PokemonType.FIRE));
        pokemons.add(new Pokemon(3, R.drawable.ic_baseline_bubble_chart_24, "Carapuce", PokemonType.WATER));

        pokemonsLiveData.setValue(pokemons);

        // When
        MainViewModel viewModel = new MainViewModel(application, pokemonRepository);
        viewModel.onTypeButtonClicked();
        viewModel.onTypeButtonClicked();
        viewModel.getPokemonsLiveData().observeForever(new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                // Then
                assertEquals(1, pokemons.size());
                assertEquals(pokemons.get(0).getName(), "Bulbizarre");
            }
        });
    }

    @Test
    public void with_type_button_clicked_thrice_should_expose_feu_only() {
        // Given
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, R.drawable.ic_baseline_airplanemode_active_24, "Bulbizarre", PokemonType.GRASS));
        pokemons.add(new Pokemon(2, R.drawable.ic_baseline_local_fire_department_24, "Salameche", PokemonType.FIRE));
        pokemons.add(new Pokemon(3, R.drawable.ic_baseline_bubble_chart_24, "Carapuce", PokemonType.WATER));

        pokemonsLiveData.setValue(pokemons);

        // When
        MainViewModel viewModel = new MainViewModel(application, pokemonRepository);
        viewModel.onTypeButtonClicked();
        viewModel.onTypeButtonClicked();
        viewModel.onTypeButtonClicked();
        viewModel.getPokemonsLiveData().observeForever(new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                // Then
                assertEquals(1, pokemons.size());
                assertEquals(pokemons.get(0).getName(), "Salameche");
            }
        });
    }

    @Test
    public void with_type_button_clicked_four_times_should_expose_water_only() {
        // Given
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, R.drawable.ic_baseline_airplanemode_active_24, "Bulbizarre", PokemonType.GRASS));
        pokemons.add(new Pokemon(2, R.drawable.ic_baseline_local_fire_department_24, "Salameche", PokemonType.FIRE));
        pokemons.add(new Pokemon(3, R.drawable.ic_baseline_bubble_chart_24, "Carapuce", PokemonType.WATER));
        pokemons.add(new Pokemon(4, R.drawable.ic_baseline_bubble_chart_24, "Tortank", PokemonType.WATER));

        pokemonsLiveData.setValue(pokemons);

        // When
        MainViewModel viewModel = new MainViewModel(application, pokemonRepository);
        viewModel.onTypeButtonClicked();
        viewModel.onTypeButtonClicked();
        viewModel.onTypeButtonClicked();
        viewModel.onTypeButtonClicked();
        viewModel.getPokemonsLiveData().observeForever(new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                // Then
                assertEquals(2, pokemons.size());
                assertEquals(pokemons.get(0).getName(), "Carapuce");
                assertEquals(pokemons.get(1).getName(), "Tortank");
            }
        });
    }
}