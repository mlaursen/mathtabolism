/**
 * 
 */
package com.mathtabolism.entity.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.mathtabolism.entity.BaseGeneratedEntity;

/**
 * 
 * @author mlaursen
 */
@Entity
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames={"account_id", "intakeDate"})
})
@NamedQueries({
	@NamedQuery(name=DailyIntake.Q_findCurrentWeek, query="SELECT di FROM DailyIntake di "
			+ "WHERE di.account.id=:account_id AND di.intakeDate BETWEEN :start_date AND :end_date "
			+ "ORDER BY di.intakeDate ASC")
})
public class DailyIntake extends BaseGeneratedEntity {
	public static final String Q_findCurrentWeek = "DailyIntake.getCurrentWeek";
	public DailyIntake() {
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account account;
	
	@Temporal(TemporalType.DATE)
	private Date intakeDate;
	private Integer calorieChange;
	private Double fatMultiplier;
	private Double carbMultiplier;
	private Double proteinMultiplier;
	
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

}
