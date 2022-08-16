package zahra.company.dictionary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zahra.company.dictionary.Model.Definitions;
import zahra.company.dictionary.R;
import zahra.company.dictionary.ViewHolders.DefinitionViewHolder;

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
        return new DefinitionViewHolder(LayoutInflater.from(context).inflate(R.layout.definitions_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        if(definitionsList.get(position).getDefinition()!=null){
        holder.textView_definition.setText("Definition :"+definitionsList.get(position).getDefinition());
        }
        holder.textView_example.setText("Example :"+definitionsList.get(position).getExample());
         StringBuilder synonyms=new StringBuilder();
         StringBuilder antonyms=new StringBuilder();
         synonyms.append(definitionsList.get(position).getSynonyms());
         antonyms.append(definitionsList.get(position).getAntonyms());
        if(synonyms!=null){
        holder.textView_synonyms.setText(synonyms);
        }
        else{
            holder.textView_antonyms.setText("-");
        }
        holder.textView_antonyms.setText(antonyms);

        holder.textView_synonyms.setSelected(true);
        holder.textView_antonyms.setSelected(true);

    }

    @Override
    public int getItemCount() {
        return definitionsList.size();
    }
}
