package com.softdesign.gameofthrones.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.gameofthrones.R;
import com.softdesign.gameofthrones.data.model.Character;
import com.softdesign.gameofthrones.utils.GameofthronesApplication;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder> {

    private Context mContext;
    private CharacterViewHolder.CustomClickListener mClickListener;

    private List<Character> mList;
    private int mHouseIcon;


    public CharactersAdapter(int houseIcon, ArrayList<Character> list, CharacterViewHolder.CustomClickListener clickListener) {
        mList = list;
        mHouseIcon = houseIcon;
        mClickListener = clickListener;
    }


    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_characters_list, parent, false);
        return new CharacterViewHolder(v, mClickListener);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {

        Character character = mList.get(position);
        holder.mCharacterName.setText(character.getName());
        holder.mCharacterTitles.setText(character.getTitles());

        Picasso.with(mContext)
                .load(mHouseIcon)
                .resizeDimen(R.dimen.spacing_L_56, R.dimen.spacing_L_56)
                .into(holder.mIcon);

    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }


    public List<Character> getList() {
        return mList;
    }


    public static class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView mIcon;
        protected TextView mCharacterName, mCharacterTitles;

        private CustomClickListener mListener;


        public CharacterViewHolder(View itemView, CustomClickListener listener) {
            super(itemView);
            this.mListener = listener;
            itemView.setOnClickListener(this);

            mIcon = (ImageView) itemView.findViewById(R.id.character_icon_iv);
            mCharacterName = (TextView) itemView.findViewById(R.id.character_name_tv);
            mCharacterTitles = (TextView) itemView.findViewById(R.id.character_titles_tv);
        }


        @Override
        public void onClick(View v) {
            mListener.onUserItemClickListener(getAdapterPosition());
        }


        public interface CustomClickListener {
            void onUserItemClickListener(int position);
        }
    }
}
