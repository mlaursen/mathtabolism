/**
 * 
 */
package com.github.mlaursen.mathtabolism.entity.account;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.github.mlaursen.mathtabolism.entity.BaseEntity;
import com.github.mlaursen.mathtabolism.entity.BasePK;
import com.github.mlaursen.mathtabolism.util.date.DateUtils;

/**
 * 
 * @author laursenm
 */
@Entity
@NamedQueries(@NamedQuery(name=AccountWeight.Q_findCurrentAccountWeight, query="SELECT aw from AccountWeight aw WHERE"
		+ " aw.pk.account.id = :account_id AND aw.pk.weighInDate = (SELECT max(aw2.pk.weighInDate) FROM AccountWeight aw2 WHERE aw2.pk.account.id = :account_id)"))
public class AccountWeight extends BaseEntity {
	public static final String Q_findCurrentAccountWeight = "AccountWeight.findCurrentAccountWeight";
	
	@EmbeddedId
	private PK pk;
	
	private double weight;
	
	
	

	/**
	 * 
	 * @return 
	 */
	public Account getAccount() {
		return pk.account;
	}

	/**
	 * 
	 * @param account 
	 */
	public void setAccount(Account account) {
		this.pk.account = account;
	}

	/**
	 * 
	 * @return 
	 */
	public Date getWeighInDate() {
		return pk.weighInDate;
	}

	/**
	 * 
	 * @param weighInDate 
	 */
	public void setWeighInDate(Date weighInDate) {
		this.pk.weighInDate = weighInDate;
	}

	/**
	 * 
	 * @return 
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * 
	 * @param weight 
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("accountId", pk.account.getId())
			.append("weight", weight)
			.append("weighInDate", pk.weighInDate)
			.toString();
	}
	
	public static class PK extends BasePK {
		private static final long serialVersionUID = 1L;

		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="account_id")
		private Account account;
		
		@Temporal(TemporalType.DATE)
		private Date weighInDate;
		public PK(Account account, Date weightInDate) {
			this.account = account;
			this.weighInDate = weightInDate;
		}
		
		/**
		 * 
		 * @return 
		 */
		public Account getAccount() {
			return account;
		}
		
		/**
		 * 
		 * @param account 
		 */
		public void setAccount(Account account) {
			this.account = account;
		}
		
		/**
		 * 
		 * @return 
		 */
		public Date getWeighInDate() {
			return weighInDate;
		}
		
		/**
		 * 
		 * @param weighInDate 
		 */
		public void setWeighInDate(Date weighInDate) {
			this.weighInDate = weighInDate;
		}

		/**
		 * Checks if the Primary Keys have the same account id and the same
		 * weigh in date (ignoring anything less than days)
		 * @param object the object to compare to
		 * @return true if equals
		 */
		@Override
		public boolean equals(Object object) {
			if(object instanceof PK) {
				PK pk = (PK) object;
				return account.getId().equals(pk.account.getId())
						&& DateUtils.isSameDate(weighInDate, pk.weighInDate);
			}
			return false;
		}

		/**
		 * @return
		 */
		@Override
		public int hashCode() {
			return account.hashCode() + weighInDate.hashCode();
		}
	}
}
