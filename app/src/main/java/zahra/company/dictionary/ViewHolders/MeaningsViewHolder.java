package zahra.company.dictionary.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import zahra.company.dictionary.R;

public class MeaningsViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_partsOfSpeech;
    public RecyclerView rv_definition;
    public MeaningsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_partsOfSpeech=itemView.findViewById(R.id.textView_partsOfSpeech);
        rv_definition=itemView.findViewById(R.id.rv_definition);

    }
}
