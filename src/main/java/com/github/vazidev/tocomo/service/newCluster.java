package com.github.vazidev.tocomo.service;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder;
import com.datastax.oss.driver.api.core.type.DataTypes;
//import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;
//import com.datastax.oss.driver.api.querybuilder.schema.CreateTableWithOptions;

import java.net.InetSocketAddress;

//import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.createTable;
 //import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.leveledCompactionStrategy;
//import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.*;


public class newCluster {




   /** public static void main(String args[]) {

        CqlSession session;
        session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .withKeyspace("tocomo")
                .build();


        CreateTableWithOptions create = createTable("tocomo", "customers")
                    .withPartitionKey("user_id", DataTypes.UUID)
                    .withPartitionKey("user_name", DataTypes.TEXT)
                    .withClusteringColumn("name", DataTypes.INT)
                    .withColumn("amount", DataTypes.DOUBLE)
                    .withColumn("status", DataTypes.BOOLEAN)
                    .withCompaction(leveledCompactionStrategy())
                    .withSnappyCompression()
                    .withClusteringOrder("status", ClusteringOrder.DESC);


    }**/
}
        

