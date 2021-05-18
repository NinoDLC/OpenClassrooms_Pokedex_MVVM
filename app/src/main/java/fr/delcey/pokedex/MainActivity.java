package fr.delcey.pokedex;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.delcey.pokedex.model.Pokemon;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        MainViewModel mainViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MainViewModel.class);

        PokemonAdapter adapter = new PokemonAdapter(new PokemonAdapter.OnPokemonClickedListener() {
            @Override
            public void onPokemonClicked(Pokemon pokemon) {
                mainViewModel.onPokemonClicked(pokemon);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.main_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.filter_button);

        mainViewModel.getPokemonsLiveData().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                adapter.submitList(pokemons);
            }
        });
        mainViewModel.getButtonNameLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String buttonName) {
                button.setText(buttonName);
            }
        });
        mainViewModel.getToastMessageLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String toastMessage) {
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.onTypeButtonClicked();
            }
        });
    }
}