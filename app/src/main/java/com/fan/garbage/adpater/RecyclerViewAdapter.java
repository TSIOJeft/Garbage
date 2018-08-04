package com.fan.garbage.adpater;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fan.garbage.R;
import com.fan.garbage.data.Data;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import static com.fan.garbage.data.ImgLoad.loadPic;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.mViewHolder> implements OnRecyclerViewClick {
    private final Context context;
    private ArrayList<Data> data;

    public RecyclerViewAdapter(final Context context, final ArrayList<Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        return new mViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
//        if (contents.isEmpty()) return;
        Data data = this.data.get(position);
        if (data != null) {
            loadPic(context, holder.pic, data.pic);
            holder.content.setText(data.content);
            holder.title.setText(data.title);
            holder.person.setText(data.bs);
            holder.money.setText(data.money);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View view, final int position) {
        if (view.getId() == R.id.item_money) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(data.get(position).url));
            context.startActivity(intent);
            Snackbar.make(view, "选择一个东西打开这个链接吧(｡･∀･)ﾉﾞ" + position, Snackbar.LENGTH_SHORT).show();
        } else {
            if (view.getId() == R.id.item_pic) {
//                ImageView pic = new ImageView(context);
//                loadPic(context, pic, data.get(position).pic);
//                new AlertDialog.Builder(context)
//                        .setView(pic)
//                        .show();
            } else {
//                new AlertDialog.Builder(context)
//                        .setTitle(data.get(position).title)
//                        .setMessage(data.get(position).content)
//                        .setPositiveButton(R.string.ok,null)
//                        .setNegativeButton(R.string.paste, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                ClipboardManager clipboardManager=(ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//                                ClipData clipData=ClipData.newPlainText("data",data.get(position).url);
//                                clipboardManager.setPrimaryClip(clipData);
//                            }
//                        })
//                        .show();
            }
        }
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        final ImageView pic = itemView.findViewById(R.id.item_pic);
        final TextView title = itemView.findViewById(R.id.item_title);
        final TextView content = itemView.findViewById(R.id.item_content);
        final MaterialButton money = itemView.findViewById(R.id.item_money);

        final TextView person = itemView.findViewById(R.id.item_person);

        final View.OnClickListener itemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerViewAdapter.this.onClick(view, getAdapterPosition());
            }
        };

        mViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(itemClickListener);
            money.setOnClickListener(itemClickListener);
            pic.setOnClickListener(itemClickListener);
        }
    }
}

