package com.github.vazidev.tocomo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCassandraRepositories
public class CassConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName(){
        return "tocomo";
    }

    @Override
    protected String getContactPoints(){
        return "localhost";
    }

    @Override
    public SchemaAction getSchemaAction(){
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace("tocomo")
                .ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true);
        return Arrays.asList(specification);
    }
}

