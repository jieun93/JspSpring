package kr.or.ddit.buyer.vo;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
@Data
public class BuyerVO {
	
	public BuyerVO() {
		super();
	}
	
	@NotBlank(message = "아이디누락")
	private String buyer_id;
	@NotBlank
	private String buyer_name;
	@NotBlank
	private String buyer_lgu;
	private String buyer_bank;
	private String buyer_bankno;
	private String buyer_bankname;
	private String buyer_zip;
	private String buyer_add1;
	private String buyer_add2;
	@NotBlank
	private String buyer_comtel;
	@NotBlank
	private String buyer_fax;
	@NotBlank
	@Email
	@Length(min=3, max=90)
	private String buyer_mail;
	private String buyer_charger;
	private String buyer_telext;
	
	private List<ProdVO> prodList; // has many 관계
	
	
	
	
	
}                          
