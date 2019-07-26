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
	private String sub1;
	private String sub2;
	private String sub3;
	private String sub4;
	private String sub5;
	private String sub6;
	private String sub7;
	private String sub8;
	private String sub9;

	public Result2(long long1, String string, String string2, String string3, String string4, String string5,
			String string6, String string7, String string8, String string9, String string10/*, String string11,
			String string12, String string13, String string14, String string15, String string16,
			String string17, String string18, String string19*/) throws ParseException {
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
		/*setSub1(string11);
		setSub2(string12);
		setSub3(string13);
		setSub4(string14);
		setSub5(string15);
		setSub6(string16);
		setSub7(string17);
		setSub8(string18);
		setSub9(string19);
*/
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
	public String getSub1() {
		return sub1;
	}
	public void setSub1(String sub1) {
		this.sub1 = sub1;
	}
	public String getSub2() {
		return sub2;
	}
	public void setSub2(String sub2) {
		this.sub2 = sub2;
	}
	public String getSub3() {
		return sub3;
	}
	public void setSub3(String sub3) {
		this.sub3 = sub3;
	}
	public String getSub4() {
		return sub4;
	}
	public void setSub4(String sub4) {
		this.sub4 = sub4;
	}
	public String getSub5() {
		return sub5;
	}
	public void setSub5(String sub5) {
		this.sub5 = sub5;
	}
	public String getSub6() {
		return sub6;
	}
	public void setSub6(String sub6) {
		this.sub6 = sub6;
	}
	public String getSub7() {
		return sub7;
	}
	public void setSub7(String sub7) {
		this.sub7 = sub7;
	}
	public String getSub8() {
		return sub8;
	}
	public void setSub8(String sub8) {
		this.sub8 = sub8;
	}
	public String getSub9() {
		return sub9;
	}
	public void setSub9(String sub9) {
		this.sub9 = sub9;
	}


}
