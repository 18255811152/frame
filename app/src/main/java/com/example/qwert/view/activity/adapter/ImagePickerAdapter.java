package com.example.qwert.view.activity.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.qwert.R;
import com.example.qwert.view.activity.WeiXinActivity;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerAdapter.WeiXinDemoViewHolder> {

    private List<ImageItem> mData;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int maxImgCount;
    /*是否添加额外的一条添加的图片*/
    private boolean isAdd;
    private OnRecyclerViewListener listener;

    /*给主界面设置回调函数*/
    public interface OnRecyclerViewListener {
        void onItemOnClick(View view, int postion);
    }

    public void setOnItemClickListener(OnRecyclerViewListener listener) {
        this.listener = listener;
    }

    public ImagePickerAdapter(Context mContext, List<ImageItem> mData, int maxImgCount) {
        this.mContext = mContext;
        this.mData = mData;
        this.maxImgCount = maxImgCount;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        setImages(mData);
    }

    /**
     * 保存图片
     *
     * @param data
     */
    public void setImages(List<ImageItem> data) {
        mData = new ArrayList<>(data);
        if (getItemCount() < maxImgCount) {
            mData.add(new ImageItem());
            isAdd = true;
        } else {
            isAdd = false;
        }
        notifyDataSetChanged();
    }

    public List<ImageItem> getImages() {
        //由于图片未选满时，最后一张显示添加图片，因此这个方法返回真正的已选图片
        if (isAdd) return new ArrayList<>(mData.subList(0, mData.size() - 1));
        else return mData;
    }


    @NonNull
    @Override

    public WeiXinDemoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new WeiXinDemoViewHolder(mLayoutInflater.inflate(R.layout.list_item_iamge, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeiXinDemoViewHolder weiXinDemoViewHolder, int postion) {
        weiXinDemoViewHolder.bind(postion);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class WeiXinDemoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivImg;
        private int ClickPostion;

        public WeiXinDemoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImg = (ImageView) itemView.findViewById(R.id.iv_img);
        }

        /*bind 绑定数据*/
        void bind(int postion) {
            itemView.setOnClickListener(this);
            //获取点击到的item的postion位置
            ImageItem imageItem = mData.get(postion);
            if (isAdd && postion == getItemCount() - 1) {
                ivImg.setImageResource(R.drawable.list_item_drawable);
                ClickPostion = WeiXinActivity.IMAGE_ITEM_ADD;
            } else {
                ImagePicker.getInstance().getImageLoader().displayImage((Activity) mContext, imageItem.path, ivImg, 0, 0);
                ClickPostion = postion;
            }
        }

        @Override
        public void onClick(View view) {
            if (null != listener)
                listener.onItemOnClick(view, ClickPostion);
        }
    }
}
