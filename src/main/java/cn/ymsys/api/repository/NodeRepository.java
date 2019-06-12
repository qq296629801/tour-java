//package cn.ymsys.api.repository;
//
//import com.mongodb.client.result.UpdateResult;
//import com.yatop.lambda.core.enums.NodeStateEnum;
//import com.yatop.lambda.core.nosql.repostory.base.BaseRepository;
//import com.yatop.lambda.core.orm.model.WfFlowNode;
//import com.yatop.lambda.core.utils.CoreTimeUtil;
//import com.yatop.lambda.framework.enums.DataStatusEnum;
//import com.yatop.lambda.framework.enums.LambdaExceptionEnum;
//import com.yatop.lambda.framework.exception.LambdaException;
//import com.yatop.lambda.framework.utils.DataUtil;
//import com.yatop.lambda.framework.utils.PagerUtil;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Repository;
//
//import java.util.Date;
//import java.util.List;
//
//import static org.springframework.data.mongodb.core.query.Criteria.where;
//
//@Repository
//public class NodeRepository extends BaseRepository {
//
//    /*
//     *
//     *   插入新节点信息（名称、所属项目ID、所属工作流ID、引用工作流组件ID、序号 ...）
//     *   返回插入记录
//     *
//     * */
//    public WfFlowNode insertNode(WfFlowNode node, String operId) {
//        if (DataUtil.isNull(node) ||
//                node.isNodeNameNotColoured() ||
//                node.isOwnerProjectIdNotColoured() ||
//                node.isOwnerFlowIdNotColoured() ||
//                node.isRefModuleIdNotColoured() ||
//                node.isSequenceNotColoured() ||
//                DataUtil.isEmpty(operId)) {
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Insert node info failed -- invalid insert data.", "无效插入数据");
//        }
//
//        WfFlowNode insertNode = new WfFlowNode();
//        try {
//            Date dtCurrentTime = CoreTimeUtil.getCurrentTime();
//            Long nodeId = mongoAutoidUtil.getNextSequence(WfFlowNode.class);
//            insertNode.setNodeId(nodeId);
//            insertNode.copyProperties(node);
//            insertNode.setNodeIdColoured(false);
//            insertNode.setLastTaskIdColoured(false);
//            insertNode.setNodeState(NodeStateEnum.NOT_READY.getState());
//            insertNode.setStatus(DataStatusEnum.NORMAL.getStatus());
//            insertNode.setLastUpdateTime(dtCurrentTime);
//            insertNode.setLastUpdateOper(operId);
//            insertNode.setCreateTime(dtCurrentTime);
//            insertNode.setCreateOper(operId);
//            mongoTemplate.save(insertNode);
//            return insertNode;
//        } catch (Throwable e) {
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Insert node info failed.", "插入节点信息失败", e);
//        }
//    }
//
//    /*
//     *
//     *   查询节点信息（按ID）
//     *   返回结果
//     *
//     * */
//    public WfFlowNode queryNode(Long nodeId) {
//        if (DataUtil.isNull(nodeId)) {
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Query node info failed -- invalid query condition.", "无效查询条件");
//        }
//
//        WfFlowNode node;
//        try {
//            node = mongoTemplate.findOne(new Query(where("nodeId").is(nodeId)), WfFlowNode.class);
//        } catch (Throwable e) {
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Query node info failed.", "查询节点信息失败", e);
//        }
//
//        if (DataUtil.isNull(node) || (node.getStatus() == DataStatusEnum.INVALID.getStatus()))
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Query node info failed -- invalid status or not found.", "节点信息不存在");
//
//        return node;
//    }
//
//    /*
//     *
//     *   查询节点信息（按工作流ID）
//     *   返回结果集
//     *
//     * */
//    public List<WfFlowNode> queryNode(Long flowId, PagerUtil pager) {
//        if (DataUtil.isNull(flowId)) {
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Query node info failed -- invalid query condition.", "无效查询条件");
//        }
//
//        try {
//            PagerUtil.startPage(pager);
//
//            Query query = new Query();
//            query.addCriteria(where("ownerFlowId").is(flowId));
//            query.addCriteria(where("status").is(DataStatusEnum.NORMAL.getStatus()));
//            query.with(new Sort(Sort.Direction.ASC, "createTime"));
//            return mongoTemplate.find(query, WfFlowNode.class);
//        } catch (Throwable e) {
//            PagerUtil.clearPage(pager);
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Query node info failed.", "查询节点信息失败", e);
//        }
//    }
//
//
//    /*
//     *
//     *   更新节点信息（名称、X坐标、Y坐标、最后任务ID、警告消息、节点状态、注释、概要、描述）
//     *   返回更新数量
//     *
//     * */
//    public Long updateNode(WfFlowNode node, String operId) {
//        if (DataUtil.isNull(node) || DataUtil.isNull(node.getNodeId()) || DataUtil.isEmpty(operId)) {
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Update node info failed -- invalid update condition.", "无效更新条件");
//        }
//
//        if (node.isNodeNameNotColoured() &&
//                node.isPositionXNotColoured() &&
//                node.isPositionYNotColoured() &&
//                node.isLastTaskIdNotColoured() &&
//                node.isWarningMsgNotColoured() &&
//                node.isNodeStateNotColoured() &&
//                node.isCommentNotColoured() &&
//                node.isSummaryNotColoured() &&
//                node.isDescriptionNotColoured()) {
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Update node info failed -- invalid update data.", "无效更新内容");
//        }
//
//        try {
//            WfFlowNode updateNode = new WfFlowNode();
//            updateNode.setNodeId(node.getNodeId());
//            if (node.isNodeNameColoured())
//                updateNode.setNodeName(node.getNodeName());
//            if (node.isPositionXColoured())
//                updateNode.setPositionX(node.getPositionX());
//            if (node.isPositionYColoured())
//                updateNode.setPositionY(node.getPositionY());
//            if (node.isLastTaskIdColoured())
//                updateNode.setLastTaskId(node.getLastTaskId());
//            if (node.isWarningMsgColoured())
//                updateNode.setWarningMsg(node.getWarningMsg());
//            if (node.isNodeStateColoured())
//                updateNode.setNodeState(node.getNodeState());
//            if (node.isCommentColoured())
//                updateNode.setComment(node.getComment());
//            if (node.isSummaryColoured())
//                updateNode.setSummary(node.getSummary());
//            if (node.isDescriptionColoured())
//                updateNode.setDescription(node.getDescription());
//
//            updateNode.setLastUpdateTime(CoreTimeUtil.getCurrentTime());
//            updateNode.setLastUpdateOper(operId);
//
//            node.setLastUpdateTime(updateNode.getLastUpdateTime());
//            node.setLastUpdateOper(updateNode.getLastUpdateOper());
//
//            Query query = new Query(where("nodeId").is(updateNode.getNodeId()));
//
//            Update update = new Update();
//            update.set("nodeName", updateNode.getNodeName());
//            update.set("positionX", updateNode.getPositionX());
//            update.set("positionY", updateNode.getPositionY());
//            update.set("lastTaskId", updateNode.getLastTaskId());
//            update.set("warningMsg", updateNode.getWarningMsg());
//            update.set("nodeState", updateNode.getNodeState());
//            update.set("comment", updateNode.getComment());
//            update.set("summary", updateNode.getSummary());
//            update.set("description", updateNode.getDescription());
//            update.set("lastUpdateTime", CoreTimeUtil.getCurrentTime());
//            update.set("lastUpdateOper", operId);
//            UpdateResult result = mongoTemplate.updateFirst(query, update, WfFlowNode.class);
//            return result.getModifiedCount();
//        } catch (Throwable e) {
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Update node info failed.", "更新节点信息失败", e);
//        }
//    }
//
//
//    /*
//     *
//     *   恢复节点信息
//     *   返回删除数量
//     *
//     * */
//    public Long recoverNode(Long nodeId, String operId) {
//        if (DataUtil.isNull(nodeId) || DataUtil.isEmpty(operId)) {
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Recover node info -- invalid recover condition.", "无效恢复条件");
//        }
//        try {
//            Query query = new Query(where("nodeId").is(nodeId));
//            Update update = new Update();
//            update.set("status", DataStatusEnum.NORMAL.getStatus());
//            update.set("lastUpdateTime", CoreTimeUtil.getCurrentTime());
//            update.set("lastUpdateOper", operId);
//            UpdateResult result = mongoTemplate.updateFirst(query, update, WfFlowNode.class);
//            return result.getModifiedCount();
//        } catch (Throwable e) {
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Recover node info failed.", "恢复节点信息失败", e);
//        }
//    }
//
//
//    /*
//     *
//     *   逻辑删除节点信息
//     *   返回删除数量
//     *
//     * */
//    public Long deleteNode(Long nodeId, String operId) {
//        if (DataUtil.isNull(nodeId) || DataUtil.isEmpty(operId)) {
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Delete node info -- invalid delete condition.", "无效删除条件");
//        }
//
//        try {
//            Query query = new Query(where("nodeId").is(nodeId));
//            Update update = new Update();
//            update.set("status", DataStatusEnum.INVALID.getStatus());
//            update.set("lastUpdateTime", CoreTimeUtil.getCurrentTime());
//            update.set("lastUpdateOper", operId);
//            UpdateResult result = mongoTemplate.updateFirst(query, update, WfFlowNode.class);
//            return result.getModifiedCount();
//
//        } catch (Throwable e) {
//            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
//                    "Delete node info failed.", "删除节点信息失败", e);
//        }
//    }
//}
