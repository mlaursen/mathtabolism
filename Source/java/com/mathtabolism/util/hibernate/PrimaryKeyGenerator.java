package com.mathtabolism.util.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.jboss.logging.Logger;

import org.apache.commons.lang3.StringUtils;

public class PrimaryKeyGenerator implements IdentifierGenerator {

	private static Logger logger = Logger.getLogger(PrimaryKeyGenerator.class);
	private static final String PREFIX = "MATH";
	
	@Override
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		Connection conn = session.connection();
		Class<?> clazz = object.getClass();
		logger.debug("Object: " + object);
		try {
			String seqName = com.mathtabolism.util.string.StringUtils.toDatabaseFormat(clazz.getSimpleName());
			PreparedStatement ps = conn.prepareStatement("SELECT nextVal('seq_" + seqName + "') nextval");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String code = PREFIX + StringUtils.leftPad(rs.getString("nextval"), 6, '0');
				logger.debug("Primary Key Generated: '" + code + "' for " + clazz);
				return code;
			}
		} catch(SQLException e) {
			logger.error(e);
			throw new HibernateException("Unable to generate a primary key for + " + clazz);
		}
		return null;
	}

}
