package cn.ymsys.api.common.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;

@Configuration
@EnableTransactionManagement
public class TransactionMangerConfig {
    static {
        System.setProperty("com.atomikos.icatch.registered", "lambda");
        System.setProperty("com.atomikos.icatch.max_actives", "500");
    }

    @Bean(name = "globalUserTransaction")
    public UserTransaction globalUserTransaction() {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public UserTransactionManager atomikosTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean(name = "globalTransactionManager")
    @DependsOn({"globalUserTransaction", "atomikosTransactionManager"})
    public JtaTransactionManager globalTransactionManager() {
        JtaTransactionManager transactionManager = new JtaTransactionManager(globalUserTransaction(), atomikosTransactionManager());
        transactionManager.setDefaultTimeout(300);
        transactionManager.setAllowCustomIsolationLevels(true);
        return transactionManager;
    }
}
