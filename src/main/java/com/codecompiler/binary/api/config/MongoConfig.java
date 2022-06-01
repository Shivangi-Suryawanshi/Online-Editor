package com.codecompiler.binary.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import com.mongodb.MongoClient;

public class MongoConfig<Mongo> extends AbstractMongoConfiguration {

	@Value("${spring.data.mongodb.host}")
	private String host;
	@Value("${spring.data.mongodb.database}")
	private String database;
	
	protected String getDatabaseName() {		
		return null;
	}
	
	@Override
	public MongoClient mongo() throws Exception {
		return new MongoClient(host);
	}


	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}
	

}