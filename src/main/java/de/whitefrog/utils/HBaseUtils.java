package de.whitefrog.utils;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;

import java.io.IOException;

public class HBaseUtils {
  private HBaseUtils() {}

  public static void createTable(HConnection connection, String tableName, String... columnFamilies) throws IOException {
    HBaseAdmin hbase = new HBaseAdmin(connection);
    HTableDescriptor descriptor = new HTableDescriptor(tableName);
    for(String family: columnFamilies) {
      HColumnDescriptor columnDescriptor = new HColumnDescriptor(family);
      descriptor.addFamily(columnDescriptor);
    }

    hbase.createTable(descriptor);
  }
  public static void renameTable(HConnection connection, String from, String to) throws IOException, InterruptedException {
    HBaseAdmin hbase = new HBaseAdmin(connection);
    String snapshotName = from + "_tmp_rename";
    hbase.disableTable(from);
    hbase.snapshot(snapshotName, from);
    hbase.cloneSnapshot(snapshotName, to);
    hbase.deleteSnapshot(snapshotName);
    hbase.deleteTable(from);
  }
}
