package cn.bro.util;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

/**
 * @author 脱缰
 * @date 2020/6/17 5:11 下午
 */
public class HBaseUtil {
    private static HBaseConfiguration conf = null;

    static {
        conf = new HBaseConfiguration();
        //conf.set("hbase.master", "10.3.61.141:60000");
        //conf.set("hbase.zookeeper.quorum", "hadoop141,hadoop142,hadoop143,hadoop144");
        //conf.set("hbase.master.port", "60000");
        conf.addResource("hbase-site.xml");
    }

    // add
    public void createTable(String tableName, String[] cfs) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(conf);
        if (admin.tableExists(tableName)) {
            System.out.println("表已经存在了");
        } else {
            HTableDescriptor tableDesc = new HTableDescriptor(tableName);//表描述
            for (int i = 0; i < cfs.length; i++) {
                tableDesc.addFamily(new HColumnDescriptor(cfs[i]));//列族
            }
            admin.createTable(tableDesc);
            System.out.println("表创建成功！");
        }
    }

    // delete table
    public void deleteTable(String tableName) throws IOException {
        try {
            HBaseAdmin admin = new HBaseAdmin(conf);
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            System.out.println("删除表成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeRow(String name, String author, String catagory) {
        try {
            HTable table = new HTable(conf, "own_logfile");
            Put put = new Put(name.getBytes());
            put.add("info".getBytes(),
                    "author".getBytes(), author.getBytes());
            table.put(put);
            put.add("info".getBytes(),
                    "catagory".getBytes(), catagory.getBytes());
            table.put(put);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // delete value
    public void deleteRow(String tableName, String rowKey) throws IOException {
        HTable table = new HTable(conf, tableName);
        java.util.List<Delete> list = new ArrayList<Delete>();
        Delete dl = new Delete(rowKey.getBytes());
        list.add(dl);
        table.delete(list);
        System.out.println("删除成功！");
    }

    public void selectRow(String tableName, String rowKey) throws IOException {
        HTable table = new HTable(conf, tableName);
        Get g = new Get(rowKey.getBytes());
        Result rs = table.get(g);
        for (KeyValue kv : rs.raw()) {
            System.out.println(new String(kv.getRow()) + " ");
            System.out.println(new String(kv.getFamily()) + ":");
            System.out.println(new String(kv.getQualifier()) + " ");
            System.out.println(kv.getTimestamp() + " ");
            System.out.println(new String(kv.getValue()) + " ");
        }
    }

    public void scaner(String tableName) {
        try {
            HTable table = new HTable(conf, tableName);
            Scan scan = new Scan();
            ResultScanner rs = table.getScanner(scan);

            for (Result r : rs) {
                KeyValue[] kv = r.raw();
                for (int i = 0; i < kv.length; i++) {
                    System.out.print(new String(kv[i].getRow()) + " ");
                    System.out.print(new String(kv[i].getFamily()) + ": ");
                    System.out.print(new String(kv[i].getQualifier()) + " ");
                    System.out.print(kv[i].getTimestamp() + " ");
                    System.out.println(new String(kv[i].getValue()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


