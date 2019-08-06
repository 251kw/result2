package com.shantery.result2;

import java.io.Serializable;
import java.text.ParseException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="result2disp")
public class Result2 implements Serializable {
	@Id
	@Column(name="result_id")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="seqTable")
	private Long id;
	private String date;
	private String nationality;
	private String employment;
	private String commercial_distribution;
	private String cost;
	private String age;
	private String closest_station;
	private String subject;
	private String text;
	private String sender;
	private String temp1;
	private String temp2;
	private String temp3;
	private String temp4;
	private String temp5;
	private String temp6;
	private String temp7;
	private String temp8;
	private String temp9;

	public Result2(long long1, String string, String string2, String string3, String string4, String string5,
			String string6, String string7, String string8, String string9, String string10) throws ParseException {
		// TODO 自動生成されたコンストラクター・スタブ
		id = long1;/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm;ss");
		Date formatDate = sdf.parse(string);*/
		date = string;
		nationality = string2;
		employment = string3;
		commercial_distribution = string4;
		cost = string5;
		age = string6;
		closest_station = string7;
		subject = string8;
		text = string9;
		sender = string10;

	}
	public Result2() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() throws ParseException {
		/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date dt = dateFormat.parse(date);
		String date2 = sdf.format(dt);
		return date2;*/	// フォーマット用
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
		return closest_station;
	}
	public void setClosest_station(String closest_station) {
		this.closest_station = closest_station;
	}
	public String getSubject() {
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
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
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

}
