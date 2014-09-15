/**
 * 
 */
package com.github.mlaursen.mathtabolism.entity.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.mlaursen.mathtabolism.constants.ActivityMultiplier;
import com.github.mlaursen.mathtabolism.constants.TDEEFormula;
import com.github.mlaursen.mathtabolism.constants.Weekday;
import com.github.mlaursen.mathtabolism.entity.BaseEntity;

/**
 * 
 * @author laursenm
 */
@Entity
@NamedQueries(
		@NamedQuery(name=AccountSetting.Q_findCurrentAccountSetting, query="SELECT as1 FROM AccountSetting as1 WHERE as1.account.id = :account_id "
				+ "AND as1.dateChanged = (SELECT max(as2.dateChanged) FROM AccountSetting as2 WHERE as2.account.id = :account_id)")
)
public class AccountSetting extends BaseEntity {
	public static final String Q_findCurrentAccountSetting = "AccountSetting.findCurrentAccountSetting";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_setting_id_gen")
	@SequenceGenerator(name = "account_setting_id_gen", sequenceName = "ACCOUNT_SETTING_ID_SEQ", allocationSize = 1)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account account;
	
	@Enumerated(EnumType.STRING)
	private Weekday recalculationDay;
	
	@Enumerated(EnumType.STRING)
	private ActivityMultiplier activityMultiplier;
	
	@Enumerated(EnumType.STRING)
	private TDEEFormula tdeeFormula;
	
	@Temporal(TemporalType.DATE)
	private Date dateChanged;

	/**
	 * 
	 * @return 
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
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
	public Account getAccount() {
		return account;
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
	 * @return
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("recalculationDay", recalculationDay)
			.append("activityMultiplier", activityMultiplier)
			.append("tdeeFormula", tdeeFormula)
			.append("dateChanged", dateChanged)
			.toString();
	}
	
	
}
