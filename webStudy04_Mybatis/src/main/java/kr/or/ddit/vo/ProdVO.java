package kr.or.ddit.vo;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.filter.wrapper.PartWrapper;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.stereotype.MimeChecker;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude= {"prod_detail", "prod_img"})
@EqualsAndHashCode(of= {"prod_id"})
public class ProdVO {
	private int rnum;
	@NotBlank(groups = UpdateGroup.class)
	private String prod_id;
	@NotBlank
	private String prod_name;
	@NotBlank
	private String prod_lgu;
	@NotBlank
	private String prod_buyer;
	@NotNull
	@Min(0)
	private Integer prod_cost;
	@NotNull
	@Min(0)
	private Integer prod_price;
	@NotNull
	@Min(0)
	private Integer prod_sale;
	@NotBlank
	private String prod_outline;
	private String prod_detail;
	
	private String prod_img;
	@NotNull
	@Min(0)
	private Integer prod_totalstock;
	private String prod_insdate;
	@NotNull
	@Min(0)
	private Integer prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	@Min(0)
	private Integer prod_qtyin;
	@Min(0)
	private Integer prod_qtysale;
	@NotNull(groups = InsertGroup.class)
	private Integer prod_mileage;
	private String lprod_nm;
	
	private BuyerVO buyer;
	
	private List<MemberVO> memberList;
	
	@MimeChecker(contentType = "image/*")
	private PartWrapper prod_image;
	
	public void setProd_image(PartWrapper prod_image) {
		if(prod_image==null) return;
		String filename = prod_image.getOriginalFilename();
		if(filename==null||filename.isEmpty()) return;
		this.prod_image = prod_image;
	}
}










