/*
package cn.ymsys.api.common.serialize;

import com.alibaba.fastjson.JSONObject;
import com.yatop.lambda.framework.model.LambdaRootModel;

import java.io.Serializable;

public class Sequences extends LambdaRootModel implements Serializable {

    private Long incrementBy = 1L;
    private Long startWith = 0L;
    private Long nomaxValue = 0x7fffffffffffffffL;
    private boolean nocycle;
    private Long cache = 100L;

    public Long getCurrVal() {
        return startWith;
    }

    public Long getNextVal() {
        return (startWith += incrementBy);
    }

    public Long getIncrementBy() {
        return incrementBy;
    }

    public void setIncrementBy(Long incrementBy) {
        this.incrementBy = incrementBy;
    }

    public Long getStartWith() {
        return startWith;
    }

    public void setStartWith(Long startWith) {
        this.startWith = startWith;
    }

    public Long getNomaxValue() {
        return nomaxValue;
    }

    public void setNomaxValue(Long nomaxValue) {
        this.nomaxValue = nomaxValue;
    }

    public boolean isNocycle() {
        return nocycle;
    }

    public void setNocycle(boolean nocycle) {
        this.nocycle = nocycle;
    }

    public Long getCache() {
        return cache;
    }

    public void setCache(Long cache) {
        this.cache = cache;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObj = new JSONObject(10, true);
        jsonObj.put("incrementBy", this.incrementBy);
        jsonObj.put("startWith", this.startWith);
        jsonObj.put("nomaxValue", this.nomaxValue);
        jsonObj.put("nocycle", this.nocycle);
        jsonObj.put("cache", this.cache);
        return jsonObj;
    }

    @Override
    public void clear() {

    }

    @Override
    public void clearColoured() {

    }

    @Override
    public boolean isColoured() {
        return false;
    }
}
*/
