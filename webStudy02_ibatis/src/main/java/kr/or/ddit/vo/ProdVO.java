package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Setter
//@Getter
//@NoArgsConstructor // 기본생성자
//@AllArgsConstructor // 모든것의 생성자 
@Data // 모든것을 사용가능한것
@ToString(exclude= {"prod_img","prod_detail"}) // 빼고 싶은게 있을떄
@EqualsAndHashCode(of= {"prod_id"}) // id만 같으면 같은객체로 판단
public class ProdVO {
	
	private String prod_id;
	private String prod_name;
	private String prod_lgu;
	private String prod_buyer;
	private Integer prod_cost;
	private Integer prod_price;
	private Integer prod_sale;
	private String prod_outline;
	private String prod_detail;
	private String prod_img;
	private Integer prod_totalstock;
	private String prod_insdate;
	private Integer prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	private Integer prod_qtyin;
	private Integer prod_qtysale;
	private Integer prod_mileage;
	
	
	private BuyerVO buyer;
}
