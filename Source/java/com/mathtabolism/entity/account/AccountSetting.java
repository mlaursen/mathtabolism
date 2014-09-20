/**
 * 
 */
package com.mathtabolism.entity.account;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.ActivityMultiplier;
import com.mathtabolism.constants.TDEEFormula;
import com.mathtabolism.constants.Weekday;
import com.mathtabolism.entity.BaseEntity;
import com.mathtabolism.entity.BasePK;

/**
 * 
 * @author laursenm
 */
@Entity
@NamedQueries(@NamedQuery(name = AccountSetting.Q_findCurrentAccountSetting, query = "SELECT as1 FROM AccountSetting as1 WHERE as1.pk.account.id = :account_id "
		+ "AND as1.pk.dateChanged = (SELECT max(as2.pk.dateChanged) FROM AccountSetting as2 WHERE as2.pk.account.id = :account_id)"))
public class AccountSetting extends BaseEntity {
	public static final String Q_findCurrentAccountSetting = "AccountSetting.findCurrentAccountSetting";
	
	@EmbeddedId
	private PK pk;
	
	@Enumerated(EnumType.STRING)
	private Weekday recalculationDay;
	
	@Enumerated(EnumType.STRING)
	private ActivityMultiplier activityMultiplier;
	
	@Enumerated(EnumType.STRING)
	private TDEEFormula tdeeFormula;
	
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
	public Account getAccount() {
		return pk.account;
	}
	
	/**
	 * 
	 * @return
	 */
	public Weekday getRecalculationDay() {
		return recalculationDay;
	}
	
	/**
	 * 
	 * @param recalculationDay
	 */
	public void setRecalculationDay(Weekday recalculationDay) {
		this.recalculationDay = recalculationDay;
	}
	
	/**
	 * 
	 * @return
	 */
	public ActivityMultiplier getActivityMultiplier() {
		return activityMultiplier;
	}
	
	/**
	 * 
	 * @param activityMultiplier
	 */
	public void setActivityMultiplier(ActivityMultiplier activityMultiplier) {
		this.activityMultiplier = activityMultiplier;
	}
	
	/**
	 * 
	 * @return
	 */
	public TDEEFormula getTdeeFormula() {
		return tdeeFormula;
	}
	
	/**
	 * 
	 * @param tdeeFormula
	 */
	public void setTdeeFormula(TDEEFormula tdeeFormula) {
		this.tdeeFormula = tdeeFormula;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getDateChanged() {
		return pk.dateChanged;
	}
	
	/**
	 * 
	 * @param dateChanged
	 */
	public void setDateChanged(Date dateChanged) {
		this.pk.dateChanged = dateChanged;
	}
	
	/**
	 * @return
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("accountId", pk.account.getId())
				.append("recalculationDay", recalculationDay).append("activityMultiplier", activityMultiplier)
				.append("tdeeFormula", tdeeFormula).append("dateChanged", pk.dateChanged).toString();
	}
	
	public class PK extends BasePK {
		private static final long serialVersionUID = 1L;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "account_id")
		private Account account;
		
		@Temporal(TemporalType.DATE)
		private Date dateChanged;
		
		public PK(Account account, Date dateChanged) {
			this.account = account;
			this.dateChanged = dateChanged;
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
		public Date getDateChanged() {
			return dateChanged;
		}
		
		/**
		 * 
		 * @param dateChanged
		 */
		public void setDateChanged(Date dateChanged) {
			this.dateChanged = dateChanged;
		}
		
		/**
		 * @param object
		 * @return
		 */
		@Override
		public boolean equals(Object object) {
			if(object instanceof PK) {
				PK pk = (PK) object;
				return account.getId().equals(pk.account.getId()) && dateChanged.equals(pk.dateChanged);
			}
			return false;
		}
		
		/**
		 * @return
		 */
		@Override
		public int hashCode() {
			return account.hashCode() + dateChanged.hashCode();
		}
		
	}
}
