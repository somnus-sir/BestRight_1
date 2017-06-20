package com.example.administrator.bestright.bean;

import java.util.List;

/**
 * Created by whn on 2016/12/23.
 */

public class ReceivedInfo_f13 {
    public int error_code;
    public String reason;
    public EntityResult result;
    public static class EntityResult{
        public List<DataEntity> data;

        public static class DataEntity{
            public String content;
            public String hashId;
            public String unixtime;
            public String updatetime;

            @Override
            public String toString() {
                return "DataEntity{" +
                        "content='" + content + '\'' +
                        ", hashId='" + hashId + '\'' +
                        ", unixtime='" + unixtime + '\'' +
                        ", updatetime='" + updatetime + '\'' +
                        '}';
            }
        }


        @Override
        public String toString() {
            return "EntityResult{" +
                    "data=" + data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ReceivedInfo{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
