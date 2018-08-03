package com.fan.garbage.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fan.garbage.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import static com.fan.garbage.data.ImgLoad.LoadPic;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.mViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> contents = new ArrayList<>();
    private ArrayList<String> pics = new ArrayList<>();
    private ArrayList<String> bs = new ArrayList<>();
    private ArrayList<String> moneys = new ArrayList<>();
    private OnRecyclerViewClick onRecyclerViewClick = null;

    public RecyclerViewAdapter(Context co, ArrayList<String> t, ArrayList<String> c, ArrayList<String> p
            , ArrayList<String> b, ArrayList<String> m) {
        context = co;
        contents = c;
        titles = t;
        pics = p;
        bs = b;
        moneys = m;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MaterialButton button = view.findViewById(R.id.item_money);
        button.setOnClickListener(this);
        view.setOnClickListener(this);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        if (contents.isEmpty()) return;
        holder.content.setText(contents.get(position));
        holder.title.setText(titles.get(position));
        LoadPic(context, holder.pic, pics.get(position));
        holder.person.setText(bs.get(position));
        holder.money.setText(moneys.get(position));
        holder.money.setTag(position);
        holder.itemView.setTag(position);
    }

    public interface OnRecyclerViewClick {
        void itemClick(View v, int p);

        void buttonClick(View v, int p);
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerViewClick != null) {
            if (view.getId() == R.id.item_money) {
                onRecyclerViewClick.buttonClick(view, (int) view.getTag());
            } else {
                onRecyclerViewClick.itemClick(view, (int) view.getTag());
            }
        }
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        ImageView pic = itemView.findViewById(R.id.item_pic);
        TextView title = itemView.findViewById(R.id.item_title);
        TextView content = itemView.findViewById(R.id.item_content);
        MaterialButton money = itemView.findViewById(R.id.item_money);
        TextView person = itemView.findViewById(R.id.item_person);

        mViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setOnRecyclerViewClick(OnRecyclerViewClick onRecyclerViewClick) {
        this.onRecyclerViewClick = onRecyclerViewClick;
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}
