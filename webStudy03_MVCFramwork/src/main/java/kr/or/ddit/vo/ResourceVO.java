package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class ResourceVO implements Serializable{
	private String res_id;
	private String res_url;
	private String description;
	private Integer sort;
	
	private Set<String> roles;
}
