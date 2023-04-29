package com.itcast.dictionary.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itcast.dictionary.Models.Definitions;
import com.itcast.dictionary.R;
import com.itcast.dictionary.ViewHolders.DefinitionViewHolder;
import com.itcast.dictionary.ViewHolders.MeaningsViewHolder;

import java.util.Dictionary;
import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionViewHolder> {
    private Context context;
    private List<Definitions> definitionsList;

    public DefinitionAdapter(Context context, List<Definitions> definitionsList) {
        this.context = context;
        this.definitionsList = definitionsList;
    }


    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DefinitionViewHolder(LayoutInflater.from(context).inflate(R.layout.definitions_list_items,parent,false));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        holder.textView_definition.setText("Definition: "+definitionsList.get(position).getDefinition());
        holder.textView_example.setText("Example: "+definitionsList.get(position).getExample());
        StringBuffer synonyms=new StringBuffer();
        StringBuffer antonyms=new StringBuffer();

        synonyms.append(definitionsList.get(position).getSynonyms());
        antonyms.append(definitionsList.get(position).getAntonyms());

        holder.textView_synonyms.setText(synonyms);
        holder.textView_antonyms.setText(antonyms);

        holder.textView_synonyms.setSelected(true);
        holder.textView_antonyms.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return definitionsList.size();
    }
}
