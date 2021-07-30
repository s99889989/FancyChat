package com.daxton.fancychat.task;


import com.daxton.fancychat.api.ChatKeySet;
import com.daxton.fancychat.config.FileConfig;

public class Start {

    //只在開服時執行的任務
    public static void execute(){
        //設定檔
        FileConfig.execute();
        //對話按鈕設置
        ChatKeySet.execute();
    }

}
