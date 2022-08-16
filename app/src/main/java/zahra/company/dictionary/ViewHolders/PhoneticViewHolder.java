package zahra.company.dictionary.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import zahra.company.dictionary.R;

public class PhoneticViewHolder extends RecyclerView.ViewHolder {
   public TextView text_phonetic;
   public ImageButton ib_audio;

    public PhoneticViewHolder(@NonNull View itemView) {
        super(itemView);
        text_phonetic=itemView.findViewById(R.id.text_phonetic);
        ib_audio=itemView.findViewById(R.id.ib_audio);

    }
}
