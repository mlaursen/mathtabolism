package com.mathtabolism.util.hibernate;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.jboss.logging.Logger;

public class PrimaryKeyGenerator implements IdentifierGenerator {
  private static Logger logger = Logger.getLogger(PrimaryKeyGenerator.class);
  private static final String PREFIX = "MATH";
  private static final String SP_GENERATE_KEY = "{call sp_generate_key(?, ?)}";
  private static final String POSTGRES_GEN_KEY = "SELECT sp_generate_key(?)";
  private static final String DEBUG_MSG = "Primary Key Generated: '%s' for %s";
  private static final String GEN_ERR = "Unable to generate a primary key for %s";
  private static final Character PADDING = '0';
  private static final int PADDING_AMT = 6;
  
  @Override
  public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
    Connection conn = session.connection();
    CallableStatement cs = null;
    PreparedStatement ps = null;
    ResultSet         rs = null;
    Class<?> clazz = object.getClass();
    logger.debug("Object: " + object);
    try {
      String seqName = com.mathtabolism.util.string.StringUtils.toDatabaseFormat(clazz.getSimpleName());
      ps = conn.prepareStatement(POSTGRES_GEN_KEY);
      ps.setString(1, seqName);
      rs = ps.executeQuery();
      rs.next();
      String code = PREFIX + StringUtils.leftPad(rs.getString(1), PADDING_AMT, PADDING);
      
//      cs = conn.prepareCall(SP_GENERATE_KEY);
//      cs.setString(1, seqName);
//      cs.registerOutParameter(2, Types.NUMERIC);
//      cs.execute();
//      String code = PREFIX + StringUtils.leftPad(cs.getString(2), PADDING_AMT, PADDING);
      logger.debug(String.format(DEBUG_MSG, code, clazz));
      return code;
    }
    catch (SQLException e) {
      logger.error(e);
      throw new HibernateException(String.format(GEN_ERR, clazz));
    }
    finally {
      if(cs != null) {
        try {
          cs.close();
        }
        catch (SQLException e) {
          logger.error(e);
        }
      }
      
      if(rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          logger.error(e);
        }
      }
      
      if(ps != null) {
        try {
          ps.close();
        } catch(SQLException e) {
          logger.error(e);
        }
      }
    }
  }
  
}
