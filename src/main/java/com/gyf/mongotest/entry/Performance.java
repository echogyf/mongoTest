package com.gyf.mongotest.entry;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection = "performance")
public class Performance {
    @Id
    private String id;

    @Indexed
    private String myUuid;

    private List<Integer> ids;
    @Indexed(expireAfterSeconds = 1260) // 设置过期时间为二十分钟
    private Date expirationDate; // 添加一个用于TTL索引的字段

    public Performance(String myUuid, List<Integer> ids) {
        this.myUuid = myUuid;
        this.ids = ids;
        this.expirationDate = new Date(System.currentTimeMillis() + 1260 * 1000); // 设置过期时间
    }

    public Performance() {

    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMyUuid() {
        return myUuid;
    }

    public void setMyUuid(String myUuid) {
        this.myUuid = myUuid;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "id='" + id + '\'' +
                ", UUID='" + myUuid + '\'' +
                ", ids=" + ids +
                ", expirationDate=" + expirationDate +
                '}';
    }
}