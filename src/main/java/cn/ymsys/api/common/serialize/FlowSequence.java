/*
package cn.ymsys.api.common.serialize;

import com.alibaba.fastjson.JSONObject;
import com.yatop.lambda.framework.model.LambdaRootModel;

import java.io.Serializable;

public class FlowSequence extends LambdaRootModel implements Serializable {
    private Sequences nodeSequence = new Sequences();
    private Sequences linkSequence = new Sequences();
    private Sequences nodePortSequence = new Sequences();

    public Sequences getNodeSequence() {
        return nodeSequence;
    }

    public void setNodeSequence(Sequences nodeSequence) {
        this.nodeSequence = nodeSequence;
    }

    public Sequences getLinkSequence() {
        return linkSequence;
    }

    public void setLinkSequence(Sequences linkSequence) {
        this.linkSequence = linkSequence;
    }

    public Sequences getNodePortSequence() {
        return nodePortSequence;
    }

    public void setNodePortSequence(Sequences nodePortSequence) {
        this.nodePortSequence = nodePortSequence;
    }


    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("nodeSequence", nodeSequence.toJSON());
        jsonObject.put("linkSequence", linkSequence.toJSON());
        jsonObject.put("nodePortSequence", nodePortSequence.toJSON());
        return jsonObject;
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
