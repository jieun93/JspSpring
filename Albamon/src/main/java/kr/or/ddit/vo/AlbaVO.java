package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
@Data
public class AlbaVO implements Serializable {
	private String al_id;
	private String al_name;
	private Integer al_age;
	private String al_address;
	private String al_hp;
	private String al_spec;
	private String al_desc;
	private String gr_code;
	private String al_career;
	private String al_gen;
	private String al_btype;
	private String al_mail;

}
