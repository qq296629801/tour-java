/*
package cn.ymsys.api.common.serialize;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.yatop.lambda.core.orm.model.*;
import com.yatop.lambda.framework.enums.LambdaExceptionEnum;
import com.yatop.lambda.framework.exception.LambdaException;
import com.yatop.lambda.framework.utils.DataUtil;
import com.yatop.lambda.workflow.core.config.ComponentConfigHelper;
import com.yatop.lambda.workflow.core.config.ModuleConfigHelper;
import com.yatop.lambda.workflow.core.context.WorkflowContext;
import com.yatop.lambda.workflow.core.mgr.workflow.node.parameter.ParameterHelper;
import com.yatop.lambda.workflow.core.richmodel.workflow.Workflow;
import com.yatop.lambda.workflow.core.richmodel.workflow.charvalue.XCharValue;
import com.yatop.lambda.workflow.core.richmodel.workflow.component.characteristic.CmptChar;
import com.yatop.lambda.workflow.core.richmodel.workflow.node.*;
import com.yatop.lambda.workflow.core.utils.CollectionUtil;

import java.util.List;
import java.util.TreeMap;

*/
/**
 * 序列化工具
 *//*

public class SerializerUtil {
    private static String FLOW_CONTENT_KEY_NODES = "@Nodes";
    private static String FLOW_CONTENT_KEY_NODE_PARAMETERS = "@Parameters";
    private static String FLOW_CONTENT_KEY_NODE_OPTIMIZE_PARAMETERS = "@OptimizeParameters";
    private static String FLOW_CONTENT_KEY_NODE_PARAMETER_ENTITY_OBJECT = "@EntityObject";
    private static String FLOW_CONTENT_KEY_NODE_PARAMETER_CHAR_VALUE_ATTRIBUTE = "charValue";
    private static String FLOW_CONTENT_KEY_NODE_INPUT_PORTS = "@InputPorts";
    private static String FLOW_CONTENT_KEY_NODE_OUTPUT_PORTS = "@OutputPorts";
    private static String FLOW_CONTENT_KEY_LINKS = "@Links";
    private static String FLOW_CONTENT_KEY_SEQUENCES = "@Sequences";
    private static String FLOW_CONTENT_KEY_NODE_OUTPUTS = "@Outputs";
    private static String FLOW_CONTENT_KEY_NODE_OUTPUT_PORT_SCHEMAS = "@Schemas";
    private static String FLOW_CONTENT_KEY_NODE_DELETE_QUEUES = "@NodeDeleteQueues";

    private Workflow workflow;
    private TreeMap<Long, Node> nodes = new TreeMap<Long, Node>();
    private TreeMap<Long, NodeLink> links = new TreeMap<Long, NodeLink>();
    private FlowSequence flowSequence = new FlowSequence();
    private TreeMap<Long, NodeDeleteQueue> nodeDeleteQueues = new TreeMap<Long, NodeDeleteQueue>();


    */
/**
     * 转换成java对象
     *
     * @param workflowContext
     *//*

    public void transformToObject(WorkflowContext workflowContext) {
        parseJavaObject(workflowContext);

        if (this.nodeCount() > 0) {
            for (Node node : this.getNodes()) {
                workflowContext.putNode(node);

                if (node.inputNodePortCount() > 0) {
                    for (NodeInputPort inputPort : node.getInputNodePorts()) {
                        workflowContext.putInputPort(inputPort);
                    }
                }

                if (node.outputNodePortCount() > 0) {
                    for (NodeOutputPort outputPort : node.getOutputNodePorts()) {
                        workflowContext.putOutputPort(outputPort);
                    }
                }
            }

            if (this.linkCount() > 0) {
                for (NodeLink link : this.getLinks()) {
                    workflowContext.putLink(link);
                }
            }
        }

        if (this.deleteQueueCount() > 0) {
            for (NodeDeleteQueue nodeDeleteQueue : this.getNodeDeleteQueues()) {
                workflowContext.putNodeDeleteQueues(nodeDeleteQueue);
            }
        }

        if (DataUtil.isNotNull(flowSequence)) {
            workflowContext.setFlowSequence(flowSequence);
        }
    }

    */
