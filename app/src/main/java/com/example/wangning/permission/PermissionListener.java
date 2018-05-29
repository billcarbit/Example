package com.example.wangning.permission;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */
public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermission);


}
