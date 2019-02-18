package com.test.repository;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepo {
  @Autowired
  @Qualifier("secondary-template")
  private NamedParameterJdbcTemplate template;

  public void test(){
      System.out.println(template.getJdbcOperations());
  }
}
