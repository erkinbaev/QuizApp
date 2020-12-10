package com.natlusrun.quizapp.ui.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.natlusrun.quizapp.App;
import com.natlusrun.quizapp.R;
import com.natlusrun.quizapp.data.PreferenceUtils;
import com.natlusrun.quizapp.data.model.Theme;
import com.natlusrun.quizapp.databinding.ThemeItemBinding;
import com.natlusrun.quizapp.ui.fragments.settings.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder> {

    ArrayList<Theme> themes = new ArrayList<>();
    private ThemeItemBinding binding;
    private int position = 0;
    private OnClickListener listener;
    private Context context;
    SharedPreferences prefs = null;

    public ThemeAdapter(OnClickListener listener, Context context) {
        this.listener = listener;
        this.context = context;
        prefs = context.getSharedPreferences(App.SHARED, Context.MODE_PRIVATE);

    }

    @NonNull
    @Override
    public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ThemeItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ThemeViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeViewHolder holder, int position) {
        binding.textview.setText(themes.get(position).getName());

        binding.radioBtn.setOnClickListener(v -> click(holder.getAdapterPosition()));
        binding.elbrus.setOnClickListener(v -> click(holder.getAdapterPosition()));


        if (position == holder.getAdapterPosition())
            binding.radioBtn.setChecked(position == prefs.getInt(SettingsFragment.THEME, 22));


    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    public void click(int position) {
        this.position = position;
        listener.openFragment(position);
    }

    public void add(Theme theme) {
        themes.add(theme);
        notifyDataSetChanged();
    }

    public class ThemeViewHolder extends RecyclerView.ViewHolder {

        public ThemeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
