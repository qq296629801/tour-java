package cn.ymsys.api.service;

import cn.ymsys.api.common.model.SysExecutionJob;
import cn.ymsys.api.common.model.SysExecutionQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class JobQueueService {
    private SysExecutionQueue queue = new SysExecutionQueue().dao();

    public List<SysExecutionQueue> getQueues() {
        return queue.find("select * from sys_execution_queue");
    }

    public boolean deleteById(int id) {
        return queue.deleteById(id);
    }

    public boolean save(SysExecutionQueue queue, SysExecutionJob job) {
        job.save();
        queue.setJobId(job.getId());
        queue.save();
        return true;
    }
}
