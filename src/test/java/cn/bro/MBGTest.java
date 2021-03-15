package cn.bro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring的单元测试
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MBGTest {


    /**
     * mybatis逆向工程
     *
     * @throws Exception
     */
    @Test
    public void mbg() throws Exception {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //获取类加载器
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        //类加载器在classpach：下获取配置文件
        InputStream is = classloader.getResourceAsStream("mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

    }

}
