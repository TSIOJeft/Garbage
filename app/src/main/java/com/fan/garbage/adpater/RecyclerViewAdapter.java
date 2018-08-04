package com.fan.garbage.adpater;

import android.content.Context;
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

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import static com.fan.garbage.data.ImgLoad.loadPic;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.mViewHolder> implements View.OnClickListener {
    private final Context context;
    private ArrayList<Data> data;
    private OnRecyclerViewClick onRecyclerViewClick = null;

    public RecyclerViewAdapter(final Context context, final ArrayList<Data> data) {
        this.context = context;
        this.data = data;
        //设置点击事件
        setOnRecyclerViewClick(new RecyclerViewAdapter.OnRecyclerViewClick() {
            @Override
            public void itemClick(View view, int position) {
                Snackbar.make(view, "你点击了布局" + position, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void buttonClick(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(data.get(position).url));
                context.startActivity(intent);
                Snackbar.make(view, "选择一个东西打开这个链接吧(｡･∀･)ﾉﾞ" + position, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void picClick(View view, int position) {
                ImageView pic = new ImageView(context);
                loadPic(context, pic, data.get(position).pic);
                new AlertDialog.Builder(context)
                        .setView(pic)
                        .show();
            }
        });
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MaterialButton button = view.findViewById(R.id.item_money);
        ImageView imageView = view.findViewById(R.id.item_pic);
        button.setOnClickListener(this);
        view.setOnClickListener(this);
        imageView.setOnClickListener(this);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
//        if (contents.isEmpty()) return;
        Data data = this.data.get(position);
        loadPic(context, holder.pic, data.pic);
        holder.content.setText(data.content);
        holder.title.setText(data.title);
        holder.person.setText(data.bs);
        holder.money.setText(data.money);
        holder.money.setTag(position);
        holder.itemView.setTag(position);
//        holder.pic.setTag(position);
//        holder.pic.setTag(position);

    }

    public interface OnRecyclerViewClick {
        void itemClick(View view, int p);

        void buttonClick(View view, int p);

        void picClick(View view, int p);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void setOnRecyclerViewClick(OnRecyclerViewClick onRecyclerViewClick) {
        this.onRecyclerViewClick = onRecyclerViewClick;
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerViewClick != null) {
            if (view.getId() == R.id.item_money) {
                onRecyclerViewClick.buttonClick(view, (int) view.getTag());
            } else {
                if (view.getId() == R.id.item_pic) {
                    onRecyclerViewClick.picClick(view, (int) view.getTag());
                } else {
                    onRecyclerViewClick.itemClick(view, (int) view.getTag());
                }
            }
        }
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        final ImageView pic = itemView.findViewById(R.id.item_pic);
        final TextView title = itemView.findViewById(R.id.item_title);
        final TextView content = itemView.findViewById(R.id.item_content);
        final MaterialButton money = itemView.findViewById(R.id.item_money);

        final TextView person = itemView.findViewById(R.id.item_person);

        mViewHolder(View itemView) {
            super(itemView);
        }
    }
}

