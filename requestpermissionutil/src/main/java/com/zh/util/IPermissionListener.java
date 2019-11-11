package com.zh.util;

import java.util.List;

/**
 * create by zj on 2019/10/23
 */
public interface IPermissionListener {
    void success(List<String> permissions);
    void cancel();
}
