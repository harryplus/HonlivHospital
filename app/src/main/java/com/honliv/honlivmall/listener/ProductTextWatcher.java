package com.honliv.honlivmall.listener;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.Product;

/**
 * Created by Rodin on 2016/11/10.
 */
public class ProductTextWatcher implements TextWatcher {
    private final EditText prodNumValue;
    private final Product privilegeProduct;
    private final Button addNumBT;
    private final Context mContext;
    private final Button subNumBT;

    public ProductTextWatcher(Context mContext, EditText prodNumValue, Product privilegeProduct, Button addNumBT, Button subNumBT) {
        this.mContext = mContext;
        this.prodNumValue = prodNumValue;
        this.privilegeProduct = privilegeProduct;
        this.addNumBT = addNumBT;
        this.subNumBT = subNumBT;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {

        String productNumStr = prodNumValue.getText().toString().trim();
        if ("".equals(productNumStr)) {
            return;
        }
        int num = 1;
        try {
            num = Integer.parseInt(s.toString());
        } catch (Exception e) {
            num = 1;
        }
        if (privilegeProduct != null && privilegeProduct.getLimitQty() != 0) {
            if (num > privilegeProduct.getLimitQty()) {
                Toast.makeText(mContext, "限购数量为(" + privilegeProduct.getLimitQty() + ")", Toast.LENGTH_SHORT).show();

                prodNumValue.removeTextChangedListener(this);
                prodNumValue.setText(privilegeProduct.getLimitQty() + "");
                prodNumValue.addTextChangedListener(this);

                addNumBT.setBackgroundResource(R.drawable.edit_product_num_add_no_enable);
                addNumBT.setClickable(false);
                return;
            } else {
                prodNumValue.removeTextChangedListener(this);
                prodNumValue.setText(num + "");
                prodNumValue.addTextChangedListener(this);

                addNumBT.setClickable(true);
            }
        }

        Editable etext = prodNumValue.getText();
        int position = etext.length();
        Selection.setSelection(etext, position);

        if (num == 1) {
            subNumBT.setBackgroundResource(R.drawable.edit_product_num_des_no_enable);
            subNumBT.setClickable(false);
        } else {
            subNumBT.setBackgroundResource(R.drawable.button_sub_number);
            subNumBT.setClickable(true);
        }
        if (num > 998) {
            addNumBT.setBackgroundResource(R.drawable.edit_product_num_add_no_enable);
            addNumBT.setClickable(false);
            return;
        }
    }
}
