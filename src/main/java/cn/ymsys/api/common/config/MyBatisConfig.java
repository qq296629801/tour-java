package cn.ymsys.api.common.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration("coreMybatisConfig")
@MapperScan(
        basePackages = {"cn.ymsys.api.orm.mapper", "cn.ymsys.api.orm.orm.extend"},
        sqlSessionFactoryRef = "coreSqlSessionFactory"
)
public class MyBatisConfig {

    @Bean("coreXaDataSource")
    @ConfigurationProperties(prefix = "tour.db-datasource")
    public DruidXADataSource xaDataSource() {
        return DataSourceBuilder.create().type(DruidXADataSource.class).build();
    }

    @Bean("coreDataSource")
    public DataSource dataSource(@Qualifier("coreXaDataSource") DruidXADataSource xaDataSource) throws SQLException {
        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setXaDataSource(xaDataSource);
        dataSource.setUniqueResourceName("coreDataSource");
        dataSource.setMinPoolSize(xaDataSource.getInitialSize());
        dataSource.setMaxPoolSize(xaDataSource.getMaxActive());
        dataSource.setLoginTimeout((int) xaDataSource.getMaxWait() / 1000);
        dataSource.setBorrowConnectionTimeout((int) xaDataSource.getMaxWait() / 1000);
        dataSource.setMaintenanceInterval((int) xaDataSource.getTimeBetweenEvictionRunsMillis() / 1000);
        dataSource.setMaxIdleTime((int) xaDataSource.getMaxEvictableIdleTimeMillis() / 1000);
        dataSource.setTestQuery(xaDataSource.getValidationQuery());
        return dataSource;
    }

    @Bean("coreJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("coreDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("coreSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("coreSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("coreSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("coreDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("./src/main/resources/*.xml"));
        bean.setPlugins(new Interceptor[]{getInterceptor()});
        return bean.getObject();
    }

    private PageInterceptor getInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("pageSizeZero", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

}
