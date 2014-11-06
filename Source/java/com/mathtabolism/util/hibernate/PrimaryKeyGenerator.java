package com.mathtabolism.util.hibernate;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

public class PrimaryKeyGenerator implements IdentifierGenerator {
  
  private static Logger logger = Logger.getLogger(PrimaryKeyGenerator.class);
  private static final String PREFIX = "MATH";
  
  @Override
  public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
    Connection conn = session.connection();
    CallableStatement cs = null;
    Class<?> clazz = object.getClass();
    logger.debug("Object: " + object);
    try {
      String seqName = com.mathtabolism.util.string.StringUtils.toDatabaseFormat(clazz.getSimpleName());
      cs = conn.prepareCall("{call sp_generate_key(?, ?)}");
      cs.setString(1, seqName);
      cs.registerOutParameter(2, Types.NUMERIC);
      cs.execute();
      String code = PREFIX + StringUtils.leftPad(cs.getString(2), 6, '0');
      logger.log(Level.INFO, "Primary Key Generated: '" + code + "' for " + clazz);
      return code;
    }
    catch (SQLException e) {
      logger.error(e);
      throw new HibernateException("Unable to generate a primary key for + " + clazz);
    }
    finally {
      if(cs != null) {
        try {
          cs.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
  
}
