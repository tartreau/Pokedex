package morgan.tartreau.pokedex;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Morgan on 18/12/2017.
 */

//on fait les itemView pour chaque pokemon
public class ViewHolder extends RecyclerView.ViewHolder  {

    private ImageView spriteImageView;
    private TextView nombreTextView;
    private TextView numeroTextView;

    public ViewHolder(View itemView) {
        super(itemView);

        spriteImageView = (ImageView) itemView.findViewById(R.id.spriteImageView);
        nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
        numeroTextView = (TextView) itemView.findViewById(R.id.numeroTextView);
    }
}