package com.elzoghby.friendlychat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ChatAdapter extends ArrayAdapter<FriendlyChat>  {

    private AppCompatActivity context;
    private List<FriendlyChat>friendlyChats;


    public ChatAdapter(AppCompatActivity context, int resource, List<FriendlyChat> objects) {
        super(context, resource, objects);

        this.context = context;
        this.friendlyChats = objects;
    }

    @Override
    public FriendlyChat getItem(int position){
        return friendlyChats.get(position);
    }

    @Override
    public View getView(final int position , View convertView , ViewGroup parent){
        ViewHolder holder;
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_message , parent , false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //boolean isPhoto = getItem(position).getPhotoUrl() != null;
        if (getItem(position).getPhotoUrl() != null){
            holder.messageTextView.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.VISIBLE);
            Picasso.with(getContext()).load(getItem(position).getPhotoUrl()).into(holder.imageView);
        }else {
            holder.messageTextView.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.GONE);
            holder.messageTextView.setText(getItem(position).getText());
        }
        holder.nameTextView.setText(getItem(position).getName());
        return convertView;

    }


    private static class ViewHolder{
        private ImageView imageView;
        private TextView messageTextView;
        private TextView nameTextView;

        public ViewHolder (View view){
            imageView = view.findViewById(R.id.photoImageView);
            messageTextView = view.findViewById(R.id.messageTextView);
            nameTextView = view.findViewById(R.id.nameTextView);
        }
    }
}
