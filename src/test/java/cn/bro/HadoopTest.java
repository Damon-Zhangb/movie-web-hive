package cn.bro;

import cn.bro.util.HBaseUtil;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;


/**
 * @author 脱缰
 * @date 2020/6/11 11:33 下午
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HadoopTest {

    @Autowired
    private FileSystem fileSystem;

    @Autowired
    private HbaseTemplate template;

    @Autowired
    private HBaseUtil hBaseUtil;


    /**
     * 测试创建文件
     */
    @Test
    public void mkdir() throws IOException {
        boolean b = fileSystem.exists(new Path("/1111"));
        System.out.println(b);
        fileSystem.close();
    }

    @Test
    public void createTable() throws IOException {
        String[] cfs = {"grade", "course"};
        hBaseUtil.createTable("score", cfs);
    }

    @Test
    public void put() throws IOException {

        hBaseUtil.writeRow("hao", "zhang", "gaoxiao");
    }

    @Test
    public void scanner() {
        hBaseUtil.scaner("score");
    }

    @Test
    public void get() throws IOException {
        hBaseUtil.selectRow("score", "baoniu");
    }

    @Test
    public void delete() throws IOException {
        hBaseUtil.deleteTable("score");
    }


}
