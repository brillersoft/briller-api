package com.hanogi.batch.utility;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UserIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection connection = session.connection();

		String prefix = "User";
		String generatedId = null;

		try {

			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(
					"select count(User_Id) as Id,Max(User_Id) as lastId from Batch_Process.login_details");

			if (rs.next()) {
				int id = rs.getInt("Id") + 101;

				String lastId = rs.getString("lastId");

				if (lastId == null) {
					generatedId = prefix + new Integer(id).toString();

					return generatedId;
				} else {
					String lastIdCount = lastId.substring(6);

					generatedId = prefix + (new Integer(lastIdCount) + 1);

					return generatedId;
				}

			}
		} catch (Exception e) {
		}

		return null;
	}
}
