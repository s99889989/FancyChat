package com.daxton.fancychat.task;


import com.daxton.fancychat.api.ChatKeySet;
import com.daxton.fancychat.config.FileConfig;

public class Reload {
    //重新讀取的一些任務
    public static void execute(){
        //設定檔
        FileConfig.reload();
        //重新設置對話按鈕
        ChatKeySet.reload();
    }

}
