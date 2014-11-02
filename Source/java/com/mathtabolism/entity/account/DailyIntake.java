/**
 * 
 */
package com.mathtabolism.entity.account;

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

import com.mathtabolism.entity.BaseEntity;
import com.mathtabolism.entity.BasePK;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries({
	@NamedQuery(name=DailyIntake.Q_findCurrentWeek, query="SELECT di FROM DailyIntake di "
			+ "WHERE di.pk.account.id=:account_id AND di.pk.intakeDate BETWEEN :start_date AND :end_date "
			+ "ORDER BY di.pk.intakeDate ASC")
})
public class DailyIntake extends BaseEntity {
	public static final String Q_findCurrentWeek = "DailyIntake.getCurrentWeek";
	public DailyIntake() {
	}
	
	public DailyIntake(Account account, Date intakeDate) {
		pk = new PK(account, intakeDate);
	}
	
	@EmbeddedId
	private PK pk;
	
	private Integer calorieChange;
	private Double fatMultiplier;
	private Double carbMultiplier;
	private Double proteinMultiplier;
	
	public Account getAccount() {
		return pk.account;
	}
	
	public void setAccount(Account account) {
		if(pk == null) {
			pk = new PK();
		}
		pk.setAccount(account);
	}
	
	public Date getIntakeDate() {
		return pk.intakeDate;
	}
	
	public void setIntakeDate(Date intakeDate) {
		if(pk == null) {
			pk = new PK();
		}
		pk.intakeDate = intakeDate;
	}
	
	/**
	 * 
	 * @return 
	 */
	public Integer getCalorieChange() {
		return calorieChange;
	}

	/**
	 * 
	 * @param calorieChange 
	 */
	public void setCalorieChange(Integer calorieChange) {
		this.calorieChange = calorieChange;
	}

	/**
	 * 
	 * @return 
	 */
	public Double getFatMultiplier() {
		return fatMultiplier;
	}

	/**
	 * 
	 * @param fatMultiplier 
	 */
	public void setFatMultiplier(Double fatMultiplier) {
		this.fatMultiplier = fatMultiplier;
	}

	/**
	 * 
	 * @return 
	 */
	public Double getCarbMultiplier() {
		return carbMultiplier;
	}

	/**
	 * 
	 * @param carbMultiplier the new Carbohydrate multiplier
	 */
	public void setCarbMultiplier(Double carbMultiplier) {
		this.carbMultiplier = carbMultiplier;
	}

	/**
	 * 
	 * @return 
	 */
	public Double getProteinMultiplier() {
		return proteinMultiplier;
	}

	/**
	 * 
	 * @param proteinMultiplier 
	 */
	public void setProteinMultiplier(Double proteinMultiplier) {
		this.proteinMultiplier = proteinMultiplier;
	}

	public static class PK extends BasePK {
		private static final long serialVersionUID = 1L;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="account_id")
		private Account account;
		
		@Temporal(TemporalType.DATE)
		private Date intakeDate;
		
		public PK() {
		}
		
		public PK(Account account, Date intakeDate) {
			this.account = account;
			this.intakeDate = intakeDate;
		}
		
		public Account getAccount() {
			return account;
		}
		
		public void setAccount(Account account) {
			this.account = account;
		}
		
		public Date getIntakeDate() {
			return intakeDate;
		}
		
		public void setIntakeDate(Date intakeDate) {
			this.intakeDate = intakeDate;
		}

		@Override
		public boolean equals(Object object) {
			if(object != null && object instanceof PK) {
				PK pk = (PK) object;
				return account.equals(pk.account)
						&& intakeDate.equals(pk.intakeDate);
			}
			return false;
		}

		@Override
		public int hashCode() {
			return account.hashCode() + intakeDate.hashCode();
		}
		
	}
}
