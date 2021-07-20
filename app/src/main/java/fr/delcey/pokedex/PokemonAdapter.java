package fr.delcey.pokedex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import fr.delcey.pokedex.model.Pokemon;

public class PokemonAdapter extends ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder> {

    @NonNull
    private final OnPokemonClickedListener mListener;

    protected PokemonAdapter(@NonNull OnPokemonClickedListener listener) {
        super(new PokemonDiffCallback());
        mListener = listener;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.PokemonViewHolder holder, int position) {
        holder.bind(getItem(position), mListener);
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iconImageView;
        private final TextView nameTextView;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);

            iconImageView = itemView.findViewById(R.id.pokemon_icon);
            nameTextView = itemView.findViewById(R.id.pokemon_name);
        }

        public void bind(@NonNull Pokemon pokemon, @NonNull OnPokemonClickedListener listener) {
            iconImageView.setImageResource(pokemon.getIconDrawableRes());
            nameTextView.setText(pokemon.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPokemonClicked(pokemon);
                }
            });
        }
    }

    public interface OnPokemonClickedListener {
        void onPokemonClicked(Pokemon pokemon);
    }

    private static class PokemonDiffCallback extends DiffUtil.ItemCallback<Pokemon> {

        @Override
        public boolean areItemsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.equals(newItem);
        }
    }
}
