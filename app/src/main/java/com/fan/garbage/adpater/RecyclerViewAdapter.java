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
import com.fan.garbage.data.BmobDataObject;
import com.fan.garbage.data.ImgLoad;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private final Context context;

    private final ArrayList<Data> data = new ArrayList<>();
    private OnRecyclerViewClick onRecyclerViewClick = null;

    public RecyclerViewAdapter(Context context) {
        this.context = context;

        setOnRecyclerViewClick(new RecyclerViewAdapter.OnRecyclerViewClick() {
            @Override
            public void itemClick(View view, int position) {
                Snackbar.make(view, "你点击了布局" + position, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void buttonClick(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(data.get(position).url));
                RecyclerViewAdapter.this.context.startActivity(intent);
                Snackbar.make(view, "选择一个东西打开这个链接吧(｡･∀･)ﾉﾞ" + position, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MaterialButton button = view.findViewById(R.id.item_money);
        button.setOnClickListener(this);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        if (contents.isEmpty()) return;
        Data data = this.data.get(position);

        holder.content.setText(data.content);
        holder.title.setText(data.title);
        holder.person.setText(data.bs);
        holder.money.setText(data.money);
        holder.money.setTag(position);
        holder.itemView.setTag(position);
        ImgLoad.loadPic(context, holder.pic, data.pic);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnRecyclerViewClick(OnRecyclerViewClick onRecyclerViewClick) {
        this.onRecyclerViewClick = onRecyclerViewClick;
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

    public void addData(int limit, int skip) {
        BmobQuery<BmobDataObject> query = new BmobQuery<>();
        query.setLimit(limit).setSkip(skip).order("-updatedAt").findObjects(new FindListener<BmobDataObject>() {
            @Override
            public void done(List<BmobDataObject> list, BmobException e) {
                if (e != null) {
//                    Log.e("data>----------", e.getErrorCode() + e.getMessage());
                    e.printStackTrace();
                } else {
                    if (!list.isEmpty()) {
                        final ArrayList<Data> temp = new ArrayList<>();
                        Data data;
                        for (BmobDataObject object : list) {
                            data = new Data();
                            data.title = object.getTitle();
                            data.content = object.getContent();
                            data.pic = object.getPic();
                            data.bs = object.getBs();
                            data.url = object.getUrl();
                            data.money = object.getMoney();
                            temp.add(data);
                        }
                        RecyclerViewAdapter.this.data.addAll(temp);
                        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                            private List<Data> oldList = new ArrayList<>(RecyclerViewAdapter.this.data);
                            private List<Data> newList = temp;

                            @Override
                            public int getOldListSize() {
                                return oldList.size();
                            }

                            @Override
                            public int getNewListSize() {
                                return newList.size();
                            }

                            @Override
                            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                                return Objects.equals(oldList.get(oldItemPosition).url, newList.get(newItemPosition).url);
                            }

                            @Override
                            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                                return true;
                            }
                        });
                        diffResult.dispatchUpdatesTo(RecyclerViewAdapter.this);

                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    private static class Data {
        String title;
        String content;
        String bs;
        String pic;
        String url;
        String money;
        int type;
    }

    public interface OnRecyclerViewClick {
        void itemClick(View view, int position);

        void buttonClick(View view, int position);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView pic = itemView.findViewById(R.id.item_pic);
        final TextView title = itemView.findViewById(R.id.item_title);
        final TextView content = itemView.findViewById(R.id.item_content);
        final MaterialButton money = itemView.findViewById(R.id.item_money);

        final TextView person = itemView.findViewById(R.id.item_person);

        ViewHolder(View itemView) {
            super(itemView);
        }

    }
}
