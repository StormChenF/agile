package com.agile.format;

import com.agile.constant.RETURN;

import java.util.HashMap;

/**
 * Created by tongmeng on 2017/1/9.
 */
public class Return {
    //头部信息
    public RETURN head = new RETURN();
    //相应结果
    public HashMap result = new HashMap();

    public Return(RETURN head, HashMap result) {
        this.head = head;
        this.result = result;
    }
}
