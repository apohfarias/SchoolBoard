package com.example.apoh.schoolboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class ItemHolderAula extends RecyclerView.ViewHolder {
    ImageView imageView;

    public ItemHolderAula(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView2);

    }
}

public class ItemListaAula {
    ImageView imageView;

    ItemListaAula( ImageView simageView){
        imageView = simageView;

    }
}
