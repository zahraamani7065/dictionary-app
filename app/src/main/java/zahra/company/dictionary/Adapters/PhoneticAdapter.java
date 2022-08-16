package zahra.company.dictionary.Adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zahra.company.dictionary.Model.Phonetics;
import zahra.company.dictionary.R;
import zahra.company.dictionary.ViewHolders.PhoneticViewHolder;

public class PhoneticAdapter extends RecyclerView.Adapter<PhoneticViewHolder> {
    private Context context;
    private List<Phonetics> phoneticsList;

    public PhoneticAdapter(Context context, List<Phonetics> phoneticsList) {
        this.context = context;
        this.phoneticsList = phoneticsList;
    }

    @NonNull
    @Override
    public PhoneticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhoneticViewHolder(LayoutInflater.from(context).inflate(R.layout.phonetic_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticViewHolder holder, int position) {
        holder.text_phonetic.setText(phoneticsList.get(position).getText());
        holder.ib_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer player=new MediaPlayer();
                try {
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource("http:"+phoneticsList.get(position).getAudio());
                    player.prepare();
                    player.reset();
                    player.start();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(context, "could not play audio!"+e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }
}
