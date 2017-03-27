package com.hejian.bilibili.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hejian.bilibili.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何健 on 2017/2/27.
 */

public class CartStorage {
    public static final String JSON_CART = "json_cart";
    private final Context mContext;
    private SparseArray<GoodsBean> sparseArray;
    private static CartStorage instance;

    private CartStorage(Context context) {
        this.mContext = context;
        sparseArray = new SparseArray<>();
        listToSparseArray();
    }

    private void listToSparseArray() {
        List<GoodsBean> beanList = getAllData();
        for (int i = 0; i < beanList.size(); i++) {
            GoodsBean goodsBean = beanList.get(i);
            sparseArray.put(goodsBean.getTid(), goodsBean);

        }
    }

    public List<GoodsBean> getAllData() {

        return getLocalData();
    }

    private List<GoodsBean> getLocalData() {
        List<GoodsBean> goodsBeans = new ArrayList<>();
        String json = CacheUtils.getString(mContext, JSON_CART);
        if (!TextUtils.isEmpty(json)) {
            goodsBeans = new Gson().fromJson(json, new TypeToken<List<GoodsBean>>() {
            }.getType());
        }

        return goodsBeans;
    }

    public static CartStorage getInstance(Context context) {
        if (instance == null) {
            synchronized (CartStorage.class) {
                if (instance == null) {
                    instance = new CartStorage(context);
                }
            }
        }
        return instance;
    }

    //添加数据
    public void addData(GoodsBean goodsBean){
        //数据添加到sparseArray
        GoodsBean tempGoodsBean = sparseArray.get(goodsBean.getTid());
        if(tempGoodsBean!=null){
            tempGoodsBean.setNumber(goodsBean.getNumber());
        }else{
            tempGoodsBean =goodsBean;
        }
        //添加到集合中
        sparseArray.put(goodsBean.getTid(),tempGoodsBean);

        //保存到本地
        saveLocal();
    }

    //删除数据
    public void deleteData(GoodsBean goodBean){
        sparseArray.delete(goodBean.getTid());
        saveLocal();
    }

    //修改数据
    public void updateData(GoodsBean goodsBean){
        sparseArray.put(goodsBean.getTid(),goodsBean);
        saveLocal();
    }

    private void saveLocal() {
        //1.把sparseArray转成List
        List<GoodsBean> goodsBeanList = sparseArrayToList();
        //2.使用Gson把List转json的String类型数据
        String saveJson = new Gson().toJson(goodsBeanList);
        //3.使用CacheUtils缓存数据
        CacheUtils.setString(mContext,JSON_CART,saveJson);


    }

    private List<GoodsBean> sparseArrayToList() {
        List<GoodsBean> goodsBeanList =new ArrayList<>();
        for(int i = 0; i <sparseArray.size() ; i++) {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }

    /**
     * 是否在购物车中存在
     * @param product_id
     * @return
     */
    public GoodsBean findDete(String product_id) {
        return  sparseArray.get(Integer.parseInt(product_id));

    }
}
