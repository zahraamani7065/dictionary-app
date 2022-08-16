package zahra.company.dictionary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zahra.company.dictionary.Model.Meanings;
import zahra.company.dictionary.R;
import zahra.company.dictionary.ViewHolders.MeaningsViewHolder;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningsViewHolder> {
    private Context context;
    private List<Meanings> meaningsList;

    public MeaningAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public MeaningsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningsViewHolder(LayoutInflater.from(context).inflate(R.layout.meanings_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningsViewHolder holder, int position) {
        holder.textView_partsOfSpeech.setText("parts of speech: "+meaningsList.get(position).getPartOfSpeech());
        holder.rv_definition.setHasFixedSize(true);
        holder.rv_definition.setLayoutManager(new GridLayoutManager(context,1));
        DefinitionAdapter definitionAdapter=new DefinitionAdapter(context,meaningsList.get(position).getDefinitions());
        holder.rv_definition.setAdapter(definitionAdapter);

    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }
}
