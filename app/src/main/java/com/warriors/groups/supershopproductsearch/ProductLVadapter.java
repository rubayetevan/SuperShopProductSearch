package com.warriors.groups.supershopproductsearch;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rubayet on 22-Dec-15.
 */
public class ProductLVadapter extends ArrayAdapter
{
    Context context;
    LayoutInflater inflater;
    ArrayList<ProductModel> productModels;

    public ProductLVadapter(Context context,ArrayList<ProductModel> productModels)
    {
        super(context, R.layout.product_list_view,productModels);
        this.context=context;
        this.productModels = productModels;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder productHolder = new ViewHolder();

        if (convertView == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_list_view,parent,false);
            productHolder.productIdTV = (TextView) convertView.findViewById(R.id.productIdTV);
            productHolder.productNameTV = (TextView) convertView.findViewById(R.id.productNameTV);
            productHolder.productPriceTV = (TextView) convertView.findViewById(R.id.productPriceTV);
            productHolder.productStatusTV = (TextView) convertView.findViewById(R.id.productStatusTV);

            convertView.setTag(productHolder);
        }
        else
        {
            productHolder = (ViewHolder) convertView.getTag();
        }
        Integer productNameId = (productModels.get(position)).getProductId();
        String productNamePosition = (productModels.get(position)).getProductName();
        String productPricePosition = (productModels.get(position)).getProductPrice();
        String productStatusPosition = (productModels.get(position)).getProductStatus();

        productHolder.productIdTV.setText(String.valueOf(productNameId));
        productHolder.productNameTV.setText(productNamePosition);
        productHolder.productPriceTV.setText(productPricePosition+" BDT");
        productHolder.productStatusTV.setText(productStatusPosition);
        if(productStatusPosition.equalsIgnoreCase("Available"))
        {
            productHolder.productStatusTV.setTextColor(0xff0000ff);
        }
        else
        {
            productHolder.productStatusTV.setTextColor(0xffff0000);
        }

        return convertView;
    }

    private static class ViewHolder
    {
     TextView productIdTV;
        TextView productNameTV;
        TextView productPriceTV;
        TextView productStatusTV;
    }
}
