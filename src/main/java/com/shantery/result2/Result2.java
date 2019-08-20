package com.shantery.result2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * result2dispデータベースのエンティティクラス
 */
@Entity
@Table(name = "result2disp")
public class Result2 implements Serializable {

	/** ID **/
	@Id
	@Column(name = "result_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
	private Long id;
	/** 日時 **/
	private String date;
	/** 国 **/
	private String nationality;
	/** 雇 **/
	private String employment;
	/** 商 **/
	private String commercial_distribution;
	/** 単金 **/
	private String cost;
	/** 年齢 **/
	private String age;
	/** 最寄駅 **/
	private String closest_station;
	/** 件名 **/
	private String subject;
	/** L/S **/
	private String text;
	/** 送信者 **/
	private String sender;
	/** 人材・案件 **/
	private String human_resource_or_work;
	/** テンポラリー１ **/
	private String temp1;
	/** テンポラリー２ **/
	private String temp2;
	/** テンポラリー３ **/
	private String temp3;
	/** テンポラリー４ **/
	private String temp4;
	/** テンポラリー５ **/
	private String temp5;
	/** テンポラリー６ **/
	private String temp6;
	/** テンポラリー７ **/
	private String temp7;
	/** テンポラリー８ **/
	private String temp8;
	/** テンポラリー９ **/
	private String temp9;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEmployment() {
		return employment;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

	public String getCommercial_distribution() {
		return commercial_distribution;
	}

	public void setCommercial_distribution(String commercial_distribution) {
		this.commercial_distribution = commercial_distribution;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getClosest_station() {
		if(closest_station.length() > 5) {
			closest_station = closest_station.substring(0, 5) + "…";
		}
		return closest_station;
	}

	public void setClosest_station(String closest_station) {
		this.closest_station = closest_station;
	}

	public String getSubject() {
		if(subject.length() > 5) {
			subject = subject.substring(0, 5) + "…";
		}
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSender() {
		if(sender.length() > 5) {
			sender = sender.substring(0, 5) + "…";
		}
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getHuman_resource_or_work() {
		return human_resource_or_work;
	}

	public void setHuman_resource_or_work(String human_resource_or_work) {
		this.human_resource_or_work = human_resource_or_work;
	}

	public String getTemp1() {
		return temp1;
	}

	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}

	public String getTemp2() {
		return temp2;
	}

	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}

	public String getTemp3() {
		return temp3;
	}

	public void setTemp3(String temp3) {
		this.temp3 = temp3;
	}

	public String getTemp4() {
		return temp4;
	}

	public void setTemp4(String temp4) {
		this.temp4 = temp4;
	}

	public String getTemp5() {
		return temp5;
	}

	public void setTemp5(String temp5) {
		this.temp5 = temp5;
	}

	public String getTemp6() {
		return temp6;
	}

	public void setTemp6(String temp6) {
		this.temp6 = temp6;
	}

	public String getTemp7() {
		return temp7;
	}

	public void setTemp7(String temp7) {
		this.temp7 = temp7;
	}

	public String getTemp8() {
		return temp8;
	}

	public void setTemp8(String temp8) {
		this.temp8 = temp8;
	}

	public String getTemp9() {
		return temp9;
	}

	public void setTemp9(String temp9) {
		this.temp9 = temp9;
	}

	/**
	 * DBから取得したエンティティをList型で返却する
	 * @return エンティティリスト
	 */
	public List<String> getResult2List() {
		List<String> result2List = new ArrayList<>();
		result2List.add(getDate());
		result2List.add(getNationality());
		result2List.add(getEmployment());
		result2List.add(getCommercial_distribution());
		result2List.add(getCost());
		result2List.add(getAge());
		result2List.add(getClosest_station());
		result2List.add(getSubject());
		result2List.add(getSender());
		result2List.add(getHuman_resource_or_work());
		result2List.add(getTemp1());
		result2List.add(getTemp2());
		result2List.add(getTemp3());
		result2List.add(getTemp4());
		result2List.add(getTemp5());
		result2List.add(getTemp6());
		result2List.add(getTemp7());
		result2List.add(getTemp8());
		result2List.add(getTemp9());
		result2List.add(getText());
		return result2List;
	}
}