/**
     * 转换成json对象
     *
     * @param workflowContext
     *//*

    public void transformToJSON(WorkflowContext workflowContext) {
        flowSequence = workflowContext.getFlowSequence();
        workflow = workflowContext.getWorkflow();

        if (workflowContext.nodeCount() > 0) {
            for (Node node : workflowContext.getNodes()) {
                if (!CollectionUtil.containsKey(nodes, node.data().getNodeId())) {
                    this.putNode(node);
                }
            }

            if (workflowContext.linkCount() > 0) {
                for (NodeLink link : workflowContext.getLinks()) {
                    if (!CollectionUtil.containsKey(links, link.data().getLinkId())) {
                        this.putLink(link);
                    }
                }
            }
        }

        if (workflowContext.deleteQueueCount() > 0) {
            for (NodeDeleteQueue nodeDeleteQueue : workflowContext.getNodeDeleteQueues()) {
                if (!CollectionUtil.containsKey(nodeDeleteQueues, nodeDeleteQueue.data().getNodeId())) {
                    this.putNodeDeleteQueue(nodeDeleteQueue);
                }
            }
        }

        parseJSONObject();
    }

    private void parseJSONObject() {
        JSONObject jsonContent = new JSONObject(9, true);
        JSONArray jsonNodes = new JSONArray(this.nodes.size());
        JSONArray jsonLinks = new JSONArray(this.links.size());
        JSONArray jsonNodeDeleteQueues = new JSONArray(this.nodeDeleteQueues.size());
        if (nodeCount() > 0) {
            for (Node node : getNodes()) {
                JSONObject jsonNode = node.toJSON();
                JSONArray jsonParameters = new JSONArray();
                JSONArray jsonOptimizeParameters = new JSONArray();
                JSONArray jsonInputPorts = new JSONArray();
                JSONArray jsonOutputPorts = new JSONArray();
                JSONArray jsonOutputs = new JSONArray();
                JSONArray jsonSchemas = new JSONArray();
                if (node.parameterCount() > 0) {
                    for (NodeParameter parameter : node.getParameters()) {
                        JSONObject jsonParameter = parameter.toJSON();
                        if (parameter.getValue().isEntityDataType()) {
                            jsonParameter.put(FLOW_CONTENT_KEY_NODE_PARAMETER_ENTITY_OBJECT, parameter.getValue().toEntityJSON());
                            jsonParameter.put(FLOW_CONTENT_KEY_NODE_PARAMETER_CHAR_VALUE_ATTRIBUTE, "Entity Object");
                        }
                        jsonParameters.add(jsonParameter);
                    }
                }

                if (node.optimizeParameterCount() > 0) {
                    for (NodeParameter parameter : node.getOptimizeParameters()) {
                        JSONObject jsonParameter = parameter.toJSON();
                        if (parameter.getValue().isEntityDataType()) {
                            jsonParameter.put(FLOW_CONTENT_KEY_NODE_PARAMETER_ENTITY_OBJECT, parameter.getValue().toEntityJSON());
                            jsonParameter.put(FLOW_CONTENT_KEY_NODE_PARAMETER_CHAR_VALUE_ATTRIBUTE, "Entity Object");
                        }
                        jsonOptimizeParameters.add(jsonParameter);
                    }
                }

                if (node.inputNodePortCount() > 0) {
                    for (NodeInputPort inputPort : node.getInputNodePorts()) {
                        JSONObject jsonInputPort = inputPort.toJSON();
                        jsonInputPorts.add(jsonInputPort);
                    }
                }

                if (node.outputNodePortCount() > 0) {
                    for (NodeOutputPort outputPort : node.getOutputNodePorts()) {
                        if (DataUtil.isNotNull(outputPort.getSchema())) {
                            JSONObject jsonSchema = outputPort.getSchema().toJSON();
                            jsonSchemas.add(jsonSchema);
                        }
                        JSONObject jsonOutputPort = outputPort.toJSON();
                        jsonOutputPorts.add(jsonOutputPort);
                    }
                }

                if (node.outputCount() > 0) {
                    for (NodeOutput nodeOutput : node.getOutputs()) {
                        JSONObject jsonOutput = nodeOutput.toJSON();
                        jsonOutputs.add(jsonOutput);
                    }
                }

                jsonNode.put(FLOW_CONTENT_KEY_NODE_OUTPUT_PORT_SCHEMAS, jsonSchemas);
                jsonNode.put(FLOW_CONTENT_KEY_NODE_OUTPUTS, jsonOutputs);
                jsonNode.put(FLOW_CONTENT_KEY_NODE_PARAMETERS, jsonParameters);
                jsonNode.put(FLOW_CONTENT_KEY_NODE_OPTIMIZE_PARAMETERS, jsonOptimizeParameters);
                jsonNode.put(FLOW_CONTENT_KEY_NODE_INPUT_PORTS, jsonInputPorts);
                jsonNode.put(FLOW_CONTENT_KEY_NODE_OUTPUT_PORTS, jsonOutputPorts);
                jsonNodes.add(jsonNode);
            }

            if (linkCount() > 0) {
                for (NodeLink link : getLinks()) {
                    JSONObject jsonLink = link.toJSON();
                    jsonLinks.add(jsonLink);
                }
            }

            if (deleteQueueCount() > 0) {
                for (NodeDeleteQueue nodeDeleteQueue : getNodeDeleteQueues()) {
                    JSONObject jsonDeleteQueue = nodeDeleteQueue.toJSON();
                    jsonNodeDeleteQueues.add(jsonDeleteQueue);
                }
            }
        }

        jsonContent.put(FLOW_CONTENT_KEY_NODE_DELETE_QUEUES, jsonNodeDeleteQueues);
        jsonContent.put(FLOW_CONTENT_KEY_NODES, jsonNodes);
        jsonContent.put(FLOW_CONTENT_KEY_LINKS, jsonLinks);
        jsonContent.put(FLOW_CONTENT_KEY_SEQUENCES, flowSequence.toJSON());

        workflow.data().setFlowContent(DataUtil.toPrettyJSONString(jsonContent));
        jsonContent.clear();
    }

    private void parseJavaObject(WorkflowContext workflowContext) {
        workflow = workflowContext.getWorkflow();
        try {
            if (DataUtil.isNotEmpty(workflow.data().getFlowContent())) {
                JSONObject jsonContent = JSONObject.parseObject(workflow.data().getFlowContent(), Feature.OrderedField);
                JSONArray jsonNodes = jsonContent.getJSONArray(FLOW_CONTENT_KEY_NODES);
                JSONArray jsonLinks = jsonContent.getJSONArray(FLOW_CONTENT_KEY_LINKS);
                JSONArray jsonNodeDeleteQueues = jsonContent.getJSONArray(FLOW_CONTENT_KEY_NODE_DELETE_QUEUES);
                JSONObject jsonFlowSequence = jsonContent.getJSONObject(FLOW_CONTENT_KEY_SEQUENCES);

                if (DataUtil.isNotNull(jsonNodes) && !jsonNodes.isEmpty()) {
                    for (int idx = 0; idx < jsonNodes.size(); idx++) {
                        JSONObject jsonNode = jsonNodes.getJSONObject(idx);
                        JSONArray jsonParameters = jsonNode.getJSONArray(FLOW_CONTENT_KEY_NODE_PARAMETERS);
                        JSONArray jsonOptimizeParameters = jsonNode.getJSONArray(FLOW_CONTENT_KEY_NODE_OPTIMIZE_PARAMETERS);
                        JSONArray jsonInputPorts = jsonNode.getJSONArray(FLOW_CONTENT_KEY_NODE_INPUT_PORTS);
                        JSONArray jsonOutputPorts = jsonNode.getJSONArray(FLOW_CONTENT_KEY_NODE_OUTPUT_PORTS);
                        JSONArray jsonOutputs = jsonNode.getJSONArray(FLOW_CONTENT_KEY_NODE_OUTPUTS);
                        JSONArray jsonSchemas = jsonNode.getJSONArray(FLOW_CONTENT_KEY_NODE_OUTPUT_PORT_SCHEMAS);
                        jsonNode.remove(FLOW_CONTENT_KEY_NODE_PARAMETERS);
                        jsonNode.remove(FLOW_CONTENT_KEY_NODE_OPTIMIZE_PARAMETERS);
                        jsonNode.remove(FLOW_CONTENT_KEY_NODE_INPUT_PORTS);
                        jsonNode.remove(FLOW_CONTENT_KEY_NODE_OUTPUT_PORTS);
                        jsonNode.remove(FLOW_CONTENT_KEY_NODE_OUTPUTS);
                        jsonNode.remove(FLOW_CONTENT_KEY_NODE_OUTPUT_PORT_SCHEMAS);

                        WfFlowNode wfFlowNode = jsonNode.toJavaObject(WfFlowNode.class);
                        Node node = new Node(workflowContext, wfFlowNode, ModuleConfigHelper.getModule(wfFlowNode.getRefModuleId()));
                        if (DataUtil.isNotNull(jsonParameters) && !jsonParameters.isEmpty()) {
                            for (int i = 0; i < jsonParameters.size(); i++) {
                                WfFlowNodeParameter wfFlowNodeParameter = jsonParameters.getJSONObject(i).toJavaObject(WfFlowNodeParameter.class);
                                XCharValue value = new XCharValue(ComponentConfigHelper.getCharacteristic(wfFlowNodeParameter.getCharId()), wfFlowNodeParameter.getCharValue());
                                if (value.isEntityDataType()) {
                                    value.parseEntityJSON(jsonParameters.getJSONObject(i).getJSONObject(FLOW_CONTENT_KEY_NODE_PARAMETER_ENTITY_OBJECT));
                                }
                                NodeParameter parameter = new NodeParameter(wfFlowNodeParameter, value);
                                node.putParameter(parameter);
                            }
                        }

                        if (node.getComponent().haveParameterContent()) {
                            for (CmptChar cmptChar : node.getComponent().getParameter().getCmptChars()) {
                                if (!node.containParameter(cmptChar)) {
                                    node.putParameter(ParameterHelper.simulateParameter(node, cmptChar, node.getComponent().getConfigCharValue(cmptChar)));
                                }
                            }
                        }

                        if (DataUtil.isNotNull(jsonOptimizeParameters) && !jsonOptimizeParameters.isEmpty()) {
                            for (int i = 0; i < jsonOptimizeParameters.size(); i++) {
                                WfFlowNodeParameter wfFlowNodeParameter = jsonOptimizeParameters.getJSONObject(i).toJavaObject(WfFlowNodeParameter.class);
                                XCharValue value = new XCharValue(ComponentConfigHelper.getCharacteristic(wfFlowNodeParameter.getCharId()), wfFlowNodeParameter.getCharValue());
                                if (value.isEntityDataType()) {
                                    value.parseEntityJSON(jsonOptimizeParameters.getJSONObject(i).getJSONObject(FLOW_CONTENT_KEY_NODE_PARAMETER_ENTITY_OBJECT));
                                }
                                NodeParameter parameter = new NodeParameter(wfFlowNodeParameter, value);
                                node.putOptimizeParameter(parameter);
                            }
                        }

                        if (node.getComponent().haveOptimizeExecutionContent()) {
                            for (CmptChar cmptChar : node.getComponent().getOptimizeExecution().getCmptChars()) {
                                if (!node.containOptimizeParameter(cmptChar)) {
                                    node.putOptimizeParameter(ParameterHelper.simulateParameter(node, cmptChar, node.getComponent().getConfigCharValue(cmptChar)));
                                }
                            }
                        }

                        if (DataUtil.isNotNull(jsonInputPorts) && !jsonInputPorts.isEmpty()) {
                            for (int i = 0; i < jsonInputPorts.size(); i++) {
                                WfFlowNodePort wfFlowNodePort = jsonInputPorts.getJSONObject(i).toJavaObject(WfFlowNodePort.class);
                                NodeInputPort inputPort = new NodeInputPort(wfFlowNodePort, ModuleConfigHelper.getModulePort(wfFlowNodePort.getRefPortId()));
                                node.putInputNodePort(inputPort);
                            }
                        }
                        if (DataUtil.isNotNull(jsonOutputPorts) && !jsonOutputPorts.isEmpty()) {
                            for (int i = 0; i < jsonOutputPorts.size(); i++) {
                                WfFlowNodePort wfFlowNodePort = jsonOutputPorts.getJSONObject(i).toJavaObject(WfFlowNodePort.class);
                                NodeOutputPort outputPort = new NodeOutputPort(wfFlowNodePort, ModuleConfigHelper.getModulePort(wfFlowNodePort.getRefPortId()));
                                if (DataUtil.isNotNull(jsonSchemas) && !jsonSchemas.isEmpty()) {
                                    WfFlowNodeSchema wfFlowNodeSchema = jsonSchemas.getJSONObject(i).toJavaObject(WfFlowNodeSchema.class);
                                    NodeSchema nodeSchema = new NodeSchema(wfFlowNodeSchema, ComponentConfigHelper.getCharacteristic(wfFlowNodePort.getRefCharId()));
                                    outputPort.setSchema(nodeSchema);
                                }
                                node.putOutputNodePort(outputPort);
                            }
                        }

                        if (DataUtil.isNotNull(jsonOutputs) && !jsonOutputs.isEmpty()) {
                            for (int i = 0; i < jsonOutputs.size(); i++) {
                                WfFlowNodeOutput wfFlowNodeOutput = jsonOutputs.getJSONObject(i).toJavaObject(WfFlowNodeOutput.class);
                                XCharValue value = new XCharValue(ComponentConfigHelper.getCharacteristic(wfFlowNodeOutput.getCharId()), wfFlowNodeOutput.getCharValue());
                                NodeOutput nodeOutput = new NodeOutput(wfFlowNodeOutput, value);
                                node.putOutput(nodeOutput);
                            }
                        }
                        this.putNode(node);
                    }
                    if (DataUtil.isNotNull(jsonLinks) && !jsonLinks.isEmpty()) {
                        for (int i = 0; i < jsonLinks.size(); i++) {
                            WfFlowNodeLink wfFlowNodeLink = jsonLinks.getJSONObject(i).toJavaObject(WfFlowNodeLink.class);
                            NodeLink link = new NodeLink(wfFlowNodeLink);
                            this.putLink(link);
                        }
                    }

                    if (DataUtil.isNotNull(jsonNodeDeleteQueues) && !jsonNodeDeleteQueues.isEmpty()) {
                        for (int i = 0; i < jsonNodeDeleteQueues.size(); i++) {
                            WfFlowNodeDeleteQueue wfFlowNodeDeleteQueue = jsonNodeDeleteQueues.getJSONObject(i).toJavaObject(WfFlowNodeDeleteQueue.class);
                            NodeDeleteQueue nodeDeleteQueue = new NodeDeleteQueue(wfFlowNodeDeleteQueue);
                            this.putNodeDeleteQueue(nodeDeleteQueue);
                        }
                    }
                }

                if (DataUtil.isNotNull(jsonFlowSequence)) {
                    flowSequence = jsonFlowSequence.toJavaObject(FlowSequence.class);
                }

                jsonContent.clear();
            }
        } catch (Throwable e) {
            throw new LambdaException(LambdaExceptionEnum.F_WORKFLOW_DEFAULT_ERROR,
                    "Parse workflow content failed -- content error.", "工作流内容错误", e, workflow.data());
        }
    }


    public Workflow getWorkflow() {
        return workflow;
    }

    public int nodeCount() {
        return nodes.size();
    }

    public List<Node> getNodes() {
        return CollectionUtil.toList(nodes);
    }

    public Node getNode(Long nodeId) {
        return CollectionUtil.get(nodes, nodeId);
    }

    public void putNode(Node node) {
        CollectionUtil.put(nodes, node.data().getNodeId(), node);
    }

    public int linkCount() {
        return links.size();
    }

    public List<NodeLink> getLinks() {
        return CollectionUtil.toList(links);
    }

    public NodeLink getLink(Long linkId) {
        return CollectionUtil.get(links, linkId);
    }

    public void putLink(NodeLink link) {
        CollectionUtil.put(links, link.data().getLinkId(), link);
    }

    public void putNodeDeleteQueue(NodeDeleteQueue nodeDeleteQueue) {
        if (CollectionUtil.containsKey(nodeDeleteQueues, nodeDeleteQueue.data().getNodeId()))
            return;
        CollectionUtil.put(nodeDeleteQueues, nodeDeleteQueue.data().getNodeId(), nodeDeleteQueue);
    }

    public List<NodeDeleteQueue> getNodeDeleteQueues() {
        return CollectionUtil.toList(nodeDeleteQueues);
    }

    public int deleteQueueCount() {
        return nodeDeleteQueues.size();
    }
}
*/
